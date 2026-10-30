package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.component.EntityThrownTridentBaseComponent;

/**
 * {@code EntityThrownTrident} represents a thrown trident with a retained item
 * and loyalty return state. Projectile movement and shooter attribution
 * combine with the trident component so impact, homing, and inventory recovery
 * share the same carried item.
 *
 * @author daoge_cmd
 */
public interface EntityThrownTrident extends
        EntityProjectile,
        EntityThrownTridentBaseComponent {
}
