package org.allaymc.api.entity.interfaces.eyeofendersignal;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

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
