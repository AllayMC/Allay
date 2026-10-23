package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.math.location.Location3dc;

/**
 * {@code PlayerMoveEvent} describes a proposed client movement between two player
 * locations. The physics engine dispatches it before accepting the movement;
 * cancellation skips the move and optionally teleports the player to
 * {@code revertTo}, which initially refers to {@code from}.
 *
 * @author daoge_cmd | Dhaiven
 */
@Getter
@CallerThread(ThreadType.DIMENSION)
public class PlayerMoveEvent extends PlayerEvent implements CancellableEvent {
    /**
     * Location before the proposed movement.
     */
    protected Location3dc from;
    /**
     * Target location from the client movement.
     */
    protected Location3dc to;

    /**
     * {@code revertTo} supplies the teleport destination when movement is cancelled
     * and initially refers to {@code from}. {@code null} suppresses that teleport
     * and leaves client reconciliation to the listener.
     */
    @Setter
    protected Location3dc revertTo;

    /**
     * Creates a movement event retaining both supplied location references.
     * {@code revertTo} initially refers to {@code from}; listeners may replace it or
     * set it to {@code null} to suppress the cancellation teleport.
     */
    public PlayerMoveEvent(EntityPlayer player, Location3dc from, Location3dc to) {
        super(player);
        this.from = from;
        this.to = to;
        this.revertTo = from;
    }
}
