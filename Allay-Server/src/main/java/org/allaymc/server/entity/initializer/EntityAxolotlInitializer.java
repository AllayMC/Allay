package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityAxolotl;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityAxolotlInitializer {
  static void init() {
    EntityTypes.AXOLOTL_TYPE = EntityTypeBuilder
            .builder(EntityAxolotl.class)
            .vanillaEntity(VanillaEntityId.AXOLOTL)
            .build();
  }
}
