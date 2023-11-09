package org.allaymc.api.blockentity.component.container;

import org.allaymc.api.blockentity.component.BlockEntityComponent;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerHolder;
import org.allaymc.api.container.FullContainerType;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * Allay Project 2023/9/16
 *
 * @author daoge_cmd
 */
public interface BlockEntityContainerHolderComponent extends ContainerHolder, BlockEntityComponent {
    @Override
    @UnmodifiableView
    default Map<FullContainerType<?>, Container> getContainers() {
        Container container = getContainer();
        return Map.of(container.getContainerType(), getContainer());
    }

    @Override
    default <T extends Container> T getContainer(FullContainerType<T> type) {
        return getContainer();
    }

    @Override
    default void addContainer(Container container) {
        setContainer(container);
    }

    <T extends Container> T getContainer();

    void setContainer(Container container);
}
