package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityChestMinecart extends Entity {
  EntityType<EntityChestMinecart> CHEST_MINECART_TYPE = EntityTypeBuilder
          .builder(EntityChestMinecart.class)
          .vanillaEntity(VanillaEntityId.CHEST_MINECART)
          .build();
}
