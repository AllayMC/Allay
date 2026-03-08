package org.allaymc.server.item.recipe;

import org.allaymc.api.block.data.PotSherds;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.data.ItemTags;
import org.allaymc.api.item.recipe.input.CraftingRecipeInput;
import org.allaymc.api.item.recipe.input.RecipeInput;
import org.allaymc.api.item.type.ItemTypes;

/**
 * DecoratedPotRecipe is the recipe for decorated pot.
 * <p>
 * The crafting pattern is a diamond shape in a 3x3 grid:
 * <pre>
 *   A
 * A   A
 *   A
 * </pre>
 * Where A is either a brick or a pottery sherd.
 *
 * @author IWareQ
 */
public final class DecoratedPotRecipe extends ComplexRecipe {
    public static final DecoratedPotRecipe INSTANCE = new DecoratedPotRecipe();

    private DecoratedPotRecipe() {
        super(ComplexRecipeIds.DECORATED_POT);
    }

    @Override
    public boolean match(RecipeInput input) {
        return this.getOutputs(input) != null;
    }

    @Override
    public ItemStack[] getOutputs(RecipeInput input) {
        if (!(input instanceof CraftingRecipeInput(ItemStack[][] items))) {
            return null;
        }

        // Must be a 3x3 crafting grid
        if (items.length != 3 || items[0].length != 3) {
            return null;
        }

        // Check the diamond pattern:
        // Row 0: [empty, back, empty]
        // Row 1: [left, empty, right]
        // Row 2: [empty, front, empty]
        if (
                !items[0][0].isEmptyOrAir() || !items[0][2].isEmptyOrAir() ||
                !items[1][1].isEmptyOrAir() ||
                !items[2][0].isEmptyOrAir() || !items[2][2].isEmptyOrAir()
        ) {
            return null;
        }

        var back = items[0][1];
        var left = items[1][0];
        var right = items[1][2];
        var front = items[2][1];
        if (
                !this.isValidIngredient(back) || !this.isValidIngredient(left) ||
                !this.isValidIngredient(right) || !this.isValidIngredient(front)
        ) {
            return null;
        }

        var decoratedPot = ItemTypes.DECORATED_POT.createItemStack(1);
        decoratedPot.setSherds(new PotSherds(
                this.getSherdIdentifier(front),
                this.getSherdIdentifier(back),
                this.getSherdIdentifier(left),
                this.getSherdIdentifier(right)
        ));

        return new ItemStack[]{decoratedPot};
    }

    private boolean isValidIngredient(ItemStack item) {
        if (item.isEmptyOrAir()) {
            return false;
        }

        var itemType = item.getItemType();
        return itemType == ItemTypes.BRICK || itemType.hasItemTag(ItemTags.DECORATED_POT_SHERDS);
    }

    private String getSherdIdentifier(ItemStack item) {
        var itemType = item.getItemType();
        if (itemType == ItemTypes.BRICK) {
            return "";
        }

        return itemType.getIdentifier().toString();
    }
}
