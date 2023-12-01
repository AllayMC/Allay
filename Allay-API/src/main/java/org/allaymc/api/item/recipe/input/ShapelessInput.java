package org.allaymc.api.item.recipe.input;

import lombok.Getter;
import org.allaymc.api.item.ItemStack;

/**
 * Allay Project 2023/12/1
 *
 * @author daoge_cmd
 */
@Getter
public class ShapelessInput implements Input {
    protected ItemStack[] items;

    public ShapelessInput(ItemStack... items) {
        if (items.length > 9) throw new IllegalArgumentException("Shapeless recipe can't have more than 9 ingredients");
        this.items = items;
    }
}
