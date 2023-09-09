package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityDolphin extends Entity {
  EntityType<EntityDolphin> DOLPHIN_TYPE = EntityTypeBuilder
          .builder(EntityDolphin.class)
          .vanillaEntity(VanillaEntityId.DOLPHIN)
          .build();
}
