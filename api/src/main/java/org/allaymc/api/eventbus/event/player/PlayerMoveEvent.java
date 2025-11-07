package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.math.location.Location3dc;

/**
 * @author daoge_cmd | Dhaiven
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerMoveEvent extends PlayerEvent implements CancellableEvent {
    protected Location3dc from;
    protected Location3dc to;

    /**
     * If event is cancelled, player get teleported to specified location, which is defaults to original one.
     * <p>
     * If set to null, no teleport would be issued; it up to user to handle client-server movement desync.
     */
    @Setter
    protected Location3dc revertTo;

    public PlayerMoveEvent(EntityPlayer player, Location3dc from, Location3dc to) {
        super(player);
        this.from = from;
        this.to = to;
        this.revertTo = from;
    }
}
