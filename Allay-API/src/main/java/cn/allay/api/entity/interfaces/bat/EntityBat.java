package cn.allay.api.entity.interfaces.bat;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityBat extends Entity {
  EntityType<EntityBat> BAT_TYPE = EntityTypeBuilder
          .builder(EntityBat.class)
          .vanillaEntity(VanillaEntityId.BAT)
          .build();
}
