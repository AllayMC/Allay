package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3ic;

/**
 * Called when a player respawns after dying.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@CallerThread(ThreadType.DIMENSION)
public class PlayerRespawnEvent extends PlayerEvent {
    /**
     * The respawn location of the player.
     */
    protected Location3ic respawnLocation;

    public PlayerRespawnEvent(EntityPlayer player) {
        super(player);
    }
}
