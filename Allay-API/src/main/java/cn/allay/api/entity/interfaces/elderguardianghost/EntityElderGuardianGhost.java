package cn.allay.api.entity.interfaces.elderguardianghost;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityElderGuardianGhost extends Entity {
  EntityType<EntityElderGuardianGhost> ELDER_GUARDIAN_GHOST_TYPE = EntityTypeBuilder
          .builder(EntityElderGuardianGhost.class)
          .vanillaEntity(VanillaEntityId.ELDER_GUARDIAN_GHOST)
          .build();
}
