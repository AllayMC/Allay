package org.allaymc.api.item.recipe;

import lombok.Getter;
import org.allaymc.api.item.ItemStack;

/**
 * Allay Project 2023/11/25
 *
 * @author daoge_cmd
 */
@Getter
public class ShapedInput implements Input {
    // 我们规定左上角物品索引为[0][0]，坐标轴为[行][列]
    protected ItemStack[][] items;

    public ShapedInput(ItemStack[][] items) {
        this.items = items;
    }

    // 3x3
    public ShapedInput(
            ItemStack item00, ItemStack item01, ItemStack item02,
            ItemStack item10, ItemStack item11, ItemStack item12,
            ItemStack item20, ItemStack item21, ItemStack item22
    ) {
        this.items = new ItemStack[][]{
                {item00, item01, item02},
                {item10, item11, item12},
                {item20, item21, item22}
        };
    }

    // 2x2
    public ShapedInput(
            ItemStack item00, ItemStack item01,
            ItemStack item10, ItemStack item11
    ) {
        this.items = new ItemStack[][]{
                {item00, item01},
                {item10, item11}
        };
    }

    // 1x1
    public ShapedInput(ItemStack item00) {
        this.items = new ItemStack[][]{
                {item00}
        };
    }
}
