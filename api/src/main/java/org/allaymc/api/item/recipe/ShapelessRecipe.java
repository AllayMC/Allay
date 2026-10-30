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
 * {@code ShapelessRecipe} matches an unordered set of occupied crafting slots
 * against ingredient descriptors. Air slots are removed, and the remaining slot
 * count must equal the descriptor count. Each descriptor claims the first
 * matching unclaimed stack, ensuring that one occupied slot cannot satisfy two
 * requirements without changing the original stacks.
 *
 * <p>
 * Descriptor order still matters when matching rules overlap: a broad
 * descriptor can claim a stack needed by a later narrow descriptor, and
 * {@code match} does not backtrack. The constructor retains the ingredient
 * array and inherited outputs, while {@code type} supplies workstation metadata
 * rather than an additional check inside {@code match}.
 *
 * @author daoge_cmd
 */
@Getter
public class ShapelessRecipe extends Recipe {
    /**
     * {@code ingredients} retains the supplied array and descriptors.
     * {@code getIngredients()} exposes them directly, so later changes alter
     * matching.
     */
    protected ItemDescriptor[] ingredients;
    /**
     * Workstation category associated with the recipe.
     */
    protected Type type;

    /**
     * Creates a shapeless recipe retaining the ingredient and output arrays
     * without copying. Descriptor order controls greedy matching when
     * requirements overlap; {@code type} identifies the workstation but is not
     * checked by {@code match}.
     */
    public ShapelessRecipe(Identifier identifier, ItemStack[] outputs, int priority, ItemDescriptor[] ingredients, Type type) {
        super(identifier, outputs, priority);
        this.ingredients = ingredients;
        this.type = type;
    }

    /**
     * Returns whether every ingredient can claim a distinct occupied crafting
     * slot. Air slots are ignored, and an occupied-slot count different from
     * the descriptor count returns {@code false} before matching begins.
     *
     * <p>
     * Descriptors are processed in array order, each removing the first
     * matching stack from a temporary pool. This prevents reuse of one slot,
     * but overlapping descriptors can cause a greedy mismatch even when another
     * assignment would succeed. Other input types return {@code false}, and the
     * supplied stacks remain unchanged.
     */
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

    /**
     * Returns an unmodifiable list of nonair stack references in input order.
     * The stacks themselves remain shared with the input array, so this
     * filtering does not create independent item copies.
     */
    protected List<ItemStack> collectNonAirItems(ItemStack[] items) {
        return Arrays.stream(items).filter(item -> item.getItemType() != AIR).toList();
    }

    /**
     * Returns the first pool index whose stack matches the descriptor. A result
     * of {@code -1} means no candidate matched; this search leaves the pool and
     * its stacks unchanged.
     */
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
     * {@code Type} identifies the workstation associated with a shapeless
     * recipe.
     */
    public enum Type {
        /**
         * Crafting grid workstation category.
         */
        CRAFTING,
        /**
         * Stonecutter recipe workstation category.
         */
        STONECUTTER,
        /**
         * Cartography table workstation category.
         */
        CARTOGRAPHY_TABLE
    }
}
