package org.allaymc.api.entity.interfaces.axolotl;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityAxolotl extends Entity {
  EntityType<EntityAxolotl> AXOLOTL_TYPE = EntityTypeBuilder
          .builder(EntityAxolotl.class)
          .vanillaEntity(VanillaEntityId.AXOLOTL)
          .build();
}
