package cn.allay.api.entity.interfaces.icebomb;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityIceBomb extends Entity {
  EntityType<EntityIceBomb> ICE_BOMB_TYPE = EntityTypeBuilder
          .builder(EntityIceBomb.class)
          .vanillaEntity(VanillaEntityId.ICE_BOMB)
          .build();
}
