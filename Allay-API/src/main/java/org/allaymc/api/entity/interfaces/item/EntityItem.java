package org.allaymc.api.entity.interfaces.item;

import org.allaymc.api.data.VanillaEntityId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypeBuilder;
import org.joml.primitives.AABBf;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityItem extends Entity, EntityItemBaseComponent {
  EntityType<EntityItem> ITEM_TYPE = EntityTypeBuilder
          .builder(EntityItem.class)
          .vanillaEntity(VanillaEntityId.ITEM)
          .addComponent(
                  info -> new EntityItemBaseComponentImpl(
                          info,
                          null
                  ),
                  EntityItemBaseComponentImpl.class
          )
          .build();

}
