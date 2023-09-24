package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityChestMinecart extends Entity {
  EntityType<EntityChestMinecart> CHEST_MINECART_TYPE = EntityTypeBuilder
          .builder(EntityChestMinecart.class)
          .vanillaEntity(VanillaEntityId.CHEST_MINECART)
          .build();
}
