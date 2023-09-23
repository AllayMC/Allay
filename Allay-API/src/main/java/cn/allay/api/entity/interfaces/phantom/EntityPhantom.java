package cn.allay.api.entity.interfaces.phantom;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPhantom extends Entity {
  EntityType<EntityPhantom> PHANTOM_TYPE = EntityTypeBuilder
          .builder(EntityPhantom.class)
          .vanillaEntity(VanillaEntityId.PHANTOM)
          .build();
}
