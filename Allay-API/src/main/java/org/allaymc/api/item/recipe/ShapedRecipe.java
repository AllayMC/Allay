package org.allaymc.api.item.recipe;

import lombok.Builder;
import lombok.Getter;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.descriptor.ItemDescriptor;

import java.util.Map;

/**
 * Allay Project 2023/11/25
 *
 * @author daoge_cmd
 */
@Getter
public class ShapedRecipe extends BaseRecipe {

    protected char[][] pattern;
    protected Map<Character, ItemDescriptor> keys;

    @Builder
    protected ShapedRecipe(Map<Character, ItemDescriptor> keys, char[][] pattern, Identifier identifier, String group, ItemStack output, String[] tags, ItemDescriptor[] unlockItems) {
        super(identifier, group, output, tags, unlockItems);
        this.keys = keys;
        this.pattern = pattern;
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
                var column = row[j];
                var descriptor = keys.get(column);
                var item = inputs[i][j];
                if (!descriptor.match(item)) {
                    return false;
                }
            }
        }

        return true;
    }
}
