package org.allaymc.api.entity.interfaces.villagerv2;

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
public interface EntityVillagerV2 extends Entity {
    EntityType<EntityVillagerV2> VILLAGER_V2_TYPE = EntityTypeBuilder
            .builder(EntityVillagerV2.class)
            .vanillaEntity(VanillaEntityId.VILLAGER_V2)
            .addComponent(info -> new EntityBaseComponentImpl<>(info,//TODO: 小村民
                            new AABBf(-0.3f, 0.0f, -0.3f, 0.3f, 1.8f, 0.3f)
                    ), EntityBaseComponentImpl.class
            )
            .build();
}
