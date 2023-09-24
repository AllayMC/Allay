package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

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
