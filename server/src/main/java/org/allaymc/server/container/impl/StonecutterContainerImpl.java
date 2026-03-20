package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.interfaces.StonecutterContainer;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
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
        return new CraftingRecipeInput(pickOneInput());
    }

    protected ItemStack pickOneInput() {
        if (isEmpty(INPUT_SLOT)) {
            return ItemAirStack.AIR_STACK;
        }

        var copy = getItemStack(INPUT_SLOT).copy(false);
        copy.setCount(1);
        return copy;
    }
}
