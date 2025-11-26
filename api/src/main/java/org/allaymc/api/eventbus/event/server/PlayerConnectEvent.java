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
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
@CallerThread(ThreadType.NETWORK)
public class PlayerConnectEvent extends ServerEvent implements CancellableEvent {
    protected Player player;
    /**
     * The reason that will be shown to the player if the event is cancelled.
     * If the event is not cancelled, this field will be ignored.
     */
    @MayContainTrKey
    @Setter
    protected String disconnectReason;
}
