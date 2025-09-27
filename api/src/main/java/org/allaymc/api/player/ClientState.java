package org.allaymc.api.player;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Represents the state of a client.
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
public enum ClientState {
    // The previous state of DISCONNECTED can be CONNECTED, LOGGED_IN or IN_GAME
    DISCONNECTED(null),
    CONNECTED(DISCONNECTED),
    LOGGED_IN(CONNECTED),
    IN_GAME(LOGGED_IN);

    /**
     * The previous state of the client. Can be {@code null} if previous state is not exist.
     */
    @Getter
    private final ClientState previousState;

    public boolean canHandlePackets() {
        return this != DISCONNECTED;
    }
}
