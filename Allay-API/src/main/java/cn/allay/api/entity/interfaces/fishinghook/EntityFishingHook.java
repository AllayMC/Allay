package cn.allay.api.entity.interfaces.fishinghook;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityFishingHook extends Entity {
  EntityType<EntityFishingHook> FISHING_HOOK_TYPE = EntityTypeBuilder
          .builder(EntityFishingHook.class)
          .vanillaEntity(VanillaEntityId.FISHING_HOOK)
          .build();
}
