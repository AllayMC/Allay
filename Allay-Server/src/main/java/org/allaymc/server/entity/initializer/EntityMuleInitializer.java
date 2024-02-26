package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityMule;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityMuleInitializer {
  static void init() {
    EntityTypes.MULE_TYPE = EntityTypeBuilder
            .builder(EntityMule.class)
            .vanillaEntity(VanillaEntityId.MULE)
            .build();
  }
}