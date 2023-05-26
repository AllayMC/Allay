package cn.allay.entity.impl;

import cn.allay.entity.Entity;
import cn.allay.entity.data.VanillaEntityId;
import cn.allay.entity.type.EntityType;
import cn.allay.entity.type.EntityTypeBuilder;
import cn.allay.entity.type.EntityTypeRegistry;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityTntMinecart extends Entity {
    EntityType<EntityTntMinecart> TYPE = EntityTypeBuilder
            .builder(EntityTntMinecart.class)
            .vanillaEntity(VanillaEntityId.TNT_MINECART)
            .addBasicComponents()
            .build().register(EntityTypeRegistry.getRegistry());
}
