package org.allaymc.api.eventbus.event.player;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.player.GameMode;

/**
 * {@code PlayerGameModeChangeEvent} describes a player entity's proposed game mode
 * transition. The player component dispatches it before changing mode-dependent
 * state; cancellation preserves the current mode, while acceptance uses
 * {@code newGameMode} and skips further changes when it equals the current mode.
 *
 * @author daoge_cmd | Dhaiven
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class PlayerGameModeChangeEvent extends PlayerEvent implements CancellableEvent {
    /**
     * Game mode before the update.
     */
    protected GameMode oldGameMode;
    /**
     * {@code newGameMode} supplies the accepted target mode. The player component
     * reads it after dispatch before updating mode-dependent state.
     */
    @Setter
    protected GameMode newGameMode;

    /**
     * Creates a {@code PlayerGameModeChangeEvent} for the supplied player and event
     * data. The constructor retains reference-valued arguments without copying them
     * and does not dispatch or apply the represented action.
     */
    public PlayerGameModeChangeEvent(EntityPlayer player, GameMode oldGameMode, GameMode newGameMode) {
        super(player);
        this.oldGameMode = oldGameMode;
        this.newGameMode = newGameMode;
    }
}
