package org.allaymc.api.entity.interfaces;

/**
 * {@code EntityBreezeWindChargeProjectile} identifies the wind charge
 * projectile associated with a breeze. Its inherited projectile contract
 * supplies motion, age, and shooter attribution, while the entity type selects
 * breeze-specific impact behavior when the server constructs its components.
 *
 * @author daoge_cmd
 */
public interface EntityBreezeWindChargeProjectile extends EntityProjectile {
}
