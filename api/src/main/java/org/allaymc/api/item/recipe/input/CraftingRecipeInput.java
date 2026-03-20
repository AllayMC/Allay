package org.allaymc.api.item.recipe.input;

import org.allaymc.api.item.ItemStack;

import static org.allaymc.api.item.type.ItemTypes.AIR;

/**
 * Represents the input for a crafting recipe.
 *
 * @param items the input items. The top-left item's index is defined as [0][0], with the coordinate system as [row][column]
 * @author daoge_cmd
 */
public record CraftingRecipeInput(ItemStack[][] items) implements RecipeInput {

    /**
     * Constructs a new 3x3 crafting recipe input with the specified items.
     *
     * @param item00 the item for the top-left position (row 0, column 0)
     * @param item01 the item for the top-center position (row 0, column 1)
     * @param item02 the item for the top-right position (row 0, column 2)
     * @param item10 the item for the middle-left position (row 1, column 0)
     * @param item11 the item for the middle-center position (row 1, column 1)
     * @param item12 the item for the middle-right position (row 1, column 2)
     * @param item20 the item for the bottom-left position (row 2, column 0)
     * @param item21 the item for the bottom-center position (row 2, column 1)
     * @param item22 the item for the bottom-right position (row 2, column 2)
     * @throws IllegalArgumentException if any item is null or if the count of non-air items is not 1
     *                                  or air items have a non-zero count
     */
    public CraftingRecipeInput(
            ItemStack item00, ItemStack item01, ItemStack item02,
            ItemStack item10, ItemStack item11, ItemStack item12,
            ItemStack item20, ItemStack item21, ItemStack item22
    ) {
        this(new ItemStack[][]{
                {item00, item01, item02},
                {item10, item11, item12},
                {item20, item21, item22}
        });
        checkValid();
    }

    /**
     * Constructs a new 2x2 crafting recipe input with the specified items.
     *
     * @param item00 the item for the top-left position (row 0, column 0)
     * @param item01 the item for the top-right position (row 0, column 1)
     * @param item10 the item for the bottom-left position (row 1, column 0)
     * @param item11 the item for the bottom-right position (row 1, column 1)
     * @throws IllegalArgumentException if any item is null or if the count of non-air items is not 1
     *                                  or air items have a non-zero count
     */
    public CraftingRecipeInput(
            ItemStack item00, ItemStack item01,
            ItemStack item10, ItemStack item11
    ) {
        this(new ItemStack[][]{
                {item00, item01},
                {item10, item11}
        });
        checkValid();
    }

    /**
     * Constructs a new recipe input with one item. This is used by stonecutter.
     *
     * @param item the item
     */
    public CraftingRecipeInput(ItemStack item) {
        this(new ItemStack[][]{{item}});
        checkValid();
    }

    /**
     * Flattens the two-dimensional array of ItemStack objects into a single-dimensional array.
     * The size of the resulting array depends on the input grid:
     * - 9 for 3x3 crafting table
     * - 4 for 2x2 crafting grid
     * - 1 for 1x1 inputs (stonecutter)
     *
     * @return a one-dimensional ItemStack array containing all the elements of the two-dimensional array.
     */
    public ItemStack[] flattenedItems() {
        if (items.length == 0) {
            return new ItemStack[0];
        }

        int size = 0;
        for (ItemStack[] itemStacks : items) {
            size += itemStacks.length;
        }

        ItemStack[] result = new ItemStack[size];
        int index = 0;
        for (ItemStack[] itemStacks : items) {
            for (ItemStack itemStack : itemStacks) {
                result[index++] = itemStack;
            }
        }
        return result;
    }

    private void checkValid() {
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
