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
 * Represents a shapeless recipe that can be matched and crafted without
 * requiring a specific arrangement of ingredients in a crafting grid.
 * This recipe checks for the presence of the required ingredients in any
 * order, as long as all ingredients match.
 *
 * @author daoge_cmd
 */
@Getter
public class ShapelessRecipe extends Recipe {
    /**
     * Represents the ingredient required for this shapeless recipe.
     */
    protected ItemDescriptor[] ingredients;
    /**
     * Specifies the type of the recipe. The type determines the block/container required to use
     * this recipe, such as whether it should be used in a crafting grid/table or a stonecutter.
     */
    protected Type type;

    public ShapelessRecipe(Identifier identifier, ItemStack[] outputs, int priority, ItemDescriptor[] ingredients, Type type) {
        super(identifier, outputs, priority);
        this.ingredients = ingredients;
        this.type = type;
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

    /**
     * Represents the type of shapeless recipe, specifying the block or container required to use
     * the recipe.
     */
    public enum Type {
        /**
         * This recipe can only be used in 2x2 crafting grid or 3x3 crafting table.
         */
        CRAFTING,
        /**
         * This recipe can only be used in a stonecutter.
         */
        STONECUTTER,
        /**
         * This recipe can only be used in a cartography table.
         */
        CARTOGRAPHY_TABLE
    }
}
