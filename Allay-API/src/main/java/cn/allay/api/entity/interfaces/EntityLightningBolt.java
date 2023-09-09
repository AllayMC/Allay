package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityLightningBolt extends Entity {
  EntityType<EntityLightningBolt> LIGHTNING_BOLT_TYPE = EntityTypeBuilder
          .builder(EntityLightningBolt.class)
          .vanillaEntity(VanillaEntityId.LIGHTNING_BOLT)
          .build();
}
