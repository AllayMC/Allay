package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityItemBaseComponent;
import org.allaymc.api.entity.component.common.EntityAttributeComponent;
import org.allaymc.api.entity.component.common.EntityDamageComponent;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityItem extends Entity, EntityItemBaseComponent, EntityDamageComponent, EntityAttributeComponent {

}
