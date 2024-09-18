package org.allaymc.api.item.recipe.input;

import lombok.Getter;
import org.allaymc.api.item.ItemStack;

/**
 * @author daoge_cmd
 */
@Getter
public class FurnaceInput implements Input {
    protected ItemStack ingredient;
    protected String tag;

    public FurnaceInput(ItemStack ingredient, String tag) {
        this.ingredient = ingredient;
        this.tag = tag;
    }
}
