package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.component.EntityArmorStandBaseComponent;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;

/**
 * {@code EntityArmorStand} represents an armor stand with living state,
 * physics, equipment containers, and pose controls. Callers manipulate the
 * stand's health, movement, held equipment, and armor-stand settings through
 * the inherited component contracts on one entity reference.
 *
 * @author daoge_cmd
 */
public interface EntityArmorStand extends EntityLiving, EntityPhysicsComponent, EntityArmorStandBaseComponent, EntityContainerHolderComponent {

}
