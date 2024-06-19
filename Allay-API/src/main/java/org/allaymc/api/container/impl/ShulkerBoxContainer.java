package org.allaymc.api.container.impl;

import org.allaymc.api.container.BaseContainer;
import org.allaymc.api.container.FullContainerType;
import org.joml.Vector3ic;

/**
 * Allay Project 2024/06/18
 *
 * @author IWareQ
 */
public class ShulkerBoxContainer extends BaseContainer {

    protected Vector3ic blockPos;

    public ShulkerBoxContainer() {
        super(FullContainerType.SHULKER_BOX);
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
