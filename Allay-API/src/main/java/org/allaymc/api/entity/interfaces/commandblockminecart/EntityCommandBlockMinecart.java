package org.allaymc.api.entity.interfaces.commandblockminecart;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityCommandBlockMinecart extends Entity {
  EntityType<EntityCommandBlockMinecart> COMMAND_BLOCK_MINECART_TYPE = EntityTypeBuilder
          .builder(EntityCommandBlockMinecart.class)
          .vanillaEntity(VanillaEntityId.COMMAND_BLOCK_MINECART)
          .build();
}