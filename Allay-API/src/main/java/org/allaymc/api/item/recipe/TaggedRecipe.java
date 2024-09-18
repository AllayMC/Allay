package org.allaymc.api.item.recipe;

/**
 * @author daoge_cmd
 */
public interface TaggedRecipe extends Recipe {
    /**
     * @return The tag indicating the crafting type applicable to this recipe.
     * For example, a crafting table recipe should have the "crafting_table" tag.
     */
    String getTag();
}
