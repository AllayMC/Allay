package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityGlowSquid extends Entity {
  EntityType<EntityGlowSquid> GLOW_SQUID_TYPE = EntityTypeBuilder
          .builder(EntityGlowSquid.class)
          .vanillaEntity(VanillaEntityId.GLOW_SQUID)
          .build();
}
