package org.allaymc.api.item.recipe;

import org.allaymc.api.item.recipe.impl.BaseRecipe;

/**
 * Represents a tagged recipe.
 *
 * @author daoge_cmd
 */
public interface TaggedRecipe extends BaseRecipe {
    /**
     * Get the tag indicating the crafting type applicable to this recipe.
     * <p>
     * The tag indicating the crafting type applicable to this recipe.
     * For example, a crafting table recipe should have the "crafting_table" tag.
     *
     * @return the tag indicating the crafting type applicable to this recipe.
     */
    String getTag();
}
