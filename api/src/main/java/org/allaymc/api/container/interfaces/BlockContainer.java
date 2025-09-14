package org.allaymc.api.container.interfaces;

import org.allaymc.api.container.Container;
import org.allaymc.api.math.position.Position3ic;

/**
 * BlockContainer represents a container that is associated with a block in the world.
 *
 * @author daoge_cmd
 */
public interface BlockContainer extends Container {
    /**
     * Gets the position of the block associated with this container.
     *
     * @return the position of the block as a Position3ic object
     */
    Position3ic getBlockPos();
}
