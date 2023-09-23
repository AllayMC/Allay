package cn.allay.api.entity.interfaces.sheep;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntitySheep extends Entity {
  EntityType<EntitySheep> SHEEP_TYPE = EntityTypeBuilder
          .builder(EntitySheep.class)
          .vanillaEntity(VanillaEntityId.SHEEP)
          .build();
}
