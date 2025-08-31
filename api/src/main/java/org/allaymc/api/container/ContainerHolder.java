package org.allaymc.api.container;

import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * Represents an object that can hold multiple containers.
 *
 * @author daoge_cmd
 */
public interface ContainerHolder {
    /**
     * Returns a map of containers.
     *
     * @return a map of containers
     */
    @UnmodifiableView
    Map<FullContainerType<?>, Container> getContainers();

    /**
     * Checks if the holder has a container of the specified type.
     *
     * @param type the type of the container
     *
     * @return {@code true} if the holder has a container of the specified type, {@code false} otherwise.
     */
    boolean hasContainer(FullContainerType<?> type);

    /**
     * Returns a container of the specified type.
     *
     * @param type the type of the container
     * @param <T>  the type of the container
     *
     * @return the container
     */
    <T extends Container> T getContainer(FullContainerType<T> type);

    /**
     * Returns a container of the specified slot type.
     *
     * @param slotType the type of the slot
     * @param <T>      the type of the container
     *
     * @return the container
     */
    <T extends Container> T getContainerBySlotType(ContainerSlotType slotType);

    /**
     * Adds a container to the holder.
     *
     * @param container the container to add
     */
    void addContainer(Container container);
}
