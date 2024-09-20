package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.entity.component.EntityItemBaseComponent;
import org.allaymc.api.entity.component.attribute.EntityAttributeComponent;

public interface EntityItem extends Entity, EntityItemBaseComponent, EntityDamageComponent, EntityAttributeComponent {

}
