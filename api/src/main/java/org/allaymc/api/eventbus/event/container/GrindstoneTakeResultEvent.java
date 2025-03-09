package org.allaymc.api.eventbus.event.container;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.container.ContainerViewer;
import org.allaymc.api.container.impl.GrindstoneContainer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * @author IWareQ
 */
@Getter
@AllArgsConstructor
public class GrindstoneTakeResultEvent extends ContainerEvent implements CancellableEvent {
    protected ContainerViewer viewer;
    protected GrindstoneContainer container;
    @Setter
    protected ItemStack resultItem;
    @Setter
    protected int experienceAmount;
}
