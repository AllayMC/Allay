package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityDonkey;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityDonkeyInitializer {
  static void init() {
    EntityTypes.DONKEY_TYPE = EntityTypeBuilder
            .builder(EntityDonkey.class)
            .vanillaEntity(VanillaEntityId.DONKEY)
            .build();
  }
}
