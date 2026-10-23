package org.allaymc.api.blockentity.component;

import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.FurnaceRecipe;

/**
 * {@code BlockEntityCampfireBaseComponent} exposes four cooking slots,
 * remaining cooking times, and a recipe-category selection. The server retains
 * items until cooking completes or an invalid recipe causes a drop, and the
 * air-stack sentinel represents an unoccupied slot.
 *
 * @author daoge_cmd
 */
public interface BlockEntityCampfireBaseComponent extends BlockEntityBaseComponent {
    /**
     * {@code COOK_TIME} supplies the remaining game ticks assigned when the
     * server starts a new campfire recipe for a retained ingredient.
     */
    int COOK_TIME = 600;

    /**
     * Returns the recipe category used to match this campfire's ingredients.
     */
    FurnaceRecipe.Type getFurnaceRecipeType();

    /**
     * Returns a retained slot stack, using the air-stack sentinel when empty.
     * The server also returns that sentinel for indices outside zero through
     * three; valid results share the stored mutable item.
     */
    ItemStack getItemStack(int slot);

    /**
     * Assigns a slot stack and resets its remaining cooking time and cached
     * recipe. The server ignores invalid indices and treats {@code null} as
     * empty; accepted assignments update viewers and comparator output without
     * consuming the supplied stack.
     */
    void setItemStack(int slot, ItemStack item);

    /**
     * Returns the retained remaining cook time in game ticks for a slot. The
     * server returns zero for indices outside zero through three, while valid
     * slots expose their stored counter directly.
     */
    int getCookTime(int slot);

    /**
     * Replaces a slot's retained remaining cooking ticks without updating
     * viewers. The server ignores invalid indices and does not clamp the
     * supplied counter; recipe discovery during ticking can reset it again.
     */
    void setCookTime(int slot, int cookTime);

    /**
     * Returns whether an empty slot and a matching campfire recipe are
     * available. The server rejects {@code null} and the air-stack sentinel;
     * this query does not reserve a slot or consume the supplied item.
     */
    boolean canAcceptItemStack(ItemStack item);

    /**
     * Copies one item into the first available slot when a recipe matches. The
     * server leaves the supplied stack unchanged, so callers perform any
     * inventory consumption separately after a successful result.
     */
    boolean tryAddItemStack(ItemStack item);
}
