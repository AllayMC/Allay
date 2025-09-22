package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.component.EntityAgeComponent;
import org.allaymc.api.entity.component.EntityItemBaseComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.attribute.EntityAttributeComponent;

public interface EntityItem extends
        EntityLiving,
        EntityItemBaseComponent,
        EntityAttributeComponent,
        EntityPhysicsComponent,
        EntityAgeComponent {

}
