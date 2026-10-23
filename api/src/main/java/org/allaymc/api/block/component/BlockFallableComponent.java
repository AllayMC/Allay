package org.allaymc.api.block.component;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.math.location.Location3d;

/**
 * {@code BlockFallableComponent} defines landing effects and impact damage for blocks
 * represented by falling entities. The falling entity uses the damage calculation
 * before invoking landing behavior, while the default calculation produces no damage
 * and the default container identifies falling-block damage.
 *
 * @author IWareQ
 */
public interface BlockFallableComponent extends BlockComponent {
    /**
     * Handles a falling block reaching its landing position after entity removal.
     * {@code fallDistance} measures the descent in blocks, and {@code blockState}
     * supplies the state available for placement or specialized landing effects.
     */
    void onLanded(Location3d location, double fallDistance, BlockState blockState);

    /**
     * Returns the impact damage for a fall distance measured in blocks. The default
     * returns {@code 0}, so falling blocks without a specialized calculation do not
     * attack nearby entities on landing.
     */
    default float calculateDamage(double fallDistance) {
        return 0;
    }

    /**
     * Creates the falling-block damage descriptor for the supplied damage amount.
     */
    default DamageContainer createDamageContainer(float damage) {
        return DamageContainer.fallingBlock(damage);
    }
}
