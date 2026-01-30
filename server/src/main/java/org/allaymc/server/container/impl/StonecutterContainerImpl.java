package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.StonecutterContainer;
import org.allaymc.api.item.recipe.input.CraftingRecipeInput;
import org.allaymc.api.item.recipe.input.RecipeInput;

/**
 * @author IWareQ
 */
public class StonecutterContainerImpl extends BlockContainerImpl implements StonecutterContainer {
    public StonecutterContainerImpl() {
        super(ContainerTypes.STONECUTTER);
    }

    @Override
    public RecipeInput createRecipeInput() {
        return new CraftingRecipeInput(getItemStack(INPUT_SLOT));
    }
}
