package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.component.EntityDyeableComponent;
import org.allaymc.api.entity.component.EntityHeadYawComponent;
import org.allaymc.api.entity.component.EntitySheepBaseComponent;

/**
 * {@code EntitySheep} represents a sheep with animal behavior, shearing state,
 * dye color, and independent head rotation. Callers inspect wool availability
 * and color through the sheep-specific components alongside the inherited
 * breeding-item, baby-state, AI, and physics contracts.
 *
 * @author daoge_cmd
 */
public interface EntitySheep extends EntityAnimal, EntitySheepBaseComponent, EntityDyeableComponent, EntityHeadYawComponent {
}
