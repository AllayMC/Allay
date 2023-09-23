package cn.allay.api.entity.interfaces.silverfish;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntitySilverfish extends Entity {
  EntityType<EntitySilverfish> SILVERFISH_TYPE = EntityTypeBuilder
          .builder(EntitySilverfish.class)
          .vanillaEntity(VanillaEntityId.SILVERFISH)
          .build();
}
