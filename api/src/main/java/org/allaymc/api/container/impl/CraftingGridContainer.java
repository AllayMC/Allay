package org.allaymc.api.container.impl;

import lombok.Getter;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.item.recipe.input.CraftingRecipeInput;
import org.allaymc.api.math.position.Position3ic;

/**
 * @author daoge_cmd
 */
@Getter
public class CraftingGridContainer extends CraftingContainer {
    public CraftingGridContainer() {
        super(FullContainerType.CRAFTING_GRID);
    }

    @Override
    public int calculateShouldConsumedItemSlotCount() {
        var count = 0;
        for (var i = 0; i < 4; i++) {
            if (!isEmpty(i)) count++;
        }
        return count;
    }

    @Override
    public CraftingRecipeInput createCraftingInput() {
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
