package org.allaymc.api.eventbus.event.server;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.player.Player;

/**
 * @author daoge_cmd
 */
@CallerThread(ThreadType.NETWORK)
public class PlayerLoginEvent extends ServerEvent implements CancellableEvent {

    /**
     * Represents the player associated with this event.
     */
    @Getter
    protected Player player;

    /**
     * The reason that will be shown to the player if the event is cancelled.
     * If the event is not cancelled, this field will be ignored.
     */
    @MayContainTrKey
    @Setter
    @Getter
    protected String disconnectReason;

    /**
     * The message that will be broadcast if the event is not cancelled.
     * If the event is cancelled, this field will be ignored.
     */
    @MayContainTrKey
    @Setter
    @Getter
    protected String joinMessage;

    public PlayerLoginEvent(Player player, String disconnectReason, String joinMessage) {
        this.player = player;
        this.disconnectReason = disconnectReason;
        this.joinMessage = joinMessage;
    }
}
