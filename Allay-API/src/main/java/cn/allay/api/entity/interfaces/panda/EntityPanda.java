package cn.allay.api.entity.interfaces.panda;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPanda extends Entity {
  EntityType<EntityPanda> PANDA_TYPE = EntityTypeBuilder
          .builder(EntityPanda.class)
          .vanillaEntity(VanillaEntityId.PANDA)
          .build();
}
