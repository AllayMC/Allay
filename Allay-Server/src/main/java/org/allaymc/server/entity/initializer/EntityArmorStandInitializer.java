package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityArmorStand;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityArmorStandInitializer {
  static void init() {
    EntityTypes.ARMOR_STAND_TYPE = EntityTypeBuilder
            .builder(EntityArmorStand.class)
            .vanillaEntity(VanillaEntityId.ARMOR_STAND)
            .build();
  }
}
