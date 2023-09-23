package cn.allay.api.entity.interfaces.arrow;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityArrow extends Entity {
  EntityType<EntityArrow> ARROW_TYPE = EntityTypeBuilder
          .builder(EntityArrow.class)
          .vanillaEntity(VanillaEntityId.ARROW)
          .build();
}
