package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.component.EntityArmorStandBaseComponent;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;

/**
 * EntityArmorStand represents an armor stand entity.
 *
 * @author daoge_cmd
 */
public interface EntityArmorStand extends EntityLiving, EntityPhysicsComponent, EntityArmorStandBaseComponent, EntityContainerHolderComponent {

}
