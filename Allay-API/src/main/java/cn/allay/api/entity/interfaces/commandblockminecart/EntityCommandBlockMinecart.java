package cn.allay.api.entity.interfaces.commandblockminecart;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityCommandBlockMinecart extends Entity {
  EntityType<EntityCommandBlockMinecart> COMMAND_BLOCK_MINECART_TYPE = EntityTypeBuilder
          .builder(EntityCommandBlockMinecart.class)
          .vanillaEntity(VanillaEntityId.COMMAND_BLOCK_MINECART)
          .build();
}
