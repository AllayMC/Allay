package org.allaymc.api.item.recipe;

import org.allaymc.api.item.recipe.input.Input;

import java.util.UUID;

/**
 * Allay Project 2023/11/26
 *
 * @author daoge_cmd
 */
public interface UniqueRecipe<INPUT extends Input> extends Recipe<INPUT> {

    /**
     * @return 配方的UUID
     */
    UUID getUUID();
}
