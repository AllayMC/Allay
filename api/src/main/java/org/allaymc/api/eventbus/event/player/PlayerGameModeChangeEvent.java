package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.player.GameMode;

/**
 * @author daoge_cmd | Dhaiven
 */
@Getter
public class PlayerGameModeChangeEvent extends PlayerEvent implements CancellableEvent {
    protected GameMode oldGameMode;
    @Setter
    protected GameMode newGameMode;

    public PlayerGameModeChangeEvent(EntityPlayer player, GameMode oldGameMode, GameMode newGameMode) {
        super(player);
        this.oldGameMode = oldGameMode;
        this.newGameMode = newGameMode;
    }
}
