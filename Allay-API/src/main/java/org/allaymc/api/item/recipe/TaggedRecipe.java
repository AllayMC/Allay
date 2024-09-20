package org.allaymc.api.item.recipe;

/**
 * Represents a tagged recipe.
 *
 * @author daoge_cmd
 */
public interface TaggedRecipe extends Recipe {
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
