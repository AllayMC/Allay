package org.allaymc.api.eventbus.event.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.player.Player;

/**
 * {@code ServerPlayerEvent} carries a player connection for server and player
 * lifecycle events.
 * The player remains the live connection object rather than a snapshot;
 * available login data and the controlled entity depend on the stage identified
 * by the concrete event subclass.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public abstract class ServerPlayerEvent extends ServerEvent {
    /**
     * {@code player} holds the live connection associated with this event.
     * Listeners must consult the concrete event's lifecycle stage before assuming
     * login or spawn.
     */
    protected Player player;
}
