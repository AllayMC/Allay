package org.allaymc.api.container.impl;


import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.container.BaseContainer;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.FullContainerType;
import org.joml.Vector3ic;

/**
 * @author IWareQ
 */
public class BlockContainer extends BaseContainer {
    @Getter
    @Setter
    protected Vector3ic blockPos;

    public BlockContainer(FullContainerType<? extends Container> containerType) {
        super(containerType);
    }
}
