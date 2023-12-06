package org.allaymc.api.container.impl;

import org.allaymc.api.container.BaseContainer;
import org.allaymc.api.container.FullContainerType;
import org.joml.Vector3ic;

import java.util.function.Supplier;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public class BarrelContainer extends BaseContainer {

    // Block pos will be set through method setBlockPos()
    // See BlockEntityContainerHolderComponent.onNBTLoaded()
    protected Vector3ic blockPos;

    public BarrelContainer() {
        super(FullContainerType.BARREL);
    }

    @Override
    public boolean hasBlockPos() {
        return true;
    }

    @Override
    public Vector3ic getBlockPos() {
        return blockPos;
    }

    @Override
    public void setBlockPos(Vector3ic blockPos) {
        this.blockPos = blockPos;
    }
}
