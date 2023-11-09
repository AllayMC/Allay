package org.allaymc.api.entity.interfaces.cavespider;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityCaveSpider extends Entity {
  EntityType<EntityCaveSpider> CAVE_SPIDER_TYPE = EntityTypeBuilder
          .builder(EntityCaveSpider.class)
          .vanillaEntity(VanillaEntityId.CAVE_SPIDER)
          .build();
}
