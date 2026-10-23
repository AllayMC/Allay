package org.allaymc.api.entity.interfaces;

/**
 * {@code EntityWindChargeProjectile} identifies the wind charge projectile
 * associated with a player launch. Its inherited projectile contract supplies
 * motion, age, and shooter attribution, while the server entity type selects
 * the wind explosion behavior used when it hits.
 *
 * @author daoge_cmd
 */
public interface EntityWindChargeProjectile extends EntityProjectile {
}
