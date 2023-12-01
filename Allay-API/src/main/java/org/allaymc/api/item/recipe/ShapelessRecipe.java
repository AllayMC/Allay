package org.allaymc.api.item.recipe;

import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.descriptor.ItemDescriptor;
import org.allaymc.api.item.recipe.input.ShapelessInput;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.CraftingDataType;

import java.util.ArrayList;
import java.util.List;

/**
 * Allay Project 2023/12/1
 *
 * @author daoge_cmd
 */
public class ShapelessRecipe extends CraftingRecipe<ShapelessInput> {

    protected ItemDescriptor[] ingredients;

    protected ShapelessRecipe(ItemDescriptor[] ingredients, Identifier identifier, ItemStack[] outputs, String tag) {
        super(identifier, outputs, tag);
        this.ingredients = ingredients;
    }

    @Override
    public boolean match(ShapelessInput input) {
        if (input.getItems().length != ingredients.length) {
            return false;
        }

        List<ItemStack> itemPool = new ArrayList<>(List.of(input.getItems()));
        var checkCount = ingredients.length;
        for (var ingredient : ingredients) {
            var index = findItem(itemPool, ingredient);
            if (index != -1) {
                itemPool.remove(index);
                checkCount--;
            }
        }
        return checkCount == 0;
    }

    protected int findItem(List<ItemStack> itemPool, ItemDescriptor target) {
        for (int index = 0; index < itemPool.size(); index++) {
            var item = itemPool.get(index);
            if (target.match(item)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public CraftingDataType getType() {
        return CraftingDataType.SHAPELESS;
    }
}
