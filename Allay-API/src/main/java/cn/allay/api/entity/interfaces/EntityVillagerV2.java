package cn.allay.api.entity.interfaces;

import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.component.base.EntityBaseComponentImpl;
import cn.allay.api.entity.init.EntityInitInfo;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;
import org.joml.primitives.AABBf;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityVillagerV2 extends Entity {
  EntityType<EntityVillagerV2> VILLAGER_V2_TYPE = EntityTypeBuilder
          .builder(EntityVillagerV2.class)
          .vanillaEntity(VanillaEntityId.VILLAGER_V2)
          .addComponent(
                  ComponentProvider.of(
                          info -> new EntityBaseComponentImpl<>(
                                  (EntityInitInfo<?>) info,
                                  //TODO: 小村民
                                  e -> new AABBf(-0.3f, 0.0f, -0.3f, 0.3f, 1.8f, 0.3f)
                          ), EntityBaseComponentImpl.class
                  ))
          .build();
}
