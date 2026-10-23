package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code PlayerInteractBlockEvent} describes a player's block interaction and its
 * action kind. The inventory transaction processor dispatches right-click
 * interactions before using the item or block; cancellation suppresses those
 * operations and sends block updates to correct the client's predicted state.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerInteractBlockEvent extends PlayerEvent implements CancellableEvent {

    /**
     * {@code interactInfo} retains the block interaction supplied by the producer,
     * including its clicked position, face, and player context.
     */
    protected final PlayerInteractInfo interactInfo;
    /**
     * Requested block interaction action kind.
     */
    protected final Action action;

    /**
     * Creates a {@code PlayerInteractBlockEvent} for the supplied player and event
     * data. The constructor retains reference-valued arguments without copying them
     * and does not dispatch or apply the represented action.
     */
    public PlayerInteractBlockEvent(EntityPlayer player, PlayerInteractInfo interactInfo, Action action) {
        super(player);
        this.interactInfo = interactInfo;
        this.action = action;
    }

    /**
     * {@code Action} identifies the mouse-style action associated with a block
     * interaction. The event retains this value so listeners distinguish the
     * intended operation before its caller invokes block or item behavior.
     */
    public enum Action {
        /**
         * Left-button block interaction.
         */
        LEFT_CLICK,
        /**
         * Right-button block interaction.
         */
        RIGHT_CLICK
    }
}
