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
 * Dropper behavior that transfers items to adjacent containers or drops them.
 *
 * @author daoge_cmd
 */
public class DropperDispenseBehavior implements DispenseBehavior {

    public static final DropperDispenseBehavior INSTANCE = new DropperDispenseBehavior();

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
                // No sound when transferring to container (vanilla behavior)
                return DispenseResult.success();
            }
        }

        dropItem(block, face, item);
        spawnDispenseEffects(block, face);
        return DispenseResult.success();
    }

    protected void spawnDispenseEffects(Block block, BlockFace face) {
        var dimension = block.getDimension();
        var center = MathUtils.center(block.getPosition());
        dimension.addSound(center, SimpleSound.BLOCK_CLICK);
        dimension.addParticle(center, new ShootParticle(face));
    }

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

    protected int[] createAllSlots(int size) {
        var slots = new int[size];
        for (int i = 0; i < size; i++) {
            slots[i] = i;
        }
        return slots;
    }

    protected void dropItem(Block block, BlockFace face, ItemStack item) {
        var dimension = block.getDimension();
        var dispensePos = getDispensePosition(block, face);

        var random = ThreadLocalRandom.current();
        var offset = random.nextDouble() * 0.1 + 0.2;

        var motion = new Vector3d();
        motion.x = face.getOffset().x() * offset;
        motion.y = 0.2;
        motion.z = face.getOffset().z() * offset;

        motion.x += random.nextGaussian() * 0.0075 * 6;
        motion.y += random.nextGaussian() * 0.0075 * 6;
        motion.z += random.nextGaussian() * 0.0075 * 6;

        var dropItem = item.copy();
        dropItem.setCount(1);
        dimension.dropItem(dropItem, dispensePos, motion);
    }

    protected Vector3d getDispensePosition(Block block, BlockFace face) {
        var pos = MathUtils.center(block.getPosition());
        pos.x += face.getOffset().x() * 0.7;
        pos.y += face.getOffset().y() * 0.7;
        pos.z += face.getOffset().z() * 0.7;

        if (face.getAxis() == BlockFace.Axis.Y) {
            pos.y -= 0.125;
        } else {
            pos.y -= 0.15625;
        }

        return pos;
    }
}
