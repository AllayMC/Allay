package cn.allay.api.entity.interfaces.tadpole;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityTadpole extends Entity {
  EntityType<EntityTadpole> TADPOLE_TYPE = EntityTypeBuilder
          .builder(EntityTadpole.class)
          .vanillaEntity(VanillaEntityId.TADPOLE)
          .build();
}
