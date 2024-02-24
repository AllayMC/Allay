package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityCaveSpider;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityCaveSpiderInitializer {
  static void init() {
    EntityTypes.CAVE_SPIDER_TYPE = EntityTypeBuilder
            .builder(EntityCaveSpider.class)
            .vanillaEntity(VanillaEntityId.CAVE_SPIDER)
            .build();
  }
}
