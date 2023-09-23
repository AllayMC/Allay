package cn.allay.api.entity.interfaces.agent;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityAgent extends Entity {
  EntityType<EntityAgent> AGENT_TYPE = EntityTypeBuilder
          .builder(EntityAgent.class)
          .vanillaEntity(VanillaEntityId.AGENT)
          .build();
}
