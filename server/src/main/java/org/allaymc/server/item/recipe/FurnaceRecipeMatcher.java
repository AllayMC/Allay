package org.allaymc.server.item.recipe;

import lombok.experimental.UtilityClass;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.FurnaceRecipe;
import org.allaymc.api.item.recipe.descriptor.ItemTagDescriptor;
import org.allaymc.api.item.recipe.input.FurnaceRecipeInput;
import org.allaymc.api.registry.Registries;

import java.util.Comparator;

/**
 * Matches furnace recipes stored under concrete item or item tag identifiers.
 */
@UtilityClass
public final class FurnaceRecipeMatcher {

    /**
     * Finds a furnace recipe for the supplied ingredient and furnace type. Recipes using a
     * concrete item descriptor are preferred over recipes using an item tag descriptor.
     *
     * @param ingredient                the item being processed
     * @param type                      the furnace type processing the item
     * @param allowNormalFurnaceFallback whether a normal furnace recipe may be used when no
     *                                   recipe exists for {@code type}
     * @return the matching recipe, or {@code null} when no recipe matches
     */
    public static FurnaceRecipe match(ItemStack ingredient, FurnaceRecipe.Type type, boolean allowNormalFurnaceFallback) {
        var input = new FurnaceRecipeInput(ingredient, type);
        var recipe = matchType(ingredient, input, type);
        if (recipe != null) {
            return recipe;
        }

        if (allowNormalFurnaceFallback && type != FurnaceRecipe.Type.FURNACE) {
            return matchType(ingredient, input, FurnaceRecipe.Type.FURNACE);
        }

        return null;
    }

    /**
     * Finds a recipe registered for one exact recipe type, first by concrete item and then by
     * the tags attached to the item. When multiple tag recipes match, recipe priority and
     * identifier are used to select a deterministic result.
     *
     * @param ingredient the item being processed
     * @param input      the complete furnace input used to validate candidate recipes
     * @param recipeType the exact recipe type to search
     * @return the matching recipe, or {@code null} when no recipe matches
     */
    private static FurnaceRecipe matchType(
            ItemStack ingredient,
            FurnaceRecipeInput input,
            FurnaceRecipe.Type recipeType
    ) {
        var directRecipe = Registries.RECIPES.get(FurnaceRecipe.buildIdentifier(ingredient, recipeType));
        if (directRecipe instanceof FurnaceRecipe furnaceRecipe && furnaceRecipe.match(input)) {
            return furnaceRecipe;
        }

        return ingredient.getItemType().getItemTags().stream()
                .map(ItemTagDescriptor::new)
                .map(descriptor -> Registries.RECIPES.get(FurnaceRecipe.buildIdentifier(descriptor, recipeType)))
                .filter(FurnaceRecipe.class::isInstance)
                .map(FurnaceRecipe.class::cast)
                .filter(recipe -> recipe.match(input))
                .min(Comparator.comparingInt(FurnaceRecipe::getPriority)
                        .thenComparing(recipe -> recipe.getIdentifier().toString()))
                .orElse(null);
    }
}
