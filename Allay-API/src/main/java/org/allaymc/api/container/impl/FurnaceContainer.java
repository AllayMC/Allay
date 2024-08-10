package org.allaymc.api.container.impl;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.container.FullContainerType;
import org.joml.Vector3ic;

/**
 * Allay Project 2024/8/11
 *
 * @author daoge_cmd
 */
public class FurnaceContainer extends SmeltingContainer {

    @Getter
    @Setter
    protected Vector3ic blockPos;

    public FurnaceContainer() {
        super(FullContainerType.FURNACE);
    }

    @Override
    public boolean hasBlockPos() {
        return true;
    }
}
