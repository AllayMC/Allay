package cn.allay.api.entity.interfaces.zoglin;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityZoglin extends Entity {
  EntityType<EntityZoglin> ZOGLIN_TYPE = EntityTypeBuilder
          .builder(EntityZoglin.class)
          .vanillaEntity(VanillaEntityId.ZOGLIN)
          .build();
}
