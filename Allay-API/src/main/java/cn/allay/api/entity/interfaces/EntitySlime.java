package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntitySlime extends Entity {
  EntityType<EntitySlime> SLIME_TYPE = EntityTypeBuilder
          .builder(EntitySlime.class)
          .vanillaEntity(VanillaEntityId.SLIME)
          .build();
}
