package org.allaymc.api.eventbus.event.server;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.player.Player;

/**
 * {@code PlayerJoinEvent} represents the client's completed local-player
 * initialization awaiting server acceptance.
 * The player has passed the spawn stage, but the server enters its in-game
 * state only after this event is accepted. Cancellation disconnects the player;
 * acceptance permits the configurable join announcement.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.NETWORK)
public class PlayerJoinEvent extends ServerPlayerEvent implements CancellableEvent {
    /**
     * {@code joinMessage} supplies the translatable announcement after acceptance,
     * with the player's name as an argument. {@code null} or blank text suppresses
     * the broadcast.
     */
    @MayContainTrKey
    @Setter
    protected String joinMessage;

    /**
     * {@code disconnectReason} supplies the text shown to the player when this
     * event is cancelled before the session enters its in-game state.
     */
    @MayContainTrKey
    @Setter
    protected String disconnectReason;

    /**
     * Creates an initialization request carrying announcement and rejection text.
     */
    public PlayerJoinEvent(Player player, String joinMessage, String disconnectReason) {
        super(player);
        this.joinMessage = joinMessage;
        this.disconnectReason = disconnectReason;
    }
}
