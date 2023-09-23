package cn.allay.api.entity.interfaces.enderman;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityEnderman extends Entity {
  EntityType<EntityEnderman> ENDERMAN_TYPE = EntityTypeBuilder
          .builder(EntityEnderman.class)
          .vanillaEntity(VanillaEntityId.ENDERMAN)
          .build();
}
