package org.allaymc.api.item.recipe.impl;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.descriptor.ItemTypeDescriptor;
import org.allaymc.api.item.recipe.Recipe;
import org.allaymc.api.item.recipe.input.PotionMixRecipeInput;
import org.allaymc.api.item.recipe.input.RecipeInput;
import org.allaymc.api.utils.identifier.Identified;
import org.allaymc.api.utils.identifier.Identifier;
import org.cloudburstmc.protocol.bedrock.data.inventory.crafting.PotionMixData;

/**
 * @author IWareQ
 */
@ToString
@Getter
public class PotionMixRecipe implements Recipe<PotionMixData>, Identified {
    protected ItemTypeDescriptor ingredient;
    protected ItemTypeDescriptor output;
    protected ItemTypeDescriptor reagent;

    /**
     * NOTICE: Potion mix recipe does not have identifier in runtime
     * We give each potion mix recipe an identifier server-side to make it possible
     * to be registered into registry
     * The identifier is simply ingredient + reagent
     */
    @Getter
    protected Identifier identifier;

    @Builder
    public PotionMixRecipe(ItemTypeDescriptor ingredient, ItemTypeDescriptor output, ItemTypeDescriptor reagent) {
        this.ingredient = ingredient;
        this.output = output;
        this.reagent = reagent;
        this.identifier = buildIdentifier(ingredient.createItemStack(), reagent.createItemStack());
    }

    public static Identifier buildIdentifier(ItemStack ingredient, ItemStack reagent) {
        return new Identifier(
                ingredient.getItemType().getIdentifier().path() + "_" + ingredient.getMeta() +
                "_" +
                reagent.getItemType().getIdentifier().path() + "_" + reagent.getMeta()
        );
    }

    @Override
    public boolean match(RecipeInput input) {
        if (input instanceof PotionMixRecipeInput potionMixRecipeInput) {
            return ingredient.match(potionMixRecipeInput.getIngredient()) && reagent.match(potionMixRecipeInput.getReagent());
        }

        return false;
    }

    @Override
    public ItemStack[] getOutputs() {
        return new ItemStack[]{getOutput()};
    }

    public ItemStack getOutput() {
        return output.createItemStack();
    }

    @Override
    public PotionMixData toNetworkData() {
        return new PotionMixData(
                ingredient.getItemType().getRuntimeId(), ingredient.getMeta(),
                reagent.getItemType().getRuntimeId(), reagent.getMeta(),
                output.getItemType().getRuntimeId(), output.getMeta()
        );
    }
}
