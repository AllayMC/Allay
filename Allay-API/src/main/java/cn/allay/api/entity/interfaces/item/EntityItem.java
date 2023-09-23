package cn.allay.api.entity.interfaces.item;

import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;
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
                          e -> new AABBf(-0.125f, 0.0f, -0.125f, 0.125f, 0.25f, 0.125f),
                          null
                  ),
                  EntityItemBaseComponentImpl.class
          )
          .build();

}
