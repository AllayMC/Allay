package org.allaymc.server.blockentity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.component.BlockEntityHopperBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityChest;
import org.allaymc.api.blockentity.interfaces.BlockEntityHopper;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.interfaces.SidedContainer;
import org.allaymc.api.entity.interfaces.EntityItem;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.eventbus.event.container.ContainerItemMoveEvent;
import org.allaymc.api.eventbus.event.container.ContainerItemPickupEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.server.component.annotation.ComponentObject;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * {@code BlockEntityHopperBaseComponentImpl} moves items between a hopper,
 * neighboring containers, and nearby dropped-item entities. Enabled ticks count
 * down the transfer delay, then attempt a push followed by a pull using the
 * inventory state left by the push. Disabled ticks preserve the delay and
 * perform neither operation.
 *
 * <p>
 * Container moves transfer one item and respect the neighbor's sided insertion
 * or extraction slots. Pickup can span multiple target slots. Each proposed
 * destination change has its own cancellable event, so a cancelled slot can be
 * skipped while later destinations still accept items. Destination writes occur
 * before the aggregate source decrement and source notification; exceptions do
 * not roll back earlier destination changes. Container-to-container moves reset
 * participating hopper cooldowns only after source notification succeeds.
 * Callers must serialize access to the live inventories throughout this
 * sequence.
 *
 * @author ClexaGod | daoge_cmd
 */
public class BlockEntityHopperBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityHopperBaseComponent {

    /**
     * {@code TAG_TRANSFER_COOLDOWN} names the integer NBT counter used to
     * preserve the hopper's current transfer delay across saves and loads.
     */
    protected static final String TAG_TRANSFER_COOLDOWN = "TransferCooldown";
    /**
     * {@code TRANSFER_COOLDOWN} supplies the server-tick delay assigned on
     * construction and after successful transfers to participating hopper block
     * entities.
     */
    protected static final int TRANSFER_COOLDOWN = 8;

    /**
     * Injected hopper supplying its live inventory and transfer-event context.
     */
    @ComponentObject
    protected BlockEntityHopper thisHopper;

    /**
     * {@code transferCooldown} counts enabled server ticks before another
     * attempt; disabled ticks leave it unchanged, and the generated setter
     * stores values directly without clamping.
     */
    @Getter
    @Setter
    protected int transferCooldown;

    /**
     * Creates hopper base state with the standard eight-tick transfer delay.
     * Enabled ticks consume that countdown before attempting a transfer; NBT
     * loaded during completion may replace the initialized delay.
     */
    public BlockEntityHopperBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
        this.transferCooldown = TRANSFER_COOLDOWN;
    }

    /**
     * Adds the current integer transfer cooldown to completed base save data.
     * The value is stored unchanged, including zero or negative values supplied
     * through the setter; this method builds data without persisting it.
     */
    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putInt(TAG_TRANSFER_COOLDOWN, transferCooldown)
                .build();
    }

    /**
     * Loads base state before applying a present integer transfer cooldown.
     * Missing or differently typed cooldown data leaves the previous delay
     * unchanged, and loaded values are accepted without clamping or
     * normalization.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForInt(TAG_TRANSFER_COOLDOWN, value -> transferCooldown = value);
    }

    /**
     * Advances the enabled hopper's cooldown or performs a transfer attempt. A
     * positive delay is decreased and ends the tick even when that decrease
     * reaches zero; disabled hoppers preserve their current delay entirely.
     *
     * <p>
     * Once the delay permits an attempt, {@code tryTransfer} may both push and
     * pull during this tick. Any successful change restores the standard delay,
     * while an unsuccessful attempt leaves zero or negative delay available for
     * another attempt on the next enabled tick. This method ignores the
     * supplied tick number.
     */
    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);
        if (isDisabled()) {
            return;
        }

        // Consume a positive cooldown tick without transferring on the same
        // call that reaches zero, preserving the delay between successive
        // enabled transfer attempts.
        if (transferCooldown > 0) {
            transferCooldown--;
            return;
        }

        if (tryTransfer()) {
            transferCooldown = TRANSFER_COOLDOWN;
        }
    }

    /**
     * Attempts a push and then a pull against the hopper's live inventory. A
     * nonempty inventory permits the push; the subsequent fullness check
     * observes the state after that attempt, so a successful push can make room
     * for a pull in the same call.
     *
     * <p>
     * The result reports whether either operation moved items, including a
     * pickup of multiple items. This helper does not test the disabled property
     * or the current cooldown, so ordinary ticking must establish those
     * admission conditions before calling it.
     */
    protected boolean tryTransfer() {
        var hopperContainer = thisHopper.getContainer();
        var changed = false;

        if (!hopperContainer.isEmpty()) {
            changed |= tryPushItems();
        }

        if (!hopperContainer.isFull()) {
            changed |= tryPullItems();
        }

        return changed;
    }

    /**
     * Attempts to push one item into the container at the hopper's facing
     * position. The source slots are tried in order until one move succeeds; a
     * missing neighbor or an inventory with no acceptable move returns
     * {@code false}.
     *
     * <p>
     * A sided destination receives the opposite of the hopper's facing
     * direction because insertion enters the neighbor through that face. An
     * empty allowed-slot array rejects that source stack, while {@code null}
     * selects unrestricted target scanning. A destination hopper receives a
     * cooldown after the move's source notification succeeds.
     */
    protected boolean tryPushItems() {
        var hopperContainer = thisHopper.getContainer();
        if (hopperContainer.isEmpty()) {
            return false;
        }

        var facingPos = getFacingPos();
        var targetContainer = getTargetContainer(facingPos);
        if (targetContainer == null) {
            return false;
        }

        var targetHopper = getHopperAt(facingPos);
        var stacks = hopperContainer.getItemStackArray();
        for (int slot = 0; slot < stacks.length; slot++) {
            if (stacks[slot] == ItemAirStack.AIR_STACK) {
                continue;
            }

            // Enter the neighboring container through the face opposite the
            // hopper's output; null permits unrestricted scanning, while an
            // empty list denies insertion.
            var allowedSlots = targetContainer instanceof SidedContainer sidedContainer
                    ? sidedContainer.getAllowedInsertSlots(getFacing().opposite(), stacks[slot])
                    : null;
            if (allowedSlots != null && allowedSlots.length == 0) {
                continue;
            }
            if (tryMoveOneItem(hopperContainer, slot, null, targetContainer, targetHopper, allowedSlots)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Attempts a pull from above before scanning nearby dropped-item entities.
     * A successful container move ends the call; an absent source or a source
     * with no permitted move falls through to entity pickup.
     *
     * <p>
     * A container move transfers at most one item, but the entity fallback can
     * insert multiple items from one entity across several target slots. An
     * empty or event-rejected container above therefore does not suppress
     * pickup of an eligible item entity in the hopper's pickup volume.
     */
    protected boolean tryPullItems() {
        var abovePos = new Position3i(BlockFace.UP.offsetPos(position), position.dimension());
        var sourceContainer = getTargetContainer(abovePos);
        if (sourceContainer != null) {
            var sourceHopper = getHopperAt(abovePos);
            if (tryPullFromContainer(sourceContainer, sourceHopper)) {
                return true;
            }
        }

        // A missing, empty, or event-rejected source container leaves entity
        // pickup eligible, so the fallback can still collect items from the
        // same pickup volume.
        return tryPullFromItemEntities();
    }

    /**
     * Attempts one item from the source's first acceptable extraction slot.
     * Sided sources must return a nonnull bottom-face extraction array; this
     * path directly iterates it and does not support the unrestricted
     * {@code null} sentinel permitted by {@code SidedContainer}. Other sources
     * use ascending indices, and {@code sourceHopper} may be {@code null} when
     * no source cooldown applies.
     */
    protected boolean tryPullFromContainer(Container sourceContainer, BlockEntityHopper sourceHopper) {
        if (sourceContainer instanceof SidedContainer sidedContainer) {
            for (var slot : sidedContainer.getAllowedExtractSlots(BlockFace.DOWN)) {
                var stack = sidedContainer.getItemStack(slot);
                if (stack == ItemAirStack.AIR_STACK) {
                    continue;
                }

                if (tryPullOneItem(sidedContainer, slot, sourceHopper)) {
                    return true;
                }
            }

            return false;
        }

        var stacks = sourceContainer.getItemStackArray();
        for (int slot = 0; slot < stacks.length; slot++) {
            if (stacks[slot] == ItemAirStack.AIR_STACK) {
                continue;
            }

            if (tryPullOneItem(sourceContainer, slot, sourceHopper)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Scans colliding item entities until one pickup moves any items. The
     * physics query uses the hopper's pickup volume and item entity type; this
     * helper does not additionally test the entity's pickup delay.
     */
    protected boolean tryPullFromItemEntities() {
        var entities = position.dimension().getEntityManager()
                .getPhysicsService()
                .computeCollidingEntities(getPickupAABB(), entity -> entity.getEntityType() == EntityTypes.ITEM);
        for (var entity : entities) {
            if (tryPullFromItemEntity((EntityItem) entity)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Attempts to insert items from the supplied entity into this hopper. A
     * {@code null} or shared-air item stack produces {@code false}; any
     * positive insertion count produces {@code true}, even when part of the
     * entity's stack remains.
     *
     * <p>
     * Insertion first changes target slots and reduces the shared source stack.
     * This helper then requests entity removal for an exhausted stack or
     * assigns the remaining stack back to the entity. Exceptions in these
     * phases do not undo accepted target writes or previously applied source
     * count changes.
     */
    protected boolean tryPullFromItemEntity(EntityItem itemEntity) {
        var stack = itemEntity.getItemStack();
        if (stack == null || stack == ItemAirStack.AIR_STACK) {
            return false;
        }

        var movedCount = insertFromItemEntity(stack, itemEntity);
        if (movedCount <= 0) {
            return false;
        }

        // Destination insertion has already reduced the shared stack; remove
        // its exhausted entity or publish the surviving remainder without
        // recreating accepted items.
        if (stack.getCount() <= 0) {
            itemEntity.remove();
        } else {
            itemEntity.setItemStack(stack);
        }
        return true;
    }

    /**
     * Moves at most one item from the source slot into an allowed destination.
     * An air source or an insertion that moves nothing returns {@code false}.
     * {@code allowedTargetSlots} may be {@code null} for unrestricted scanning,
     * and either hopper argument may be {@code null} when no cooldown applies.
     *
     * <p>
     * Insertion changes the destination and reduces the source stack before
     * this helper clears or notifies the source slot. Only after that
     * notification succeeds are participating hopper cooldowns reset. A later
     * exception leaves earlier changes applied, so callers serialize inventory
     * access and must not treat this sequence as an atomic transfer.
     */
    protected boolean tryMoveOneItem(Container source, int sourceSlot, BlockEntityHopper sourceHopper,
                                     Container target, BlockEntityHopper targetHopper, int[] allowedTargetSlots) {
        var stack = source.getItemStack(sourceSlot);
        if (stack == ItemAirStack.AIR_STACK) {
            return false;
        }

        if (!tryInsertOneItem(stack, target, allowedTargetSlots, source, sourceSlot)) {
            return false;
        }

        if (stack.getCount() <= 0) {
            source.clearSlot(sourceSlot);
        } else {
            source.notifySlotChange(sourceSlot);
        }
        // Reset neighbor cooldowns after source notification succeeds, so a
        // transfer failure earlier in the sequence does not apply these later
        // scheduling changes.
        if (sourceHopper != null) {
            sourceHopper.setTransferCooldown(TRANSFER_COOLDOWN);
        }
        if (targetHopper != null) {
            targetHopper.setTransferCooldown(TRANSFER_COOLDOWN);
        }
        return true;
    }

    /**
     * Attempts one source item using this hopper as the unrestricted
     * destination. The shared move helper performs event checks and source
     * notification; a nonnull {@code sourceHopper} also receives a cooldown
     * after success.
     */
    protected boolean tryPullOneItem(Container source, int sourceSlot, BlockEntityHopper sourceHopper) {
        return tryMoveOneItem(source, sourceSlot, sourceHopper, thisHopper.getContainer(), thisHopper, null);
    }

    /**
     * Attempts to insert one source item and reports whether any item moved.
     * {@code allowedSlots} may be {@code null} to scan every destination slot;
     * source container information selects transfer events before each proposed
     * write.
     */
    protected boolean tryInsertOneItem(ItemStack sourceStack, Container target, int[] allowedSlots, Container sourceContainer, int sourceSlot) {
        return insertIntoContainer(sourceStack, target, 1, allowedSlots, sourceContainer, sourceSlot, null) > 0;
    }

    /**
     * Inserts as much of the supplied entity stack as this hopper can accept.
     * Callers supply a nonnull {@code pickupEntity} so each destination
     * proposal dispatches a pickup event; the helper reduces the source count
     * but leaves entity removal or reassignment to its caller.
     */
    protected int insertFromItemEntity(ItemStack sourceStack, EntityItem pickupEntity) {
        return insertIntoContainer(sourceStack, thisHopper.getContainer(), Integer.MAX_VALUE, null, null, -1, pickupEntity);
    }

    /**
     * Finds a container-holding block entity at {@code targetPos} in this
     * hopper's dimension. A missing holder returns {@code null}; a paired chest
     * supplies its refreshed combined container instead of only one chest's
     * inventory.
     */
    protected Container getTargetContainer(Position3ic targetPos) {
        var blockEntity = position.dimension().getBlockEntity(targetPos);
        if (!(blockEntity instanceof BlockEntityContainerHolderComponent containerHolder)) {
            return null;
        }

        if (containerHolder instanceof BlockEntityChest chest && chest.isPaired()) {
            // Use the paired chest's refreshed view so automated insertion and
            // extraction see both underlying inventories in the same lead-based
            // slot order as players.
            return chest.getDoubleChestContainer();
        }

        return containerHolder.getContainer();
    }

    /**
     * Finds a hopper at {@code targetPos} in this hopper's dimension. The
     * result is {@code null} when no hopper occupies that position, allowing
     * transfer callers to skip a nonexistent neighbor cooldown.
     */
    protected BlockEntityHopper getHopperAt(Position3ic targetPos) {
        var blockEntity = position.dimension().getBlockEntity(targetPos);
        return blockEntity instanceof BlockEntityHopper hopper ? hopper : null;
    }

    /**
     * Returns the adjacent facing position within this hopper's current
     * dimension.
     */
    protected Position3ic getFacingPos() {
        return new Position3i(getFacing().offsetPos(position), position.dimension());
    }

    /**
     * Decodes the hopper block's {@code FACING_DIRECTION} as a
     * {@code BlockFace}. An invalid property index returns {@code null};
     * callers deriving positions or insertion faces require the block state to
     * contain a valid index.
     */
    protected BlockFace getFacing() {
        var blockState = position.dimension().getBlockState(position);
        return BlockFace.fromIndex(blockState.getPropertyValue(BlockPropertyTypes.FACING_DIRECTION));
    }

    /**
     * Returns whether the hopper block's toggle property currently disables
     * transfers.
     */
    protected boolean isDisabled() {
        return position.dimension().getBlockState(position).getPropertyValue(BlockPropertyTypes.TOGGLE_BIT);
    }

    /**
     * Returns a one-by-two-by-one-block pickup box covering this hopper and the
     * block above.
     */
    protected AABBdc getPickupAABB() {
        var x = position.x();
        var y = position.y();
        var z = position.z();
        // Include both the hopper block and the block immediately above for
        // pickup.
        return new AABBd(x, y, z, x + 1, y + 2, z + 1);
    }

    /**
     * Inserts up to {@code maxMoveCount} source items and returns the accepted
     * count. {@code null}, air, or a nonpositive maximum returns zero. A
     * {@code null} slot array scans the entire target; an explicit array
     * supplies its own order and must contain valid target indices.
     *
     * <p>
     * Each proposed destination change runs its own event check. Cancellation
     * skips that proposed write while later slots can still accept items.
     * Target writes and notifications happen during scanning, but the shared
     * source count is reduced only after scanning completes. An exception
     * before that final reduction can leave accepted items in the target
     * without charging the source. Callers also perform source-container
     * notification or entity cleanup after this helper returns.
     */
    protected int insertIntoContainer(ItemStack sourceStack, Container target, int maxMoveCount, int[] allowedSlots,
                                      Container sourceContainer, int sourceSlot, EntityItem pickupEntity) {
        if (sourceStack == null || sourceStack == ItemAirStack.AIR_STACK || maxMoveCount <= 0) {
            return 0;
        }

        int remaining = Math.min(sourceStack.getCount(), maxMoveCount);
        int movedCount = 0;

        if (allowedSlots == null) {
            var size = target.getItemStackArray().length;
            for (int slot = 0; slot < size; slot++) {
                if (remaining <= 0) {
                    break;
                }

                movedCount += tryMoveToSlot(target, sourceStack, slot, remaining, sourceContainer, sourceSlot, pickupEntity);
                remaining = Math.min(sourceStack.getCount(), maxMoveCount) - movedCount;
            }
        } else {
            for (int slot : allowedSlots) {
                if (remaining <= 0) {
                    break;
                }

                movedCount += tryMoveToSlot(target, sourceStack, slot, remaining, sourceContainer, sourceSlot, pickupEntity);
                remaining = Math.min(sourceStack.getCount(), maxMoveCount) - movedCount;
            }
        }

        // Charge the source only after all destination attempts finish; an
        // exception during a target write or notification leaves preceding
        // target changes uncompensated.
        if (movedCount > 0) {
            sourceStack.reduceCount(movedCount);
        }
        return movedCount;
    }

    /**
     * Proposes and applies one destination-slot contribution to an insertion.
     * An empty slot receives a copied source stack limited by item capacity; a
     * mergeable nonfull stack grows in place up to its remaining capacity.
     * Incompatible or event-rejected slots contribute zero.
     *
     * <p>
     * The move event runs after the capacity calculation and before the
     * destination write. The method does not recheck capacity after event
     * listeners return, and it leaves source decrement to the enclosing scan. A
     * notification failure can therefore expose a changed destination while the
     * source still holds its original count.
     */
    protected int tryMoveToSlot(Container target, ItemStack sourceStack, int slot, int remaining,
                                Container sourceContainer, int sourceSlot, EntityItem pickupEntity) {
        var targetStack = target.getItemStack(slot);
        int maxStackSize = sourceStack.getItemType().getItemData().maxStackSize();
        if (targetStack == ItemAirStack.AIR_STACK) {
            int moveCount = Math.min(remaining, maxStackSize);
            if (!isMoveAllowed(sourceContainer, sourceSlot, pickupEntity, target, slot, sourceStack, moveCount)) {
                return 0;
            }

            // Give an empty destination its own stack before the enclosing scan
            // reduces the source; sharing that mutable count would charge both
            // sides together.
            var newStack = sourceStack.copy();
            newStack.setCount(moveCount);
            target.setItemStack(slot, newStack);
            return moveCount;
        }

        if (targetStack.canMerge(sourceStack, true) && !targetStack.isFull()) {
            int targetMax = targetStack.getItemType().getItemData().maxStackSize();
            int space = targetMax - targetStack.getCount();
            if (space <= 0) {
                return 0;
            }

            int moveCount = Math.min(remaining, space);
            if (!isMoveAllowed(sourceContainer, sourceSlot, pickupEntity, target, slot, sourceStack, moveCount)) {
                return 0;
            }

            targetStack.increaseCount(moveCount);
            target.notifySlotChange(slot);
            return moveCount;
        }

        return 0;
    }

    /**
     * Returns whether the applicable move or pickup event accepts this
     * proposal. Nonpositive counts are rejected; a source container takes
     * precedence over pickup context, and an operation with neither context is
     * allowed without dispatching an event.
     */
    protected boolean isMoveAllowed(Container sourceContainer, int sourceSlot, EntityItem pickupEntity,
                                    Container target, int targetSlot, ItemStack sourceStack, int moveCount) {
        if (moveCount <= 0) {
            return false;
        }

        if (sourceContainer != null) {
            var event = new ContainerItemMoveEvent(sourceContainer, sourceSlot, target, targetSlot, sourceStack.getItemType(), moveCount);
            return event.call();
        }

        if (pickupEntity != null) {
            var event = new ContainerItemPickupEvent(pickupEntity, target, targetSlot, moveCount);
            return event.call();
        }

        return true;
    }
}
