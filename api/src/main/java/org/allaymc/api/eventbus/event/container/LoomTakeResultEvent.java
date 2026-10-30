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
 * {@code LoomTakeResultEvent} represents a proposed patterned banner before
 * created-output publication. Listeners may replace the result stack or cancel the
 * action; successful dispatch stores the selected result without immediately sending an
 * ordinary container slot update.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
@CallerThread(ThreadType.WORLD)
public class LoomTakeResultEvent extends ContainerEvent implements CancellableEvent {
    /**
     * Viewer requesting the loom result.
     */
    protected ContainerViewer viewer;
    /**
     * Loom container supplying the banner ingredients.
     */
    protected LoomContainer container;
    /**
     * {@code resultItem} becomes the created-output stack after successful dispatch.
     * Listeners may replace this reference with a non-{@code null} banner result.
     */
    @Setter
    protected ItemStack resultItem;
}
