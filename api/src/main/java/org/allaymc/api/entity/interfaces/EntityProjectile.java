package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityAgeComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntityProjectileComponent;

/**
 * @author daoge_cmd
 */
public interface EntityProjectile extends
        Entity,
        EntityPhysicsComponent,
        EntityProjectileComponent,
        EntityAgeComponent {
}
