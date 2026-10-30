package org.allaymc.api.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.entity.interfaces.EntityPlayer;

/**
 * {@code ClientState} identifies a connection's progress from transport connection
 * through joining the game. {@code previousState} defines the normal predecessor
 * used by lifecycle checks; disconnection may occur from any active stage, so
 * {@code DISCONNECTED} has no single predecessor.
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
public enum ClientState {
    /**
     * {@code DISCONNECTED} marks a terminated connection. Any active state can
     * precede it, so {@code previousState} is {@code null}.
     */
    DISCONNECTED(null),
    /**
     * {@code CONNECTED} marks the initial state of an accepted connection before
     * login data and controlled entities are initialized.
     */
    CONNECTED(DISCONNECTED),
    /**
     * {@code LOGGED_IN} marks completed login initialization. The client has not yet
     * completed the player-entity spawning transition.
     */
    LOGGED_IN(CONNECTED),
    /**
     * {@code SPAWNED} marks a connection whose controlled player has entered the
     * spawning sequence. Start-game transmission and dimension membership occur
     * during that sequence before final joining.
     */
    SPAWNED(LOGGED_IN),
    /**
     * {@code IN_GAME} marks a client that acknowledged local player initialization
     * and can receive final joining synchronization.
     */
    IN_GAME(SPAWNED);

    /**
     * {@code previousState} identifies the normal predecessor required by lifecycle
     * transitions. {@code null} on {@code DISCONNECTED} reflects its multiple
     * possible predecessors.
     */
    @Getter
    private final ClientState previousState;

    /**
     * Returns whether this state permits packet handling for the connection.
     */
    public boolean canHandlePackets() {
        return this != DISCONNECTED;
    }
}
