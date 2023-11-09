package org.allaymc.api.entity.interfaces.elderguardianghost;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

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
