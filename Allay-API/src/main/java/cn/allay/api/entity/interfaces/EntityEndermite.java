package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityEndermite extends Entity {
  EntityType<EntityEndermite> ENDERMITE_TYPE = EntityTypeBuilder
          .builder(EntityEndermite.class)
          .vanillaEntity(VanillaEntityId.ENDERMITE)
          .build();
}
