package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.component.EntityAnimalComponent;

/**
 * Marker interface for animal entities that have AI and can breed.
 *
 * @author daoge_cmd
 */
public interface EntityAnimal extends EntityIntelligent, EntityAnimalComponent {
}
