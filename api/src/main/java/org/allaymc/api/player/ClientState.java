package org.allaymc.api.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * Represents the state of a client.
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
public enum ClientState {
    // The previous state of DISCONNECTED can be CONNECTED, LOGGED_IN, SPAWNED or IN_GAME
    DISCONNECTED(null),
    /**
     * The client is connected to the server. This is the initial state of a newly connected client.
     */
    CONNECTED(DISCONNECTED),
    /**
     * The client is logged in.
     */
    LOGGED_IN(CONNECTED),
    /**
     * The client is spawned in the dimension, which means that the client is start controlling a {@link EntityPlayer}.
     */
    SPAWNED(LOGGED_IN),
    /**
     * The client is fully joined the game.
     */
    IN_GAME(SPAWNED);

    /**
     * The previous state of the client. Can be {@code null} if previous state is not exist.
     */
    @Getter
    private final ClientState previousState;

    public boolean canHandlePackets() {
        return this != DISCONNECTED;
    }
}
