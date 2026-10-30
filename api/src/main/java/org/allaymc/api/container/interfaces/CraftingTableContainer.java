package org.allaymc.api.container.interfaces;

/**
 * {@code CraftingTableContainer} combines a block location with the recipe-input
 * contract for the nine-slot crafting table grid. Container operations manage the input
 * stacks, and recipe processing obtains a matching representation through {@link
 * RecipeContainer#createRecipeInput()}.
 *
 * @author daoge_cmd
 */
public interface CraftingTableContainer extends BlockContainer, RecipeContainer {
}
