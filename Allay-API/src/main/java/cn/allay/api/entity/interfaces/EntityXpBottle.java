package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityXpBottle extends Entity {
  EntityType<EntityXpBottle> XP_BOTTLE_TYPE = EntityTypeBuilder
          .builder(EntityXpBottle.class)
          .vanillaEntity(VanillaEntityId.XP_BOTTLE)
          .build();
}
