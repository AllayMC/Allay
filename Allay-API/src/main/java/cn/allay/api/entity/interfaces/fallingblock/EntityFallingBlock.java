package cn.allay.api.entity.interfaces.fallingblock;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityFallingBlock extends Entity {
  EntityType<EntityFallingBlock> FALLING_BLOCK_TYPE = EntityTypeBuilder
          .builder(EntityFallingBlock.class)
          .vanillaEntity(VanillaEntityId.FALLING_BLOCK)
          .build();
}
