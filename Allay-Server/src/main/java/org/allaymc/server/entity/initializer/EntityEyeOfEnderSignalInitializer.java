package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityEyeOfEnderSignal;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityEyeOfEnderSignalInitializer {
  static void init() {
    EntityTypes.EYE_OF_ENDER_SIGNAL_TYPE = EntityTypeBuilder
            .builder(EntityEyeOfEnderSignal.class)
            .vanillaEntity(VanillaEntityId.EYE_OF_ENDER_SIGNAL)
            .build();
  }
}
