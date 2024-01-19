package org.allaymc.api.container.impl;

import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.item.recipe.input.CraftingInput;
import org.joml.Vector3ic;

/**
 * Allay Project 2024/1/19
 *
 * @author daoge_cmd
 */
public class CraftingTableContainer extends CraftingContainer {

    protected Vector3ic craftingTablePos;

    public CraftingTableContainer() {
        super(FullContainerType.CRAFTING_TABLE);
    }

    @Override
    public Vector3ic getBlockPos() {
        return craftingTablePos;
    }

    @Override
    public void setBlockPos(Vector3ic blockPos) {
        this.craftingTablePos = blockPos;
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
    public int calculateShouldConsumedItemCount() {
        var count = 0;
        for (var i = 4; i <= 12; i++) {
            if (!isEmpty(i)) count++;
        }
        return count;
    }

    @Override
    public boolean hasBlockPos() {
        return true;
    }
}
