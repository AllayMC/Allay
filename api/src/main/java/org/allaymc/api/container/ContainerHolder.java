package org.allaymc.api.container;

import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * ContainerHolder represents an object that can hold multiple containers.
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
    Map<ContainerType<?>, Container> getContainers();

    /**
     * Checks if the holder has a container of the specified type.
     *
     * @param type the type of the container
     * @return {@code true} if the holder has a container of the specified type, {@code false} otherwise.
     */
    boolean hasContainer(ContainerType<?> type);

    /**
     * Returns a container of the specified type.
     *
     * @param type the type of the container
     * @param <T>  the type of the container
     * @return the container
     */
    <T extends Container> T getContainer(ContainerType<T> type);

    /**
     * Adds a container to the holder.
     *
     * @param container the container to add
     */
    void addContainer(Container container);
}
