package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.component.EntityAIComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;

/**
 * Marker interface for entities that have AI (intelligent entities).
 * Extends {@link EntityLiving}, {@link EntityAIComponent}, and {@link EntityPhysicsComponent}.
 *
 * @author daoge_cmd
 */
public interface EntityIntelligent extends EntityLiving, EntityAIComponent, EntityPhysicsComponent {
}
