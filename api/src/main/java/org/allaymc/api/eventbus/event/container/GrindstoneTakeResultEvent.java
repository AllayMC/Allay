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
 * {@code GrindstoneTakeResultEvent} represents a proposed grindstone output and its
 * experience award. Listeners may replace the result, change the experience amount, or
 * cancel the operation before the processor drops experience and stores the created
 * output.
 *
 * @author IWareQ
 */
@Getter
@AllArgsConstructor
@CallerThread(ThreadType.WORLD)
public class GrindstoneTakeResultEvent extends ContainerEvent implements CancellableEvent {
    /**
     * Viewer requesting the grindstone result.
     */
    protected ContainerViewer viewer;
    /**
     * Grindstone container supplying the operation inputs.
     */
    protected GrindstoneContainer container;
    /**
     * {@code resultItem} becomes the created-output stack after successful dispatch.
     * Listeners may replace this reference with a non-{@code null} result stack.
     */
    @Setter
    protected ItemStack resultItem;
    /**
     * {@code experienceAmount} counts experience points dropped after dispatch. The
     * processor drops an orb only when the listener-adjusted value is positive.
     */
    @Setter
    protected int experienceAmount;
}
