package org.allaymc.api.item.recipe;

import lombok.Builder;
import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.descriptor.DefaultDescriptor;
import org.allaymc.api.item.recipe.input.FurnaceInput;
import org.allaymc.api.item.recipe.input.Input;
import org.allaymc.api.utils.Identifier;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.CraftingDataType;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.FurnaceRecipeData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.RecipeData;

/**
 * Allay Project 2024/8/11
 *
 * @author daoge_cmd
 */
public class FurnaceRecipe implements Recipe, TaggedRecipe, IdentifiedRecipe {
    public static final String FURNACE_TAG = "furnace";
    public static final String BLAST_FURNACE_TAG = "blast_furnace";
    public static final String SMOKER_TAG = "smoker";

    @Getter
    protected String tag;
    @Getter
    protected DefaultDescriptor ingredient;
    protected ItemStack output;
    // NOTICE: Furnace recipe does not have identifier in runtime
    // We give each furnace recipe an identifier server-side to make it possible
    // to be registered into registry
    // The identifier is simply ingredient + tag
    @Getter
    protected Identifier identifier;

    @Builder
    public FurnaceRecipe(DefaultDescriptor ingredient, ItemStack output, String tag) {
        this.ingredient = ingredient;
        this.output = output;
        this.tag = tag;
        this.identifier = new Identifier(ingredient.getItemType().getIdentifier() + "_" + tag);
    }

    @Override
    public boolean match(Input input) {
        if (!(input instanceof FurnaceInput furnaceInput)) return false;
        if (!furnaceInput.getTag().equals(tag)) return false;
        return ingredient.match(furnaceInput.getIngredient());
    }

    @Override
    public ItemStack[] getOutputs() {
        return new ItemStack[]{output};
    }

    @Override
    public CraftingDataType getType() {
        return CraftingDataType.FURNACE;
    }

    @Override
    public RecipeData toNetworkRecipeData() {
        return FurnaceRecipeData.of(
                getType(), ingredient.getItemType().getRuntimeId(),
                0, output.toNetworkItemData(), tag
        );
    }
}
