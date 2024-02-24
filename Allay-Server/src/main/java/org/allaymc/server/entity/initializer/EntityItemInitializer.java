package org.allaymc.server.entity.initializer;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.interfaces.EntityItem;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.server.entity.component.item.EntityItemBaseComponentImpl;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityItemInitializer {
  static void init() {
    EntityTypes.ITEM_TYPE = EntityTypeBuilder
            .builder(EntityItem.class)
            .vanillaEntity(VanillaEntityId.ITEM)
            .addComponent(EntityItemBaseComponentImpl::new, EntityItemBaseComponentImpl.class)
            .build();
  }
}
