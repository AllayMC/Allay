package org.allaymc.api.container;

import com.google.common.collect.BiMap;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.jetbrains.annotations.UnmodifiableView;

/**
 * @author daoge_cmd
 */
public interface ContainerViewer {

    byte assignInventoryId();

    void sendContents(Container container);

    void sendContentsWithSpecificContainerId(Container container, int containerId);

    void sendContentsWithSpecificContainerId(Container container, int containerId, int slot);

    void sendContent(Container container, int slot);

    void onOpen(byte assignedId, Container container);

    void onClose(byte assignedId, Container container);

    void onSlotChange(Container container, int slot);

    <T extends Container> T getOpenedContainer(FullContainerType<T> type);

    <T extends Container> T getOpenedContainerBySlotType(ContainerSlotType slotType);

    Container getOpenedContainer(byte id);

    @UnmodifiableView
    BiMap<Byte, Container> getIdToContainer();

    @UnmodifiableView
    BiMap<FullContainerType<?>, Container> getTypeToContainer();

    default void closeAllContainers() {
        getIdToContainer().forEach(this::onClose);
    }

    void sendContainerData(byte assignedId, int property, int value);
}
