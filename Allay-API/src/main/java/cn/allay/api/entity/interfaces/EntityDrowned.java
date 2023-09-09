package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityDrowned extends Entity {
  EntityType<EntityDrowned> DROWNED_TYPE = EntityTypeBuilder
          .builder(EntityDrowned.class)
          .vanillaEntity(VanillaEntityId.DROWNED)
          .build();
}
