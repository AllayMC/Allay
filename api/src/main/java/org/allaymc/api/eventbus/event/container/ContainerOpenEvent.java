package org.allaymc.api.eventbus.event.container;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerViewer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code ContainerOpenEvent} represents a viewer's attempt to open a container before
 * window registration. Cancellation stops the normal opening path before the viewer
 * callback, membership update, and open listeners; an already registered viewer bypasses
 * this event in the base implementation.
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
@CallerThread(ThreadType.WORLD)
public class ContainerOpenEvent extends ContainerEvent implements CancellableEvent {
    /**
     * Viewer requesting the container window.
     */
    protected ContainerViewer viewer;
    /**
     * Container targeted by the opening attempt.
     */
    protected Container container;
}
