package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.component.EntityAIComponent;
import org.allaymc.api.entity.component.EntityParallelTickComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;

/**
 * {@code EntityIntelligent} represents a living entity with AI, physics, and
 * parallel-tick capabilities. {@link EntityAIComponent} exposes the behavior
 * group and its movement memory, while the inherited physics and tick contracts
 * govern how implementations update the entity's movement and behavior.
 *
 * @author daoge_cmd
 */
public interface EntityIntelligent extends EntityLiving, EntityAIComponent, EntityPhysicsComponent, EntityParallelTickComponent {
}
