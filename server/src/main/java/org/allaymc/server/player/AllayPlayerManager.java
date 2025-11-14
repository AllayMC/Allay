package org.allaymc.server.player;

import com.google.common.collect.Sets;
import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.CustomKey;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.network.IPBanEvent;
import org.allaymc.api.eventbus.event.network.IPUnbanEvent;
import org.allaymc.api.eventbus.event.player.PlayerBanEvent;
import org.allaymc.api.eventbus.event.player.PlayerQuitEvent;
import org.allaymc.api.eventbus.event.player.PlayerUnbanEvent;
import org.allaymc.api.eventbus.event.server.WhitelistAddPlayerEvent;
import org.allaymc.api.eventbus.event.server.WhitelistChangeEvent;
import org.allaymc.api.eventbus.event.server.WhitelistRemovePlayerEvent;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.player.ClientState;
import org.allaymc.api.player.PlayerManager;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.server.AllayServer;
import org.allaymc.server.network.AllayNetworkInterface;
import org.allaymc.server.utils.Utils;
import org.jetbrains.annotations.UnmodifiableView;

import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author daoge_cmd
 */
public class AllayPlayerManager implements PlayerManager {

    protected static final String BAN_INFO_FILE_NAME = "ban-info.yml";
    protected static final String WHITELIST_FILE_NAME = "whitelist.yml";
    protected static final String OPERATORS_FILE_NAME = "operators.yml";

    @Getter
    protected final AllayPlayerStorage playerStorage;
    @Getter
    protected final AllayNetworkInterface networkInterface;

    protected final Map<UUID, EntityPlayer> players;
    protected final BanInfo banInfo;
    protected final Whitelist whitelist;
    protected final Operators operators;

    public AllayPlayerManager(AllayPlayerStorage playerStorage, AllayNetworkInterface networkInterface) {
        this.playerStorage = playerStorage;
        this.networkInterface = networkInterface;
        this.players = new Object2ObjectOpenHashMap<>();
        this.banInfo = ConfigManager.create(BanInfo.class, org.allaymc.server.utils.Utils.createConfigInitializer(Path.of(BAN_INFO_FILE_NAME)));
        this.whitelist = ConfigManager.create(Whitelist.class, Utils.createConfigInitializer(Path.of(WHITELIST_FILE_NAME)));
        this.operators = ConfigManager.create(Operators.class, Utils.createConfigInitializer(Path.of(OPERATORS_FILE_NAME)));
    }

    public void tick(long currentTick) {
        this.playerStorage.tick(currentTick);
    }

    public void shutdown() {
        this.playerStorage.shutdown();
        this.banInfo.save();
        this.whitelist.save();
        this.operators.save();
    }

    @Override
    @UnmodifiableView
    public Map<UUID, EntityPlayer> getPlayers() {
        return Collections.unmodifiableMap(players);
    }

    @Override
    public int getMaxPlayerCount() {
        return networkInterface.getMaxPlayerCount();
    }

    @Override
    public void setMaxPlayerCount(int value) {
        networkInterface.setMaxPlayerCount(value);
    }

    @Override
    public void savePlayerData() {
        players.values().stream().filter(EntityPlayer::isInitialized).forEach(playerStorage::savePlayerData);
    }

    @Override
    public boolean isBanned(String uuidOrName) {
        return banInfo.bannedPlayers().contains(uuidOrName);
    }

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

    @Override
    public Set<String> getBannedPlayers() {
        return Collections.unmodifiableSet(banInfo.bannedPlayers());
    }

    @Override
    public boolean isIPBanned(String ip) {
        return banInfo.bannedIps().contains(ip);
    }

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

    @Override
    public Set<String> getBannedIPs() {
        return Collections.unmodifiableSet(banInfo.bannedIps());
    }

    @Override
    public boolean getWhitelistStatus() {
        return AllayServer.getSettings().genericSettings().enableWhitelist();
    }

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

    @Override
    public boolean isWhitelisted(String uuidOrName) {
        return whitelist.whitelist().contains(uuidOrName);
    }

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

    @Override
    public @UnmodifiableView Set<String> getWhitelistedPlayers() {
        return Collections.unmodifiableSet(whitelist.whitelist());
    }

    @Override
    public boolean isOperator(String uuidOrName) {
        return operators.operators().contains(uuidOrName);
    }

    @Override
    public void setOperator(String uuidOrName, boolean value) {
        var player = players.values().stream()
                .filter(p -> p.getLoginData().getUuid().toString().equals(uuidOrName) || p.getOriginName().equals(uuidOrName))
                .findFirst();
        if (value) {
            operators.operators().add(uuidOrName);
            player.ifPresent(p -> p.setOperator(true));
        } else {
            operators.operators().remove(uuidOrName);
            player.ifPresent(p -> p.setOperator(false));
        }
    }

    public void startNetworkInterface() {
        this.networkInterface.start();
    }

    public void shutdownNetworkInterface() {
        this.networkInterface.shutdown();
    }

    public synchronized void addPlayer(EntityPlayer player) {
        this.players.put(player.getLoginData().getUuid(), player);
        this.networkInterface.setPlayerCount(this.players.size());
        Server.getInstance().getMessageChannel().addReceiver(player);
        broadcastPlayerListChange(player, true);
        // NOTICE: player list should be sent to the player itself later when the client is fully loaded.
        // Otherwise, the player's skin will not be shown correctly client-side.
    }

    public synchronized void removePlayer(EntityPlayer player) {
        var server = Server.getInstance();
        server.sendTranslatable(TrKeys.ALLAY_NETWORK_CLIENT_DISCONNECTED, player.getSocketAddress().toString());

        // At this time the client has disconnected
        if (player.getLastClientState().ordinal() >= ClientState.LOGGED_IN.ordinal()) {
            this.players.remove(player.getLoginData().getUuid());
            this.networkInterface.setPlayerCount(this.players.size());

            var event = new PlayerQuitEvent(player, TextFormat.YELLOW + "%" + TrKeys.MC_MULTIPLAYER_PLAYER_LEFT);
            event.call();

            server.getMessageChannel().broadcastTranslatable(event.getQuitMessage(), player.getOriginName());
            server.getMessageChannel().removeReceiver(player);

            // The player is added to the world and loaded data during the LOGGED_IN status, while he can log off
            // the server without waiting for the status change to IN_GAME, which is why the session remains and the
            // server thinks that the player is still on the server, but after such manipulations, the player client
            // will crash every time he logs on to the server
            if (player.getDimension() != null) {
                // The dimension of the player may be null, that because the client is still handling resource packs
                // and is not added or going to be added (willBeSpawnedNextTick == true) to any dimension. After handling
                // resource packs, the dimension of the player should always be non-null regardless of the status of the
                // player because there is a check in EntityPlayerBaseComponentImpl#setLocationBeforeSpawn()
                player.getDimension().removePlayer(player);
                this.playerStorage.savePlayerData(player);
                broadcastPlayerListChange(player, false);
            }
        }

    }

    /// Broadcast the player list change to other players except the player itself
    protected void broadcastPlayerListChange(EntityPlayer player, boolean add) {
        for (var other : players.values()) {
            if (other == player) {
                continue;
            }

            other.viewPlayerListChange(Collections.singleton(player), add);
        }
    }

    @Getter
    @Accessors(fluent = true)
    public static class Whitelist extends OkaeriConfig {
        @Comment("Whitelisted player list. The value can be player's name or uuid")
        private Set<String> whitelist = Sets.newConcurrentHashSet();
    }

    @Getter
    @Accessors(fluent = true)
    public static class BanInfo extends OkaeriConfig {
        @CustomKey("banned-players")
        @Comment("Banned player list. The value can be player's name or uuid")
        private Set<String> bannedPlayers = Sets.newConcurrentHashSet();

        @CustomKey("banned-ips")
        @Comment("Banned ip list")
        private Set<String> bannedIps = Sets.newConcurrentHashSet();
    }

    @Getter
    @Accessors(fluent = true)
    public static class Operators extends OkaeriConfig {
        @CustomKey("operators")
        @Comment("Operators list. The value can be player's name or uuid")
        private Set<String> operators = Sets.newConcurrentHashSet();
    }
}
