package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityVillagerV2;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.server.entity.component.common.EntityAttributeComponentImpl;
import org.allaymc.server.entity.component.common.EntityDamageComponentImpl;

import static org.allaymc.api.entity.component.common.EntityAttributeComponent.basicEntityAttributes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityVillagerV2Initializer {
  static void init() {
    EntityTypes.VILLAGER_V2_TYPE = EntityTypeBuilder
            .builder(EntityVillagerV2.class)
            .vanillaEntity(VanillaEntityId.VILLAGER_V2)
            .addComponent(() -> new EntityAttributeComponentImpl(basicEntityAttributes()), EntityAttributeComponentImpl.class)
            .addComponent(EntityDamageComponentImpl::new, EntityDamageComponentImpl.class)
            .build();
  }
}
