package org.allaymc.api.block.dispenser;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;

/**
 * Represents the result of a dispense operation.
 *
 * @author daoge_cmd
 */
public record DispenseResult(
        boolean succeeded,
        ItemStack remainingItem
) {
    private static final DispenseResult SUCCESS = new DispenseResult(true, ItemAirStack.AIR_STACK);
    private static final DispenseResult FAILED = new DispenseResult(false, ItemAirStack.AIR_STACK);

    /**
     * Creates a successful result that consumes the item.
     *
     * @return a successful DispenseResult
     */
    public static DispenseResult success() {
        return SUCCESS;
    }

    /**
     * Creates a successful result with a remaining item (e.g., empty bucket after placing water).
     *
     * @param remainingItem the item to return to the dispenser
     * @return a successful DispenseResult with the remaining item
     */
    public static DispenseResult success(ItemStack remainingItem) {
        return new DispenseResult(true, remainingItem);
    }

    /**
     * Creates a failed result that does not consume the item.
     *
     * @return a failed DispenseResult
     */
    public static DispenseResult fail() {
        return FAILED;
    }

    /**
     * Creates a failed result with a specific item to return.
     *
     * @param remainingItem the item to return to the dispenser
     * @return a failed DispenseResult with the remaining item
     */
    public static DispenseResult fail(ItemStack remainingItem) {
        return new DispenseResult(false, remainingItem);
    }
}
