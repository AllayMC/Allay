package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.interfaces.CraftingTableContainer;
import org.allaymc.api.item.recipe.input.CraftingRecipeInput;
import org.allaymc.api.item.recipe.input.RecipeInput;

/**
 * @author daoge_cmd
 */
public class CraftingTableContainerImpl extends CraftingContainerImpl implements CraftingTableContainer {
    public CraftingTableContainerImpl() {
        super(ContainerType.CRAFTING_TABLE);
    }

    @Override
    public RecipeInput createRecipeInput() {
        return new CraftingRecipeInput(
                pickOne(0), pickOne(1), pickOne(2),
                pickOne(3), pickOne(4), pickOne(5),
                pickOne(6), pickOne(7), pickOne(8)
        );
    }
}
