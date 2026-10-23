package org.allaymc.api.container.interfaces;

import org.allaymc.api.container.Container;
import org.allaymc.api.math.position.Position3ic;
import org.jetbrains.annotations.ApiStatus;

/**
 * {@code BlockContainer} associates item storage with a block position in a dimension.
 * Viewers use that position when opening the block interface, and block entity owners
 * supply the position when they attach or move the container.
 *
 * @author daoge_cmd
 */
public interface BlockContainer extends Container {
    /**
     * Returns the block position used when viewers open this container. The position can
     * be {@code null} before its owner initializes it, so owners must set it before
     * opening the block interface.
     */
    Position3ic getBlockPos();

    /**
     * Associates this container with the supplied block position. The owner must install
     * the position before opening the interface; implementations may retain {@code pos}
     * without copying its coordinates.
     */
    @ApiStatus.OverrideOnly
    void setBlockPos(Position3ic pos);
}
