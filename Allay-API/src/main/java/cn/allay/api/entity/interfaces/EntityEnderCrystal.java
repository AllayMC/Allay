package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

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
