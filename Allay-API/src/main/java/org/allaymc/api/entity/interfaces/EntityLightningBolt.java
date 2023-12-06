package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

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
