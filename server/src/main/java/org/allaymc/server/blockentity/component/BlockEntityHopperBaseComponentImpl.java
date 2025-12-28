package org.allaymc.server.blockentity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.component.BlockEntityHopperBaseComponent;
import org.allaymc.api.blockentity.component.BlockEntityPairableComponent;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.interfaces.SidedContainer;
import org.allaymc.api.eventbus.event.container.ContainerItemMoveEvent;
import org.allaymc.api.eventbus.event.container.ContainerItemPickupEvent;
import org.allaymc.api.entity.interfaces.EntityItem;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.server.container.impl.DoubleChestContainerImpl;
import org.allaymc.server.component.annotation.Dependency;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * @author ClexaGod
 */
public class BlockEntityHopperBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityHopperBaseComponent {

    protected static final String TAG_TRANSFER_COOLDOWN = "TransferCooldown";
    protected static final int TRANSFER_COOLDOWN_TICKS = 8;

    @Dependency
    protected BlockEntityContainerHolderComponent containerHolderComponent;

    @Getter
    @Setter
    protected int transferCooldown = TRANSFER_COOLDOWN_TICKS;

    public BlockEntityHopperBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
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
            transferCooldown = TRANSFER_COOLDOWN_TICKS;
        }
    }

    protected boolean tryTransfer() {
        var hopperContainer = containerHolderComponent.getContainer();
        boolean changed = false;
        if (!hopperContainer.isEmpty()) {
            changed |= tryPushItems(hopperContainer);
        }
        if (!isContainerFull(hopperContainer)) {
            changed |= tryPullItems(hopperContainer);
        }
        return changed;
    }

    protected boolean tryPushItems(Container hopperContainer) {
        if (hopperContainer.isEmpty()) {
            return false;
        }

        var facing = getFacing();
        var target = getTargetContainer(getFacingPos());
        if (target == null) {
            return false;
        }
        var targetSide = facing.opposite();

        var stacks = hopperContainer.getItemStackArray();
        for (int slot = 0; slot < stacks.length; slot++) {
            if (stacks[slot] == ItemAirStack.AIR_STACK) {
                continue;
            }
            var allowedSlots = target.container instanceof SidedContainer sided
                    ? sided.getAllowedInsertSlots(targetSide, stacks[slot])
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

    protected boolean tryPullItems(Container hopperContainer) {
        var dimension = position.dimension();
        if (dimension == null) {
            return false;
        }
        var sourcePos = new Position3i(BlockFace.UP.offsetPos(position), dimension);
        var source = getTargetContainer(sourcePos);
        if (source != null && tryPullFromContainer(source, hopperContainer)) {
            return true;
        }

        return tryPullFromItemEntities(hopperContainer);
    }

    protected boolean tryPullFromContainer(TransferTarget source, Container hopperContainer) {
        var sourceSide = BlockFace.DOWN;
        var allowedSlots = source.container instanceof SidedContainer sided
                ? sided.getAllowedExtractSlots(sourceSide)
                : null;
        if (allowedSlots != null) {
            for (var slot : allowedSlots) {
                var stack = source.container.getItemStack(slot);
                if (stack == ItemAirStack.AIR_STACK) {
                    continue;
                }
                if (tryMoveOneItem(source.container, slot, source, new TransferTarget(hopperContainer, this), null)) {
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
            if (tryMoveOneItem(source.container, slot, source, new TransferTarget(hopperContainer, this), null)) {
                return true;
            }
        }
        return false;
    }

    protected boolean tryPullFromItemEntities(Container hopperContainer) {
        var dimension = position.dimension();
        if (dimension == null) {
            return false;
        }

        var entities = dimension.getEntityManager()
                .getPhysicsService()
                .computeCollidingEntities(getPickupAABB(), entity -> entity instanceof EntityItem);
        for (var entity : entities) {
            if (!(entity instanceof EntityItem itemEntity)) {
                continue;
            }
            if (tryPullFromItemEntity(itemEntity, hopperContainer)) {
                return true;
            }
        }
        return false;
    }

    protected boolean tryPullFromItemEntity(EntityItem itemEntity, Container hopperContainer) {
        var stack = itemEntity.getItemStack();
        if (stack == null || stack == ItemAirStack.AIR_STACK) {
            return false;
        }

        var moved = insertIntoContainer(stack, hopperContainer, Integer.MAX_VALUE, null, null, -1, itemEntity);
        if (moved <= 0) {
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
        if (sourceTarget != null && sourceTarget.hopperComponent != null) {
            sourceTarget.hopperComponent.setTransferCooldown(TRANSFER_COOLDOWN_TICKS);
        }
        if (target.hopperComponent != null) {
            target.hopperComponent.setTransferCooldown(TRANSFER_COOLDOWN_TICKS);
        }
        return true;
    }

    protected boolean tryInsertOneItem(ItemStack sourceStack, Container target, int[] allowedSlots, Container sourceContainer, int sourceSlot) {
        return insertIntoContainer(sourceStack, target, 1, allowedSlots, sourceContainer, sourceSlot, null) > 0;
    }

    protected TransferTarget getTargetContainer(Position3ic targetPos) {
        var dimension = position.dimension();
        if (dimension == null) {
            return null;
        }

        var blockEntity = dimension.getBlockEntity(targetPos);
        if (!(blockEntity instanceof BlockEntityContainerHolderComponent holder)) {
            return null;
        }

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

        return new TransferTarget(holder.getContainer(), blockEntity instanceof BlockEntityHopperBaseComponent hopperComponent ? hopperComponent : null);
    }

    protected Position3ic getFacingPos() {
        var dimension = position.dimension();
        if (dimension == null) {
            return position;
        }

        var facing = getFacing();
        return new Position3i(facing.offsetPos(position), dimension);
    }

    protected BlockFace getFacing() {
        var dimension = position.dimension();
        if (dimension == null) {
            return BlockFace.DOWN;
        }

        var blockState = dimension.getBlockState(position);
        var facingIndex = blockState.getPropertyValue(BlockPropertyTypes.FACING_DIRECTION);
        var facing = BlockFace.fromIndex(facingIndex);
        return facing == null ? BlockFace.DOWN : facing;
    }

    protected boolean isDisabled() {
        var dimension = position.dimension();
        if (dimension == null) {
            return true;
        }

        var blockState = dimension.getBlockState(position);
        return blockState.getPropertyValue(BlockPropertyTypes.TOGGLE_BIT);
    }

    protected AABBdc getPickupAABB() {
        var x = position.x();
        var y = position.y();
        var z = position.z();
        return new AABBd(x, y, z, x + 1, y + 2, z + 1);
    }

    protected int insertIntoContainer(ItemStack sourceStack, Container target, int maxMove) {
        return insertIntoContainer(sourceStack, target, maxMove, null, null, -1, null);
    }

    protected int insertIntoContainer(ItemStack sourceStack, Container target, int maxMove, int[] allowedSlots,
                                      Container sourceContainer, int sourceSlot, EntityItem pickupEntity) {
        if (sourceStack == null || sourceStack == ItemAirStack.AIR_STACK || maxMove <= 0) {
            return 0;
        }

        int remaining = Math.min(sourceStack.getCount(), maxMove);
        int moved = 0;
        int maxStackSize = sourceStack.getItemType().getItemData().maxStackSize();

        if (allowedSlots == null) {
            var size = target.getItemStackArray().length;
            for (int slot = 0; slot < size; slot++) {
                if (remaining <= 0) {
                    break;
                }
                moved += tryMoveToSlot(target, sourceStack, slot, maxStackSize, remaining, sourceContainer, sourceSlot, pickupEntity);
                remaining = Math.min(sourceStack.getCount(), maxMove) - moved;
            }
        } else {
            for (int slot : allowedSlots) {
                if (remaining <= 0) {
                    break;
                }
                moved += tryMoveToSlot(target, sourceStack, slot, maxStackSize, remaining, sourceContainer, sourceSlot, pickupEntity);
                remaining = Math.min(sourceStack.getCount(), maxMove) - moved;
            }
        }

        if (moved > 0) {
            sourceStack.reduceCount(moved);
        }
        return moved;
    }

    protected int tryMoveToSlot(Container target, ItemStack sourceStack, int slot, int maxStackSize, int remaining,
                                Container sourceContainer, int sourceSlot, EntityItem pickupEntity) {
        var targetStack = target.getItemStack(slot);
        if (targetStack == ItemAirStack.AIR_STACK) {
            int move = Math.min(remaining, maxStackSize);
            if (!isMoveAllowed(sourceContainer, sourceSlot, pickupEntity, target, slot, sourceStack, move)) {
                return 0;
            }
            var newStack = sourceStack.copy();
            newStack.setCount(move);
            target.setItemStack(slot, newStack);
            return move;
        }

        if (targetStack.canMerge(sourceStack, true) && !targetStack.isFull()) {
            int targetMax = targetStack.getItemType().getItemData().maxStackSize();
            int space = targetMax - targetStack.getCount();
            if (space <= 0) {
                return 0;
            }
            int move = Math.min(remaining, space);
            if (!isMoveAllowed(sourceContainer, sourceSlot, pickupEntity, target, slot, sourceStack, move)) {
                return 0;
            }
            targetStack.increaseCount(move);
            target.notifySlotChange(slot);
            return move;
        }

        return 0;
    }

    protected boolean isMoveAllowed(Container sourceContainer, int sourceSlot, EntityItem pickupEntity,
                                    Container target, int targetSlot, ItemStack sourceStack, int move) {
        if (move <= 0) {
            return false;
        }
        if (sourceContainer != null) {
            var event = new ContainerItemMoveEvent(
                    sourceContainer, sourceSlot,
                    target, targetSlot,
                    sourceStack.getItemType(), move
            );
            return event.call();
        }
        if (pickupEntity != null) {
            var event = new ContainerItemPickupEvent(
                    pickupEntity,
                    target, targetSlot,
                    sourceStack.getItemType(), move
            );
            return event.call();
        }
        return true;
    }

    protected boolean isContainerFull(Container container) {
        for (var stack : container.getItemStackArray()) {
            if (stack == ItemAirStack.AIR_STACK || !stack.isFull()) {
                return false;
            }
        }
        return true;
    }

    protected record TransferTarget(Container container, BlockEntityHopperBaseComponent hopperComponent) {
    }
}
