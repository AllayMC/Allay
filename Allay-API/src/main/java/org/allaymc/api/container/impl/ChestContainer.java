package org.allaymc.api.container.impl;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.container.BaseContainer;
import org.allaymc.api.container.FullContainerType;
import org.joml.Vector3ic;

/**
 * Allay Project 2024/3/2
 *
 * @author daoge_cmd
 */
public class ChestContainer extends BaseContainer {

    @Getter
    @Setter
    protected Vector3ic blockPos;

    public ChestContainer() {
        super(FullContainerType.CHEST);
    }

    @Override
    public boolean hasBlockPos() {
        return true;
    }
}
