package org.allaymc.server.container.impl;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.interfaces.BlockContainer;
import org.allaymc.api.math.position.Position3ic;

/**
 * {@code BlockContainerImpl} adds a block position to the base item-container
 * implementation. The block entity owner supplies that position before opening its
 * interface, and viewer opening uses the stored position to associate the window with
 * the world block.
 *
 * @author IWareQ
 */
public class BlockContainerImpl extends BaseContainer implements BlockContainer {
    /**
     * {@code blockPos} is retained and returned without copying. It starts as {@code
     * null}; the owner must set it before viewers open a block interface.
     */
    @Getter
    @Setter
    protected Position3ic blockPos;

    /**
     * Creates empty storage for the supplied type with no block position yet. The owner
     * must install {@code blockPos} before a viewer attempts to open the block
     * interface.
     */
    public BlockContainerImpl(ContainerType<? extends Container> containerType) {
        super(containerType);
    }
}
