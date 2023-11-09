package org.allaymc.api.entity.interfaces.tntminecart;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityTntMinecart extends Entity {
  EntityType<EntityTntMinecart> TNT_MINECART_TYPE = EntityTypeBuilder
          .builder(EntityTntMinecart.class)
          .vanillaEntity(VanillaEntityId.TNT_MINECART)
          .build();
}
