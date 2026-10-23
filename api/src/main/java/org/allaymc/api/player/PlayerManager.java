package org.allaymc.api.player;

import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.network.NetworkManager;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * {@code PlayerManager} exposes online players, their storage, and server admission
 * lists. Player lookup uses persistent UUIDs or exact original names, while ban,
 * whitelist, and operator changes affect the active server policy; the
 * implementation defines when those policies are persisted.
 *
 * @author daoge_cmd
 */
public interface PlayerManager {

    /**
     * Returns an unmodifiable live view of online players indexed by persistent
     * UUID. The view reflects subsequent membership changes, so callers must not
     * treat it as an independent snapshot or mutate its entries.
     */
    @UnmodifiableView
    Map<UUID, Player> getPlayers();

    /**
     * Applies the consumer to each currently registered player in the live view.
     * Iteration follows the backing map's behavior; callers must coordinate
     * membership changes rather than assuming a stable snapshot.
     */
    default void forEachPlayer(Consumer<Player> consumer) {
        getPlayers().values().forEach(consumer);
    }

    /**
     * Returns the number of player entries currently registered as online.
     */
    default int getPlayerCount() {
        return getPlayers().size();
    }

    /**
     * Returns the configured maximum player count advertised by the server.
     */
    int getMaxPlayerCount();

    /**
     * Changes the maximum online player count advertised by the network interfaces.
     * The standard implementation stores the supplied value without validation and
     * does not disconnect existing players when the limit is lowered.
     */
    void setMaxPlayerCount(int value);

    /**
     * Disconnects every registered player using
     * {@code TrKeys.MC_DISCONNECT_DISCONNECTED} as the translated default reason.
     * Each connection performs its own lifecycle cleanup and language selection
     * through its disconnect implementation.
     */
    default void disconnectAllPlayers() {
        disconnectAllPlayers(TrKeys.MC_DISCONNECT_DISCONNECTED);
    }

    /**
     * Disconnects every registered player with the supplied reason. Each connection
     * resolves translation keys using its own login language, and a failure from a
     * connection can interrupt the iteration.
     */
    default void disconnectAllPlayers(@MayContainTrKey String reason) {
        forEachPlayer(player -> player.disconnect(reason));
    }

    /**
     * Returns the storage implementation used for persistent player data operations.
     */
    PlayerStorage getPlayerStorage();

    /**
     * Saves current data for registered players that have fully initialized. The
     * standard implementation performs storage calls sequentially, so a propagated
     * storage failure prevents later players in that iteration from being saved.
     */
    void savePlayerData();

    /**
     * Returns an online player whose original name exactly equals
     * {@code playerName}. The comparison is case-sensitive and returns {@code null}
     * when no current entry matches; display names are not searched.
     */
    default Player getPlayerByName(String playerName) {
        return getPlayers().values().stream()
                .filter(player -> player.getOriginName().equals(playerName))
                .findFirst()
                .orElse(null);
    }

    /**
     * Returns whether the exact UUID or name string is in the player ban set.
     */
    boolean isBanned(String uuidOrName);

    /**
     * Adds an exact UUID or name string to the player ban set and disconnects
     * matching online players. Existing entries and cancelled ban events return
     * {@code false}; an accepted new entry returns {@code true}.
     */
    boolean ban(String uuidOrName);

    /**
     * Removes an exact UUID or name string after its unban event is accepted.
     * Missing entries and cancelled events return {@code false}; successful removal
     * returns {@code true} without reconnecting previously disconnected players.
     */
    boolean unban(String uuidOrName);

    /**
     * Returns an unmodifiable live view of banned UUID and name strings. The view
     * reflects later policy changes and cannot be used to add or remove entries
     * directly.
     */
    @UnmodifiableView
    Set<String> getBannedPlayers();

    /**
     * Returns whether the exact supplied string appears in the IP ban set.
     */
    boolean isIPBanned(String ip);

    /**
     * Adds an IP string after its ban event is accepted and disconnects matching
     * online connections. Existing entries and cancelled events return
     * {@code false}; the standard implementation matches its parsed socket-address
     * string.
     */
    boolean banIP(String ip);

    /**
     * Removes an IP string after its unban event is accepted. Missing entries and
     * cancelled events return {@code false}; successful removal returns {@code true}
     * without reconnecting previously disconnected clients.
     */
    boolean unbanIP(String ip);

    /**
     * Returns an unmodifiable live view of the stored IP ban strings. The view
     * reflects subsequent policy changes and supplies no normalization or validation
     * of those values.
     */
    @UnmodifiableView
    Set<String> getBannedIPs();

    /**
     * Returns whether the current server settings enable admission through the
     * whitelist.
     */
    boolean getWhitelistStatus();

    /**
     * Changes whitelist enforcement after a cancellable status-change event accepts
     * the request. Enabling enforcement disconnects registered players who match
     * neither their UUID nor original name in the whitelist.
     */
    void setWhitelistStatus(boolean enable);

    /**
     * Returns whether the player's persistent UUID or exact original name is
     * whitelisted. Either key is sufficient, and login data must already be
     * available before this convenience check runs.
     */
    default boolean isWhitelisted(Player player) {
        return isWhitelisted(player.getLoginData().getUuid().toString()) || isWhitelisted(player.getOriginName());
    }

    /**
     * Returns whether the exact UUID or name string is currently whitelisted.
     */
    boolean isWhitelisted(String uuidOrName);

    /**
     * Adds an exact UUID or name string after a cancellable addition event. Existing
     * entries and cancelled events return {@code false}; successful set insertion
     * returns {@code true} and affects later admission checks.
     */
    boolean addToWhitelist(String uuidOrName);

    /**
     * Removes an exact UUID or name string after its removal event is accepted. The
     * standard implementation disconnects online players matching that string even
     * when whitelist enforcement is currently disabled.
     */
    boolean removeFromWhitelist(String uuidOrName);

    /**
     * Returns an unmodifiable live view of the stored whitelist strings. Changes
     * through manager operations remain visible, and callers cannot use this view to
     * modify admission policy directly.
     */
    @UnmodifiableView
    Set<String> getWhitelistedPlayers();

    /**
     * Returns whether the player's persistent UUID or exact original name is an
     * operator entry. Either key is sufficient, and the player must already have
     * decoded login data.
     */
    default boolean isOperator(Player player) {
        return isOperator(player.getLoginData().getUuid().toString()) || isOperator(player.getOriginName());
    }

    /**
     * Returns whether the exact UUID or name string is an operator entry.
     */
    boolean isOperator(String uuidOrName);

    /**
     * Changes the stored operator flag for an exact UUID or name string. The
     * standard implementation refreshes abilities for the first matching online
     * player when membership changes; persistent config output is deferred until
     * manager shutdown.
     */
    void setOperator(String uuidOrName, boolean value);

    /**
     * Returns the network manager responsible for the server's registered
     * interfaces.
     */
    NetworkManager getNetworkManager();
}
