package org.allaymc.api.eventbus.event.server;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.player.ClientPlayMode;
import org.allaymc.api.player.InputInteractionModel;
import org.allaymc.api.player.InputMode;
import org.allaymc.api.player.Player;

/**
 * Called when one of player control modes update.
 *
 * @author zernix2077
 */
@Getter
@Setter
@CallerThread(ThreadType.NETWORK)
public class PlayerControlModeUpdateEvent extends ServerPlayerEvent {
    /**
     * Player's old input mode.
     */
    protected InputMode oldInputMode;

    /**
     * Player's new input mode.
     */
    protected InputMode newInputMode;

    /**
     * Player's old play mode.
     */
    protected ClientPlayMode oldPlayMode;

    /**
     * Player's new play mode.
     */
    protected ClientPlayMode newPlayMode;

    /**
     * Player's old input interaction model.
     */
    protected InputInteractionModel oldInteractionModel;

    /**
     * Player's new input interaction model.
     */
    protected InputInteractionModel newInteractionModel;

    public PlayerControlModeUpdateEvent(
            Player player,
            InputMode oldInputMode,
            InputMode newInputMode,
            ClientPlayMode oldPlayMode,
            ClientPlayMode newPlayMode,
            InputInteractionModel oldInteractionModel,
            InputInteractionModel newInteractionModel
    ) {
        super(player);
        this.oldInputMode = oldInputMode;
        this.newInputMode = newInputMode;
        this.oldPlayMode = oldPlayMode;
        this.newPlayMode = newPlayMode;
        this.oldInteractionModel = oldInteractionModel;
        this.newInteractionModel = newInteractionModel;
    }
}
