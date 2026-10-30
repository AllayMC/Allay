package org.allaymc.api.item.recipe;

import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.input.PotionRecipeInput;
import org.allaymc.api.item.recipe.input.RecipeInput;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * {@code PotionRecipe} describes a brewing transformation selected by
 * ingredient and reagent item types and metadata. Matching ignores stack counts
 * and NBT, while construction retains both reference stacks and the output
 * stack; callers must keep those shared stacks stable while the recipe is in
 * use.
 *
 * @author IWareQ | daoge_cmd
 */
@Getter
public class PotionRecipe extends Recipe {
    /**
     * {@code ingredient} retains the supplied primary stack, and its current
     * type and metadata determine matching after construction.
     */
    protected final ItemStack ingredient;
    /**
     * {@code reagent} retains the supplied secondary stack, and its current
     * type and metadata determine matching after construction.
     */
    protected final ItemStack reagent;

    /**
     * Creates a zero-priority brewing recipe retaining all three supplied
     * stacks. The identifier is computed from ingredient and reagent types and
     * metadata at construction, so later stack mutations can change matching
     * without rebuilding that identifier.
     */
    public PotionRecipe(ItemStack ingredient, ItemStack output, ItemStack reagent) {
        super(buildIdentifier(ingredient, reagent), new ItemStack[]{output}, 0);
        this.ingredient = ingredient;
        this.reagent = reagent;
    }

    /**
     * Builds the brewing identifier from both input types and metadata values.
     * Stack counts, NBT, and the eventual output do not participate, so recipes
     * differing only in those properties share this identifier.
     */
    public static Identifier buildIdentifier(ItemStack ingredient, ItemStack reagent) {
        return new Identifier("potion." + index(ingredient) + "_" + index(reagent));
    }

    /**
     * Combines an item's normalized namespaced identifier and its metadata
     * value.
     */
    private static String index(ItemStack item) {
        // Recipe construction and brewing lookup both use buildIdentifier, so keep
        // the source namespace inside its generated path. Replacing the separator
        // prevents Identifier from treating that item namespace as the recipe namespace.
        return item.getItemType().getIdentifier().toString().replace(Identifier.NAMESPACE_SEPARATOR, "_") + "_" + item.getMeta();
    }

    /**
     * Returns whether a potion input matches both retained item types and
     * metadata values. Other input representations return {@code false}; stack
     * counts and NBT are ignored, and matching leaves both input stacks
     * untouched.
     */
    @Override
    public boolean match(RecipeInput input) {
        if (input instanceof PotionRecipeInput(ItemStack ingredient1, ItemStack reagent1)) {
            return ingredient.getItemType() == ingredient1.getItemType() && ingredient.getMeta() == ingredient1.getMeta() &&
                   reagent.getItemType() == reagent1.getItemType() && reagent.getMeta() == reagent1.getMeta();
        }

        return false;
    }
}
