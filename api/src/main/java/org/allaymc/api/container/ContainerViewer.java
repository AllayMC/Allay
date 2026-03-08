package org.allaymc.api.container;

import java.util.Set;

/**
 * Represents a container viewer that can open and close containers.
 *
 * @author daoge_cmd
 */
public interface ContainerViewer {
    /**
     * View the contents of an opened container.
     *
     * @param container the container to view
     * @throws IllegalStateException if this viewer does not open the container
     */
    void viewContainerContents(Container container);

    /**
     * View a slot in an opened container.
     *
     * @param container the container to view
     * @param slot      the slot to view
     * @throws IllegalStateException if this viewer does not open the container
     */
    void viewContainerSlot(Container container, int slot);

    /**
     * Open a container in the viewer's side.
     *
     * @param container the container that is opened
     * @return the assigned id for this container
     * @throws IllegalStateException if this viewer has opened the container
     */
    byte viewContainerOpen(Container container);

    /**
     * Close a container in the viewer's side.
     *
     * @param container the container that is closed
     * @throws IllegalStateException if this viewer hasn't opened the container
     */
    void viewContainerClose(Container container);

    /**
     * View container data.
     *
     * @param container the container to view
     * @param property  the property to view
     * @param value     the value to view
     * @throws IllegalStateException if this viewer hasn't opened the container
     */
    void viewContainerData(Container container, int property, int value);

    /**
     * Get the container that is opened with a specific type.
     *
     * @param type the type of the container
     * @return the container
     */
    <T extends Container> T getOpenedContainer(ContainerType<T> type);

    /**
     * Get the container opened with the assigned id.
     *
     * @param id the assigned id of the container
     * @return the container
     */
    Container getOpenedContainer(byte id);

    /**
     * Get the opened containers.
     *
     * @return the opened containers.
     */
    Set<Container> getOpenedContainers();

    /**
     * Close all containers that are opened by this viewer.
     */
    void closeAllOpenedContainers();
}
