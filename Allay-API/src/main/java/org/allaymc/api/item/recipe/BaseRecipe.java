package org.allaymc.api.item.recipe;

import lombok.Builder;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.descriptor.ItemDescriptor;
import org.jetbrains.annotations.Nullable;

import static org.allaymc.api.item.interfaces.ItemAirStack.AIR_TYPE;

/**
 * Allay Project 2023/11/25
 *
 * @author daoge_cmd
 */
public abstract class BaseRecipe implements Recipe {
    protected static final ItemStack[][] EMPTY_ITEM_STACK_ARRAY = new ItemStack[0][0];
    protected static ItemDescriptor[] EMPTY_DESCRIPTOR_ARRAY = new ItemDescriptor[0];
    protected Identifier identifier;
    protected String group;
    protected ItemStack output;
    protected String[] tags;
    protected ItemDescriptor[] unlockItems = EMPTY_DESCRIPTOR_ARRAY;

    @Builder
    protected BaseRecipe(Identifier identifier, String group, ItemStack output, String[] tags, ItemDescriptor[] unlockItems) {
        this.identifier = identifier;
        this.group = group;
        this.output = output;
        this.tags = tags;
        this.unlockItems = unlockItems;
    }

    @Override
    public Identifier getIdentifier() {
        return identifier;
    }

    @Override
    public @Nullable String getGroup() {
        return group;
    }

    @Override
    public ItemStack getOutput() {
        return output;
    }

    @Override
    public String[] getTags() {
        return tags;
    }

    @Override
    public ItemDescriptor[] getUnlockItems() {
        return unlockItems;
    }

    /**
     * Remove useless row and column
     * @param inputs [o, o, x]    [x, o, o]
     *               [o, o, x] or [x, o, o] or etc...
     *               [x, x, x]    [x, x, x]
     * @return [o, o]
     *         [o, o]
     */
    protected ItemStack[][] removeUselessRowAndColumn(ItemStack[][] inputs) {
        int startRow = 0, endRow = inputs.length;
        for (int row = 0; row < inputs.length; row++) {
            if (notAllEmpty(inputs[row])) {
                startRow = row;
                break;
            }
        }
        for (int row = inputs.length - 1; row >= 0; row--) {
            if (notAllEmpty(inputs[row])) {
                endRow = row;
                break;
            }
        }
        int startColumn = 0, endColumn = inputs[0].length;
        for (int column = 0; column < inputs[0].length; column++) {
            if (notAllEmpty(inputs[column])) {
                startColumn = column;
                break;
            }
        }
        for (int column = inputs[0].length - 1; column >= 0; column--) {
            if (notAllEmpty(inputs[column])) {
                endColumn = column;
                break;
            }
        }

        if (endRow == 0) {
            return EMPTY_ITEM_STACK_ARRAY;
        }

        if (startRow == 0 && endRow == inputs.length - 1 && startColumn == 0 && endColumn == inputs[0].length - 1) {
            return inputs;
        }

        ItemStack[][] result = new ItemStack[endRow - startRow + 1][endColumn - startColumn + 1];
        for (int row = startRow; row <= endRow; row++) {
            for (int column = startColumn; column <= endColumn; column++) {
                result[row - startRow][column - startColumn] = inputs[row][column];
            }
        }

        return result;
    }

    protected boolean notAllEmpty(ItemStack[] inputs) {
        for (var item : inputs) {
            if (item.getItemType() != AIR_TYPE) {
                return true;
            }
        }
        return false;
    }
}
