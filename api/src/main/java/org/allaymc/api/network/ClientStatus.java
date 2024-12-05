package org.allaymc.api.network;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents the status of a client.
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
public enum ClientStatus {
    NEW(null),
    CONNECTED(NEW),
    LOGGED_IN(CONNECTED),
    IN_GAME(LOGGED_IN),
    DISCONNECTED(IN_GAME);

    /**
     * The previous status of the client.
     */
    @Getter
    private final ClientStatus previousStatus;
}
