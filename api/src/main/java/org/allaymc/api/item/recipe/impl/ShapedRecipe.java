package org.allaymc.api.item.recipe.impl;

import lombok.Builder;
import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.descriptor.ItemDescriptor;
import org.allaymc.api.item.recipe.input.CraftingRecipeInput;
import org.allaymc.api.item.recipe.input.RecipeInput;
import org.allaymc.api.utils.Identifier;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.CraftingDataType;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.RecipeData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.ShapedRecipeData;
import org.cloudburstmc.protocol.bedrock.data.inventory.descriptor.ItemDescriptorWithCount;

import java.util.*;

import static org.allaymc.api.item.type.ItemTypes.AIR;

/**
 * Represents a shaped recipe.
 *
 * @author daoge_cmd
 */
@Getter
public class ShapedRecipe extends CraftingRecipe {

    /**
     * The character that represents an empty key in the pattern.
     */
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
    public boolean match(RecipeInput input) {
        if (!(input instanceof CraftingRecipeInput craftingRecipeInput)) return false;

        var inputs = removeUselessRowAndColumn(craftingRecipeInput.getItems());
        // Empty input not allowed
        if (inputs.length == 0) return false;
        if (inputs.length > pattern.length) return false;
        if (inputs[0].length > pattern[0].length) return false;

        for (int i = 0, patternLength = pattern.length; i < patternLength; i++) {
            var row = pattern[i];
            for (int j = 0, rowLength = row.length; j < rowLength; j++) {
                var key = row[j];
                var item = inputs[i][j];
                if (key == EMPTY_KEY_CHAR) {
                    if (!item.getItemType().equals(AIR)) return false;
                } else {
                    var descriptor = keys.get(key);
                    if (!descriptor.match(item)) return false;
                }
            }
        }

        return true;
    }

    // @formatter:off
    /**
     * Remove useless row and column。
     *
     * @param inputs <pre>
     *               {@code
     *               [o, o, x]    [x, o, o]
     *               [o, o, x] or [x, o, o] or etc...
     *               [x, x, x]    [x, x, x]
     *               }
     *               </pre>
     *
     * @return <pre>
     *     {@code
     *     [o, o]
     *     [o, o]
     *     }
     * </pre>
     */
    // @formatter:on
    protected ItemStack[][] removeUselessRowAndColumn(ItemStack[][] inputs) {
        int
                startRow = 0,
                endRow = inputs.length - 1;
        for (int row = 0; row < inputs.length; row++) {
            if (notAllEmptyRow(inputs[row])) {
                startRow = row;
                break;
            }

            // If all are air, return empty array directly
            if (row == inputs.length - 1) return EMPTY_ITEM_STACK_ARRAY;
        }

        for (int row = inputs.length - 1; row >= 0; row--) {
            if (notAllEmptyRow(inputs[row])) {
                endRow = row;
                break;
            }
        }

        int
                startColumn = 0,
                endColumn = inputs[0].length - 1;
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

        if (startRow == 0 && endRow == inputs.length - 1 &&
            startColumn == 0 && endColumn == inputs[0].length - 1
        ) return inputs;

        var result = new ItemStack[endRow - startRow + 1][endColumn - startColumn + 1];
        for (int row = startRow; row <= endRow; row++) {
            if (endColumn + 1 - startColumn >= 0)
                System.arraycopy(inputs[row], startColumn, result[row - startRow], 0, endColumn + 1 - startColumn);
        }

        return result;
    }

    protected boolean notAllEmptyRow(ItemStack[] inputs) {
        return Arrays.stream(inputs).anyMatch(item -> item.getItemType() != AIR);
    }

    protected boolean notAllEmptyColumn(ItemStack[][] inputs, int column) {
        return Arrays.stream(inputs).anyMatch(row -> row[column].getItemType() != AIR);
    }

    public interface PatternHelper {
        static char[][] build(
                char a, char b, char c,
                char d, char e, char f,
                char g, char h, char i
        ) {
            return new char[][]{
                    {a, b, c},
                    {d, e, f},
                    {g, h, i}
            };
        }

        static char[][] build(
                char a, char b,
                char c, char d
        ) {
            return new char[][]{
                    {a, b},
                    {c, d}
            };
        }

        static char[][] build(char a) {
            return new char[][]{{a}};
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
            var commonLength = stringList.getFirst().length();
            var pattern = new char[stringList.size()][commonLength];
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
