package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.player.GameMode;

/**
 * Called when a player's game mode changes.
 *
 * @author daoge_cmd | Dhaiven
 */
@Getter
@CallerThread(ThreadType.WORLD)
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
