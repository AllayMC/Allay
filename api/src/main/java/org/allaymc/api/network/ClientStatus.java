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
    // The previous status of DISCONNECTED can be CONNECTED, LOGGED_IN or IN_GAME
    DISCONNECTED(null),
    CONNECTED(DISCONNECTED),
    LOGGED_IN(CONNECTED),
    IN_GAME(LOGGED_IN);

    /**
     * The previous status of the client. Can be {@code null} if previous status is not exist.
     */
    @Getter
    private final ClientStatus previousStatus;

    public boolean canHandlePackets() {
        return this != DISCONNECTED;
    }
}
