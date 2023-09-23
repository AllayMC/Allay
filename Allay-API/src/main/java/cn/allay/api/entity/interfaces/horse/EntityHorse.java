package cn.allay.api.entity.interfaces.horse;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityHorse extends Entity {
  EntityType<EntityHorse> HORSE_TYPE = EntityTypeBuilder
          .builder(EntityHorse.class)
          .vanillaEntity(VanillaEntityId.HORSE)
          .build();
}
