package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityMagmaCube extends Entity {
  EntityType<EntityMagmaCube> MAGMA_CUBE_TYPE = EntityTypeBuilder
          .builder(EntityMagmaCube.class)
          .vanillaEntity(VanillaEntityId.MAGMA_CUBE)
          .build();
}
