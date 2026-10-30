package org.allaymc.api.entity.interfaces;

/**
 * {@code EntityEnderPearl} identifies a thrown ender pearl with projectile
 * motion and shooter attribution. Its server physics implementation uses the
 * shooter when resolving a permitted impact, so the entity contract retains
 * both travel state and launch ownership.
 */
public interface EntityEnderPearl extends EntityProjectile {

}
