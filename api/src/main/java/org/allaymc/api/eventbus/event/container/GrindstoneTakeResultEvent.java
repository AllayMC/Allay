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
    /**
     * The viewer taking the grindstone result.
     */
    protected ContainerViewer viewer;
    /**
     * The grindstone container.
     */
    protected GrindstoneContainer container;
    /**
     * The result item being taken.
     */
    @Setter
    protected ItemStack resultItem;
    /**
     * The amount of experience to award.
     */
    @Setter
    protected int experienceAmount;
}
