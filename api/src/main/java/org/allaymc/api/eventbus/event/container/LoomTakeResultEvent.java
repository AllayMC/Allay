package org.allaymc.api.eventbus.event.container;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.container.ContainerViewer;
import org.allaymc.api.container.interfaces.LoomContainer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * Called when a player takes the result item from a loom.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
@CallerThread(ThreadType.WORLD)
public class LoomTakeResultEvent extends ContainerEvent implements CancellableEvent {
    /**
     * The viewer taking the loom result.
     */
    protected ContainerViewer viewer;
    /**
     * The loom container.
     */
    protected LoomContainer container;
    /**
     * The result item being taken.
     */
    @Setter
    protected ItemStack resultItem;
}
