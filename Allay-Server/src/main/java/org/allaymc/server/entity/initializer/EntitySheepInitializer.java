package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntitySheep;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntitySheepInitializer {
  static void init() {
    EntityTypes.SHEEP_TYPE = EntityTypeBuilder
            .builder(EntitySheep.class)
            .vanillaEntity(VanillaEntityId.SHEEP)
            .build();
  }
}
