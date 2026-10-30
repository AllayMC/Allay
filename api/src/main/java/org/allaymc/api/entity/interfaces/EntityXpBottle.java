package org.allaymc.api.entity.interfaces;

/**
 * {@code EntityXpBottle} identifies a thrown experience bottle traveling under
 * projectile physics. The inherited projectile contract supplies motion, age,
 * and shooter attribution, while its server physics implementation turns an
 * impact into bottle effects and dropped experience orbs.
 */
public interface EntityXpBottle extends EntityProjectile {

}
