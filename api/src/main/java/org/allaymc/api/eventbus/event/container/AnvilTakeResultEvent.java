package org.allaymc.api.eventbus.event.container;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.container.ContainerViewer;
import org.allaymc.api.container.interfaces.AnvilContainer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * Called when a player takes the result item from an anvil.
 *
 * @author IWareQ
 */
@Getter
@AllArgsConstructor
@CallerThread(ThreadType.WORLD)
public class AnvilTakeResultEvent extends ContainerEvent implements CancellableEvent {
    /**
     * The viewer taking the anvil result.
     */
    protected ContainerViewer viewer;
    /**
     * The anvil container.
     */
    protected AnvilContainer container;
    /**
     * The result item being taken.
     */
    @Setter
    protected ItemStack resultItem;
}
