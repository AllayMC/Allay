package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityCreeper extends Entity {
  EntityType<EntityCreeper> CREEPER_TYPE = EntityTypeBuilder
          .builder(EntityCreeper.class)
          .vanillaEntity(VanillaEntityId.CREEPER)
          .build();
}
