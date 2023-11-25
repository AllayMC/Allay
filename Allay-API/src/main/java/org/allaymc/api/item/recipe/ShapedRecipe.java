package org.allaymc.api.item.recipe;

import lombok.Getter;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.descriptor.ItemDescriptor;

import java.util.List;
import java.util.Map;

import static org.allaymc.api.item.interfaces.ItemAirStack.AIR_TYPE;

/**
 * Allay Project 2023/11/25
 *
 * @author daoge_cmd
 */
@Getter
public class ShapedRecipe extends BaseRecipe {

    protected char[][] pattern;
    protected Map<Character, ItemDescriptor> keys;
    // 配方优先级，当出现多个匹配配方时客户端需要根据优先级决定使用哪个配方
    // 服务端实现并不需要用到此参数，但是客户端需要
    protected int priority;

    protected ShapedRecipe(Map<Character, ItemDescriptor> keys, char[][] pattern, int priority, Identifier identifier, String group, ItemStack[] outputs, String[] tags, ItemDescriptor[] unlockItems) {
        super(identifier, group, outputs, tags, unlockItems);
        this.keys = keys;
        this.pattern = pattern;
        this.priority = priority;
    }

    public static ShapedRecipeBuilder builder() {
        return new ShapedRecipeBuilder();
    }

    @Override
    public boolean match(Input input) {
        ItemStack[][] inputs;
        if (input instanceof ShapedInput shapedInput) {
            inputs = removeUselessRowAndColumn(shapedInput.getItems());
        } else return false;

        // Check size
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
                if (key == ' ') {
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

    protected static final ItemStack[][] EMPTY_ITEM_STACK_ARRAY = new ItemStack[0][0];

    /**
     * Remove useless row and column
     * @param inputs [o, o, x]    [x, o, o]
     *               [o, o, x] or [x, o, o] or etc...
     *               [x, x, x]    [x, x, x]
     * @return [o, o]
     *         [o, o]
     */
    protected ItemStack[][] removeUselessRowAndColumn(ItemStack[][] inputs) {
        int startRow = 0, endRow = inputs.length - 1;
        for (int row = 0; row < inputs.length; row++) {
            if (notAllEmptyRow(inputs[row])) {
                startRow = row;
                break;
            }
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

    public static class ShapedRecipeBuilder {
        private Map<Character, ItemDescriptor> keys;
        private char[][] pattern;
        private int priority;
        private Identifier identifier;
        private String group;
        private ItemStack[] outputs;
        private String[] tags;
        private String unlockCondition;
        private ItemDescriptor[] unlockItems = EMPTY_DESCRIPTOR_ARRAY;

        ShapedRecipeBuilder() {
        }

        public ShapedRecipeBuilder keys(Map<Character, ItemDescriptor> keys) {
            this.keys = keys;
            return this;
        }

        public ShapedRecipeBuilder pattern(List<String> stringList) {
            var commonLength = stringList.get(0).length();
            pattern = new char[stringList.size()][commonLength];
            for (int row = 0; row < stringList.size(); row++) {
                var line = stringList.get(row);
                if (line.length() != commonLength)
                    throw new IllegalArgumentException("All row in pattern must have the same length");
                var charArray = line.toCharArray();
                System.arraycopy(charArray, 0, pattern[row], 0, charArray.length);
            }
            return this;
        }

        public ShapedRecipeBuilder pattern(char[][] pattern) {
            this.pattern = pattern;
            return this;
        }

        // 3x3
        public ShapedRecipeBuilder pattern(
                char a, char b, char c,
                char d, char e, char f,
                char g, char h, char i) {
            this.pattern = new char[][]{
                    {a, b, c},
                    {d, e, f},
                    {g, h, i}
            };
            return this;
        }

        // 2x2
        public ShapedRecipeBuilder pattern(
                char a, char b,
                char c, char d) {
            this.pattern = new char[][]{
                    {a, b},
                    {c, d}
            };
            return this;
        }

        // 1x1

        public ShapedRecipeBuilder pattern(char a) {
            this.pattern = new char[][]{
                    {a}
            };
            return this;
        }

        public ShapedRecipeBuilder pattern(
                String l1,
                String l2,
                String l3
        ) {
            this.pattern = new char[][]{
                    l1.toCharArray(),
                    l2.toCharArray(),
                    l3.toCharArray()
            };
            return this;
        }

        public ShapedRecipeBuilder pattern(
                String l1,
                String l2) {
            this.pattern = new char[][]{
                    l1.toCharArray(),
                    l2.toCharArray()
            };
            return this;
        }

        public ShapedRecipeBuilder priority(int priority) {
            this.priority = priority;
            return this;
        }

        public ShapedRecipeBuilder identifier(Identifier identifier) {
            this.identifier = identifier;
            return this;
        }

        public ShapedRecipeBuilder group(String group) {
            this.group = group;
            return this;
        }

        public ShapedRecipeBuilder outputs(ItemStack[] outputs) {
            this.outputs = outputs;
            return this;
        }

        public ShapedRecipeBuilder outputs(ItemStack output) {
            this.outputs = new ItemStack[]{output};
            return this;
        }

        public ShapedRecipeBuilder tags(String[] tags) {
            this.tags = tags;
            return this;
        }

        public ShapedRecipeBuilder unlockItems(ItemDescriptor[] unlockItems) {
            this.unlockItems = unlockItems;
            return this;
        }

        public ShapedRecipeBuilder unlockCondition(String unlockCondition) {
            this.unlockCondition = unlockCondition;
            return this;
        }

        public ShapedRecipe build() {
            return new ShapedRecipe(this.keys, this.pattern, this.priority, this.identifier, this.group, this.outputs, this.tags, this.unlockItems);
        }
    }
}
