package org.allaymc.api.item.recipe.input;

import org.allaymc.api.item.ItemStack;

import static org.allaymc.api.item.type.ItemTypes.AIR;

/**
 * {@code CraftingRecipeInput} represents a crafting grid as row-major stack
 * references, including air in empty positions. The canonical record
 * constructor retains its array without validation or copying; the one-, four-,
 * and nine-stack convenience constructors allocate grids and validate counts.
 * {@code flattenedItems} creates a new container while preserving every stack
 * reference.
 *
 * @param items shared row-major grid indexed as {@code [row][column]}. The
 * canonical constructor retains {@code items} directly without validating array
 * shape, {@code null} entries, or stack counts.
 *
 * @author daoge_cmd
 */
public record CraftingRecipeInput(ItemStack[][] items) implements RecipeInput {

    /**
     * Creates a three-by-three grid retaining the supplied stacks in row order.
     * Every nonair stack must have count {@code 1}, every air stack must have
     * count {@code 0}, and {@code null} stacks are rejected.
     *
     * @throws IllegalArgumentException if a stack is {@code null} or its count
     * violates the air/nonair rule
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
     * Creates a two-by-two grid retaining the supplied stacks in row order.
     * Every nonair stack must have count {@code 1}, every air stack must have
     * count {@code 0}, and {@code null} stacks are rejected.
     *
     * @throws IllegalArgumentException if a stack is {@code null} or its count
     * violates the air/nonair rule
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
     * Creates a single-position crafting input used by stonecutting recipes.
     * The stack is retained directly and must be non-{@code null}, with count
     * {@code 0} for air or {@code 1} for any other type.
     */
    public CraftingRecipeInput(ItemStack item) {
        this(new ItemStack[][]{{item}});
        checkValid();
    }

    /**
     * Returns a newly allocated array containing every grid entry in row order.
     * Air entries remain present and stacks remain shared; empty grids yield an
     * empty array, and ragged rows are flattened according to their actual
     * lengths.
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

    /**
     * Checks that every stored stack is non-{@code null} and has the required
     * count. Air requires count {@code 0}, other types require {@code 1}, and
     * the first violation throws {@code IllegalArgumentException}.
     */
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

    /**
     * Returns {@code BIG} for exactly three rows and {@code SMALL} otherwise.
     * This classification uses only the outer array length, so arbitrary grids
     * supplied through the canonical constructor are not shape-validated here.
     */
    public Type getType() {
        return items.length == 3 ? Type.BIG : Type.SMALL;
    }

    /**
     * {@code Type} classifies crafting inputs by their outer row count.
     */
    public enum Type {
        /**
         * Exactly three grid rows.
         */
        BIG,
        /**
         * Any other row count.
         */
        SMALL
    }
}
