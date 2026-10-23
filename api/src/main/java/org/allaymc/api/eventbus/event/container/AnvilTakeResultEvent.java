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
 * {@code AnvilTakeResultEvent} represents the proposed anvil result before it enters the
 * player's created-output slot. Listeners may replace the result or cancel publication;
 * cancellation does not restore experience or anvil changes already applied by the
 * processor.
 *
 * @author IWareQ
 */
@Getter
@AllArgsConstructor
@CallerThread(ThreadType.WORLD)
public class AnvilTakeResultEvent extends ContainerEvent implements CancellableEvent {
    /**
     * Viewer requesting the anvil result.
     */
    protected ContainerViewer viewer;
    /**
     * Anvil container supplying the operation inputs.
     */
    protected AnvilContainer container;
    /**
     * {@code resultItem} is installed in the created-output slot after successful
     * dispatch. Listeners may replace this shared reference with a non-{@code null}
     * stack.
     */
    @Setter
    protected ItemStack resultItem;
}
