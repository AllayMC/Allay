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
public interface EntityCommandBlockMinecart extends Entity {
    EntityType<EntityCommandBlockMinecart> TYPE = EntityTypeBuilder
            .builder(EntityCommandBlockMinecart.class)
            .vanillaEntity(VanillaEntityId.COMMAND_BLOCK_MINECART)
            .addBasicComponents()
            .build().register(EntityTypeRegistry.getRegistry());
}
