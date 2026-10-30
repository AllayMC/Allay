package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.EntityTntBaseComponent;

/**
 * {@code EntityTnt} represents a primed TNT entity with a remaining fuse and
 * physical movement. The fuse contract controls countdown and explosion
 * attempts, while the physics contract lets primed TNT move before that
 * countdown finishes.
 */
public interface EntityTnt extends Entity, EntityTntBaseComponent, EntityPhysicsComponent {

}
