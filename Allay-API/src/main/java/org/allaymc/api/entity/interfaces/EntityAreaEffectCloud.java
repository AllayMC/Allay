package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityAreaEffectCloud extends Entity {
  EntityType<EntityAreaEffectCloud> AREA_EFFECT_CLOUD_TYPE = EntityTypeBuilder
          .builder(EntityAreaEffectCloud.class)
          .vanillaEntity(VanillaEntityId.AREA_EFFECT_CLOUD)
          .build();
}
