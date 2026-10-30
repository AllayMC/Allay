package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.component.EntityPotionComponent;

/**
 * {@code EntitySplashPotion} identifies a thrown potion whose contents are
 * applied around an impact. Its inherited projectile contract provides travel
 * and shooter information, while the potion component supplies the effects and
 * appearance used by splash handling.
 */
public interface EntitySplashPotion extends EntityProjectile, EntityPotionComponent {

}
