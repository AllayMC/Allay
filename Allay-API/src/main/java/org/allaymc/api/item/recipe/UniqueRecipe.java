package org.allaymc.api.item.recipe;

import java.util.UUID;

/**
 * @author daoge_cmd
 */
public interface UniqueRecipe extends Recipe {
    /**
     * @return The UUID of the recipe
     */
    UUID getUuid();
}
