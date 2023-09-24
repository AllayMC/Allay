package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityTntMinecart extends Entity {
  EntityType<EntityTntMinecart> TNT_MINECART_TYPE = EntityTypeBuilder
          .builder(EntityTntMinecart.class)
          .vanillaEntity(VanillaEntityId.TNT_MINECART)
          .build();
}
