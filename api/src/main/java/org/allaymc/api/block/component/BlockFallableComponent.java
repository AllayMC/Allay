package org.allaymc.api.block.component;

import org.allaymc.api.math.location.Location3d;

/**
 * @author IWareQ
 */
public interface BlockFallableComponent extends BlockComponent {
    /**
     * Called when the block has landed after falling.
     *
     * @param location     the location where the block lands
     * @param fallDistance the distance the block has fallen
     */
    void onLanded(Location3d location, double fallDistance);

    /**
     * Calculates the damage based on the fall distance.
     *
     * @param fallDistance the distance the block has fallen
     *
     * @return the calculated damage
     */
    default float getDamage(double fallDistance) {
        return 0;
    }
}
