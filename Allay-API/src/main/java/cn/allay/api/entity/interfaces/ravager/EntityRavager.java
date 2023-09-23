package cn.allay.api.entity.interfaces.ravager;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityRavager extends Entity {
  EntityType<EntityRavager> RAVAGER_TYPE = EntityTypeBuilder
          .builder(EntityRavager.class)
          .vanillaEntity(VanillaEntityId.RAVAGER)
          .build();
}
