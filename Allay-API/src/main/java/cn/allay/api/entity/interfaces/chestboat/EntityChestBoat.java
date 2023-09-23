package cn.allay.api.entity.interfaces.chestboat;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityChestBoat extends Entity {
  EntityType<EntityChestBoat> CHEST_BOAT_TYPE = EntityTypeBuilder
          .builder(EntityChestBoat.class)
          .vanillaEntity(VanillaEntityId.CHEST_BOAT)
          .build();
}
