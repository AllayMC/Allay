package cn.allay.api.entity.interfaces.egg;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityEgg extends Entity {
  EntityType<EntityEgg> EGG_TYPE = EntityTypeBuilder
          .builder(EntityEgg.class)
          .vanillaEntity(VanillaEntityId.EGG)
          .build();
}
