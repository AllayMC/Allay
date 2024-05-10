package org.allaymc.api.item.recipe;

import lombok.Builder;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.descriptor.ItemDescriptor;
import org.allaymc.api.item.recipe.input.CraftingInput;
import org.allaymc.api.item.recipe.input.Input;
import org.allaymc.api.utils.Identifier;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.CraftingDataType;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.RecipeData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.ShapedRecipeData;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.allaymc.api.item.type.ItemTypes.AIR_TYPE;

/**
 * Allay Project 2023/11/25
 *
 * @author daoge_cmd
 */
public class ShapedRecipe extends CraftingRecipe {

    public static final char EMPTY_KEY_CHAR = ' ';
    protected static final ItemStack[][] EMPTY_ITEM_STACK_ARRAY = new ItemStack[0][0];
    protected char[][] pattern;
    protected Map<Character, ItemDescriptor> keys;

    @Builder
    protected ShapedRecipe(Map<Character, ItemDescriptor> keys, char[][] pattern, Identifier identifier, ItemStack[] outputs, String tag, UUID uuid, int priority) {
        super(identifier, outputs, tag, uuid, priority);
        this.keys = keys;
        this.pattern = pattern;
        this.networkRecipeDataCache = buildNetworkRecipeData();
    }

    @Override
    public CraftingDataType getType() {
        return CraftingDataType.SHAPED;
    }

    protected RecipeData buildNetworkRecipeData() {
        return ShapedRecipeData.of(
                getType(), identifier.toString(),
                pattern[0].length, pattern.length,
                buildNetworkIngredients(), buildNetworkOutputs(), uuid,
                tag, priority, networkId
        );
    }

    protected List<ItemDescriptorWithCount> buildNetworkIngredients() {
        List<ItemDescriptorWithCount> ingredients = new ArrayList<>();
        for (var sub : pattern) {
            for (var k : sub) {
                if (k == EMPTY_KEY_CHAR) {
                    ingredients.add(ItemDescriptorWithCount.EMPTY);
                    continue;
                }
                var descriptor = keys.get(k);
                ingredients.add(new ItemDescriptorWithCount(descriptor.toNetwork(), 1));
            }
        }
        return ingredients;
    }

    @Override
    public boolean match(Input input) {
        ItemStack[][] inputs;
        if (input instanceof CraftingInput craftingInput) {
            inputs = removeUselessRowAndColumn(craftingInput.getItems());
        } else return false;

        // Check size
        if (inputs.length == 0) {
            // Empty input not allowed
            return false;
        }
        if (inputs.length > pattern.length) {
            return false;
        }
        if (inputs[0].length > pattern[0].length) {
            return false;
        }

        for (int i = 0, patternLength = pattern.length; i < patternLength; i++) {
            var row = pattern[i];
            for (int j = 0, rowLength = row.length; j < rowLength; j++) {
                var key = row[j];
                var item = inputs[i][j];
                if (key == EMPTY_KEY_CHAR) {
                    if (!item.getItemType().equals(AIR_TYPE)) {
                        return false;
                    }
                } else {
                    var descriptor = keys.get(key);
                    if (!descriptor.match(item)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Remove useless row and column
     *
     * @param inputs [o, o, x]    [x, o, o]
     *               [o, o, x] or [x, o, o] or etc...
     *               [x, x, x]    [x, x, x]
     *
     * @return [o, o]
     * [o, o]
     */
    protected ItemStack[][] removeUselessRowAndColumn(ItemStack[][] inputs) {
        int startRow = 0, endRow = inputs.length - 1;
        for (int row = 0; row < inputs.length; row++) {
            if (notAllEmptyRow(inputs[row])) {
                startRow = row;
                break;
            }
            // 发现全部都是空气，直接返回空数组
            if (row == inputs.length - 1) return EMPTY_ITEM_STACK_ARRAY;
        }
        for (int row = inputs.length - 1; row >= 0; row--) {
            if (notAllEmptyRow(inputs[row])) {
                endRow = row;
                break;
            }
        }
        int startColumn = 0, endColumn = inputs[0].length - 1;
        for (int column = 0; column < inputs[0].length; column++) {
            if (notAllEmptyColumn(inputs, column)) {
                startColumn = column;
                break;
            }
        }
        for (int column = inputs[0].length - 1; column >= 0; column--) {
            if (notAllEmptyColumn(inputs, column)) {
                endColumn = column;
                break;
            }
        }

        if (startRow == 0 && endRow == inputs.length - 1 && startColumn == 0 && endColumn == inputs[0].length - 1) {
            return inputs;
        }

        ItemStack[][] result = new ItemStack[endRow - startRow + 1][endColumn - startColumn + 1];
        for (int row = startRow; row <= endRow; row++) {
            if (endColumn + 1 - startColumn >= 0)
                System.arraycopy(inputs[row], startColumn, result[row - startRow], 0, endColumn + 1 - startColumn);
        }

        return result;
    }

    protected boolean notAllEmptyRow(ItemStack[] inputs) {
        for (var item : inputs) {
            if (item.getItemType() != AIR_TYPE) {
                return true;
            }
        }
        return false;
    }

    protected boolean notAllEmptyColumn(ItemStack[][] inputs, int column) {
        for (var row : inputs) {
            if (row[column].getItemType() != AIR_TYPE) {
                return true;
            }
        }
        return false;
    }

    public char[][] getPattern() {
        return pattern;
    }

    public Map<Character, ItemDescriptor> getKeys() {
        return keys;
    }

    public interface PatternHelper {
        static char[][] build(char a, char b, char c,
                              char d, char e, char f,
                              char g, char h, char i) {
            return new char[][]{
                    {a, b, c},
                    {d, e, f},
                    {g, h, i}
            };
        }

        static char[][] build(char a, char b,
                              char c, char d) {
            return new char[][]{
                    {a, b},
                    {c, d}
            };
        }

        static char[][] build(char a) {
            return new char[][]{
                    {a}
            };
        }

        static char[][] build(String l1, String l2, String l3) {
            return new char[][]{
                    l1.toCharArray(),
                    l2.toCharArray(),
                    l3.toCharArray()
            };
        }

        static char[][] build(String l1, String l2) {
            return new char[][]{
                    l1.toCharArray(),
                    l2.toCharArray()
            };
        }

        static char[][] build(List<String> stringList) {
            var commonLength = stringList.get(0).length();
            char[][] pattern = new char[stringList.size()][commonLength];
            for (int row = 0; row < stringList.size(); row++) {
                var line = stringList.get(row);
                if (line.length() != commonLength)
                    throw new IllegalArgumentException("All row in pattern must have the same length");
                var charArray = line.toCharArray();
                System.arraycopy(charArray, 0, pattern[row], 0, charArray.length);
            }
            return pattern;
        }
    }
}
