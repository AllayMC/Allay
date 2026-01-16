package org.allaymc.api.eventbus.event.container;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.container.ContainerViewer;
import org.allaymc.api.container.interfaces.GrindstoneContainer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * Called when a player takes the result item from a grindstone.
 *
 * @author IWareQ
 */
@Getter
@AllArgsConstructor
@CallerThread(ThreadType.WORLD)
public class GrindstoneTakeResultEvent extends ContainerEvent implements CancellableEvent {
    protected ContainerViewer viewer;
    protected GrindstoneContainer container;
    @Setter
    protected ItemStack resultItem;
    @Setter
    protected int experienceAmount;
}
