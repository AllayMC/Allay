package org.allaymc.api.entity.interfaces.endercrystal;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityEnderCrystal extends Entity {
  EntityType<EntityEnderCrystal> ENDER_CRYSTAL_TYPE = EntityTypeBuilder
          .builder(EntityEnderCrystal.class)
          .vanillaEntity(VanillaEntityId.ENDER_CRYSTAL)
          .build();
}
