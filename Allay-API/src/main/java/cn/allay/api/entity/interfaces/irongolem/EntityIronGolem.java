package cn.allay.api.entity.interfaces.irongolem;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityIronGolem extends Entity {
  EntityType<EntityIronGolem> IRON_GOLEM_TYPE = EntityTypeBuilder
          .builder(EntityIronGolem.class)
          .vanillaEntity(VanillaEntityId.IRON_GOLEM)
          .build();
}
