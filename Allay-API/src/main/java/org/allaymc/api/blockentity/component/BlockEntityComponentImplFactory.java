package org.allaymc.api.blockentity.component;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.component.interfaces.ComponentProvider;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerViewer;

import java.util.function.Consumer;

/**
 * Allay Project 2023/12/6
 *
 * @author daoge_cmd
 */
public interface BlockEntityComponentImplFactory {

    ApiInstanceHolder<BlockEntityComponentImplFactory> FACTORY = ApiInstanceHolder.of();

    static BlockEntityComponentImplFactory getFactory() {
        return FACTORY.get();
    }

    ComponentProvider<BlockEntityComponent> createBlockEntityBaseComponent();

    ComponentProvider<BlockEntityComponent> createBlockEntityBarrelBaseComponent();

    ComponentProvider<BlockEntityComponent> createBlockEntityContainerHolderComponent(Container container);

    ComponentProvider<BlockEntityComponent> createBlockEntityContainerHolderComponent(Container container,
                                                                                      Consumer<ContainerViewer> onOpenListener,
                                                                                      Consumer<ContainerViewer> onCloseListener);
}
