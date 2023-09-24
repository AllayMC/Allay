package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityCat extends Entity {
  EntityType<EntityCat> CAT_TYPE = EntityTypeBuilder
          .builder(EntityCat.class)
          .vanillaEntity(VanillaEntityId.CAT)
          .build();
}
