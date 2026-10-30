package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.component.EntityPhysicsComponent;

/**
 * {@code EntityVillagerV2} represents the newer villager entity variant with
 * living-state and physics capabilities. {@link EntityLiving} supplies health,
 * damage, and entity lifecycle operations, while {@link EntityPhysicsComponent}
 * provides movement state and physics hooks for the same villager entity.
 */
public interface EntityVillagerV2 extends EntityLiving, EntityPhysicsComponent {
}
