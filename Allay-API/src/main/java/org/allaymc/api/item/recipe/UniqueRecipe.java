package org.allaymc.api.item.recipe;

import java.util.UUID;

/**
 * Allay Project 2023/11/26
 *
 * @author daoge_cmd
 */
public interface UniqueRecipe extends Recipe {
    /**
     * @return The UUID of the recipe
     */
    UUID getUuid();
}
