package org.allaymc.server.item.recipe;

import lombok.experimental.UtilityClass;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.FurnaceRecipe;
import org.allaymc.api.item.recipe.descriptor.ItemTagDescriptor;
import org.allaymc.api.item.recipe.input.FurnaceRecipeInput;
import org.allaymc.api.registry.Registries;

import java.util.Comparator;

/**
 * {@code FurnaceRecipeMatcher} selects registered furnace recipes using
 * concrete item keys and item-tag keys. A search first considers the requested
 * furnace category, then optionally considers ordinary furnace recipes only if
 * that search has no match. Within each category, a matching concrete-item
 * recipe wins before any tag recipe, regardless of priority.
 *
 * <p>
 * Multiple matching tag recipes are ordered by ascending priority and then
 * identifier text, giving a stable choice when an ingredient has several tags.
 * Every candidate is checked against the original {@code FurnaceRecipeInput};
 * the fallback therefore relies on {@code FurnaceRecipe.Type.FURNACE} accepting
 * other furnace categories during recipe matching. No lookup consumes or
 * modifies the ingredient stack.
 */
@UtilityClass
public final class FurnaceRecipeMatcher {

    /**
     * Returns a matching recipe for the requested processing category, or
     * {@code null} when no permitted search succeeds. A matching exact-category
     * recipe wins before any ordinary-furnace fallback, including
     * exact-category tag recipes competing with a fallback concrete recipe.
     *
     * <p>
     * When {@code allowNormalFurnaceFallback} is {@code true} and the requested
     * category has no match, a non-{@code FURNACE} category also searches
     * ordinary furnace recipes. Each category prefers a matching concrete-item
     * recipe, then chooses matching tag recipes by ascending priority and
     * identifier text. The ingredient remains unchanged.
     */
    public static FurnaceRecipe match(ItemStack ingredient, FurnaceRecipe.Type type, boolean allowNormalFurnaceFallback) {
        var input = new FurnaceRecipeInput(ingredient, type);
        var recipe = matchType(ingredient, input, type);
        if (recipe != null) {
            return recipe;
        }

        // The requested category has no usable candidate. Reuse the original input
        // because ordinary furnace recipes explicitly accept inputs from other furnace categories.
        if (allowNormalFurnaceFallback && type != FurnaceRecipe.Type.FURNACE) {
            return matchType(ingredient, input, FurnaceRecipe.Type.FURNACE);
        }

        return null;
    }

    /**
     * Returns a matching recipe registered under one category's generated keys.
     * A usable concrete-item entry wins immediately, so tag recipe priorities
     * never override that entry; candidates must also pass matching against the
     * complete supplied input.
     *
     * <p>
     * If the concrete lookup fails or does not match, the search checks keys
     * for every tag on the ingredient type. Matching tag recipes are ordered by
     * ascending priority and then identifier text, preventing tag iteration
     * order from deciding ties. No matching entry yields {@code null}.
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

        // A missing or rejected concrete entry leaves tag recipes eligible. Compare
        // every matching tag candidate so item-tag iteration order cannot decide the winner.
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
