package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.component.EntityAIComponent;

/**
 * Marker interface for entities that have AI (intelligent entities).
 * Extends both {@link EntityLiving} and {@link EntityAIComponent}.
 *
 * @author daoge_cmd
 */
public interface EntityIntelligent extends EntityLiving, EntityAIComponent {
}
