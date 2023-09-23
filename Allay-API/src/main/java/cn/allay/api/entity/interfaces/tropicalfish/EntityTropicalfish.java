package cn.allay.api.entity.interfaces.tropicalfish;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityTropicalfish extends Entity {
  EntityType<EntityTropicalfish> TROPICALFISH_TYPE = EntityTypeBuilder
          .builder(EntityTropicalfish.class)
          .vanillaEntity(VanillaEntityId.TROPICALFISH)
          .build();
}
