package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityFallingBlockBaseComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;

/**
 * {@code EntityFallingBlock} represents a block state traveling through the
 * world as an entity. The combined block-state and physics contracts let
 * landing handling choose placement or an item drop after the falling entity
 * finishes its travel.
 */
public interface EntityFallingBlock extends Entity, EntityFallingBlockBaseComponent, EntityPhysicsComponent {

}
