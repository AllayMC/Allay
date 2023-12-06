package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityEnderDragon extends Entity {
  EntityType<EntityEnderDragon> ENDER_DRAGON_TYPE = EntityTypeBuilder
          .builder(EntityEnderDragon.class)
          .vanillaEntity(VanillaEntityId.ENDER_DRAGON)
          .build();
}
