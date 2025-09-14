package org.allaymc.api.container.interfaces;

import org.allaymc.api.container.Container;
import org.allaymc.api.math.position.Position3ic;

/**
 * @author daoge_cmd
 */
public interface BlockContainer extends Container {
    Position3ic getBlockPos();
}
