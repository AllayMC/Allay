package org.allaymc.api.container.impl;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.container.BaseContainer;
import org.allaymc.api.container.FullContainerType;
import org.joml.Vector3ic;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public class BarrelContainer extends BaseContainer {

    // Block pos will be set through method setBlockPos()
    // See BlockEntityContainerHolderComponent.onNBTLoaded()
    @Getter
    @Setter
    protected Vector3ic blockPos;

    public BarrelContainer() {
        super(FullContainerType.BARREL);
    }

    @Override
    public boolean hasBlockPos() {
        return true;
    }
}
