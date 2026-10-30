package org.allaymc.api.container;

import java.util.Set;

/**
 * {@code ContainerViewer} maintains the viewing side of container windows and receives
 * content, slot, and property updates. A container opens the viewer before registering
 * its assigned window ID, while closing and subsequent update calls use the viewer's
 * established container association. Implementations may also recognize built-in entity
 * containers without an explicit window.
 *
 * @author daoge_cmd
 */
public interface ContainerViewer {
    /**
     * Displays all stacks currently stored in the container. The viewer must already
     * recognize its window or built-in container identity; an unrecognized container
     * throws {@code IllegalStateException} instead of receiving a new association.
     */
    void viewContainerContents(Container container);

    /**
     * Displays the current stack at one container slot. The viewer must recognize the
     * container's window or built-in identity; an unrecognized container throws {@code
     * IllegalStateException} before sending the update.
     */
    void viewContainerSlot(Container container, int slot);

    /**
     * Opens the viewer's container interface and returns its assigned window ID. Opening
     * an already associated container throws {@code IllegalStateException}; callers use
     * the returned ID to record membership on the container side.
     */
    byte viewContainerOpen(Container container);

    /**
     * Closes the viewer's interface for an associated container and removes its window
     * mapping. A container absent from the viewer's open-window associations throws
     * {@code IllegalStateException} instead of establishing a new mapping.
     */
    void viewContainerClose(Container container);

    /**
     * Displays a numeric property for an explicitly opened container window. Property
     * meaning depends on the container interface; an absent window association throws
     * {@code IllegalStateException} before sending the property value.
     */
    void viewContainerData(Container container, int property, int value);

    /**
     * Returns the open container matching the supplied type identity. {@code null}
     * indicates no matching association; implementations may include auxiliary inventory
     * containers that are implicitly open with the player inventory.
     */
    <T extends Container> T getOpenedContainer(ContainerType<T> type);

    /**
     * Returns the container associated with the assigned window ID. {@code null}
     * indicates that the ID has no open container association, so callers must handle an
     * absent result.
     */
    Container getOpenedContainer(byte id);

    /**
     * Returns the container objects currently recognized as open by this viewer.
     * Implementations may include auxiliary inventory containers opened implicitly; the
     * container objects themselves remain shared with their owners.
     */
    Set<Container> getOpenedContainers();

    /**
     * Attempts to close every container currently recognized as open by this viewer.
     * Each container performs its ordinary removal path, including close notifications
     * and any container-specific listener effects.
     */
    void closeAllOpenedContainers();
}
