package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityMule extends Entity {
  EntityType<EntityMule> MULE_TYPE = EntityTypeBuilder
          .builder(EntityMule.class)
          .vanillaEntity(VanillaEntityId.MULE)
          .build();
}
