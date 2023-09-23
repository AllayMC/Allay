package cn.allay.api.entity.interfaces.spider;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntitySpider extends Entity {
  EntityType<EntitySpider> SPIDER_TYPE = EntityTypeBuilder
          .builder(EntitySpider.class)
          .vanillaEntity(VanillaEntityId.SPIDER)
          .build();
}
