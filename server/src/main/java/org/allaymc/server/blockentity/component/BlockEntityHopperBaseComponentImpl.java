package org.allaymc.server.blockentity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.component.BlockEntityHopperBaseComponent;
import org.allaymc.api.blockentity.component.BlockEntityPairableComponent;
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
import org.allaymc.server.container.impl.DoubleChestContainerImpl;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * @author ClexaGod | daoge_cmd
 */
public class BlockEntityHopperBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityHopperBaseComponent {

    protected static final String TAG_TRANSFER_COOLDOWN = "TransferCooldown";
    protected static final int TRANSFER_COOLDOWN = 8;

    @ComponentObject
    protected BlockEntityHopper thisHopper;

    @Getter
    @Setter
    protected int transferCooldown;

    public BlockEntityHopperBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
        this.transferCooldown = TRANSFER_COOLDOWN;
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putInt(TAG_TRANSFER_COOLDOWN, transferCooldown)
                .build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForInt(TAG_TRANSFER_COOLDOWN, value -> transferCooldown = value);
    }

    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);
        if (isDisabled()) {
            return;
        }

        if (transferCooldown > 0) {
            transferCooldown--;
            return;
        }

        if (tryTransfer()) {
            transferCooldown = TRANSFER_COOLDOWN;
        }
    }

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

    protected boolean tryPushItems() {
        var hopperContainer = thisHopper.getContainer();
        if (hopperContainer.isEmpty()) {
            return false;
        }

        var target = getTargetContainer(getFacingPos());
        if (target == null) {
            return false;
        }

        var stacks = hopperContainer.getItemStackArray();
        for (int slot = 0; slot < stacks.length; slot++) {
            if (stacks[slot] == ItemAirStack.AIR_STACK) {
                continue;
            }

            var allowedSlots = target.container instanceof SidedContainer sidedContainer
                    ? sidedContainer.getAllowedInsertSlots(getFacing().opposite(), stacks[slot])
                    : null;
            if (allowedSlots != null && allowedSlots.length == 0) {
                continue;
            }
            if (tryMoveOneItem(hopperContainer, slot, null, target, allowedSlots)) {
                return true;
            }
        }

        return false;
    }

    protected boolean tryPullItems() {
        var source = getTargetContainer(new Position3i(BlockFace.UP.offsetPos(position), position.dimension()));
        if (source != null && tryPullFromContainer(source)) {
            return true;
        }

        return tryPullFromItemEntities();
    }

    protected boolean tryPullFromContainer(TransferTarget source) {
        var hopperContainer = thisHopper.getContainer();
        if (source.container instanceof SidedContainer sidedContainer) {
            for (var slot : sidedContainer.getAllowedExtractSlots(BlockFace.DOWN)) {
                var stack = sidedContainer.getItemStack(slot);
                if (stack == ItemAirStack.AIR_STACK) {
                    continue;
                }

                if (tryMoveOneItem(sidedContainer, slot, source, new TransferTarget(hopperContainer, thisHopper), null)) {
                    return true;
                }
            }

            return false;
        }

        var stacks = source.container.getItemStackArray();
        for (int slot = 0; slot < stacks.length; slot++) {
            if (stacks[slot] == ItemAirStack.AIR_STACK) {
                continue;
            }

            if (tryMoveOneItem(source.container, slot, source, new TransferTarget(hopperContainer, thisHopper), null)) {
                return true;
            }
        }
        return false;
    }

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

    protected boolean tryPullFromItemEntity(EntityItem itemEntity) {
        var hopperContainer = thisHopper.getContainer();
        var stack = itemEntity.getItemStack();
        if (stack == null || stack == ItemAirStack.AIR_STACK) {
            return false;
        }

        var movedCount = insertIntoContainer(stack, hopperContainer, Integer.MAX_VALUE, null, null, -1, itemEntity);
        if (movedCount <= 0) {
            return false;
        }

        if (stack.getCount() <= 0) {
            itemEntity.remove();
        } else {
            itemEntity.setItemStack(stack);
        }
        return true;
    }

    protected boolean tryMoveOneItem(Container source, int sourceSlot, TransferTarget sourceTarget, TransferTarget target, int[] allowedTargetSlots) {
        var stack = source.getItemStack(sourceSlot);
        if (stack == ItemAirStack.AIR_STACK) {
            return false;
        }

        if (!tryInsertOneItem(stack, target.container, allowedTargetSlots, source, sourceSlot)) {
            return false;
        }

        if (stack.getCount() <= 0) {
            source.clearSlot(sourceSlot);
        } else {
            source.notifySlotChange(sourceSlot);
        }
        if (sourceTarget != null && sourceTarget.hopper != null) {
            sourceTarget.hopper.setTransferCooldown(TRANSFER_COOLDOWN);
        }
        if (target.hopper != null) {
            target.hopper.setTransferCooldown(TRANSFER_COOLDOWN);
        }
        return true;
    }

    protected boolean tryInsertOneItem(ItemStack sourceStack, Container target, int[] allowedSlots, Container sourceContainer, int sourceSlot) {
        return insertIntoContainer(sourceStack, target, 1, allowedSlots, sourceContainer, sourceSlot, null) > 0;
    }

    protected TransferTarget getTargetContainer(Position3ic targetPos) {
        var blockEntity = position.dimension().getBlockEntity(targetPos);
        if (!(blockEntity instanceof BlockEntityContainerHolderComponent holder)) {
            return null;
        }

        // TODO: refactor it
        if (blockEntity instanceof BlockEntityPairableComponent pairable && pairable.isPaired()) {
            var pair = pairable.getPair();
            if (!(pair instanceof BlockEntityContainerHolderComponent pairHolder)) {
                return new TransferTarget(holder.getContainer(), null);
            }

            var doubleChest = new DoubleChestContainerImpl();
            var left = holder.getContainer();
            var right = pairHolder.getContainer();
            if (!pairable.isLead()) {
                var temp = left;
                left = right;
                right = temp;
            }
            doubleChest.setLeft(left);
            doubleChest.setRight(right);
            return new TransferTarget(doubleChest, null);
        }

        return new TransferTarget(holder.getContainer(), blockEntity instanceof BlockEntityHopper hopper ? hopper : null);
    }

    protected Position3ic getFacingPos() {
        return new Position3i(getFacing().offsetPos(position), position.dimension());
    }

    protected BlockFace getFacing() {
        var blockState = position.dimension().getBlockState(position);
        var facingIndex = blockState.getPropertyValue(BlockPropertyTypes.FACING_DIRECTION);
        var facing = BlockFace.fromIndex(facingIndex);
        return facing == null ? BlockFace.DOWN : facing;
    }

    protected boolean isDisabled() {
        return position.dimension().getBlockState(position).getPropertyValue(BlockPropertyTypes.TOGGLE_BIT);
    }

    protected AABBdc getPickupAABB() {
        var x = position.x();
        var y = position.y();
        var z = position.z();
        return new AABBd(x, y, z, x + 1, y + 2, z + 1);
    }

    protected int insertIntoContainer(ItemStack sourceStack, Container target, int maxMoveCount, int[] allowedSlots,
                                      Container sourceContainer, int sourceSlot, EntityItem pickupEntity) {
        if (sourceStack == null || sourceStack == ItemAirStack.AIR_STACK || maxMoveCount <= 0) {
            return 0;
        }

        int remaining = Math.min(sourceStack.getCount(), maxMoveCount);
        int movedCount = 0;
        int maxStackSize = sourceStack.getItemType().getItemData().maxStackSize();

        if (allowedSlots == null) {
            var size = target.getItemStackArray().length;
            for (int slot = 0; slot < size; slot++) {
                if (remaining <= 0) {
                    break;
                }

                movedCount += tryMoveToSlot(target, sourceStack, slot, maxStackSize, remaining, sourceContainer, sourceSlot, pickupEntity);
                remaining = Math.min(sourceStack.getCount(), maxMoveCount) - movedCount;
            }
        } else {
            for (int slot : allowedSlots) {
                if (remaining <= 0) {
                    break;
                }

                movedCount += tryMoveToSlot(target, sourceStack, slot, maxStackSize, remaining, sourceContainer, sourceSlot, pickupEntity);
                remaining = Math.min(sourceStack.getCount(), maxMoveCount) - movedCount;
            }
        }

        if (movedCount > 0) {
            sourceStack.reduceCount(movedCount);
        }
        return movedCount;
    }

    protected int tryMoveToSlot(Container target, ItemStack sourceStack, int slot, int maxStackSize, int remaining,
                                Container sourceContainer, int sourceSlot, EntityItem pickupEntity) {
        var targetStack = target.getItemStack(slot);
        if (targetStack == ItemAirStack.AIR_STACK) {
            int moveCount = Math.min(remaining, maxStackSize);
            if (!isMoveAllowed(sourceContainer, sourceSlot, pickupEntity, target, slot, sourceStack, moveCount)) {
                return 0;
            }

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

    protected record TransferTarget(Container container, BlockEntityHopper hopper) {
    }
}
