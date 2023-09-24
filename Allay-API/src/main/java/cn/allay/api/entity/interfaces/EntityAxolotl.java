package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityAxolotl extends Entity {
  EntityType<EntityAxolotl> AXOLOTL_TYPE = EntityTypeBuilder
          .builder(EntityAxolotl.class)
          .vanillaEntity(VanillaEntityId.AXOLOTL)
          .build();
}
