package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityFishingHook;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityFishingHookInitializer {
  static void init() {
    EntityTypes.FISHING_HOOK_TYPE = EntityTypeBuilder
            .builder(EntityFishingHook.class)
            .vanillaEntity(VanillaEntityId.FISHING_HOOK)
            .build();
  }
}
