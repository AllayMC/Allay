package org.allaymc.api.eventbus.event.container;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerViewer;

/**
 * {@code ContainerCloseEvent} represents a request to remove a viewer from a container.
 * The base implementation dispatches it before checking membership, so listeners can
 * receive the event even when the viewer has no registered window to close.
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
@CallerThread(ThreadType.WORLD)
public class ContainerCloseEvent extends ContainerEvent {
    /**
     * Viewer targeted by the removal request.
     */
    protected ContainerViewer viewer;
    /**
     * Container receiving the removal request.
     */
    protected Container container;
}
