package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityLivingComponent;

/**
 * {@code EntityLiving} represents an entity with health, damage, and other
 * living state. {@link Entity} supplies the common entity lifecycle, while
 * {@link EntityLivingComponent} exposes the living operations that callers use
 * alongside that lifecycle through the same entity reference.
 *
 * @author daoge_cmd
 */
public interface EntityLiving extends Entity, EntityLivingComponent {
}
