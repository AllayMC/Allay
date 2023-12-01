package org.allaymc.api.item.recipe;

import org.allaymc.api.identifier.Identified;
import org.allaymc.api.item.recipe.input.Input;

/**
 * Allay Project 2023/11/26
 *
 * 具有标识符的配方
 *
 * @author daoge_cmd
 */
public interface IdentifiedRecipe<INPUT extends Input> extends Recipe<INPUT>, Identified {
}
