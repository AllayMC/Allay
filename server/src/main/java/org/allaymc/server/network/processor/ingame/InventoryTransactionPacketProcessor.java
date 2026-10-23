package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.*;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.player.Player;
import org.allaymc.api.world.sound.AttackSound;
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
 * {@code InventoryTransactionPacketProcessor} handles client item use, release,
 * entity interaction, and inventory-drop transactions for one connection. The
 * processor selects the server's held stack after applicable hotbar updates
 * rather than trusting the packet's item payload. Block-click handling gives
 * item interaction and block interaction priority over placement, and rejected
 * or unsuccessful placement paths resend the affected server block states.
 * Air-use events distinguish starting a sustained use from an immediate
 * right-click action.
 *
 * <p>
 * Repeated block clicks are suppressed by a retained position pair and a
 * wall-clock interval that advances even for suppressed clicks. Normal drop
 * transactions identify their world and container actions by source type rather
 * than list order, then delegate the count difference to inventory dropping.
 * Accepted operations can alter items, blocks, or entities before the final
 * held-item notification; this handler does not undo those effects after a
 * later callback failure or early return. Its click-history fields provide no
 * independent synchronization.
 *
 * @author Cool_Loong
 */
@Slf4j
public class InventoryTransactionPacketProcessor extends PacketProcessor<InventoryTransactionPacket> {
    /**
     * {@code ITEM_USE_CLICK_BLOCK} identifies block-click item use. The
     * {@code ITEM_USE} branch uses this action to attempt interaction before placement.
     */
    public static final int ITEM_USE_CLICK_BLOCK = 0;
    /**
     * {@code ITEM_USE_CLICK_AIR} identifies air-click item use. The
     * {@code ITEM_USE} branch uses this action to start or complete sustained use.
     */
    public static final int ITEM_USE_CLICK_AIR = 1;
    /**
     * {@code ITEM_USE_BREAK_BLOCK} names the block-break item-use code. This
     * processor does not handle that action in its {@code ITEM_USE} switch.
     */
    public static final int ITEM_USE_BREAK_BLOCK = 2;

    /**
     * {@code ITEM_USE_ON_ENTITY_INTERACT} selects entity interaction. The held
     * item's interaction runs before the target's fallback interaction callback.
     */
    public static final int ITEM_USE_ON_ENTITY_INTERACT = 0;
    /**
     * {@code ITEM_USE_ON_ENTITY_ATTACK} selects entity attacks. Attack handling
     * requires a living target and the applicable player or mob attack permission.
     */
    public static final int ITEM_USE_ON_ENTITY_ATTACK = 1;

    /**
     * {@code ITEM_RELEASE_RELEASE} selects completion of sustained air use. The
     * release branch invokes its use event only when air use is active.
     */
    public static final int ITEM_RELEASE_RELEASE = 0;
    /**
     * {@code ITEM_RELEASE_CONSUME} names the consume release code. The release
     * branch currently accepts this value without applying a consume operation.
     */
    public static final int ITEM_RELEASE_CONSUME = 1;

    /**
     * {@code SPAM_CLICK_THRESHOLD_MS} limits repeated identical block-click
     * samples to intervals of at least one hundred wall-clock milliseconds.
     */
    private static final long SPAM_CLICK_THRESHOLD_MS = 100;

    // lastClickTime, lastBlockPos, and lastClickPos describe the latest sampled
    // block click. isSpamClick replaces all three even when rejecting a duplicate;
    // the position references are retained directly without synchronization.
    /** Latest sample wall-clock milliseconds. */
    private long lastClickTime;
    /** Latest sampled block position. */
    private Vector3ic lastBlockPos;
    /** Latest sampled relative click position. */
    private Vector3fc lastClickPos;

    /**
     * Returns whether this click repeats both positions inside the suppression
     * interval. The position arguments must be nonnull, and their references
     * become the next comparison sample without copying.
     *
     * <p>
     * Every invocation records its wall-clock time and positions, including a
     * suppressed click. A stream of identical clicks therefore keeps extending
     * the interval until a full gap occurs. Exact vector equality controls the
     * positional comparison, and this helper provides no synchronization or
     * correction for wall-clock changes.
     */
    private boolean isSpamClick(Vector3ic blockPos, Vector3fc clickPos) {
        var now = System.currentTimeMillis();
        var spam = now - this.lastClickTime < SPAM_CLICK_THRESHOLD_MS
                   && blockPos.equals(this.lastBlockPos)
                   && clickPos.equals(this.lastClickPos);
        // Record the sample after deciding admission so even a suppressed
        // duplicate extends the next comparison window rather than leaving the
        // previously admitted timestamp unchanged.
        this.lastClickTime = now;
        this.lastBlockPos = blockPos;
        this.lastClickPos = clickPos;
        return spam;
    }

    /**
     * Applies the transaction's supported item or inventory action to the
     * controlled entity. Item-use and entity-use transactions first select the
     * packet's hotbar slot without echoing that selection, then operate on the
     * server's resulting held item.
     *
     * <p>
     * Block interaction and placement consult permissions and cancellable events,
     * resending current block states on their specified rejection paths. Sustained
     * air use stops before its completion event runs. Entity interaction tries the
     * item before the target fallback, while normal dropping requires two actions
     * containing both supported source types. Effects already performed survive
     * later failures; early returns can bypass the final hand update, which clears
     * a broken stack or notifies its current contents.
     */
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
                        if (entity.isUsingItemInAir()) {
                            break;
                        }

                        var clickBlockPos = NetworkHelper.fromNetwork(packet.getBlockPosition());
                        var clickPos = NetworkHelper.fromNetwork(packet.getClickPosition());
                        // Ignore rapid repeats of the same block and relative
                        // click position so one held interaction does not
                        // repeatedly reach placement and interaction callbacks.
                        // https://github.com/pmmp/PocketMine-MP/blob/835c383d4e126df6f38000e3217ad6a325b7a1f7/src/network/mcpe/handler/InGamePacketHandler.php#L475
                        if (isSpamClick(clickBlockPos, clickPos)) {
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
                            // The client can predict changes at both the
                            // clicked and placement positions; permission
                            // rejection resends both authoritative layer-zero states.
                            player.viewBlockUpdate(clickBlockPos, 0, dimension.getBlockState(clickBlockPos));
                            player.viewBlockUpdate(placeBlockPos, 0, dimension.getBlockState(placeBlockPos));
                            break;
                        }

                        var event = new PlayerInteractBlockEvent(entity, interactInfo, PlayerInteractBlockEvent.Action.RIGHT_CLICK);
                        if (!event.call()) {
                            // Event rejection prevents server interaction, so
                            // restore both positions that the client may have
                            // predicted from this same click.
                            player.viewBlockUpdate(clickBlockPos, 0, dimension.getBlockState(clickBlockPos));
                            player.viewBlockUpdate(placeBlockPos, 0, dimension.getBlockState(placeBlockPos));
                            break;
                        }

                        var sneaking = entity.isSneaking();
                        var useItemOnBlock = !sneaking;
                        var useBlock = !sneaking || itemInHand.getItemType() == AIR;

                        if (useItemOnBlock && itemInHand.useItemOnBlock(dimension, placeBlockPos, interactInfo)) {
                            // A successful item interaction consumes this click;
                            // skipping block interaction prevents the same
                            // input from triggering both handlers.
                            break;
                        }

                        if (useBlock && interactedBlock.getBehavior().onInteract(itemInHand, dimension, interactInfo)) {
                            break;
                        }

                        // Neither interaction consumed the click, so resend the
                        // clicked block before placement to correct the client's
                        // interaction prediction independently of placement success.
                        var blockStateClicked = dimension.getBlockState(clickBlockPos);
                        player.viewBlockUpdate(clickBlockPos, 0, blockStateClicked);

                        if (itemInHand.getItemType() == AIR) {
                            break;
                        }

                        // Refused or unsuccessful placement falls back to air
                        // use and resends the placement position, preserving
                        // usable-item behavior while correcting predicted placement.
                        if (!player.canPlaceBlocks()) {
                            handleItemUseInAir(entity, itemInHand);
                            player.viewBlockUpdate(placeBlockPos, 0, dimension.getBlockState(placeBlockPos));
                            break;
                        }

                        if (!itemInHand.placeBlock(dimension, placeBlockPos, interactInfo)) {
                            handleItemUseInAir(entity, itemInHand);
                            var blockStateReplaced = dimension.getBlockState(placeBlockPos);
                            player.viewBlockUpdate(placeBlockPos, 0, blockStateReplaced);
                        }
                    }
                    case ITEM_USE_CLICK_AIR -> {
                        // An active block-use state owns this input; ignoring
                        // the air click prevents a simultaneous sustained-use
                        // animation while block interaction is still active.
                        if (entity.isUsingItemOnBlock()) {
                            break;
                        }

                        if (!entity.isUsingItemInAir()) {
                            handleItemUseInAir(entity, itemInHand);
                        } else {
                            // Stop sustained use before its completion event so
                            // cancellation or callback failure cannot leave this
                            // completed click marked as still using the item.
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
                            // Release ends the use state before listeners run;
                            // a cancelled completion event suppresses the item
                            // effect without keeping the use active.
                            entity.setUsingItemInAir(false);
                            var event = new PlayerUseItemInAirEvent(entity, entity.getItemUsingInAirTime());
                            if (event.call()) {
                                itemInHand.useItemInAir(entity, event.getUsingTime());
                            }
                        }
                    }
                    case ITEM_RELEASE_CONSUME -> {
                        // TODO: Establish the consume action's current protocol
                        // semantics before implementing it; this branch applies
                        // no consume effect today.
                    }
                }
            }
            case ITEM_USE_ON_ENTITY -> {
                var target = entity.getDimension().getEntityManager().getEntity(packet.getRuntimeEntityId());
                // A target can disappear before this queued transaction runs,
                // so ignore the stale runtime ID instead of dereferencing a
                // removed entity during interaction or attack.
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
                            // An item that declines interaction leaves handling
                            // to the target, allowing entity behavior to respond
                            // without running both successful interaction paths.
                            target.onInteract(entity, itemInHand);
                        }
                    }
                    case ITEM_USE_ON_ENTITY_ATTACK -> {
                        if (!(target instanceof EntityLivingComponent damageable)) {
                            return;
                        }
                        if (target instanceof EntityPlayer) {
                            if (!player.canAttackPlayers()) {
                                return;
                            }
                        } else if (!player.canAttackMobs()) {
                            return;
                        }

                        var damage = itemInHand.calculateAttackDamage(entity, target);
                        if (damage == 0) {
                            // Zero calculated damage still produces a one-point attack.
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
                // Keyboard dropping uses this two-action transaction even with
                // item-stack requests enabled; inventory dropping instead uses
                // ItemStackRequest. The captured sequence is documented in
                // docs/old/protocol_research/item_throwing.md.
                if (packet.getActions().size() != 2) {
                    // Other normal transactions, including book edits, do not
                    // match this supported drop shape; leave them untouched
                    // instead of interpreting arbitrary actions as a drop.
                    log.debug("Expected two actions for dropping an item, got {}", packet.getActions().size());
                    return;
                }

                // Protocol versions can reorder WORLD_INTERACTION and CONTAINER;
                // identify their roles by source type so the count difference
                // remains associated with the inventory action in either order.
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

        // Successful callbacks may change count or durability; clear a broken
        // held stack or notify its contents so the client sees the resulting
        // hand state after the transaction.
        if (itemInHand.isBroken()) {
            entity.clearItemInHand();
        } else {
            entity.notifyItemInHandChange();
        }
    }

    /**
     * Returns whether the stack supports sustained air use for this entity.
     * Unsupported stacks return {@code false}; supported stacks return {@code true}
     * even when the start event rejects setting the use flag, preventing an
     * immediate right-click fallback after cancellation.
     */
    boolean tryStartUsingItemInAir(EntityPlayer player, ItemStack itemStack) {
        if (!itemStack.canUseItemInAir(player)) {
            return false;
        }

        if (new PlayerStartUseItemInAirEvent(player).call()) {
            player.setUsingItemInAir(true);
        }
        return true;
    }

    /**
     * Starts sustained air use or offers an immediate right-click event for an
     * unsupported stack. A cancelled sustained-use start still consumes this
     * path, while immediate item behavior runs only after its own event accepts.
     */
    void handleItemUseInAir(EntityPlayer player, ItemStack itemStack) {
        if (tryStartUsingItemInAir(player, itemStack)) {
            return;
        }

        // Only stacks without sustained-use behavior reach this fallback;
        // cancelling a supported stack's start event must not invoke a different
        // item-use action for the same click.
        if (new PlayerRightClickItemInAirEvent(player).call()) {
            itemStack.rightClickItemInAir(player);
        }
    }

    /**
     * Returns the inventory transaction packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.INVENTORY_TRANSACTION;
    }
}
