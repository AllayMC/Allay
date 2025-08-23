package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.component.EntityArrowBaseComponent;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.entity.component.attribute.EntityAttributeComponent;

public interface EntityArrow extends EntityProjectile, EntityArrowBaseComponent, EntityDamageComponent, EntityAttributeComponent {
}
