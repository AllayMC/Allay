package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.component.EntityPotionComponent;

/**
 * {@code EntityLingeringPotion} identifies a thrown potion with contents used
 * by lingering impact handling. Projectile movement and shooter attribution
 * come from the inherited projectile contract, while the potion component
 * supplies the type used when impact creates lingering effects.
 */
public interface EntityLingeringPotion extends EntityProjectile, EntityPotionComponent {

}
