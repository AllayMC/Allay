package org.allaymc.server.container.impl;

import lombok.Getter;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.item.recipe.input.CraftingRecipeInput;
import org.allaymc.api.item.recipe.input.RecipeInput;
import org.allaymc.api.math.position.Position3ic;

/**
 * @author daoge_cmd
 */
@Getter
public class CraftingGridContainerImpl extends CraftingContainerImpl {
    public CraftingGridContainerImpl() {
        super(ContainerType.CRAFTING_GRID);
    }

    @Override
    public RecipeInput createRecipeInput() {
        return new CraftingRecipeInput(
                pickOne(0), pickOne(1),
                pickOne(2), pickOne(3)
        );
    }

    @Override
    public Position3ic getBlockPos() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setBlockPos(Position3ic blockPos) {
        throw new UnsupportedOperationException();
    }
}
