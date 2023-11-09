package org.allaymc.api.entity.interfaces.silverfish;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

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
