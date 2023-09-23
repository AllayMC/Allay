package cn.allay.api.entity.interfaces.strider;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityStrider extends Entity {
  EntityType<EntityStrider> STRIDER_TYPE = EntityTypeBuilder
          .builder(EntityStrider.class)
          .vanillaEntity(VanillaEntityId.STRIDER)
          .build();
}
