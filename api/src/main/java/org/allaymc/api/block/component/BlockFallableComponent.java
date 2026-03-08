package org.allaymc.api.block.component;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.entity.damage.DamageContainer;
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
     * @param blockState   the block state to be placed
     */
    void onLanded(Location3d location, double fallDistance, BlockState blockState);

    /**
     * Calculates the damage based on the fall distance.
     *
     * @param fallDistance the distance the block has fallen
     *
     * @return the calculated damage
     */
    default float calculateDamage(double fallDistance) {
        return 0;
    }

    /**
     * Creates the damage container for this falling block.
     *
     * @param damage the damage amount
     *
     * @return the damage container
     */
    default DamageContainer createDamageContainer(float damage) {
        return DamageContainer.fallingBlock(damage);
    }
}
