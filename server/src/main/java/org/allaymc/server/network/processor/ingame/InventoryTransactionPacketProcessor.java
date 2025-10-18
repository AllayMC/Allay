package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerInteractAirEvent;
import org.allaymc.api.eventbus.event.player.PlayerInteractBlockEvent;
import org.allaymc.api.eventbus.event.player.PlayerInteractEntityEvent;
import org.allaymc.server.network.NetworkHelper;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.data.inventory.transaction.InventorySource;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.InventoryTransactionPacket;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

import java.util.Objects;

import static org.allaymc.api.item.type.ItemTypes.AIR;

/**
 * @author Cool_Loong
 */
@Slf4j
public class InventoryTransactionPacketProcessor extends PacketProcessor<InventoryTransactionPacket> {
    public static final int ITEM_USE_CLICK_BLOCK = 0;
    public static final int ITEM_USE_CLICK_AIR = 1;
    public static final int ITEM_USE_BREAK_BLOCK = 2;

    public static final int ITEM_USE_ON_ENTITY_INTERACT = 0;
    public static final int ITEM_USE_ON_ENTITY_ATTACK = 1;

    public static final int ITEM_RELEASE_RELEASE = 0;
    public static final int ITEM_RELEASE_CONSUME = 1;

    private static final double SPAM_BUG_TIME_THRESHOLD = 100d; // 100ms
    private static final double SPAM_BUG_POS_THRESHOLD = 0.00001d;

    private LastRightClickData lastRightClick;

    private boolean isSpamClick(Vector3fc clickPos, Vector3ic clickBlockPos, Vector3fc playerPos) {
        var currentTime = System.currentTimeMillis();
        if (lastRightClick == null) {
            lastRightClick = new LastRightClickData(playerPos, clickBlockPos, clickPos, currentTime);
            return false;
        }

        boolean isSpam = (currentTime - lastRightClick.time()) < SPAM_BUG_TIME_THRESHOLD &&
                lastRightClick.playerPos().distanceSquared(playerPos) < SPAM_BUG_POS_THRESHOLD &&
                lastRightClick.blockPos().equals(clickBlockPos) &&
                lastRightClick.clickPos().distanceSquared(clickPos) < SPAM_BUG_POS_THRESHOLD;

        lastRightClick = new LastRightClickData(playerPos, clickBlockPos, clickPos, currentTime);
        return isSpam;
    }

    @Override
    public void handleSync(EntityPlayer player, InventoryTransactionPacket packet, long receiveTime) {
        var itemInHand = player.getItemInHand();
        var transactionType = packet.getTransactionType();
        switch (transactionType) {
            case ITEM_USE -> {
                var blockFace = BlockFace.fromIndex(packet.getBlockFace());
                var world = player.getLocation().dimension();
                switch (packet.getActionType()) {
                    case ITEM_USE_CLICK_BLOCK -> {
                        var clickBlockPos = NetworkHelper.fromNetwork(packet.getBlockPosition());
                        var clickPos = NetworkHelper.fromNetwork(packet.getClickPosition());
                        // https://github.com/pmmp/PocketMine-MP/blob/835c383d4e126df6f38000e3217ad6a325b7a1f7/src/network/mcpe/handler/InGamePacketHandler.php#L475
                        if (isSpamClick(clickPos, clickBlockPos,
                                NetworkHelper.fromNetwork(packet.getPlayerPosition()))) {
                            break;
                        }

                        var dimension = player.getDimension();
                        var clickedBlockStateReplaceable = dimension.getBlockState(clickBlockPos).getBlockType()
                                .hasBlockTag(BlockTags.REPLACEABLE);
                        var placeBlockPos = clickedBlockStateReplaceable ? clickBlockPos
                                : Objects.requireNonNull(blockFace).offsetPos(clickBlockPos);
                        var interactedBlock = world.getBlockState(clickBlockPos);
                        var interactInfo = new PlayerInteractInfo(
                                player, clickBlockPos,
                                clickPos, blockFace);

                        var event = new PlayerInteractBlockEvent(player, interactInfo,
                                PlayerInteractBlockEvent.Action.RIGHT_CLICK);
                        if (!event.call()) {
                            break;
                        }

                        player.setUsingItemInAir(false);
                        itemInHand.rightClickItemOn(dimension, placeBlockPos, interactInfo);
                        if (player.isUsingItemOnBlock()) {
                            if (itemInHand.useItemOnBlock(dimension, placeBlockPos, interactInfo)) {
                                // Using item on the block successfully, no need to call
                                // BlockBehavior::onInteract()
                                break;
                            }

                            if (!interactedBlock.getBehavior().onInteract(itemInHand, dimension, interactInfo)) {
                                // Player interaction with the block was unsuccessful, and we need to override
                                // the
                                // client block change by sending block update
                                var blockStateClicked = dimension.getBlockState(clickBlockPos);
                                player.viewBlockUpdate(clickBlockPos, 0, blockStateClicked);

                                // Player places a block
                                if (itemInHand.getItemType() == AIR) {
                                    break;
                                }

                                if (!itemInHand.placeBlock(dimension, placeBlockPos, interactInfo)) {
                                    var blockStateReplaced = dimension.getBlockState(placeBlockPos);
                                    player.viewBlockUpdate(placeBlockPos, 0, blockStateReplaced);
                                }
                            }
                        }
                    }
                    case ITEM_USE_CLICK_AIR -> {
                        var event = new PlayerInteractAirEvent(player);
                        if (!event.call()) {
                            break;
                        }
                        itemInHand.clickItemInAir(player);
                        if (!player.isUsingItemInAir()) {
                            if (itemInHand.canUseItemInAir(player)) {
                                // Start using item
                                player.setUsingItemInAir(true, receiveTime);
                            }
                        } else if (player.isUsingItemInAir()) {
                            // Item used
                            player.setUsingItemInAir(false);
                            itemInHand.useItemInAir(player, player.getItemUsingInAirTime(receiveTime));
                        }
                    }
                }
            }
            case ITEM_RELEASE -> {
                switch (packet.getActionType()) {
                    case ITEM_RELEASE_RELEASE -> {
                        itemInHand.releaseItem(player, player.getItemUsingInAirTime(receiveTime));
                        // When a player is interrupted from eating, the ITEM_USE_CLICK_AIR is not sent
                        // However ITEM_RELEASE_RELEASE will always be sent when the player stops using
                        // the item, regardless of whether it was used successfully or not
                        // Therefore, when we receive ITEM_RELEASE_RELEASE, we also refresh the player's
                        // item usage status as a supplement to the ITEM_USE_CLICK_AIR
                        player.setUsingItemInAir(false);
                    }
                    case ITEM_RELEASE_CONSUME -> {
                        // TODO: It seems that this value is deprecated
                    }
                }
            }
            case ITEM_USE_ON_ENTITY -> {
                var target = player.getDimension().getEntityManager().getEntity(packet.getRuntimeEntityId());
                // In some cases, for example when a falling block entity solidifies, latency
                // may allow attacking an entity that
                // no longer exists server side. This is expected, so we shouldn't throw
                // NullPointerException.
                if (target == null) {
                    log.debug("Player {} try to attack a entity which doesn't exist! Entity id: {}",
                            player.getOriginName(), packet.getRuntimeEntityId());
                    return;
                }
                if (!player.canReach(target.getLocation())) {
                    return;
                }

                switch (packet.getActionType()) {
                    case ITEM_USE_ON_ENTITY_INTERACT -> {
                        var clickPos = NetworkHelper.fromNetwork(packet.getClickPosition());
                        var event = new PlayerInteractEntityEvent(player, target, itemInHand, clickPos);
                        if (!event.call()) {
                            return;
                        }

                        if (!itemInHand.interactEntity(player, target)) {
                            target.onInteract(player, itemInHand);
                        }
                    }
                    case ITEM_USE_ON_ENTITY_ATTACK -> {
                        // Doesn't have damage component, can't attack
                        if (!(target instanceof EntityLivingComponent damageable)) {
                            return;
                        }

                        var damage = itemInHand.calculateAttackDamage();
                        if (damage == 0) {
                            damage = 1;
                        }

                        var damageContainer = DamageContainer.entityAttack(player, damage);
                        if (damageable.attack(damageContainer)) {
                            itemInHand.onAttackEntity(player, target);
                        }
                    }
                }
            }
            case NORMAL -> {
                // When the ItemStackRequest system is used, this transaction type is used for
                // dropping items by pressing Q.
                // I don't know why they don't just use ItemStackRequest for that too, which
                // already supports dropping items by
                // clicking them outside an open inventory menu, but for now it is what it is.
                // Fortunately, this means we can
                // be much stricter about the validation criteria. For more details, see
                // item_throwing.md
                if (packet.getActions().size() != 2) {
                    // Editing writable book will also send this packet with three actions, so just
                    // don't warn it
                    log.debug("Expected two actions for dropping an item, got {}", packet.getActions().size());
                    return;
                }

                var worldInteractionAction = packet.getActions().getFirst();
                if (!worldInteractionAction.getSource().getType().equals(InventorySource.Type.WORLD_INTERACTION)) {
                    log.warn("Expected WORLD_INTERACTION action type, got {}",
                            worldInteractionAction.getSource().getType());
                    return;
                }

                var containerAction = packet.getActions().getLast();
                if (!containerAction.getSource().getType().equals(InventorySource.Type.CONTAINER)) {
                    log.warn("Expected CONTAINER action type, got {}", containerAction.getSource().getType());
                    return;
                }

                var dropSlot = containerAction.getSlot();
                var dropCount = containerAction.getFromItem().getCount() - containerAction.getToItem().getCount();
                if (!player.tryDropItem(ContainerTypes.INVENTORY, dropSlot, dropCount)) {
                    log.warn("Failed to drop item from slot {} with count {}", dropSlot, dropCount);
                }
            }
        }

        // The item may have been changed or broken
        // So we need to send update to client
        if (itemInHand.isBroken()) {
            player.clearItemInHand();
        } else {
            player.notifyItemInHandChange();
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.INVENTORY_TRANSACTION;
    }

    private record LastRightClickData(Vector3fc playerPos, Vector3ic blockPos, Vector3fc clickPos, double time) {
    }
}