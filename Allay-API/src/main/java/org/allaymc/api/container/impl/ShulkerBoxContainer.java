package org.allaymc.api.container.impl;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.container.BaseContainer;
import org.allaymc.api.container.FullContainerType;
import org.joml.Vector3ic;

/**
 * Allay Project 2024/06/18
 *
 * @author IWareQ
 */
public class ShulkerBoxContainer extends BaseContainer {

    @Getter
    @Setter
    protected Vector3ic blockPos;

    public ShulkerBoxContainer() {
        super(FullContainerType.SHULKER_BOX);
    }

    @Override
    public boolean hasBlockPos() {
        return true;
    }
}
