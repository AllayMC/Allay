package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityLightningBoltBaseComponent;

/**
 * {@code EntityLightningBolt} represents a lightning strike with configurable
 * gameplay effects and the common entity lifecycle.
 * {@link EntityLightningBoltBaseComponent} exposes whether the strike applies
 * effects such as damage and fire, so callers can control that behavior through
 * the same entity reference.
 */
public interface EntityLightningBolt extends Entity, EntityLightningBoltBaseComponent {

}
