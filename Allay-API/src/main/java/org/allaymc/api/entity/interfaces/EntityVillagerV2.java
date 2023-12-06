package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.joml.primitives.AABBf;

import static org.allaymc.api.entity.component.EntityComponentImplFactory.getFactory;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityVillagerV2 extends Entity {
  EntityType<EntityVillagerV2> VILLAGER_V2_TYPE = EntityTypeBuilder
          .builder(EntityVillagerV2.class)
          .vanillaEntity(VanillaEntityId.VILLAGER_V2)
          .addComponent(getFactory().createEntityBaseComponent(new AABBf(-0.3f, 0.0f, -0.3f, 0.3f, 1.8f, 0.3f)))
          .build();
}
