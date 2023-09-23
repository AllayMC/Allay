package cn.allay.api.entity.interfaces.armorstand;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

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
