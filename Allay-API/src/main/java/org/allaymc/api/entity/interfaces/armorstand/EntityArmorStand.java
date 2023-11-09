package org.allaymc.api.entity.interfaces.armorstand;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityArmorStand extends Entity {
  EntityType<EntityArmorStand> ARMOR_STAND_TYPE = EntityTypeBuilder
          .builder(EntityArmorStand.class)
          .vanillaEntity(VanillaEntityId.ARMOR_STAND)
          .build();
}
