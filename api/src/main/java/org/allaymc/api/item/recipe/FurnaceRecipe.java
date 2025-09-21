package org.allaymc.api.item.recipe;

import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.input.FurnaceRecipeInput;
import org.allaymc.api.item.recipe.input.RecipeInput;
import org.allaymc.api.utils.identifier.Identifier;

import java.util.Locale;

/**
 * Represents a furnace recipe.
 *
 * @author daoge_cmd
 */
@Getter
public class FurnaceRecipe extends Recipe {
    /**
     * Represents the ingredient required for this furnace recipe.
     */
    protected final ItemStack ingredient;
    /**
     * Represents the type of the furnace recipe, which specifies the type of block required to use
     * this recipe.
     */
    protected final Type type;

    /**
     * Constructs a new FurnaceRecipe instance. This recipe is used for smelting or cooking
     * processes in various furnace-related blocks, producing specific outputs based on the
     * provided ingredient.
     *
     * @param ingredient the item type descriptor representing the input item for the recipe;
     *                   it determines the required input's type and metadata for the recipe.
     * @param output     the item stack representing the output produced by this recipe
     *                   when the ingredient is processed in the furnace.
     * @param type       the type of furnace in which this recipe can be processed; specifies
     *                   the valid furnace block (e.g., furnace, blast furnace, smoker, campfire).
     *                   When the type is {@link FurnaceRecipe.Type#FURNACE}, the recipe can be used
     *                   in all furnace blocks.
     */
    public FurnaceRecipe(ItemStack ingredient, ItemStack output, Type type) {
        super(buildIdentifier(ingredient, type), new ItemStack[]{output}, 0);
        this.ingredient = ingredient;
        this.type = type;
    }

    public static Identifier buildIdentifier(ItemStack ingredient, Type type) {
        return new Identifier("furnace." + index(ingredient) + "_" + type.name().toLowerCase(Locale.ROOT));
    }

    private static String index(ItemStack item) {
        return item.getItemType().getIdentifier().toString().replace(Identifier.NAMESPACE_SEPARATOR, "_");
    }

    @Override
    public boolean match(RecipeInput input) {
        if (input instanceof FurnaceRecipeInput(
                ItemStack ingredient1, Type type1
        ) && (type == Type.FURNACE || type == type1)) {
            return ingredient.getItemType() == ingredient1.getItemType();
        }

        return false;
    }

    /**
     * Enumerates the various types of furnace recipes, specifying the types of blocks
     * in which the recipes can be used. Each type restricts the recipe's usability to
     * a specific kind of furnace block or allows it across all furnace blocks.
     */
    public enum Type {
        /**
         * The recipe can be used in all furnace blocks.
         */
        FURNACE,
        /**
         * The recipe can only be used in a blast furnace.
         */
        BLAST_FURNACE,
        /**
         * The recipe can only be used in a smoker.
         */
        SMOKER,
        /**
         * The recipe can only be used in a campfire.
         */
        CAMPFIRE,
        /**
         * The recipe can only be used in a soul campfire.
         */
        SOUL_CAMPFIRE;
    }
}
