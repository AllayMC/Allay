package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityLivingComponent;

/**
 * EntityLiving represents an entity which is a living entity. Every living entity should
 * extend this interface.
 *
 * @author daoge_cmd
 */
public interface EntityLiving extends Entity, EntityLivingComponent {
}
