package org.allaymc.api.container;

import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * {@code ContainerHolder} groups containers by their container-type identity for
 * entities and block entities. Ordinary multi-container holders keep one container per
 * type and support matching typed lookup; {@code BlockEntityContainerHolderComponent}
 * currently substitutes single-container replacement and unchecked lookup behavior,
 * which callers must account for.
 *
 * @author daoge_cmd
 */
public interface ContainerHolder {
    /**
     * Returns an unmodifiable map of held containers keyed by type identity. {@code
     * BaseContainerHolder} exposes a live view; {@code
     * BlockEntityContainerHolderComponent} instead returns an immutable snapshot of its
     * current container reference.
     */
    @UnmodifiableView
    Map<ContainerType<?>, Container> getContainers();

    /**
     * Returns whether a container is registered under the supplied type identity.
     */
    boolean hasContainer(ContainerType<?> type);

    /**
     * Returns the container matching the type identity, or {@code null} when absent. The
     * {@code BlockEntityContainerHolderComponent} default currently ignores {@code
     * type}, so callers using that adapter must verify that the stored subtype matches.
     */
    <T extends Container> T getContainer(ContainerType<T> type);

    /**
     * Registers the container under its reported type identity. {@code
     * BaseContainerHolder} rejects duplicate types with {@code ContainerException}; the
     * {@code BlockEntityContainerHolderComponent} default instead replaces its current
     * reference without migrating contents or rejecting an existing type.
     */
    void addContainer(Container container);
}
