package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityAreaEffectCloudBaseComponent;
import org.allaymc.api.entity.component.EntityPotionComponent;

/**
 * {@code EntityAreaEffectCloud} represents a cloud with configurable
 * area-effect timing and potion contents.
 * {@link EntityAreaEffectCloudBaseComponent} exposes radius changes, duration,
 * and reapplication delay, while {@link EntityPotionComponent} identifies the
 * potion associated with the same entity lifecycle throughout the cloud's
 * configured lifetime.
 */
public interface EntityAreaEffectCloud extends Entity, EntityAreaEffectCloudBaseComponent, EntityPotionComponent {

}
