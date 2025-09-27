package org.allaymc.api.container.interfaces;

import org.allaymc.api.container.Container;
import org.allaymc.api.math.position.Position3ic;
import org.jetbrains.annotations.ApiStatus;

/**
 * BlockContainer represents a container that is associated with a block in the world.
 *
 * @author daoge_cmd
 */
public interface BlockContainer extends Container {
    /**
     * Gets the position of the block associated with this container.
     *
     * @return the position of the block
     */
    Position3ic getBlockPos();

    /**
     * Sets the position of the block associated with this container.
     *
     * @param pos the position of the block
     */
    @ApiStatus.OverrideOnly
    void setBlockPos(Position3ic pos);
}
