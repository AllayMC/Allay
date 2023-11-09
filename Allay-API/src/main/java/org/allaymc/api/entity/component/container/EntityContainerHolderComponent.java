package org.allaymc.api.entity.component.container;

import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerHolder;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.component.EntityComponent;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
public interface EntityContainerHolderComponent extends ContainerHolder, EntityComponent {
    @Override
    @UnmodifiableView
    Map<FullContainerType<?>, Container> getContainers();

    @Override
    <T extends Container> T getContainer(FullContainerType<T> type);

    @Override
    void addContainer(Container container);
}
