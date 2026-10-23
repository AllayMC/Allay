package org.allaymc.api.scoreboard.scorer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.player.Player;
import org.allaymc.api.server.Server;

import java.util.UUID;

/**
 * {@code PlayerScorer} represents a player score using the login UUID retained across connections.
 * A current player-manager lookup supplies the online name; offline naming uses the UUID's most
 * significant bits. The generated {@code UUID} constructor can retain {@code null}, but equality, naming,
 * and persistence require a non-{@code null} identity.
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public final class PlayerScorer implements Scorer {

    /**
     * {@code uuid} identifies the persistent player score. {@code getPlayer} and {@code getName}
     * resolve current presentation through the player manager instead of retaining a connection.
     */
    private final UUID uuid;

    /**
     * Creates a player scorer by parsing the supplied UUID string. Invalid UUID syntax raises
     * {@code IllegalArgumentException}, and the resulting identity does not require a currently
     * connected player.
     */
    public PlayerScorer(String uuid) {
        this(UUID.fromString(uuid));
    }

    /**
     * Creates a player scorer retaining the UUID from the supplied player's login data.
     */
    public PlayerScorer(Player player) {
        this(player.getLoginData().getUuid());
    }

    /**
     * Returns the player currently registered under the stored UUID, or {@code null} when none
     * exists. A {@code null} stored UUID also returns {@code null} without consulting the player
     * manager.
     */
    public Player getPlayer() {
        if (uuid == null) {
            return null;
        }

        return Server.getInstance().getPlayerManager().getPlayers().get(uuid);
    }

    /**
     * Returns whether the player manager currently contains this scorer's UUID.
     */
    public boolean isOnline() {
        return getPlayer() != null;
    }

    /**
     * Returns the scorer category used when encoding this scoreboard entry.
     */
    @Override
    public ScorerType getScorerType() {
        return ScorerType.PLAYER;
    }

    /**
     * Returns a hash derived from this scorer's stored identity value.
     */
    @Override
    public int hashCode() {
        return uuid != null ? uuid.hashCode() : 0;
    }

    /**
     * Returns whether another player scorer has the same UUID value. This operation requires this
     * scorer's stored UUID to be non-{@code null} when comparing another player scorer, even though
     * {@code getPlayer} tolerates a missing UUID.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PlayerScorer playerScorer) {
            return uuid.equals(playerScorer.uuid);
        }
        return false;
    }

    /**
     * Returns the current online player's original name, falling back to the stored UUID's most
     * significant bits in decimal. This operation requires a non-{@code null} UUID even though
     * {@code getPlayer} tolerates one.
     */
    @Override
    public String getName() {
        var player = Server.getInstance().getPlayerManager().getPlayers().get(uuid);
        // Offline players have no live display name in the manager, so use a
        // stable numeric label derived from the retained UUID until they reconnect.
        return player == null ? String.valueOf(uuid.getMostSignificantBits()) : player.getOriginName();
    }

}
