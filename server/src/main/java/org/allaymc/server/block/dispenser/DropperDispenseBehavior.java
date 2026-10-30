package org.allaymc.server.block.dispenser;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dispenser.DispenseBehavior;
import org.allaymc.api.block.dispenser.DispenseResult;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.interfaces.SidedContainer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.particle.ShootParticle;
import org.allaymc.api.world.sound.SimpleSound;
import org.joml.Vector3d;

import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code DropperDispenseBehavior} transfers one selected item into an adjacent
 * container or ejects it when insertion fails. Sided containers supply the
 * admissible slot order for the face receiving the item; ordinary containers use
 * every slot in ascending order.
 *
 * <p>
 * An empty target slot retains the single-item copy, while a merge increments the
 * target stack without changing the copied input. The calling block entity
 * consumes its original only after the returned success result, so the transfer
 * spans two separately applied inventory updates. Insertion plays no dispenser
 * effects; rejected insertion uses the ordinary one-item ejection sound, particle,
 * and randomized motion.
 *
 * @author daoge_cmd
 */
public class DropperDispenseBehavior implements DispenseBehavior {

    /**
     * {@code INSTANCE} is the shared dropper transfer behavior. Dropper block
     * entities select it regardless of the selected item's dispenser registry
     * entry.
     */
    public static final DropperDispenseBehavior INSTANCE = new DropperDispenseBehavior();

    /**
     * Offers one copied item to the adjacent container before falling back to item
     * ejection. Container insertion uses the face opposite the dropper output and
     * returns success without dispense effects when a slot accepts the item. The
     * target changes before the caller later consumes its source item, and there
     * is no cross-container rollback if that caller fails afterward. A rejected
     * insertion ejects a fresh single-item copy instead.
     */
    @Override
    public DispenseResult dispense(Block block, BlockFace face, ItemStack item) {
        var targetPos = block.offsetPos(face).getPosition();
        var dimension = block.getDimension();
        var targetBlockEntity = dimension.getBlockEntity(targetPos);

        if (targetBlockEntity instanceof BlockEntityContainerHolderComponent containerHolder) {
            var container = containerHolder.getContainer();
            var itemToInsert = item.copy();
            itemToInsert.setCount(1);

            if (tryInsertItem(container, face.opposite(), itemToInsert)) {
                return DispenseResult.success();
            }
        }

        // Rejected or unavailable container insertion leaves the source unconsumed
        // until this fallback ejects one item and the caller processes the success
        // result.
        dropItem(block, face, item);
        spawnDispenseEffects(block, face);
        return DispenseResult.success();
    }

    /**
     * Emits the dispenser click sound and directional shoot particle.
     */
    protected void spawnDispenseEffects(Block block, BlockFace face) {
        var dimension = block.getDimension();
        var center = MathUtils.center(block.getPosition());
        dimension.addSound(center, SimpleSound.BLOCK_CLICK);
        dimension.addParticle(center, new ShootParticle(face));
    }

    /**
     * Inserts one item into the first compatible allowed slot and returns whether
     * it succeeded. A sided container supplies its insertion slot order; other
     * containers use ascending slot indices. Slots containing {@code null} or
     * {@code ItemAirStack.AIR_STACK} retain the supplied item reference, while
     * merge slots increase their existing count by one and notify viewers. The
     * input count is retained, so callers use this helper for a single selected
     * item and consume the source separately.
     */
    protected boolean tryInsertItem(Container container, BlockFace insertFace, ItemStack item) {
        int[] allowedSlots;
        if (container instanceof SidedContainer sidedContainer) {
            allowedSlots = sidedContainer.getAllowedInsertSlots(insertFace, item);
            if (allowedSlots.length == 0) {
                return false;
            }
        } else {
            allowedSlots = null;
        }

        var stacks = container.getItemStackArray();
        var slotsToCheck = allowedSlots != null ? allowedSlots : createAllSlots(stacks.length);

        for (int slot : slotsToCheck) {
            var targetStack = stacks[slot];

            // Use the first permitted empty or mergeable slot; the caller supplied
            // one selected item and will consume the original after this target
            // update succeeds.
            if (targetStack == ItemAirStack.AIR_STACK || targetStack == null) {
                container.setItemStack(slot, item);
                return true;
            }

            if (targetStack.canMerge(item, true) && !targetStack.isFull()) {
                var maxStackSize = targetStack.getItemType().getItemData().maxStackSize();
                var space = maxStackSize - targetStack.getCount();
                if (space > 0) {
                    targetStack.increaseCount(1);
                    container.notifySlotChange(slot);
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Returns a new array containing every slot index in ascending order.
     */
    protected int[] createAllSlots(int size) {
        var slots = new int[size];
        for (int i = 0; i < size; i++) {
            slots[i] = i;
        }
        return slots;
    }

    /**
     * Drops a single copied item at the dispenser output with randomized motion.
     * The caller retains the original stack and performs inventory consumption
     * after this behavior returns its success result.
     */
    protected void dropItem(Block block, BlockFace face, ItemStack item) {
        var dimension = block.getDimension();
        var dispensePos = getDispensePosition(block, face);

        var random = ThreadLocalRandom.current();
        // Select an outward horizontal speed between 0.2 and 0.3 before spread.
        var offset = random.nextDouble() * 0.1 + 0.2;

        var motion = new Vector3d();
        motion.x = face.getOffset().x() * offset;
        motion.y = 0.2;
        motion.z = face.getOffset().z() * offset;

        // Independent Gaussian spread perturbs all axes after the outward motion
        // is set, so ejected items do not share one exact flight direction.
        motion.x += random.nextGaussian() * 0.0075 * 6;
        motion.y += random.nextGaussian() * 0.0075 * 6;
        motion.z += random.nextGaussian() * 0.0075 * 6;

        var dropItem = item.copy();
        dropItem.setCount(1);
        dimension.dropItem(dropItem, dispensePos, motion);
    }

    /**
     * Returns a new face-offset output position for the dropped item. The point
     * moves beyond the dispenser boundary and uses a smaller downward adjustment
     * for vertical output faces than horizontal faces.
     */
    protected Vector3d getDispensePosition(Block block, BlockFace face) {
        var pos = MathUtils.center(block.getPosition());
        // A 0.7-block face offset places the origin beyond the block boundary.
        pos.x += face.getOffset().x() * 0.7;
        pos.y += face.getOffset().y() * 0.7;
        pos.z += face.getOffset().z() * 0.7;

        // Keep horizontal ejections below mid-height; vertical faces use a smaller
        // downward adjustment.
        if (face.getAxis() == BlockFace.Axis.Y) {
            pos.y -= 0.125;
        } else {
            pos.y -= 0.15625;
        }

        return pos;
    }
}
