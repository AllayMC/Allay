package org.allaymc.api.entity.interfaces.villager;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.base.EntityBaseComponentImpl;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.joml.primitives.AABBf;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityVillager extends Entity {
    EntityType<EntityVillager> VILLAGER_TYPE = EntityTypeBuilder
            .builder(EntityVillager.class)
            .vanillaEntity(VanillaEntityId.VILLAGER)
            .addComponent(
                    info -> new EntityBaseComponentImpl<>(info,//TODO: 小村民
                            new AABBf(-0.3f, 0.0f, -0.3f, 0.3f, 1.8f, 0.3f)
                    ), EntityBaseComponentImpl.class
            )
            .build();
}
