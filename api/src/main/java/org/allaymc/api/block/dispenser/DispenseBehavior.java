package org.allaymc.api.block.dispenser;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.item.ItemStack;

/**
 * Represents a behavior for dispensing items from a dispenser.
 * <p>
 * Implementations of this interface define how specific items are dispensed when
 * a dispenser is activated. Each item type can have a unique dispense behavior
 * registered in the dispenser behavior registry.
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface DispenseBehavior {
    /**
     * Executes the dispense behavior for an item.
     *
     * @param block the dispenser block
     * @param face  the direction the dispenser is facing
     * @param item  a copy of the item to dispense with count set to 1
     * @return the result of the dispense operation
     */
    DispenseResult dispense(Block block, BlockFace face, ItemStack item);
}
