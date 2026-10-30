package org.allaymc.api.entity.interfaces;

/**
 * {@code EntitySnowball} identifies a snowball traveling as a projectile
 * entity. The inherited projectile contract supplies motion, age, and shooter
 * attribution, while the server physics implementation resolves snowball
 * impact particles and target-specific damage.
 */
public interface EntitySnowball extends EntityProjectile {

}
