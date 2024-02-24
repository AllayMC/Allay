package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntitySpider;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntitySpiderInitializer {
  static void init() {
    EntityTypes.SPIDER_TYPE = EntityTypeBuilder
            .builder(EntitySpider.class)
            .vanillaEntity(VanillaEntityId.SPIDER)
            .build();
  }
}
