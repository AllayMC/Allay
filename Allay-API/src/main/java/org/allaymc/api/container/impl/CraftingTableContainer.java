package org.allaymc.api.container.impl;

import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.item.recipe.input.CraftingInput;

import java.util.stream.IntStream;

/**
 * @author daoge_cmd
 */
public class CraftingTableContainer extends CraftingContainer {
    public CraftingTableContainer() {
        super(FullContainerType.CRAFTING_TABLE);
    }

    @Override
    public CraftingInput createCraftingInput() {
        return new CraftingInput(
                pickOne(0), pickOne(1), pickOne(2),
                pickOne(3), pickOne(4), pickOne(5),
                pickOne(6), pickOne(7), pickOne(8)
        );
    }

    @Override
    public int calculateShouldConsumedItemSlotCount() {
        return (int) IntStream.range(0, 9).filter(i -> !isEmpty(i)).count();
    }
}
