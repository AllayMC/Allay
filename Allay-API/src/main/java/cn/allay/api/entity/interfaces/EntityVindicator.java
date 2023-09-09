package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityVindicator extends Entity {
  EntityType<EntityVindicator> VINDICATOR_TYPE = EntityTypeBuilder
          .builder(EntityVindicator.class)
          .vanillaEntity(VanillaEntityId.VINDICATOR)
          .build();
}
