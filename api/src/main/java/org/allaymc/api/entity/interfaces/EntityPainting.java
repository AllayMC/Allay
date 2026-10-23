package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.component.EntityPaintingBaseComponent;

/**
 * {@code EntityPainting} represents a painting with living state and a selected
 * painting variant. {@link EntityPaintingBaseComponent} exposes the variant
 * that controls the artwork, while {@link EntityLiving} supplies lifecycle,
 * health, and damage operations for the same painting entity.
 */
public interface EntityPainting extends EntityLiving, EntityPaintingBaseComponent {

}
