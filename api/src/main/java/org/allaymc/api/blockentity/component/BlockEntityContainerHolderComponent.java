package org.allaymc.api.blockentity.component;

import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerHolder;
import org.allaymc.api.container.ContainerType;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * @author daoge_cmd
 */
public interface BlockEntityContainerHolderComponent extends ContainerHolder, BlockEntityComponent {
    @Override
    @UnmodifiableView
    default Map<ContainerType<?>, Container> getContainers() {
        Container container = getContainer();
        return Map.of(container.getContainerType(), container);
    }

    @Override
    default <T extends Container> T getContainer(ContainerType<T> type) {
        return getContainer();
    }

    @Override
    default void addContainer(Container container) {
        setContainer(container);
    }

    /**
     * Gets the container.
     *
     * @param <T> the type of the container
     * @return the container
     */
    <T extends Container> T getContainer();

    /**
     * Sets the container.
     *
     * @param container the container to set
     */
    void setContainer(Container container);

    /**
     * Returns whether items should be dropped when the block is broken.
     *
     * @return {@code true} if items should be dropped, {@code false} otherwise
     */
    boolean shouldDropItemOnBreak();

    /**
     * Sets whether items should be dropped when the block is broken.
     * <p>
     * This is useful for pistons to prevent items from dropping
     * when moving a container block.
     *
     * @param drop {@code true} to drop items, {@code false} to prevent dropping
     */
    void setDropItemOnBreak(boolean drop);
}
