package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.interfaces.BlockBedBehavior;
import org.allaymc.api.block.interfaces.BlockCopperDoorBehavior;
import org.allaymc.api.block.interfaces.BlockDoorBehavior;
import org.allaymc.api.block.interfaces.BlockTallGrassBehavior;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.*;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.player.Player;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.AttackSound;
import org.allaymc.server.item.impl.ItemStackImpl;
import org.allaymc.server.network.NetworkHelper;
import org.allaymc.server.network.processor.LatencyCriticalPacketProcessor;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.InventorySource;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.ItemUseTransaction;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.InventoryTransactionPacket;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.allaymc.api.item.type.ItemTypes.AIR;

/**
 * @author Cool_Loong
 */
@Slf4j
public class InventoryTransactionPacketProcessor extends PacketProcessor<InventoryTransactionPacket> implements LatencyCriticalPacketProcessor {
    public static final int ITEM_USE_CLICK_BLOCK = 0;
    public static final int ITEM_USE_CLICK_AIR = 1;
    public static final int ITEM_USE_BREAK_BLOCK = 2;

    public static final int ITEM_USE_ON_ENTITY_INTERACT = 0;
    public static final int ITEM_USE_ON_ENTITY_ATTACK = 1;

    public static final int ITEM_RELEASE_RELEASE = 0;
    public static final int ITEM_RELEASE_CONSUME = 1;

    // Mirrors PocketMine-MP's right-click spam filter: the 1.26.30+ client re-sends the same
    // interaction every simulation tick until it is resolved, so near-identical clicks within
    // this window are the client retransmitting one interaction, not fresh clicks.
    private static final long SPAM_CLICK_THRESHOLD_MS = 100;
    private static final double SPAM_CLICK_DISTANCE_SQUARED = 0.00001;
    // No per-client attack rate limit exists elsewhere; cap how often the world thread processes
    // damage, reach checks and sound broadcasts from one attacker.
    private static final long ATTACK_INTERVAL_MS = 250;

    private long lastClickTime;
    private Vector3ic lastBlockPos;
    private Vector3fc lastClickPos;
    private Vector3f lastPlayerPos;
    private int lastFace;
    private final Map<Long, Long> attackTimestamps = new HashMap<>();

    private boolean isSpamClick(Vector3ic blockPos, Vector3fc clickPos, Vector3f playerPos, int face) {
        var now = System.currentTimeMillis();
        var spam = now - this.lastClickTime < SPAM_CLICK_THRESHOLD_MS
                   && face == this.lastFace
                   && playerPos.distanceSquared(this.lastPlayerPos) < SPAM_CLICK_DISTANCE_SQUARED
                   && blockPos.equals(this.lastBlockPos)
                   && clickPos.distanceSquared(this.lastClickPos) < SPAM_CLICK_DISTANCE_SQUARED;
        this.lastClickTime = now;
        this.lastBlockPos = blockPos;
        this.lastClickPos = clickPos;
        this.lastPlayerPos = playerPos;
        this.lastFace = face;
        return spam;
    }

    @Override
    public void handleSync(Player player, InventoryTransactionPacket packet, long receiveTime) {
        var entity = player.getControlledEntity();

        var transactionType = packet.getTransactionType();
        var itemInHand = entity.getItemInHand();
        switch (transactionType) {
            case ITEM_USE -> {
                entity.setHandSlot(packet.getHotbarSlot(), false);
                itemInHand = entity.getItemInHand();

                var blockFace = BlockFace.fromIndex(packet.getBlockFace());
                var world = entity.getLocation().dimension();
                switch (packet.getActionType()) {
                    case ITEM_USE_CLICK_BLOCK -> {
                        // 1.26.30+: the client re-simulates this interaction every tick and tells us
                        // its prediction. A FAILURE here means the client simulation already
                        // diverged and rendered nothing for this interaction. PocketMine-MP keeps
                        // processing FAILURE interactions (b237cac) but PowerNukkitX drops them:
                        // committing a placement the client never rendered makes the block ghost
                        // in the client's world, so only SUCCESS predictions are committed here.
                        var predictionFailed = packet.getClientInteractPrediction() == ItemUseTransaction.PredictedResult.FAILURE;
                        if (predictionFailed) {
                            log.debug("Player {} reported a failed block-interaction prediction at {}; dropping the interaction",
                                    player.getOriginName(), NetworkHelper.fromNetwork(packet.getBlockPosition()));
                            break;
                        }

                        if (entity.isUsingItemInAir()) {
                            break;
                        }

                        var clickBlockPos = NetworkHelper.fromNetwork(packet.getBlockPosition());
                        var clickPos = NetworkHelper.fromNetwork(packet.getClickPosition());
                        // https://github.com/pmmp/PocketMine-MP/blob/835c383d4e126df6f38000e3217ad6a325b7a1f7/src/network/mcpe/handler/InGamePacketHandler.php#L475
                        if (isSpamClick(clickBlockPos, clickPos, packet.getPlayerPosition(), packet.getBlockFace())) {
                            break;
                        }

                        var dimension = entity.getDimension();
                        var clickedBlockStateReplaceable = dimension.getBlockState(clickBlockPos).getBlockType().hasBlockTag(BlockTags.REPLACEABLE);
                        var placeBlockPos = clickedBlockStateReplaceable ? clickBlockPos : Objects.requireNonNull(blockFace).offsetPos(clickBlockPos);

                        var interactedBlock = world.getBlockState(clickBlockPos);
                        var interactInfo = new PlayerInteractInfo(
                                entity, clickBlockPos,
                                clickPos, blockFace
                        );

                        if (!player.canInteractWithBlocks()) {
                            player.viewBlockUpdate(clickBlockPos, 0, dimension.getBlockState(clickBlockPos));
                            player.viewBlockUpdate(placeBlockPos, 0, dimension.getBlockState(placeBlockPos));
                            break;
                        }

                        var event = new PlayerInteractBlockEvent(entity, interactInfo, PlayerInteractBlockEvent.Action.RIGHT_CLICK);
                        if (!event.call()) {
                            player.viewBlockUpdate(clickBlockPos, 0, dimension.getBlockState(clickBlockPos));
                            player.viewBlockUpdate(placeBlockPos, 0, dimension.getBlockState(placeBlockPos));
                            break;
                        }

                        var sneaking = entity.isSneaking();
                        var useItemOnBlock = !sneaking;
                        var useBlock = !sneaking || itemInHand.getItemType() == AIR;

                        if (useItemOnBlock && itemInHand.useItemOnBlock(dimension, placeBlockPos, interactInfo)) {
                            // Using the item on the block successfully, no need to call BlockBehavior::onInteract()
                            break;
                        }

                        if (useBlock && interactedBlock.getBehavior().onInteract(itemInHand, dimension, interactInfo)) {
                            break;
                        }

                        // Block interaction was unsuccessful or skipped, override
                        // client block change by sending a block update
                        var blockStateClicked = dimension.getBlockState(clickBlockPos);
                        player.viewBlockUpdate(clickBlockPos, 0, blockStateClicked);

                        // Player places a block
                        if (itemInHand.getItemType() == AIR) {
                            break;
                        }

                        if (!player.canPlaceBlocks()) {
                            handleItemUseInAir(entity, itemInHand);
                            syncBlockPlacement(player, dimension, clickBlockPos, placeBlockPos, isMultiBlockItem(itemInHand));
                            break;
                        }

                        // Player places a block; judge the entity collisions at the position the client
                        // declared in this very click, the freshest authoritative statement of
                        // where its body is (see ItemStackImpl#placeBlock).
                        var declaredPlayerPos = packet.getPlayerPosition();
                        boolean placed = itemInHand instanceof ItemStackImpl itemStackImpl
                                ? itemStackImpl.placeBlock(dimension, placeBlockPos, interactInfo, new org.joml.Vector3f(declaredPlayerPos.getX(), declaredPlayerPos.getY(), declaredPlayerPos.getZ()))
                                : itemInHand.placeBlock(dimension, placeBlockPos, interactInfo);
                        if (!placed) {
                            if (dimension.getBlockState(placeBlockPos).getBlockType() == itemInHand.getItemType().getBlockType()) {
                                // The very block we are trying to place already exists there. This is a
                                // duplicated placement (1.26.30+ prediction retransmission); the
                                // authoritative state is re-sent below, which settles the client's
                                // simulation without un-placing anything.
                            } else {
                                handleItemUseInAir(entity, itemInHand);
                            }
                        }

                        // Confirm the placement against the client's prediction loop and re-sync the
                        // interaction target and its surroundings. Without this reply the client's
                        // simulation reverts the block and keeps re-sending the interaction, and the
                        // client may also have predicted changes for the adjacent blocks, which we
                        // then correct. FAILURE-predicted interactions never reach this point.
                        syncBlockPlacement(player, dimension, clickBlockPos, placeBlockPos, isMultiBlockItem(itemInHand));
                    }
                    case ITEM_USE_CLICK_AIR -> {
                        // If the player is interacting with a block, ignore click-air to prevent
                        // triggering item use (e.g., eating animation) during block interactions
                        if (entity.isUsingItemOnBlock()) {
                            break;
                        }

                        if (!entity.isUsingItemInAir()) {
                            handleItemUseInAir(entity, itemInHand);
                        } else {
                            entity.setUsingItemInAir(false);
                            var event = new PlayerUseItemInAirEvent(entity, entity.getItemUsingInAirTime());
                            if (event.call()) {
                                itemInHand.useItemInAir(entity, event.getUsingTime());
                            }
                        }
                    }
                }
            }
            case ITEM_RELEASE -> {
                switch (packet.getActionType()) {
                    case ITEM_RELEASE_RELEASE -> {
                        if (entity.isUsingItemInAir()) {
                            entity.setUsingItemInAir(false);
                            var event = new PlayerUseItemInAirEvent(entity, entity.getItemUsingInAirTime());
                            if (event.call()) {
                                itemInHand.useItemInAir(entity, event.getUsingTime());
                            }
                        }
                    }
                    case ITEM_RELEASE_CONSUME -> {
                        // TODO: It seems that this value is deprecated
                    }
                }
            }
            case ITEM_USE_ON_ENTITY -> {
                var target = entity.getDimension().getEntityManager().getEntity(packet.getRuntimeEntityId());
                // In some cases, for example when a falling block entity solidifies, latency may allow attacking an entity that
                // no longer exists server side. This is expected, so we shouldn't throw NullPointerException.
                if (target == null) {
                    log.debug("Player {} try to attack a entity which doesn't exist! Entity id: {}", player.getOriginName(), packet.getRuntimeEntityId());
                    return;
                }
                if (!entity.canReach(target.getLocation())) {
                    return;
                }

                entity.setHandSlot(packet.getHotbarSlot(), false);
                itemInHand = entity.getItemInHand();

                switch (packet.getActionType()) {
                    case ITEM_USE_ON_ENTITY_INTERACT -> {
                        var clickPos = NetworkHelper.fromNetwork(packet.getClickPosition());
                        var event = new PlayerInteractEntityEvent(entity, target, itemInHand, clickPos);
                        if (!event.call()) {
                            return;
                        }

                        if (!itemInHand.interactEntity(entity, target)) {
                            target.onInteract(entity, itemInHand);
                        }
                    }
                    case ITEM_USE_ON_ENTITY_ATTACK -> {
                        // Doesn't have damage component, can't attack
                        if (!(target instanceof EntityLivingComponent damageable)) {
                            return;
                        }
                        // Per-attacker rate limit: without this, a flooder can saturate the
                        // world thread with reach checks, damage evaluation and per-attack
                        // sound broadcasts to every viewer.
                        var now = System.currentTimeMillis();
                        if (now - this.attackTimestamps.getOrDefault(entity.getRuntimeId(), 0L) < ATTACK_INTERVAL_MS) {
                            log.debug("Player {} attack packet dropped (rate limited)", player.getOriginName());
                            return;
                        }
                        this.attackTimestamps.put(entity.getRuntimeId(), now);
                        if (target instanceof EntityPlayer) {
                            if (!player.canAttackPlayers()) {
                                return;
                            }
                        } else if (!player.canAttackMobs()) {
                            return;
                        }

                        var damage = itemInHand.calculateAttackDamage(entity, target);
                        if (damage == 0) {
                            damage = 1;
                        }

                        var damageContainer = DamageContainer.entityAttack(entity, damage);
                        var attackSuccess = damageable.attack(damageContainer);
                        entity.getDimension().addSound(target.getLocation(), new AttackSound(attackSuccess));
                        if (attackSuccess) {
                            itemInHand.onAttackEntity(entity, target);
                        }
                    }
                }
            }
            case NORMAL -> {
                // When the ItemStackRequest system is used, this transaction type is used for dropping items by pressing Q.
                // I don't know why they don't just use ItemStackRequest for that too, which already supports dropping items by
                // clicking them outside an open inventory menu, but for now it is what it is. Fortunately, this means we can
                // be much stricter about the validation criteria. For more details, see item_throwing.md
                if (packet.getActions().size() != 2) {
                    // Editing writable book will also send this packet with three actions, so just don't warn it
                    log.debug("Expected two actions for dropping an item, got {}", packet.getActions().size());
                    return;
                }

                // 1.26.30 reordered the action list: WORLD_INTERACTION now comes after CONTAINER.
                // Instead of assuming a fixed order with getFirst()/getLast(), we search by type.
                var worldInteractionAction = packet.getActions().stream()
                        .filter(action -> action.source().type().equals(InventorySource.Type.WORLD_INTERACTION))
                        .findFirst()
                        .orElse(null);
                var containerAction = packet.getActions().stream()
                        .filter(action -> action.source().type().equals(InventorySource.Type.CONTAINER))
                        .findFirst()
                        .orElse(null);

                if (worldInteractionAction == null) {
                    log.warn("Expected WORLD_INTERACTION action type not found");
                    return;
                }

                if (containerAction == null) {
                    log.warn("Expected CONTAINER action type not found");
                    return;
                }

                var dropSlot = containerAction.slot();
                var dropCount = containerAction.fromItem().getCount() - containerAction.toItem().getCount();
                if (!entity.tryDropItem(ContainerTypes.INVENTORY, dropSlot, dropCount)) {
                    log.warn("Failed to drop item from slot {} with count {}", dropSlot, dropCount);
                }
            }
        }

        // The item may have been changed or broken
        // So we need to send update to client
        if (itemInHand.isBroken()) {
            entity.clearItemInHand();
        } else {
            entity.notifyItemInHandChange();
        }
    }

    boolean tryStartUsingItemInAir(EntityPlayer player, ItemStack itemStack) {
        if (!itemStack.canUseItemInAir(player)) {
            return false;
        }

        if (new PlayerStartUseItemInAirEvent(player).call()) {
            player.setUsingItemInAir(true);
        }
        return true;
    }

    void handleItemUseInAir(EntityPlayer player, ItemStack itemStack) {
        if (tryStartUsingItemInAir(player, itemStack)) {
            return;
        }

        if (new PlayerRightClickItemInAirEvent(player).call()) {
            itemStack.rightClickItemInAir(player);
        }
    }

    /**
     * Re-sends the authoritative state of the clicked block, the placed block and their
     * neighbours after a block interaction, so that the client's predictive simulation
     * settles on the server's world state (see PocketMine-MP's syncBlocksNearby).
     *
     * @param syncPlacedNeighbours whether to also re-send the placed block's neighbours; true only
     *                             for items that place multiple blocks (beds, doors, double-tall
     *                             plants), whose second cell the client cannot predict
     */
    void syncBlockPlacement(Player player, Dimension dimension, Vector3ic clickBlockPos, Vector3ic placeBlockPos, boolean syncPlacedNeighbours) {
        for (var face : BlockFace.values()) {
            player.viewBlockUpdate(face.offsetPos(clickBlockPos), 0, dimension.getBlockState(face.offsetPos(clickBlockPos)));
        }
        // The placed cell itself is always sent, since the server may have placed a state the
        // client did not predict. Its neighbours are skipped for single-block items: the "air"
        // updates arrive after the client already started its next placement on the same face and
        // race it (PocketMine-MP e2557ee, PNX), causing wrong-face placements and flicker.
        player.viewBlockUpdate(placeBlockPos, 0, dimension.getBlockState(placeBlockPos));
        if (syncPlacedNeighbours) {
            for (var face : BlockFace.values()) {
                var pos = face.offsetPos(placeBlockPos);
                if (!pos.equals(clickBlockPos)) {
                    player.viewBlockUpdate(pos, 0, dimension.getBlockState(pos));
                }
            }
        }
    }

    /**
     * Whether placing an item occupies more than the single clicked cell, so that the client
     * cannot predict the second cell by itself and the full resync must be kept.
     */
    boolean isMultiBlockItem(ItemStack itemInHand) {
        var blockType = itemInHand.getItemType().getBlockType();
        if (blockType == null) {
            return false;
        }
        var behavior = blockType.getBlockBehavior();
        return behavior instanceof BlockBedBehavior || behavior instanceof BlockDoorBehavior ||
               behavior instanceof BlockCopperDoorBehavior || behavior instanceof BlockTallGrassBehavior;
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.INVENTORY_TRANSACTION;
    }
}
