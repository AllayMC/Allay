package org.allaymc.api.container.impl;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.container.BaseContainer;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemAirStack;
import org.allaymc.api.item.recipe.input.CraftingInput;
import org.joml.Vector3ic;

/**
 * Allay Project 2023/12/1
 *
 * @author daoge_cmd
 */
@Getter
@Setter
public class CraftingTableContainer extends BaseContainer {

    protected Vector3ic lastCraftingTablePos;

    public CraftingTableContainer() {
        super(FullContainerType.CRAFTING_TABLE);
    }

    @Override
    public boolean hasBlockPos() {
        return true;
    }

    @Override
    public Vector3ic getBlockPos() {
        return lastCraftingTablePos;
    }

    public CraftingInput createInput() {
        return new CraftingInput(
                pickOne(0), pickOne(1), pickOne(2),
                pickOne(3), pickOne(4), pickOne(5),
                pickOne(6), pickOne(7), pickOne(8)
        );
    }

    protected ItemStack pickOne(int slot) {
        var copy = content[slot].copy(false);
        copy.setCount(1);
        return copy;
    }
}
