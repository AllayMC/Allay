package cn.allay.api.entity.interfaces.allay;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityAllay extends Entity {
  EntityType<EntityAllay> ALLAY_TYPE = EntityTypeBuilder
          .builder(EntityAllay.class)
          .vanillaEntity(VanillaEntityId.ALLAY)
          .build();
}
