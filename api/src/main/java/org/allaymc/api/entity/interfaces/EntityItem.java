package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.component.EntityAgeComponent;
import org.allaymc.api.entity.component.EntityItemBaseComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;

/**
 * {@code EntityItem} represents a dropped mutable item stack with lifetime,
 * motion, and damage handling. Its carried-stack contract supports merging and
 * pickup, while the inherited entity components keep the world object active
 * until that stack is consumed or removed.
 */
public interface EntityItem extends
        EntityLiving,
        EntityItemBaseComponent,
        EntityPhysicsComponent,
        EntityAgeComponent {

}
