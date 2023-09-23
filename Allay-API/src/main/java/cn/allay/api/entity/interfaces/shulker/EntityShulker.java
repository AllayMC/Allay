package cn.allay.api.entity.interfaces.shulker;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityShulker extends Entity {
  EntityType<EntityShulker> SHULKER_TYPE = EntityTypeBuilder
          .builder(EntityShulker.class)
          .vanillaEntity(VanillaEntityId.SHULKER)
          .build();
}
