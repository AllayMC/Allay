package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.component.EntityArrowBaseComponent;
import org.allaymc.api.entity.component.EntityPotionComponent;

/**
 * {@code EntityArrow} represents an arrow with projectile motion,
 * living-entity damage state, and optional potion contents. Its arrow
 * component supplies embedded-state and pickup flags, allowing impact handling
 * and player pickup to operate on the same entity.
 */
public interface EntityArrow extends EntityLiving, EntityProjectile, EntityArrowBaseComponent, EntityPotionComponent {
}
