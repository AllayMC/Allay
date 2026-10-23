package org.allaymc.api.item.recipe.input;

/**
 * {@code RecipeInput} marks the input representations accepted by recipe
 * matching. Concrete records distinguish crafting grids, furnace processing,
 * brewing, and smithing slots; each recipe implementation selects its supported
 * representation before inspecting the contained stack references.
 *
 * @author daoge_cmd
 */
public interface RecipeInput {
}
