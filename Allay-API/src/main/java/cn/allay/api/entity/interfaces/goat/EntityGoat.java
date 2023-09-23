package cn.allay.api.entity.interfaces.goat;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityGoat extends Entity {
  EntityType<EntityGoat> GOAT_TYPE = EntityTypeBuilder
          .builder(EntityGoat.class)
          .vanillaEntity(VanillaEntityId.GOAT)
          .build();
}
