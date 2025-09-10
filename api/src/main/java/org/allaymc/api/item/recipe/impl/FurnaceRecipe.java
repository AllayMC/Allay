package org.allaymc.api.item.recipe.impl;

import lombok.Builder;
import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.descriptor.DefaultDescriptor;
import org.allaymc.api.item.recipe.IdentifiedRecipe;
import org.allaymc.api.item.recipe.TaggedRecipe;
import org.allaymc.api.item.recipe.input.FurnaceRecipeInput;
import org.allaymc.api.item.recipe.input.RecipeInput;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.utils.identifier.Identifier;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.CraftingDataType;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.FurnaceRecipeData;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.recipe.RecipeData;

/**
 * Represents a furnace recipe.
 *
 * @author daoge_cmd
 */
public class FurnaceRecipe implements BaseRecipe, TaggedRecipe, IdentifiedRecipe {
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
        this.identifier = buildFurnaceRecipeIdentifier(ingredient.getItemType(), tag);
    }

    public static Identifier buildFurnaceRecipeIdentifier(ItemType<?> ingredient, String tag) {
        return new Identifier(ingredient.getIdentifier() + "_" + tag);
    }

    @Override
    public boolean match(RecipeInput input) {
        if (!(input instanceof FurnaceRecipeInput furnaceRecipeInput)) return false;
        if (!isFurnaceTypeSuitable(furnaceRecipeInput)) return false;
        return ingredient.match(furnaceRecipeInput.getIngredient());
    }

    @Override
    public ItemStack[] getOutputs() {
        return new ItemStack[]{output};
    }

    public ItemStack getOutput() {
        return output;
    }

    public boolean isFurnaceTypeMostSuitable(FurnaceRecipeInput input) {
        return tag.equals(input.getTag());
    }

    public boolean isFurnaceTypeSuitable(FurnaceRecipeInput input) {
        return tag.equals(FURNACE_TAG) || tag.equals(input.getTag());
    }

    @Override
    public CraftingDataType getType() {
        return CraftingDataType.FURNACE;
    }

    @Override
    public RecipeData toNetworkData() {
        return FurnaceRecipeData.of(
                getType(), ingredient.getItemType().getRuntimeId(),
                0, output.toNetworkItemData(), tag
        );
    }
}
