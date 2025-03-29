package org.allaymc.api.block.component;

import org.allaymc.api.math.location.Location3d;

/**
 * @author IWareQ
 */
public interface BlockFallableComponent extends BlockComponent {
    void onLanded(Location3d location, double fallDistance);
}
