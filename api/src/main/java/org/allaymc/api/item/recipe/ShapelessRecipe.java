package org.allaymc.api.item.recipe;

import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.descriptor.ItemDescriptor;
import org.allaymc.api.item.recipe.input.CraftingRecipeInput;
import org.allaymc.api.item.recipe.input.RecipeInput;
import org.allaymc.api.utils.identifier.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.allaymc.api.item.type.ItemTypes.AIR;

/**
 * @author daoge_cmd
 */
@Getter
public class ShapelessRecipe extends Recipe {
    protected ItemDescriptor[] ingredients;

    public ShapelessRecipe(Identifier identifier, ItemStack[] outputs, int priority, ItemDescriptor[] ingredients) {
        super(identifier, outputs, priority);
        this.ingredients = ingredients;
    }

    @Override
    public boolean match(RecipeInput input) {
        if (!(input instanceof CraftingRecipeInput craftingRecipeInput)) {
            return false;
        }

        var inputItems = collectNonAirItems(craftingRecipeInput.flattenedItems());
        if (inputItems.size() != ingredients.length) {
            return false;
        }

        var itemPool = new ArrayList<>(inputItems);

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

    protected List<ItemStack> collectNonAirItems(ItemStack[] items) {
        return Arrays.stream(items).filter(item -> item.getItemType() != AIR).toList();
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
}
