package org.allaymc.api.eventbus.event.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.player.Player;

/**
 * Represents an event related to a player issued by the server. This is the base class for all player events.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public abstract class ServerPlayerEvent extends ServerEvent {
    /**
     * The {@code Player} instance associated with this event.
     */
    protected Player player;
}
