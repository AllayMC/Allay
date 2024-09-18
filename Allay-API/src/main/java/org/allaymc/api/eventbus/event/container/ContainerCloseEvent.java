package org.allaymc.api.eventbus.event.container;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerViewer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
public class ContainerCloseEvent extends ContainerEvent implements CancellableEvent {
    protected ContainerViewer viewer;
    protected Container container;
}
