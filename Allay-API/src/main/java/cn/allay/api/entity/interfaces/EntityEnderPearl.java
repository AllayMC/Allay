package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityEnderPearl extends Entity {
  EntityType<EntityEnderPearl> ENDER_PEARL_TYPE = EntityTypeBuilder
          .builder(EntityEnderPearl.class)
          .vanillaEntity(VanillaEntityId.ENDER_PEARL)
          .build();
}
