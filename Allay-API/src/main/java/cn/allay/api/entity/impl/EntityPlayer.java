package cn.allay.api.entity.impl;

import cn.allay.api.component.annotation.AutoRegister;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.component.impl.attribute.EntityAttributeComponent;
import cn.allay.api.entity.component.impl.attribute.EntityAttributeComponentImpl;
import cn.allay.api.entity.component.impl.inventory.EntityInventoryHolderComponent;
import cn.allay.api.entity.component.impl.inventory.EntityInventoryHolderComponentImpl;
import cn.allay.api.entity.component.impl.inventory.EntityInventoryViewerComponent;
import cn.allay.api.entity.component.impl.inventory.EntityInventoryViewerComponentImpl;
import cn.allay.api.entity.component.impl.playercontroller.EntityPlayerControllerComponent;
import cn.allay.api.entity.component.impl.playercontroller.EntityPlayerControllerComponentImpl;
import cn.allay.api.inventory.impl.PlayerArmorInventory;
import cn.allay.api.inventory.impl.PlayerCursorInventory;
import cn.allay.api.inventory.impl.PlayerInventory;
import cn.allay.api.inventory.impl.PlayerOffhandInventory;

import static cn.allay.api.entity.component.impl.attribute.EntityAttributeComponentImpl.basicAttributes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPlayer extends
        Entity,
        EntityAttributeComponent,
        EntityInventoryHolderComponent,
        EntityInventoryViewerComponent,
        EntityPlayerControllerComponent {
    @AutoRegister
    ComponentProvider<EntityAttributeComponentImpl> ATTRIBUTE_COMPONENT =
            ComponentProvider.of(
                    () -> new EntityAttributeComponentImpl(basicAttributes()),
                    EntityAttributeComponentImpl.class
            );

    @AutoRegister
    ComponentProvider<EntityInventoryHolderComponentImpl> INVENTORY_HOLDER_COMPONENT =
            ComponentProvider.of(
                    () -> new EntityInventoryHolderComponentImpl(
                            new PlayerInventory(),
                            new PlayerCursorInventory(),
                            new PlayerArmorInventory(),
                            new PlayerOffhandInventory()
                    ),
                    EntityInventoryHolderComponentImpl.class
            );

    @AutoRegister
    ComponentProvider<EntityInventoryViewerComponentImpl> INVENTORY_VIEWER_COMPONENT =
            ComponentProvider.of(
                    EntityInventoryViewerComponentImpl::new,
                    EntityInventoryViewerComponentImpl.class
            );

    @AutoRegister
    ComponentProvider<EntityPlayerControllerComponentImpl> PLAYER_CONTROLLER_COMPONENT =
            ComponentProvider.of(
                    EntityPlayerControllerComponentImpl::new,
                    EntityPlayerControllerComponentImpl.class
            );
}
