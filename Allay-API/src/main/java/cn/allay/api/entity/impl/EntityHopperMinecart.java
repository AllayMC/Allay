package cn.allay.api.entity.impl;

import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;
import cn.allay.api.entity.type.EntityTypeRegistry;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.data.VanillaEntityId;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityHopperMinecart extends Entity {
    EntityType<EntityHopperMinecart> TYPE = EntityTypeBuilder
            .builder(EntityHopperMinecart.class)
            .vanillaEntity(VanillaEntityId.HOPPER_MINECART)
            .addBasicComponents()
            .build().register(EntityTypeRegistry.getRegistry());
}
