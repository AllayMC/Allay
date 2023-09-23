package cn.allay.api.entity.interfaces.lingeringpotion;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityLingeringPotion extends Entity {
  EntityType<EntityLingeringPotion> LINGERING_POTION_TYPE = EntityTypeBuilder
          .builder(EntityLingeringPotion.class)
          .vanillaEntity(VanillaEntityId.LINGERING_POTION)
          .build();
}
