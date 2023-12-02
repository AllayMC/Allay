package org.allaymc.api.container.impl;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.container.BaseContainer;
import org.allaymc.api.container.ContainerViewer;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.input.CraftingInput;
import org.joml.Vector3ic;

/**
 * Allay Project 2023/12/1
 *
 * @author daoge_cmd
 */
@Getter
@Setter
public class CraftingGridContainer extends BaseContainer {

    protected Vector3ic lastCraftingTablePos;
    protected boolean isUsingCraftingTable = false;

    public CraftingGridContainer() {
        super(FullContainerType.CRAFTING_GRID);
    }

    @Override
    public boolean hasBlockPos() {
        return true;
    }

    @Override
    public Vector3ic getBlockPos() {
        return lastCraftingTablePos;
    }


    @Override
    public void onClose(ContainerViewer viewer) {
        if (isUsingCraftingTable) {
            isUsingCraftingTable = false;
        }
    }

    public int calculateShouldConsumedItemCount() {
        var count = 0;
        if (isUsingCraftingTable) {
            for (var i = 4; i <= 12; i++) {
                if (!isEmpty(i)) count++;
            }
        } else {
            for (var i = 0; i <= 4; i++) {
                if (!isEmpty(i)) count++;
            }
        }
        return count;
    }

    public CraftingInput createCraftingInput() {
        return isUsingCraftingTable ? createCraftingTableInput() : createCraftingGridInput();
    }

    public CraftingInput createCraftingGridInput() {
        return new CraftingInput(
                pickOne(0), pickOne(1),
                pickOne(2), pickOne(3)
        );
    }

    public CraftingInput createCraftingTableInput() {
        return new CraftingInput(
                pickOne(4), pickOne(5), pickOne(6),
                pickOne(7), pickOne(8), pickOne(9),
                pickOne(10), pickOne(11), pickOne(12)
        );
    }

    protected ItemStack pickOne(int slot) {
        var copy = content[slot].copy(false);
        copy.setCount(1);
        return copy;
    }
}
