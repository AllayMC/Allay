package org.allaymc.api.entity.component.container;

import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerViewer;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.component.EntityComponent;
import com.google.common.collect.BiMap;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

/**
 * Allay Project 2023/7/15
 *
 * @author daoge_cmd
 */
public interface EntityContainerViewerComponent extends ContainerViewer, EntityComponent {
    @Override
    byte assignInventoryId();

    @Override
    void sendContents(Container container);

    @Override
    void sendContentsWithSpecificContainerId(Container container, int containerId);

    @Override
    void sendContentsWithSpecificContainerId(Container container, int containerId, int slot);

    @Override
    void sendContent(Container container, int slot);

    @Override
    void onOpen(byte assignedId, Container container);

    @Override
    void onClose(byte assignedId, Container container);

    @Override
    void onSlotChange(Container container, int slot);

    @Override
    @Nullable <T extends Container> T getOpenedContainer(FullContainerType<T> type);

    @Override
    Container getOpenedContainer(byte id);

    @Override
    @UnmodifiableView BiMap<Byte, Container> getId2ContainerBiMap();

    @Override
    @UnmodifiableView BiMap<FullContainerType<?>, Container> getType2ContainerBiMap();
}
