package org.allaymc.api.entity.interfaces;

/**
 * {@code EntitySmallFireball} identifies a small fireball traveling under
 * projectile physics. The inherited projectile contract carries motion, age,
 * and shooter attribution, while the server entity type selects the ignition
 * and damage behavior used for its impacts.
 *
 * @author daoge_cmd
 */
public interface EntitySmallFireball extends EntityProjectile {
}
