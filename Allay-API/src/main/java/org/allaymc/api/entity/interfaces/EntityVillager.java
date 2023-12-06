package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityComponentImplFactory;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.joml.primitives.AABBf;

import static org.allaymc.api.entity.component.EntityComponentImplFactory.getFactory;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityVillager extends Entity {
  EntityType<EntityVillager> VILLAGER_TYPE = EntityTypeBuilder
          .builder(EntityVillager.class)
          .vanillaEntity(VanillaEntityId.VILLAGER)
          .addComponent(getFactory().createEntityBaseComponent(new AABBf(-0.3f, 0.0f, -0.3f, 0.3f, 1.8f, 0.3f)))
          .build();
}
