package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.component.EntityAnimalComponent;
import org.allaymc.api.entity.component.EntityBabyComponent;

/**
 * {@code EntityAnimal} represents an animal with intelligent living behavior,
 * breeding-item recognition, and baby state. {@link EntityIntelligent} supplies
 * AI and movement capabilities, while {@link EntityAnimalComponent} identifies
 * acceptable breeding items and {@link EntityBabyComponent} exposes the current
 * baby flag for the same animal instance.
 *
 * @author daoge_cmd
 */
public interface EntityAnimal extends EntityIntelligent, EntityAnimalComponent, EntityBabyComponent {
}
