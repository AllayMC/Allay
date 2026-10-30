package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityAgeComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntityProjectileComponent;

/**
 * {@code EntityProjectile} represents an entity traveling under projectile
 * physics with age and optional shooter attribution. Specialized projectile
 * interfaces inherit this combination so impact handlers can access travel
 * state and launch ownership through one entity contract.
 *
 * @author daoge_cmd
 */
public interface EntityProjectile extends
        Entity,
        EntityPhysicsComponent,
        EntityProjectileComponent,
        EntityAgeComponent {
}
