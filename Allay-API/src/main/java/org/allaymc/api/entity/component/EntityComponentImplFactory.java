package org.allaymc.api.entity.component;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.component.interfaces.ComponentProvider;
import org.allaymc.api.container.Container;
import org.allaymc.api.entity.attribute.AttributeType;
import org.allaymc.api.item.ItemStack;
import org.joml.primitives.AABBfc;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Allay Project 2023/12/6
 *
 * @author daoge_cmd
 */
public interface EntityComponentImplFactory {
    ApiInstanceHolder<EntityComponentImplFactory> FACTORY = ApiInstanceHolder.of();

    static EntityComponentImplFactory getFactory() {
        return FACTORY.get();
    }

    ComponentProvider<EntityComponent> createEntityBaseComponent(AABBfc aabb);

    ComponentProvider<EntityComponent> createEntityPlayerBaseComponent();

    ComponentProvider<EntityComponent> createEntityItemBaseComponent(@Nullable ItemStack itemStack);

    ComponentProvider<EntityComponent> createEntityAttributeComponent(List<AttributeType> attributeTypes);

    ComponentProvider<EntityComponent> createEntityContainerHolderComponent(Container... containers);

    ComponentProvider<EntityComponent> createEntityPlayerContainerHolderComponent();

    ComponentProvider<EntityComponent> createEntityPlayerContainerViewerComponent();

    ComponentProvider<EntityComponent> createEntityPlayerNetworkComponent();
}
