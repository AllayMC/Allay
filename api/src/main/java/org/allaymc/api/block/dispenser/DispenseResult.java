package org.allaymc.api.block.dispenser;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;

/**
 * {@code DispenseResult} represents a dispense action's success flag and optional
 * replacement item. The built-in dispenser consumes one original item on success
 * and inserts or drops the replacement; a failed result preserves the original and
 * its replacement is ignored. {@code ItemAirStack.AIR_STACK} is the no-replacement
 * sentinel, and supplied replacement stacks remain shared references.
 *
 * @param succeeded whether the action succeeded; the built-in dispenser consumes
 *         one selected item only when this value is {@code true}
 * @param remainingItem replacement item reference; the built-in dispenser examines
 *         this value only for successful results and treats
 *         {@code ItemAirStack.AIR_STACK} as no replacement
 * @author daoge_cmd
 */
public record DispenseResult(
        boolean succeeded,
        ItemStack remainingItem
) {
    // SUCCESS and FAILED share the ItemAirStack.AIR_STACK no-replacement sentinel.
    // success() and fail() return these cached records; their success flags select
    // the dispenser's source-consumption decision.
    /**
     * Cached successful outcome without a replacement item.
     */
    private static final DispenseResult SUCCESS = new DispenseResult(true, ItemAirStack.AIR_STACK);
    /**
     * Cached failed outcome without a replacement item.
     */
    private static final DispenseResult FAILED = new DispenseResult(false, ItemAirStack.AIR_STACK);

    /**
     * Returns the cached successful outcome with no replacement item. The
     * dispenser consumes one original item and performs no replacement insertion
     * when it receives this result.
     */
    public static DispenseResult success() {
        return SUCCESS;
    }

    /**
     * Returns a successful outcome retaining the supplied replacement stack. The
     * built-in dispenser consumes the selected item before handling
     * {@code remainingItem}. Callers supply a usable stack or
     * {@code ItemAirStack.AIR_STACK}; this factory does not validate the reference
     * before the later inventory operation.
     */
    public static DispenseResult success(ItemStack remainingItem) {
        return new DispenseResult(true, remainingItem);
    }

    /**
     * Returns the cached failed outcome with no replacement item. The built-in
     * dispenser leaves its selected item in place and plays its failure sound for
     * this result.
     */
    public static DispenseResult fail() {
        return FAILED;
    }

    /**
     * Returns a failed outcome retaining the supplied replacement stack reference.
     * The built-in dispenser leaves its selected item in place and ignores
     * {@code remainingItem} for failed results.
     */
    public static DispenseResult fail(ItemStack remainingItem) {
        return new DispenseResult(false, remainingItem);
    }
}
