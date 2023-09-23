package cn.allay.api.entity.interfaces.npc;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityNpc extends Entity {
  EntityType<EntityNpc> NPC_TYPE = EntityTypeBuilder
          .builder(EntityNpc.class)
          .vanillaEntity(VanillaEntityId.NPC)
          .build();
}
