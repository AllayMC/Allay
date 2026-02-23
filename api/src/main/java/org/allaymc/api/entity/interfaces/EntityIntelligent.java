package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.component.EntityAIComponent;
import org.allaymc.api.entity.component.EntityParallelTickComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;

/**
 * Marker interface for entities that have AI (intelligent entities).
 *
 * @author daoge_cmd
 */
public interface EntityIntelligent extends EntityLiving, EntityAIComponent, EntityPhysicsComponent, EntityParallelTickComponent {
}
