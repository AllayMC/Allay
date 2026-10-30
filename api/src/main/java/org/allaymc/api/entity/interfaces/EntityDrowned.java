package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityUndeadComponent;

/**
 * {@code EntityDrowned} identifies drowned entities through the common entity
 * and undead component contracts. {@link Entity} supplies the entity lifecycle,
 * while {@link EntityUndeadComponent} exposes the sunlight-ignition rule used
 * when consumers apply behavior to undead entities. The default sunlight rule
 * permits ignition.
 */
public interface EntityDrowned extends Entity, EntityUndeadComponent {

}
