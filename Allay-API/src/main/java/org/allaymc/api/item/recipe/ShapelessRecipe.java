package org.allaymc.api.item.recipe;

import lombok.Builder;
import lombok.Getter;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.descriptor.ItemDescriptor;
import org.allaymc.api.item.recipe.input.CraftingInput;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.CraftingDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Allay Project 2023/12/1
 *
 * @author daoge_cmd
 */
@Getter
public class ShapelessRecipe extends CraftingRecipe<CraftingInput> {

    protected ItemDescriptor[] ingredients;

    @Builder
    protected ShapelessRecipe(ItemDescriptor[] ingredients, Identifier identifier, ItemStack[] outputs, String tag, UUID uuid, int priority) {
        super(identifier, outputs, tag, uuid, priority);
        this.ingredients = ingredients;
    }

    @Override
    public boolean match(CraftingInput input) {
        if (input.getItems().length != ingredients.length) {
            return false;
        }

        List<ItemStack> itemPool = new ArrayList<>(List.of(input.getFlattenItems()));
        var checkCount = ingredients.length;
        for (var ingredient : ingredients) {
            var index = findItem(itemPool, ingredient);
            if (index != -1) {
                itemPool.remove(index);
                checkCount--;
            }
        }
        return checkCount == 0 && itemPool.isEmpty();
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
