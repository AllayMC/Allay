package org.allaymc.api.entity.interfaces;

/**
 * {@code EntityEgg} identifies an egg traveling as a projectile entity. The
 * inherited projectile contract supplies motion, age, and shooter attribution,
 * while the server entity type selects egg-specific impact processing and
 * possible chicken spawning.
 *
 * @author daoge_cmd
 */
public interface EntityEgg extends EntityProjectile {
}
