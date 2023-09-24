package cn.allay.api.entity.interfaces;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityEyeOfEnderSignal extends Entity {
  EntityType<EntityEyeOfEnderSignal> EYE_OF_ENDER_SIGNAL_TYPE = EntityTypeBuilder
          .builder(EntityEyeOfEnderSignal.class)
          .vanillaEntity(VanillaEntityId.EYE_OF_ENDER_SIGNAL)
          .build();
}
