package org.allaymc.server.item.recipe;

import org.allaymc.api.block.data.PotSherds;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.data.ItemTags;
import org.allaymc.api.item.recipe.input.CraftingRecipeInput;
import org.allaymc.api.item.recipe.input.RecipeInput;
import org.allaymc.api.item.type.ItemTypes;

/**
 * {@code DecoratedPotRecipe} produces decorated pots from the four cardinal
 * ingredient positions in a three-by-three crafting grid. The corners and
 * center must be empty, and each occupied position accepts either a brick or an
 * item carrying {@code ItemTags.DECORATED_POT_SHERDS}. Matching constructs the
 * same output as crafting and discards it after checking success.
 *
 * <p>
 * Grid order and pot-face order differ: the upper ingredient is the back face,
 * the lower ingredient is the front, and the middle row supplies left and
 * right. Output construction rearranges these values into {@code PotSherds}
 * front, back, left, right order. A brick becomes the empty face identifier,
 * preserving undecorated sides alongside named sherds.
 *
 * @author IWareQ
 */
public final class DecoratedPotRecipe extends ComplexRecipe {
    /**
     * {@code INSTANCE} supplies the shared decorated-pot recipe using the
     * client-defined special recipe UUID.
     */
    public static final DecoratedPotRecipe INSTANCE = new DecoratedPotRecipe();

    /**
     * Creates the singleton recipe with the protocol-defined decorated-pot
     * UUID.
     */
    private DecoratedPotRecipe() {
        super(ComplexRecipeIds.DECORATED_POT);
    }

    /**
     * Returns whether input-dependent output construction accepts this crafting
     * grid. The check creates and discards a decorated-pot output for valid
     * ingredients, and it inherits the same grid shape and stack requirements
     * as {@code getOutputs}.
     */
    @Override
    public boolean match(RecipeInput input) {
        return this.getOutputs(input) != null;
    }

    /**
     * Creates one decorated pot when a three-by-three input contains valid
     * cardinal ingredients and empty corners and center. Other input
     * representations, rejected dimensions, occupied empty positions, or
     * invalid ingredients return {@code null}; callers supply rectangular
     * arrays with non-{@code null} stacks.
     *
     * <p>
     * The top, bottom, left, and right ingredient positions become back, front,
     * left, and right pot faces respectively. Bricks store empty face
     * identifiers, and tagged sherds store their item identifiers. The returned
     * pot and output array are newly created, while the supplied ingredients
     * remain unchanged.
     */
    @Override
    public ItemStack[] getOutputs(RecipeInput input) {
        if (!(input instanceof CraftingRecipeInput(ItemStack[][] items))) {
            return null;
        }

        // Pot faces come from fixed cardinal positions, so smaller grids cannot
        // represent this recipe and must be rejected before positional reads.
        if (items.length != 3 || items[0].length != 3) {
            return null;
        }

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
        // Crafting visits the back face first, but PotSherds stores the front
        // face first. Reorder the ingredients so decorations stay on their intended faces.
        decoratedPot.setSherds(new PotSherds(
                this.getSherdIdentifier(front),
                this.getSherdIdentifier(back),
                this.getSherdIdentifier(left),
                this.getSherdIdentifier(right)
        ));

        return new ItemStack[]{decoratedPot};
    }

    /**
     * Returns whether a nonempty ingredient is a brick or a tagged pottery
     * sherd. Empty stacks and unrelated item types return {@code false};
     * matching does not restrict valid ingredient stack counts.
     */
    private boolean isValidIngredient(ItemStack item) {
        if (item.isEmptyOrAir()) {
            return false;
        }

        var itemType = item.getItemType();
        return itemType == ItemTypes.BRICK || itemType.hasItemTag(ItemTags.DECORATED_POT_SHERDS);
    }

    /**
     * Returns the item identifier used for a pot face. Bricks produce
     * {@code ""}, the undecorated-face representation consumed by
     * {@code PotSherds}; every other item supplies its namespaced item-type
     * identifier.
     */
    private String getSherdIdentifier(ItemStack item) {
        var itemType = item.getItemType();
        // PotSherds represents brick faces with empty identifiers. Preserve that
        // representation instead of recording a brick item identifier as a decoration.
        if (itemType == ItemTypes.BRICK) {
            return "";
        }

        return itemType.getIdentifier().toString();
    }
}
