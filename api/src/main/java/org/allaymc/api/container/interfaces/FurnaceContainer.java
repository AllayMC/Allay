package org.allaymc.api.container.interfaces;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;

/**
 * {@code FurnaceContainer} exposes ingredient, fuel, and result slots together with the
 * directional access rules used by furnace automation. Named accessors operate on the
 * same stored stacks as ordinary container access, and their setters follow the standard
 * viewer and listener notification path. The container describes access candidates;
 * transfer code still decides whether an item fits.
 *
 * <p>
 * Insertion from above targets the ingredient slot, insertion from below is denied, and
 * lateral insertion requires positive furnace burn duration. Extraction is limited to
 * the result slot from below, so automation cannot remove ingredients or fuel through
 * these access rules.
 *
 * @author daoge_cmd
 */
public interface FurnaceContainer extends BlockContainer, SidedContainer {
    /**
     * The furnace slot constants identify processing positions for named accessors and
     * directional automation. Smelting ingredient slot index.
     */
    int INGREDIENT_SLOT = 0;

    /**
     * Furnace fuel slot index.
     */
    int FUEL_SLOT = 1;

    /**
     * Smelting result slot index.
     */
    int RESULT_SLOT = 2;

    /**
     * Returns the stack stored in the furnace ingredient slot. The returned stack is
     * shared with the container; callers must notify the slot after directly changing
     * its contents.
     */
    default ItemStack getIngredient() {
        return getItemStack(INGREDIENT_SLOT);
    }

    /**
     * Replaces the furnace ingredient slot and notifies container observers. {@code
     * itemStack} must be non-{@code null}; the supplied stack becomes the stored value
     * rather than an independent copy.
     */
    default void setIngredient(ItemStack itemStack) {
        setItemStack(INGREDIENT_SLOT, itemStack);
    }

    /**
     * Returns the stack stored in the furnace fuel slot. The returned stack is shared
     * with the container; callers must notify the slot after directly changing its
     * contents.
     */
    default ItemStack getFuel() {
        return getItemStack(FUEL_SLOT);
    }

    /**
     * Replaces the furnace fuel slot and notifies container observers. {@code itemStack}
     * must be non-{@code null}; the supplied stack becomes the stored value rather than
     * an independent copy.
     */
    default void setFuel(ItemStack itemStack) {
        setItemStack(FUEL_SLOT, itemStack);
    }

    /**
     * Returns the stack stored in the furnace result slot. The returned stack is shared
     * with the container; callers must notify the slot after directly changing its
     * contents.
     */
    default ItemStack getResult() {
        return getItemStack(RESULT_SLOT);
    }

    /**
     * Replaces the furnace result slot and notifies container observers. {@code
     * itemStack} must be non-{@code null}; the supplied stack becomes the stored value
     * rather than an independent copy.
     */
    default void setResult(ItemStack itemStack) {
        setItemStack(RESULT_SLOT, itemStack);
    }

    /**
     * Selects furnace insertion slots by entry face and fuel eligibility. {@code UP}
     * always targets {@code INGREDIENT_SLOT}, while {@code DOWN} returns an empty array
     * regardless of the item. Other faces target {@code FUEL_SLOT} only when {@code
     * isFuel(stack)} accepts the stack.
     *
     * <p>
     * The returned candidates describe automation access rather than recipe validation
     * or available capacity. This ordering allows ordinary ingredient insertion from
     * above without requiring the stack to qualify as fuel.
     */
    @Override
    default int[] getAllowedInsertSlots(BlockFace side, ItemStack stack) {
        if (side == BlockFace.UP) {
            return new int[]{INGREDIENT_SLOT};
        }
        if (side == BlockFace.DOWN) {
            return new int[0];
        }
        return isFuel(stack) ? new int[]{FUEL_SLOT} : new int[0];
    }

    /**
     * Returns the result slot for extraction from below. Every other face returns an
     * empty array, so the automation consumer cannot extract ingredients or fuel through
     * this interface.
     */
    @Override
    default int[] getAllowedExtractSlots(BlockFace side) {
        if (side == BlockFace.DOWN) {
            return new int[]{RESULT_SLOT};
        }
        return new int[0];
    }

    /**
     * Returns whether the stack has a positive furnace burn duration. {@code null} and
     * the shared air stack produce {@code false}, allowing directional checks to reject
     * absent fuel without reading item metadata.
     */
    private static boolean isFuel(ItemStack stack) {
        if (stack == null || stack == ItemAirStack.AIR_STACK) {
            return false;
        }
        return stack.getItemType().getItemData().furnaceBurnDuration() > 0;
    }
}
