package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityLightningBolt;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityLightningBoltInitializer {
  static void init() {
    EntityTypes.LIGHTNING_BOLT_TYPE = EntityTypeBuilder
            .builder(EntityLightningBolt.class)
            .vanillaEntity(VanillaEntityId.LIGHTNING_BOLT)
            .build();
  }
}
