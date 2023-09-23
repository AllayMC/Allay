package cn.allay.api.entity.interfaces.areaeffectcloud;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityAreaEffectCloud extends Entity {
  EntityType<EntityAreaEffectCloud> AREA_EFFECT_CLOUD_TYPE = EntityTypeBuilder
          .builder(EntityAreaEffectCloud.class)
          .vanillaEntity(VanillaEntityId.AREA_EFFECT_CLOUD)
          .build();
}
