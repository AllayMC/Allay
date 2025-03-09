package org.allaymc.api.eventbus.event.container;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.container.ContainerViewer;
import org.allaymc.api.container.impl.AnvilContainer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * @author IWareQ
 */
@Getter
@AllArgsConstructor
public class AnvilTakeResultEvent extends ContainerEvent implements CancellableEvent {
    protected ContainerViewer viewer;
    protected AnvilContainer container;
    @Setter
    protected ItemStack resultItem;
}
