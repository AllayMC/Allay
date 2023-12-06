package org.allaymc.api.entity.interfaces;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.item.EntityItemBaseComponent;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;

import static org.allaymc.api.entity.component.EntityComponentImplFactory.getFactory;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityItem extends Entity, EntityItemBaseComponent {
  EntityType<EntityItem> ITEM_TYPE = EntityTypeBuilder
          .builder(EntityItem.class)
          .vanillaEntity(VanillaEntityId.ITEM)
          .addComponent(getFactory().createEntityItemBaseComponent(null))
          .build();

}
