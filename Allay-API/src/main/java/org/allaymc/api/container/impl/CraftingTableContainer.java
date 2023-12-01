package org.allaymc.api.container.impl;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.container.BaseContainer;
import org.allaymc.api.container.FullContainerType;
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
}
