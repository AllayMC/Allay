package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityEnderCrystal;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityEnderCrystalInitializer {
  static void init() {
    EntityTypes.ENDER_CRYSTAL_TYPE = EntityTypeBuilder
            .builder(EntityEnderCrystal.class)
            .vanillaEntity(VanillaEntityId.ENDER_CRYSTAL)
            .build();
  }
}
