package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.interfaces.SmithingTableContainer;
import org.allaymc.api.item.recipe.input.RecipeInput;
import org.allaymc.api.item.recipe.input.SmithingRecipeInput;

/**
 * @author IWareQ
 */
public class SmithingTableContainerImpl extends BlockContainerImpl implements SmithingTableContainer {

    public SmithingTableContainerImpl() {
        super(ContainerType.SMITHING_TABLE);
    }

    @Override
    public RecipeInput createRecipeInput() {
        return new SmithingRecipeInput(getTemplate(), getInput(), getMaterial());
    }
}
