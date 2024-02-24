package org.allaymc.api.container.impl;

import lombok.Getter;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.item.recipe.input.CraftingInput;

/**
 * Allay Project 2023/12/1
 *
 * @author daoge_cmd
 */
@Getter
public class CraftingGridContainer extends CraftingContainer {

    public CraftingGridContainer() {
        super(FullContainerType.CRAFTING_GRID);
    }

    @Override
    public int calculateShouldConsumedItemCount() {
        var count = 0;
        for (var i = 0; i < 4; i++) {
            if (!isEmpty(i)) count++;
        }
        return count;
    }

    @Override
    public CraftingInput createCraftingInput() {
        return new CraftingInput(
                pickOne(0), pickOne(1),
                pickOne(2), pickOne(3)
        );
    }
}
