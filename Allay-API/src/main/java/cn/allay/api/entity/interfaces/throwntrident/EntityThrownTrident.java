package cn.allay.api.entity.interfaces.throwntrident;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityThrownTrident extends Entity {
  EntityType<EntityThrownTrident> THROWN_TRIDENT_TYPE = EntityTypeBuilder
          .builder(EntityThrownTrident.class)
          .vanillaEntity(VanillaEntityId.THROWN_TRIDENT)
          .build();
}
