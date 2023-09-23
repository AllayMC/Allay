package cn.allay.api.entity.interfaces.evocationillager;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityEvocationIllager extends Entity {
  EntityType<EntityEvocationIllager> EVOCATION_ILLAGER_TYPE = EntityTypeBuilder
          .builder(EntityEvocationIllager.class)
          .vanillaEntity(VanillaEntityId.EVOCATION_ILLAGER)
          .build();
}
