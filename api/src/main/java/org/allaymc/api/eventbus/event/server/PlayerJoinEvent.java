package org.allaymc.api.eventbus.event.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.player.Player;

/**
 * Called when a player joins the server.
 *
 * @author daoge_cmd
 */
@CallerThread(ThreadType.NETWORK)
@Getter
public class PlayerJoinEvent extends ServerPlayerEvent implements CancellableEvent {
    /**
     * The player associated with this event.
     */
    protected Player player;

    /**
     * The message that will be broadcast if the event is not cancelled.
     */
    @MayContainTrKey
    @Setter
    protected String joinMessage;

    /**
     * The reason that will be shown to the player if the event is cancelled.
     */
    @MayContainTrKey
    @Setter
    protected String disconnectReason;

    public PlayerJoinEvent(Player player, String joinMessage, String disconnectReason) {
        super(player);
        this.joinMessage = joinMessage;
        this.disconnectReason = disconnectReason;
    }
}
