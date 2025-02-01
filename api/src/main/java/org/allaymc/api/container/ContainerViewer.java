package org.allaymc.api.container;

import com.google.common.collect.BiMap;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.UnmodifiableView;

/**
 * Represents a container viewer that can open and close containers.
 *
 * @author daoge_cmd
 */
public interface ContainerViewer {

    /**
     * Assign a unique id to a container.
     *
     * @return the assigned id.
     */
    byte assignContainerId();

    /**
     * Send the contents of a container to the viewer.
     *
     * @param container the container to send the contents of.
     */
    void sendContents(Container container);

    /**
     * Send the contents of a container to the viewer with a specific container id.
     *
     * @param container   the container to send the contents of.
     * @param containerId the id to send the contents with.
     */
    void sendContentsWithSpecificContainerId(Container container, int containerId);

    /**
     * Send the contents of a container to the viewer with a specific container id and slot.
     *
     * @param container   the container to send the contents of.
     * @param containerId the id to send the contents with.
     * @param slot        the slot to send the contents of.
     */
    void sendContentsWithSpecificContainerId(Container container, int containerId, int slot);

    /**
     * Send the contents of a container to the viewer with a specific slot.
     *
     * @param container the container to send the contents of.
     * @param slot      the slot to send the contents of.
     */
    void sendContent(Container container, int slot);

    /**
     * Called when a container is opened.
     *
     * @param assignedId the id assigned to the container.
     * @param container  the container that was opened.
     */
    @ApiStatus.OverrideOnly
    void onOpen(byte assignedId, Container container);

    /**
     * Called when a container is closed.
     *
     * @param assignedId the id assigned to the container.
     * @param container  the container that was closed.
     */
    @ApiStatus.OverrideOnly
    void onClose(byte assignedId, Container container);

    /**
     * Notify an item in a slot that has been changed.
     *
     * @param container the container.
     * @param slot      the slot.
     */
    void notifySlotChange(Container container, int slot);

    /**
     * Get the container that is opened with a specific type.
     *
     * @param type the type of the container.
     *
     * @return the container.
     */
    <T extends Container> T getOpenedContainer(FullContainerType<T> type);

    /**
     * Get the container that is opened with a specific slot type.
     *
     * @param slotType the slot type of the container.
     *
     * @return the container.
     */
    <T extends Container> T getOpenedContainerBySlotType(ContainerSlotType slotType);

    /**
     * Get the container that is opened with the assigned id.
     *
     * @param id the assigned id of the container.
     *
     * @return the container.
     */
    Container getOpenedContainer(byte id);

    /**
     * Get assigned-id-container map.
     *
     * @return the map.
     */
    @UnmodifiableView
    BiMap<Byte, Container> getIdToContainerMap();

    /**
     * Get type-container map.
     *
     * @return the map.
     */
    @UnmodifiableView
    BiMap<FullContainerType<?>, Container> getTypeToContainerMap();

    /**
     * Close all containers.
     */
    default void closeAllContainers() {
        getIdToContainerMap().forEach(this::onClose);
    }

    /**
     * Send container data to the viewer.
     *
     * @param assignedId the assigned id of the container.
     * @param property   the property to send.
     * @param value      the value to send.
     */
    @ApiStatus.OverrideOnly
    void sendContainerData(byte assignedId, int property, int value);
}
