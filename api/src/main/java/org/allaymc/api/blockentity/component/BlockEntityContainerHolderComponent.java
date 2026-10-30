package org.allaymc.api.blockentity.component;

import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerHolder;
import org.allaymc.api.container.ContainerType;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * {@code BlockEntityContainerHolderComponent} exposes the single live container
 * owned by a block entity and its break-drop policy. The default
 * multi-container adapters use that same container: adding replaces it, and
 * typed retrieval does not validate the requested container type.
 *
 * @author daoge_cmd
 */
public interface BlockEntityContainerHolderComponent extends ContainerHolder, BlockEntityComponent {
    /**
     * Returns an immutable single-entry map containing the current live
     * container. Each call creates the map from that container's type; the
     * contained inventory remains mutable and is shared with this holder.
     */
    @Override
    @UnmodifiableView
    default Map<ContainerType<?>, Container> getContainers() {
        Container container = getContainer();
        return Map.of(container.getContainerType(), container);
    }

    /**
     * Returns the current container without checking the requested
     * {@code type}. Callers must ensure the expected subtype matches the stored
     * container; this adapter does not report a missing type by returning
     * {@code null}.
     */
    @Override
    default <T extends Container> T getContainer(ContainerType<T> type) {
        return getContainer();
    }

    /**
     * Replaces the holder's single container through {@code setContainer}. This
     * adapter does not add a second inventory or migrate existing items,
     * viewers, or listeners into the supplied container.
     */
    @Override
    default void addContainer(Container container) {
        setContainer(container);
    }

    /**
     * Returns the holder's live container using the caller's expected subtype.
     * Callers share the same mutable inventory with the block entity and must
     * choose a subtype compatible with the installed container.
     */
    <T extends Container> T getContainer();

    /**
     * Replaces the live container reference used by this holder. Callers must
     * provide a compatible initialized container and arrange any required item,
     * viewer, or listener migration; reference replacement alone does not
     * perform that work.
     */
    void setContainer(Container container);

    /**
     * Returns whether replacement handling should drop this container's items.
     * The standard holder enables dropping initially; disabling the policy also
     * skips that handler's viewer removal and inventory clearing.
     */
    boolean shouldDropItemOnBreak();

    /**
     * Sets whether replacement handling drops the holder's inventory contents.
     * Moving-container callers can disable the policy before replacement so the
     * handler leaves the inventory intact for their separate movement path.
     */
    void setDropItemOnBreak(boolean drop);
}
