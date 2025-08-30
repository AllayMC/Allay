package org.allaymc.api.eventbus.event.player;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3ic;
import lombok.Getter;
import lombok.Setter;

/**
 * @author daoge_cmd
 */
public class PlayerRespawnEvent extends PlayerEvent {
    @Getter
    @Setter
    protected Location3ic respawnLocation;

    public PlayerRespawnEvent(EntityPlayer player) {
        super(player);
    }
}
