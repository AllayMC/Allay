package cn.allay.api.entity.impl;

import cn.allay.api.component.annotation.AutoRegister;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.container.impl.*;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.component.impl.attribute.EntityAttributeComponent;
import cn.allay.api.entity.component.impl.attribute.EntityAttributeComponentImpl;
import cn.allay.api.entity.component.impl.base.EntityBaseComponentImpl;
import cn.allay.api.entity.component.impl.container.EntityContainerHolderComponent;
import cn.allay.api.entity.component.impl.container.EntityContainerHolderComponentImpl;
import cn.allay.api.entity.component.impl.container.EntityContainerViewerComponent;
import cn.allay.api.entity.component.impl.container.EntityContainerViewerComponentImpl;
import cn.allay.api.entity.component.impl.playercontroller.EntityPlayerControllerComponent;
import cn.allay.api.entity.component.impl.playercontroller.EntityPlayerControllerComponentImpl;
import cn.allay.api.entity.type.EntityInitInfo;
import org.joml.primitives.AABBd;

import static cn.allay.api.entity.component.impl.attribute.EntityAttributeComponentImpl.basicAttributes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPlayer extends
        Entity,
        EntityAttributeComponent,
        EntityContainerHolderComponent,
        EntityContainerViewerComponent,
        EntityPlayerControllerComponent {
    @AutoRegister
    ComponentProvider<EntityBaseComponentImpl> BASE_COMPONENT =
            ComponentProvider.of(
                    info -> new EntityBaseComponentImpl((EntityInitInfo) info, e -> new AABBd(-0.3, 0, -0.3, 0.3, 1.8, 0.3)),
                    EntityBaseComponentImpl.class
            );

    @AutoRegister
    ComponentProvider<EntityAttributeComponentImpl> ATTRIBUTE_COMPONENT =
            ComponentProvider.of(
                    () -> new EntityAttributeComponentImpl(basicAttributes()),
                    EntityAttributeComponentImpl.class
            );

    @AutoRegister
    ComponentProvider<EntityContainerHolderComponentImpl> CONTAINER_HOLDER_COMPONENT =
            ComponentProvider.of(
                    () -> new EntityContainerHolderComponentImpl(
                            new PlayerInventoryContainer(),
                            new PlayerCursorContainer(),
                            new PlayerCreatedOutputContainer(),
                            new PlayerArmorContainer(),
                            new PlayerOffhandContainer()
                    ),
                    EntityContainerHolderComponentImpl.class
            );

    @AutoRegister
    ComponentProvider<EntityContainerViewerComponentImpl> CONTAINER_VIEWER_COMPONENT =
            ComponentProvider.of(
                    EntityContainerViewerComponentImpl::new,
                    EntityContainerViewerComponentImpl.class
            );

    @AutoRegister
    ComponentProvider<EntityPlayerControllerComponentImpl> PLAYER_CONTROLLER_COMPONENT =
            ComponentProvider.of(
                    EntityPlayerControllerComponentImpl::new,
                    EntityPlayerControllerComponentImpl.class
            );
}
