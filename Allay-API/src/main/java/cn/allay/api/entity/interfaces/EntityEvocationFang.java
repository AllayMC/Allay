package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityEvocationFang extends Entity {
  EntityType<EntityEvocationFang> EVOCATION_FANG_TYPE = EntityTypeBuilder
          .builder(EntityEvocationFang.class)
          .vanillaEntity(VanillaEntityId.EVOCATION_FANG)
          .build();
}
