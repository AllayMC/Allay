package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityEnderDragon extends Entity {
  EntityType<EntityEnderDragon> ENDER_DRAGON_TYPE = EntityTypeBuilder
          .builder(EntityEnderDragon.class)
          .vanillaEntity(VanillaEntityId.ENDER_DRAGON)
          .build();
}
