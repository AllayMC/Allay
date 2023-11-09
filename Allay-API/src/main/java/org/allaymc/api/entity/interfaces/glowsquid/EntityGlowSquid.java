package org.allaymc.api.entity.interfaces.glowsquid;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

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
