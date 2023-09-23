package cn.allay.api.entity.interfaces.elderguardian;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityElderGuardian extends Entity {
  EntityType<EntityElderGuardian> ELDER_GUARDIAN_TYPE = EntityTypeBuilder
          .builder(EntityElderGuardian.class)
          .vanillaEntity(VanillaEntityId.ELDER_GUARDIAN)
          .build();
}
