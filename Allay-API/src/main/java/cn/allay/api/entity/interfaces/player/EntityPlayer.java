package cn.allay.api.entity.interfaces.player;

import cn.allay.api.container.impl.*;
import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.component.attribute.EntityAttributeComponent;
import cn.allay.api.entity.component.attribute.EntityAttributeComponentImpl;
import cn.allay.api.entity.component.container.EntityContainerHolderComponent;
import cn.allay.api.entity.component.container.EntityContainerHolderComponentImpl;
import cn.allay.api.entity.component.container.EntityContainerViewerComponent;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;
import org.joml.primitives.AABBf;

import static cn.allay.api.entity.component.attribute.EntityAttributeComponentImpl.basicAttributes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPlayer extends
        Entity,
        EntityPlayerBaseComponent,
        EntityAttributeComponent,
        EntityContainerHolderComponent,
        EntityContainerViewerComponent {
    EntityType<EntityPlayer> PLAYER_TYPE = EntityTypeBuilder
            .builder(EntityPlayer.class)
            .vanillaEntity(VanillaEntityId.PLAYER)
            .addComponent(
                    info -> new EntityPlayerBaseComponentImpl(info, e -> new AABBf(-0.3f, 0.0f, -0.3f, 0.3f, 1.8f, 0.3f)),
                    EntityPlayerBaseComponentImpl.class
            )
            .addComponent(info -> new EntityAttributeComponentImpl(basicAttributes()), EntityAttributeComponentImpl.class)
            .addComponent(info -> new EntityContainerHolderComponentImpl(
                            new PlayerInventoryContainer(((EntityPlayerInitInfo) info).getClient()),
                            new PlayerCursorContainer(),
                            new PlayerCreatedOutputContainer(),
                            new PlayerArmorContainer(),
                            new PlayerOffhandContainer()
                    ),
                    EntityContainerHolderComponentImpl.class)
            .addComponent(info -> new EntityPlayerContainerViewerComponentImpl(), EntityPlayerContainerViewerComponentImpl.class)
            .build();
}

