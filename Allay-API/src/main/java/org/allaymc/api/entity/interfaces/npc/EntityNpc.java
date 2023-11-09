package org.allaymc.api.entity.interfaces.npc;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

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
