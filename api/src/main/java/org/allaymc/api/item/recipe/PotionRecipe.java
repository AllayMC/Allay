package org.allaymc.api.item.recipe;

import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.input.PotionRecipeInput;
import org.allaymc.api.item.recipe.input.RecipeInput;
import org.allaymc.api.utils.identifier.Identifier;

/**
 * Represents a potion recipe.
 *
 * @author IWareQ | daoge_cmd
 */
@Getter
public class PotionRecipe extends Recipe {
    protected final ItemStack ingredient;
    protected final ItemStack reagent;

    /**
     * Constructs a new PotionRecipe with the specified ingredient, output, and reagent.
     *
     * @param ingredient the primary ingredient required for the potion recipe
     * @param output     the resulting potion after crafting the recipe
     * @param reagent    the secondary reagent used alongside the ingredient for the recipe
     */
    public PotionRecipe(ItemStack ingredient, ItemStack output, ItemStack reagent) {
        super(buildIdentifier(ingredient, reagent), new ItemStack[]{output}, 0);
        this.ingredient = ingredient;
        this.reagent = reagent;
    }

    public static Identifier buildIdentifier(ItemStack ingredient, ItemStack reagent) {
        return new Identifier("potion." + index(ingredient) + "_" + index(reagent));
    }

    private static String index(ItemStack item) {
        return item.getItemType().getIdentifier().toString().replace(Identifier.NAMESPACE_SEPARATOR, "_") + "_" + item.getMeta();
    }

    @Override
    public boolean match(RecipeInput input) {
        if (input instanceof PotionRecipeInput(ItemStack ingredient1, ItemStack reagent1)) {
            return ingredient.getItemType() == ingredient1.getItemType() && ingredient.getMeta() == ingredient1.getMeta() &&
                   reagent.getItemType() == reagent1.getItemType() && reagent.getMeta() == reagent1.getMeta();
        }

        return false;
    }
}
