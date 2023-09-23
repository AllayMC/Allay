package cn.allay.api.entity.interfaces.shulkerbullet;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityShulkerBullet extends Entity {
  EntityType<EntityShulkerBullet> SHULKER_BULLET_TYPE = EntityTypeBuilder
          .builder(EntityShulkerBullet.class)
          .vanillaEntity(VanillaEntityId.SHULKER_BULLET)
          .build();
}
