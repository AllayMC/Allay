package org.allaymc.api.item.recipe;

import org.allaymc.api.item.recipe.input.Input;

/**
 * Allay Project 2023/11/26
 *
 * @author daoge_cmd
 */
public interface TaggedRecipe<INPUT extends Input> extends Recipe<INPUT> {
    /**
     * @return 这个配方所适用的合成类型。例如，工作台配方应具有 "crafting_table" tag
     */
    String getTag();
}
