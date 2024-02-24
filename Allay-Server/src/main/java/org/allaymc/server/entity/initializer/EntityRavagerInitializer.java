package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityRavager;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityRavagerInitializer {
  static void init() {
    EntityTypes.RAVAGER_TYPE = EntityTypeBuilder
            .builder(EntityRavager.class)
            .vanillaEntity(VanillaEntityId.RAVAGER)
            .build();
  }
}
