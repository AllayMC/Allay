package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3ic;

/**
 * {@code PlayerRespawnEvent} holds the destination selected for a player's respawn
 * after death. The respawn processor assigns its computed spawn location before
 * dispatch, then uses the listener-selected location for readiness and
 * teleportation; the event does not support cancellation.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@CallerThread(ThreadType.WORLD)
public class PlayerRespawnEvent extends PlayerEvent {
    /**
     * {@code respawnLocation} supplies the destination used after dispatch. The
     * producer initializes it before calling listeners, and the constructor leaves
     * it {@code null}.
     */
    protected Location3ic respawnLocation;

    /**
     * Creates a respawn event associated with the supplied player.
     * {@code respawnLocation} starts as {@code null}, so the producer must assign
     * the destination before dispatching it for a real respawn.
     */
    public PlayerRespawnEvent(EntityPlayer player) {
        super(player);
    }
}
