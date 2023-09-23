package cn.allay.api.entity.interfaces.ghast;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityGhast extends Entity {
  EntityType<EntityGhast> GHAST_TYPE = EntityTypeBuilder
          .builder(EntityGhast.class)
          .vanillaEntity(VanillaEntityId.GHAST)
          .build();
}
