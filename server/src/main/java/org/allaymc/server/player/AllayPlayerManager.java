package org.allaymc.server.player;

import com.google.common.collect.Sets;
import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.CustomKey;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.allaymc.api.eventbus.event.server.*;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.player.ClientState;
import org.allaymc.api.player.Player;
import org.allaymc.api.player.PlayerManager;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.server.AllayServer;
import org.allaymc.server.network.AllayNetworkInterface;
import org.allaymc.server.network.AllayNetworkManager;
import org.allaymc.server.utils.Utils;
import org.allaymc.server.world.AllayDimension;
import org.jetbrains.annotations.UnmodifiableView;

import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * {@code AllayPlayerManager} tracks online player connections and manages storage,
 * admission lists, and operator entries. Configuration objects retain mutable sets
 * during the server lifetime; shutdown writes those policy files, while player-data
 * saves use the selected storage implementation. Ban and whitelist operations
 * dispatch their own cancellable events before changing the corresponding policy.
 *
 * <p>
 * Online membership is keyed by resolved UUID. Adding a duplicate identity replaces
 * the map entry before disconnecting its former connection, and removal checks
 * object identity so the old connection cannot delete the replacement entry. The
 * synchronized add and remove methods protect that membership sequence, but other
 * reads and mutations do not share the same synchronization. Callers must therefore
 * preserve the server's execution discipline rather than treating the whole manager
 * as thread-safe.
 *
 * @author daoge_cmd
 */
public class AllayPlayerManager implements PlayerManager {

    /**
     * {@code BAN_INFO_FILE_NAME} selects the ban policy configuration path used
     * during construction and saved through {@code banInfo} at shutdown.
     */
    protected static final String BAN_INFO_FILE_NAME = "ban-info.yml";
    /**
     * {@code WHITELIST_FILE_NAME} selects the whitelist configuration path used
     * during construction and saved through {@code whitelist} at shutdown.
     */
    protected static final String WHITELIST_FILE_NAME = "whitelist.yml";
    /**
     * {@code OPERATORS_FILE_NAME} selects the operator configuration path used
     * during construction and saved through {@code operators} at shutdown.
     */
    protected static final String OPERATORS_FILE_NAME = "operators.yml";

    /**
     * Player data storage and lifecycle implementation.
     */
    @Getter
    protected final AllayPlayerStorage playerStorage;
    /**
     * Network interface lifecycle and advertisement manager.
     */
    @Getter
    protected final AllayNetworkManager networkManager;

    /**
     * {@code players} maps persistent {@code UUID} values to current connections. Duplicate
     * identities replace earlier values; only {@code addPlayer} and
     * {@code removePlayer} synchronize their membership sequences.
     */
    protected final Map<UUID, Player> players;
    /**
     * Mutable player and IP ban configuration.
     */
    protected final BanInfo banInfo;
    /**
     * Mutable UUID and name whitelist configuration.
     */
    protected final Whitelist whitelist;
    /**
     * Mutable UUID and name operator configuration.
     */
    protected final Operators operators;
    /**
     * Maximum advertised online player count.
     */
    protected int maxPlayerCount;

    /**
     * Creates the player manager with the supplied storage and network managers. The
     * constructor reads the initial maximum count from server settings and
     * initializes ban, whitelist, and operator files through their configuration
     * initializers.
     */
    public AllayPlayerManager(AllayPlayerStorage playerStorage, AllayNetworkManager networkManager) {
        this.playerStorage = playerStorage;
        this.networkManager = networkManager;
        this.maxPlayerCount = AllayServer.getSettings().genericSettings().maxPlayerCount();
        this.players = new Object2ObjectOpenHashMap<>();
        this.banInfo = ConfigManager.create(BanInfo.class, org.allaymc.server.utils.Utils.createConfigInitializer(Path.of(BAN_INFO_FILE_NAME)));
        this.whitelist = ConfigManager.create(Whitelist.class, Utils.createConfigInitializer(Path.of(WHITELIST_FILE_NAME)));
        this.operators = ConfigManager.create(Operators.class, Utils.createConfigInitializer(Path.of(OPERATORS_FILE_NAME)));
    }

    /**
     * Ticks player storage before ticking each registered connection for the current
     * server tick. Callers must coordinate membership changes because this iteration
     * uses the mutable player map directly.
     */
    public void tick(long currentTick) {
        this.playerStorage.tick(currentTick);
        this.players.values().forEach(player -> ((AllayPlayer) player).tick(currentTick));
    }

    /**
     * Shuts down player storage and saves ban, whitelist, and operator
     * configurations in that order. This method does not disconnect players or
     * perform a player-data save, and an earlier failure prevents later saves.
     */
    public void shutdown() {
        this.playerStorage.shutdown();
        this.banInfo.save();
        this.whitelist.save();
        this.operators.save();
    }

    /**
     * Returns an unmodifiable live view of online players indexed by persistent
     * UUID. The view reflects subsequent membership changes, so callers must not
     * treat it as an independent snapshot or mutate its entries.
     */
    @Override
    @UnmodifiableView
    public Map<UUID, Player> getPlayers() {
        return Collections.unmodifiableMap(players);
    }

    /**
     * Returns the configured maximum player count advertised by the server.
     */
    @Override
    public int getMaxPlayerCount() {
        return maxPlayerCount;
    }

    /**
     * Changes the maximum online player count advertised by the network interfaces.
     * The standard implementation stores the supplied value without validation and
     * does not disconnect existing players when the limit is lowered.
     */
    @Override
    public void setMaxPlayerCount(int value) {
        this.maxPlayerCount = value;
        for (var iface : networkManager.getInterfaces()) {
            ((AllayNetworkInterface) iface).setMaxPlayerCount(value);
        }
    }

    /**
     * Saves current data for registered players that have fully initialized. The
     * standard implementation performs storage calls sequentially, so a propagated
     * storage failure prevents later players in that iteration from being saved.
     */
    @Override
    public void savePlayerData() {
        players.values().stream().filter(Player::isInitialized).forEach(playerStorage::savePlayerData);
    }

    /**
     * Returns whether the exact UUID or name string is in the player ban set.
     */
    @Override
    public boolean isBanned(String uuidOrName) {
        return banInfo.bannedPlayers().contains(uuidOrName);
    }

    /**
     * Adds an exact UUID or name string to the player ban set and disconnects
     * matching online players. Existing entries and cancelled ban events return
     * {@code false}; an accepted new entry returns {@code true}.
     */
    @Override
    public boolean ban(String uuidOrName) {
        if (isBanned(uuidOrName)) {
            return false;
        }

        var event = new PlayerBanEvent(uuidOrName);
        if (!event.call()) {
            return false;
        }

        banInfo.bannedPlayers().add(uuidOrName);
        players.values().stream()
                .filter(player -> player.getLoginData().getUuid().toString().equals(uuidOrName) || player.getOriginName().equals(uuidOrName))
                .forEach(player -> player.disconnect("You are banned!"));

        return true;
    }

    /**
     * Removes an exact UUID or name string after its unban event is accepted.
     * Missing entries and cancelled events return {@code false}; successful removal
     * returns {@code true} without reconnecting previously disconnected players.
     */
    @Override
    public boolean unban(String uuidOrName) {
        if (!isBanned(uuidOrName)) {
            return false;
        }

        var event = new PlayerUnbanEvent(uuidOrName);
        if (!event.call()) {
            return false;
        }

        banInfo.bannedPlayers().remove(uuidOrName);
        return true;
    }

    /**
     * Returns an unmodifiable live view of banned UUID and name strings. The view
     * reflects later policy changes and cannot be used to add or remove entries
     * directly.
     */
    @Override
    public Set<String> getBannedPlayers() {
        return Collections.unmodifiableSet(banInfo.bannedPlayers());
    }

    /**
     * Returns whether the exact supplied string appears in the IP ban set.
     */
    @Override
    public boolean isIPBanned(String ip) {
        return banInfo.bannedIps().contains(ip);
    }

    /**
     * Adds an IP string after its ban event is accepted and disconnects matching
     * online connections. Existing entries and cancelled events return
     * {@code false}; the standard implementation matches its parsed socket-address
     * string.
     */
    @Override
    public boolean banIP(String ip) {
        if (isIPBanned(ip)) {
            return false;
        }

        var event = new IPBanEvent(ip);
        if (!event.call()) {
            return false;
        }

        banInfo.bannedIps().add(ip);
        players.values().stream()
                .filter(player -> AllayStringUtils.fastTwoPartSplit(player.getSocketAddress().toString().substring(1), ":", "")[0].equals(ip))
                .forEach(player -> player.disconnect(TrKeys.ALLAY_DISCONNECT_BANIP));

        return true;
    }

    /**
     * Removes an IP string after its unban event is accepted. Missing entries and
     * cancelled events return {@code false}; successful removal returns {@code true}
     * without reconnecting previously disconnected clients.
     */
    @Override
    public boolean unbanIP(String ip) {
        if (!isIPBanned(ip)) {
            return false;
        }

        var event = new IPUnbanEvent(ip);
        if (!event.call()) {
            return false;
        }

        banInfo.bannedIps().remove(ip);
        return true;
    }

    /**
     * Returns an unmodifiable live view of the stored IP ban strings. The view
     * reflects subsequent policy changes and supplies no normalization or validation
     * of those values.
     */
    @Override
    public Set<String> getBannedIPs() {
        return Collections.unmodifiableSet(banInfo.bannedIps());
    }

    /**
     * Returns whether the current server settings enable admission through the
     * whitelist.
     */
    @Override
    public boolean getWhitelistStatus() {
        return AllayServer.getSettings().genericSettings().enableWhitelist();
    }

    /**
     * Changes whitelist enforcement after a cancellable status-change event accepts
     * the request. Enabling enforcement disconnects registered players who match
     * neither their UUID nor original name in the whitelist.
     */
    @Override
    public void setWhitelistStatus(boolean enable) {
        var event = new WhitelistChangeEvent(enable);
        if (!event.call()) {
            return;
        }

        AllayServer.getSettings().genericSettings().enableWhitelist(enable);
        if (enable) {
            getPlayers().values().stream()
                    .filter(player -> !isWhitelisted(player))
                    .forEach(player -> player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_NOTALLOWED));
        }
    }

    /**
     * Returns whether the exact UUID or name string is in the whitelist.
     */
    @Override
    public boolean isWhitelisted(String uuidOrName) {
        return whitelist.whitelist().contains(uuidOrName);
    }

    /**
     * Adds an exact UUID or name string after a cancellable addition event. Existing
     * entries and cancelled events return {@code false}; successful set insertion
     * returns {@code true} and affects later admission checks.
     */
    @Override
    public boolean addToWhitelist(String uuidOrName) {
        if (isWhitelisted(uuidOrName)) {
            return false;
        }

        var event = new WhitelistAddPlayerEvent(uuidOrName);
        if (!event.call()) {
            return false;
        }

        return whitelist.whitelist().add(uuidOrName);
    }

    /**
     * Removes an exact UUID or name string after its removal event is accepted. The
     * standard implementation disconnects online players matching that string even
     * when whitelist enforcement is currently disabled.
     */
    @Override
    public boolean removeFromWhitelist(String uuidOrName) {
        if (!isWhitelisted(uuidOrName)) {
            return false;
        }

        var event = new WhitelistRemovePlayerEvent(uuidOrName);
        if (!event.call()) {
            return false;
        }

        whitelist.whitelist().remove(uuidOrName);
        players.values().stream()
                .filter(player -> player.getLoginData().getUuid().toString().equals(uuidOrName) || player.getOriginName().equals(uuidOrName))
                .forEach(player -> player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_NOTALLOWED));
        return true;
    }

    /**
     * Returns an unmodifiable live view of the stored whitelist strings. Changes
     * through manager operations remain visible, and callers cannot use this view to
     * modify admission policy directly.
     */
    @Override
    public @UnmodifiableView Set<String> getWhitelistedPlayers() {
        return Collections.unmodifiableSet(whitelist.whitelist());
    }

    /**
     * Returns whether the exact UUID or name string is in the operator set.
     */
    @Override
    public boolean isOperator(String uuidOrName) {
        return operators.operators().contains(uuidOrName);
    }

    /**
     * Changes the stored operator flag for an exact UUID or name string. The
     * standard implementation refreshes abilities for the first matching online
     * player when membership changes; persistent config output is deferred until
     * manager shutdown.
     */
    @Override
    public void setOperator(String uuidOrName, boolean value) {
        if (value == isOperator(uuidOrName)) {
            return;
        }

        if (value) {
            operators.operators().add(uuidOrName);
        } else {
            operators.operators().remove(uuidOrName);
        }

        players.values().stream()
                .filter(p -> p.getLoginData().getUuid().toString().equals(uuidOrName) || p.getOriginName().equals(uuidOrName))
                .findFirst()
                .ifPresent(player -> this.players.values().forEach(viewer -> viewer.viewPlayerAbilities(player)));
    }

    /**
     * Starts every registered network interface through the configured network
     * manager.
     */
    public void startNetworkInterfaces() {
        this.networkManager.startAll();
    }

    /**
     * Shuts down registered network interfaces through the configured network
     * manager.
     */
    public void shutdownNetworkInterfaces() {
        this.networkManager.shutdownAll();
    }

    /**
     * Registers a spawned player under its persistent UUID and announces its
     * membership to other players. The caller must provide initialized login data
     * and a controlled entity; synchronization serializes this registration against
     * other add and remove calls.
     *
     * <p>
     * A duplicate UUID replaces the old map entry before the old connection is
     * disconnected. That ordering lets identity-checked removal preserve the new
     * connection. The method updates advertisements and message-channel membership
     * before broadcasting the list addition; the joining client receives its
     * complete player list later during full-join synchronization.
     */
    public synchronized void addPlayer(Player player) {
        var previous = this.players.put(player.getLoginData().getUuid(), player);
        if (previous != null) {
            // Disconnect the displaced connection after publishing the replacement; its identity-checked
            // removal must not delete the newly registered connection with the same UUID.
            previous.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_BODY_LOGGEDINELSEWHERE);
        }

        updatePlayerCount();
        Server.getInstance().getMessageChannel().addReceiver(player.getControlledEntity());
        broadcastPlayerListChange(player, true);
        // Defer this client's complete list to AllayPlayer.onFullyJoin, where its own skin and other
        // joining state are synchronized after the initial chunk threshold.
    }

    /**
     * Removes a disconnected player's memberships and saves its final data when its
     * previous state reached spawning. The current map entry is removed only when it
     * still refers to this connection, preserving a replacement connection with the
     * same UUID.
     *
     * <p>
     * The quit event controls the departure message, then message-channel removal
     * and dimension removal are requested before storage saving and player-list
     * removal broadcast. Dimension removal may queue entity work, so saving does not
     * wait for its callback. Earlier exceptions can interrupt this sequence; the
     * method does not roll back already completed cleanup.
     */
    public synchronized void removePlayer(Player player) {
        var server = Server.getInstance();
        server.sendTranslatable(TrKeys.ALLAY_NETWORK_CLIENT_DISCONNECTED, player.getSocketAddress().toString());

        // Only connections that reached spawning have player-list, dimension, and message memberships
        // to clean up here; earlier disconnects skip this player-data save path.
        if (player.getLastClientState().ordinal() >= ClientState.SPAWNED.ordinal()) {
            var uuid = player.getLoginData().getUuid();
            if (this.players.get(uuid) == player) {
                this.players.remove(uuid);
                updatePlayerCount();
            }

            var event = new PlayerQuitEvent(player, TextFormat.YELLOW + "%" + TrKeys.MC_MULTIPLAYER_PLAYER_LEFT);
            event.call();

            var quitMessage = event.getQuitMessage();
            if (quitMessage != null && !quitMessage.isBlank()) {
                server.getMessageChannel().broadcastTranslatable(quitMessage, player.getOriginName());
            }

            var entity = player.getControlledEntity();
            server.getMessageChannel().removeReceiver(entity);
            // Request dimension-level removal so its player set and chunk loader are cleaned up along
            // with the entity, rather than removing only the entity manager entry.
            var dimension = entity.getDimension();
            if (dimension != null) {
                ((AllayDimension) dimension).removePlayer(player);
            }
            
            this.playerStorage.savePlayerData(player);
            broadcastPlayerListChange(player, false);
        }

    }

    /**
     * Sends an addition or removal for the supplied player to every other current
     * connection. The player itself is excluded; its initial complete list is sent
     * only after its full-join sequence reaches synchronization.
     */
    protected void broadcastPlayerListChange(Player player, boolean add) {
        for (var other : players.values()) {
            if (other == player) {
                continue;
            }

            other.viewPlayerListChange(Collections.singleton(player), add);
        }
    }

    /**
     * Publishes the current map size to each registered network interface. The
     * manager expects its interfaces to be {@code AllayNetworkInterface} instances,
     * and this updates advertisements rather than enforcing a connection limit.
     */
    protected void updatePlayerCount() {
        var count = this.players.size();
        for (var iface : networkManager.getInterfaces()) {
            ((AllayNetworkInterface) iface).setPlayerCount(count);
        }
    }

    /**
     * {@code Whitelist} holds UUID and player-name strings accepted by the manager's
     * whitelist checks. Its mutable set is exposed by a Lombok fluent getter for
     * configuration and policy updates.
     */
    @Getter
    @Accessors(fluent = true)
    public static class Whitelist extends OkaeriConfig {
        /**
         * {@code whitelist} retains exact UUID and name strings. Manager admission
         * checks accept either identity, and configuration saving persists the
         * current set.
         */
        @Comment("Whitelisted player list. The value can be player's name or uuid")
        private Set<String> whitelist = Sets.newConcurrentHashSet();
    }

    /**
     * {@code BanInfo} holds player-identity and IP strings rejected by the manager's
     * ban checks. Its concurrent sets support policy mutations, and the
     * configuration object persists them when the manager saves it.
     */
    @Getter
    @Accessors(fluent = true)
    public static class BanInfo extends OkaeriConfig {
        /**
         * {@code bannedPlayers} retains exact UUID and name strings. The manager
         * adds or removes entries after cancellable policy events and saves them at
         * shutdown.
         */
        @CustomKey("banned-players")
        @Comment("Banned player list. The value can be player's name or uuid")
        private Set<String> bannedPlayers = Sets.newConcurrentHashSet();

        /**
         * {@code bannedIps} retains exact IP strings. The manager exposes an
         * unmodifiable live view and saves policy changes through this configuration
         * at shutdown.
         */
        @CustomKey("banned-ips")
        @Comment("Banned ip list")
        private Set<String> bannedIps = Sets.newConcurrentHashSet();
    }

    /**
     * {@code Operators} holds UUID and player-name strings granted operator status
     * by the manager. Its mutable set is exposed through a Lombok fluent getter for
     * configuration persistence and policy checks.
     */
    @Getter
    @Accessors(fluent = true)
    public static class Operators extends OkaeriConfig {
        /**
         * {@code operators} retains exact UUID and name strings. Manager permission
         * checks accept either identity, and configuration saving persists the
         * current set.
         */
        @CustomKey("operators")
        @Comment("Operators list. The value can be player's name or uuid")
        private Set<String> operators = Sets.newConcurrentHashSet();
    }
}
