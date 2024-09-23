package org.allaymc.api.item.recipe;

import java.util.UUID;

/**
 * Represents a unique recipe.
 *
 * @author daoge_cmd
 */
public interface UniqueRecipe extends Recipe {
    /**
     * Get the UUID of the recipe.
     *
     * @return the UUID of the recipe.
     */
    UUID getUuid();
}