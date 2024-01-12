package org.allaymc.server.entity.component;

import org.allaymc.api.component.interfaces.ComponentProvider;
import org.allaymc.api.component.interfaces.ComponentProvider.SimpleComponentProvider;
import org.allaymc.api.container.Container;
import org.allaymc.api.entity.attribute.AttributeType;
import org.allaymc.api.entity.component.EntityComponent;
import org.allaymc.api.entity.component.EntityComponentImplFactory;
import org.allaymc.api.entity.init.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityItem;
import org.allaymc.api.item.ItemStack;
import org.allaymc.server.entity.component.common.EntityAttributeComponentImpl;
import org.allaymc.server.entity.component.common.EntityBaseComponentImpl;
import org.allaymc.server.entity.component.common.EntityContainerHolderComponentImpl;
import org.allaymc.server.entity.component.common.EntityDamageComponentImpl;
import org.allaymc.server.entity.component.item.EntityItemBaseComponentImpl;
import org.allaymc.server.entity.component.player.EntityPlayerBaseComponentImpl;
import org.allaymc.server.entity.component.player.EntityPlayerContainerHolderComponentImpl;
import org.allaymc.server.entity.component.player.EntityPlayerContainerViewerComponentImpl;
import org.allaymc.server.entity.component.player.EntityPlayerNetworkComponentImpl;
import org.joml.primitives.AABBfc;

import java.util.List;

/**
 * Allay Project 2023/12/6
 *
 * @author daoge_cmd
 */
public class AllayEntityComponentImplFactory implements EntityComponentImplFactory {
    @Override
    public ComponentProvider<EntityComponent> createEntityBaseComponent(AABBfc aabb) {
        return new SimpleComponentProvider<EntityComponent, EntityInitInfo<?>>(info -> new EntityBaseComponentImpl<>(info, aabb), EntityBaseComponentImpl.class);
    }

    @Override
    public ComponentProvider<EntityComponent> createEntityPlayerBaseComponent() {
        return new SimpleComponentProvider<>(EntityPlayerBaseComponentImpl::new, EntityPlayerBaseComponentImpl.class);
    }

    @Override
    public ComponentProvider<EntityComponent> createEntityItemBaseComponent(ItemStack itemStack) {
        return new SimpleComponentProvider<EntityComponent, EntityInitInfo<EntityItem>>(info -> new EntityItemBaseComponentImpl(info, itemStack), EntityItemBaseComponentImpl.class);
    }

    @Override
    public ComponentProvider<EntityComponent> createEntityAttributeComponent(List<AttributeType> attributeTypes) {
        return new SimpleComponentProvider<>(info -> new EntityAttributeComponentImpl(attributeTypes), EntityAttributeComponentImpl.class);
    }

    @Override
    public ComponentProvider<EntityComponent> createEntityContainerHolderComponent(Container... containers) {
        return new SimpleComponentProvider<>(info -> new EntityContainerHolderComponentImpl(containers), EntityContainerHolderComponentImpl.class);
    }

    @Override
    public ComponentProvider<EntityComponent> createEntityPlayerContainerHolderComponent() {
        return new SimpleComponentProvider<>(info -> new EntityPlayerContainerHolderComponentImpl(), EntityPlayerContainerHolderComponentImpl.class);
    }

    @Override
    public ComponentProvider<EntityComponent> createEntityPlayerContainerViewerComponent() {
        return new SimpleComponentProvider<>(info -> new EntityPlayerContainerViewerComponentImpl(), EntityPlayerContainerViewerComponentImpl.class);
    }

    @Override
    public ComponentProvider<EntityComponent> createEntityPlayerNetworkComponent() {
        return new SimpleComponentProvider<>(info -> new EntityPlayerNetworkComponentImpl(), EntityPlayerNetworkComponentImpl.class);
    }

    @Override
    public ComponentProvider<EntityComponent> createEntityDamageComponent() {
        return new SimpleComponentProvider<>(info -> new EntityDamageComponentImpl(), EntityDamageComponentImpl.class);
    }
}
