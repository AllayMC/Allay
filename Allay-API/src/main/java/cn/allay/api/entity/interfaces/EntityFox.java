package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityFox extends Entity {
  EntityType<EntityFox> FOX_TYPE = EntityTypeBuilder
          .builder(EntityFox.class)
          .vanillaEntity(VanillaEntityId.FOX)
          .build();
}
