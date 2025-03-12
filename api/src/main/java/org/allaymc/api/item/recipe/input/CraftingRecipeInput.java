package org.allaymc.api.item.recipe.input;

import lombok.Getter;
import org.allaymc.api.item.ItemStack;

import static org.allaymc.api.item.type.ItemTypes.AIR;

/**
 * Represents a crafting input.
 *
 * @author daoge_cmd
 */
@Getter
public class CraftingRecipeInput implements RecipeInput {
    // We define the top-left item index as [0][0], with the coordinate system as [row][column]
    protected ItemStack[][] items;

    // 3x3
    public CraftingRecipeInput(
            ItemStack item00, ItemStack item01, ItemStack item02,
            ItemStack item10, ItemStack item11, ItemStack item12,
            ItemStack item20, ItemStack item21, ItemStack item22
    ) {
        this.items = new ItemStack[][]{
                {item00, item01, item02},
                {item10, item11, item12},
                {item20, item21, item22}
        };
        checkValid();
    }

    // 2x2
    public CraftingRecipeInput(
            ItemStack item00, ItemStack item01,
            ItemStack item10, ItemStack item11
    ) {
        this.items = new ItemStack[][]{
                {item00, item01},
                {item10, item11}
        };
        checkValid();
    }

    public ItemStack[] getFlattenItems() {
        ItemStack[] result = new ItemStack[getType() == Type.BIG ? 9 : 4];
        int index = 0;
        for (ItemStack[] itemStacks : items) {
            for (ItemStack itemStack : itemStacks) {
                result[index++] = itemStack;
            }
        }
        return result;
    }

    protected void checkValid() {
        for (ItemStack[] itemStacks : items) {
            for (ItemStack itemStack : itemStacks) {
                if (itemStack == null) {
                    throw new IllegalArgumentException("ItemStack cannot be null");
                }
                if (itemStack.getItemType() != AIR) {
                    if (itemStack.getCount() != 1) {
                        throw new IllegalArgumentException("Non-air ItemStack count must be 1");
                    }
                } else {
                    if (itemStack.getCount() != 0) {
                        throw new IllegalArgumentException("Air ItemStack count must be 0");
                    }
                }
            }
        }
    }

    public Type getType() {
        return items.length == 3 ? Type.BIG : Type.SMALL;
    }

    public enum Type {
        BIG,
        SMALL
    }
}
