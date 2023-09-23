package cn.allay.api.entity.interfaces.xporb;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityXpOrb extends Entity {
  EntityType<EntityXpOrb> XP_ORB_TYPE = EntityTypeBuilder
          .builder(EntityXpOrb.class)
          .vanillaEntity(VanillaEntityId.XP_ORB)
          .build();
}
