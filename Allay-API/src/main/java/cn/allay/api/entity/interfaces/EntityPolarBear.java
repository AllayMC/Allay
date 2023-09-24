package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPolarBear extends Entity {
  EntityType<EntityPolarBear> POLAR_BEAR_TYPE = EntityTypeBuilder
          .builder(EntityPolarBear.class)
          .vanillaEntity(VanillaEntityId.POLAR_BEAR)
          .build();
}
