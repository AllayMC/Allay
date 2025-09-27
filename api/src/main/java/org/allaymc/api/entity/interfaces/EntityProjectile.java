package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityAgeComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntityProjectileComponent;

/**
 * EntityProjectile represents an entity which is projectile. Every projectile entity should
 * extend this interface.
 *
 * @author daoge_cmd
 */
public interface EntityProjectile extends
        Entity,
        EntityPhysicsComponent,
        EntityProjectileComponent,
        EntityAgeComponent {
}
