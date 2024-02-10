package org.allaymc.server.blockentity.component;

import org.allaymc.api.blockentity.component.BlockEntityComponent;
import org.allaymc.api.blockentity.component.BlockEntityComponentImplFactory;
import org.allaymc.api.blockentity.init.BlockEntityInitInfo;
import org.allaymc.api.component.interfaces.ComponentProvider;
import org.allaymc.api.component.interfaces.ComponentProvider.SimpleComponentProvider;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerViewer;
import org.allaymc.server.blockentity.component.barrel.BlockEntityBarrelBaseComponentImpl;
import org.allaymc.server.blockentity.component.common.BlockEntityBaseComponentImpl;
import org.allaymc.server.blockentity.component.common.BlockEntityContainerHolderComponentImpl;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Allay Project 2023/12/6
 *
 * @author daoge_cmd
 */
public class AllayBlockEntityComponentImplFactory implements BlockEntityComponentImplFactory {
    @Override
    public ComponentProvider<BlockEntityComponent> createBlockEntityBaseComponent() {
        return new SimpleComponentProvider<BlockEntityComponent, BlockEntityInitInfo<?>>(BlockEntityBaseComponentImpl::new, BlockEntityBaseComponentImpl.class);
    }

    @Override
    public ComponentProvider<BlockEntityComponent> createBlockEntityBarrelBaseComponent() {
        return new SimpleComponentProvider<>(BlockEntityBarrelBaseComponentImpl::new, BlockEntityBarrelBaseComponentImpl.class);
    }

    @Override
    public ComponentProvider<BlockEntityComponent> createBlockEntityContainerHolderComponent(Supplier<Container> containerSupplier) {
        return new SimpleComponentProvider<>(info -> new BlockEntityContainerHolderComponentImpl(containerSupplier), BlockEntityContainerHolderComponentImpl.class);
    }

    @Override
    public ComponentProvider<BlockEntityComponent> createBlockEntityContainerHolderComponent(Supplier<Container> containerSupplier, Consumer<ContainerViewer> onOpenListener, Consumer<ContainerViewer> onCloseListener) {
        return new SimpleComponentProvider<>(info -> new BlockEntityContainerHolderComponentImpl(containerSupplier, onOpenListener, onCloseListener), BlockEntityContainerHolderComponentImpl.class);
    }
}
