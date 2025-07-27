package org.allaymc.server.client.service;

import eu.okaeri.configs.ConfigManager;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Getter;
import org.allaymc.api.client.data.DeviceInfo;
import org.allaymc.api.client.service.PlayerService;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.network.IPBanEvent;
import org.allaymc.api.eventbus.event.network.IPUnbanEvent;
import org.allaymc.api.eventbus.event.player.PlayerBanEvent;
import org.allaymc.api.eventbus.event.player.PlayerQuitEvent;
import org.allaymc.api.eventbus.event.player.PlayerUnbanEvent;
import org.allaymc.api.eventbus.event.server.WhitelistAddPlayerEvent;
import org.allaymc.api.eventbus.event.server.WhitelistRemovePlayerEvent;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.network.ClientStatus;
import org.allaymc.api.server.BanInfo;
import org.allaymc.api.server.Server;
import org.allaymc.api.server.Whitelist;
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.api.utils.Utils;
import org.allaymc.server.client.storage.AllayPlayerStorage;
import org.allaymc.server.network.AllayNetworkInterface;
import org.cloudburstmc.protocol.bedrock.data.skin.SerializedSkin;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayerListPacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.awt.*;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author daoge_cmd
 */
public class AllayPlayerService implements PlayerService {

    protected static final String BAN_INFO_FILE_NAME = "ban-info.yml";
    protected static final String WHITELIST_FILE_NAME = "whitelist.yml";

    @Getter
    protected final AllayPlayerStorage playerStorage;
    @Getter
    protected final AllayNetworkInterface networkInterface;
    protected final Map<UUID, EntityPlayer> players;
    protected final Map<UUID, PlayerListPacket.Entry> playerListEntries;
    protected final BanInfo banInfo;
    protected final Whitelist whitelist;

    public AllayPlayerService(AllayPlayerStorage playerStorage, AllayNetworkInterface networkInterface) {
        this.playerStorage = playerStorage;
        this.networkInterface = networkInterface;
        this.players = new Object2ObjectOpenHashMap<>();
        this.playerListEntries = new Object2ObjectOpenHashMap<>();
        this.banInfo = ConfigManager.create(BanInfo.class, Utils.createConfigInitializer(Path.of(BAN_INFO_FILE_NAME)));
        this.whitelist = ConfigManager.create(Whitelist.class, Utils.createConfigInitializer(Path.of(WHITELIST_FILE_NAME)));
    }

    public void tick(long currentTick) {
        this.playerStorage.tick(currentTick);
    }

    public void shutdown() {
        this.playerStorage.shutdown();
        this.banInfo.save();
        this.whitelist.save();
    }

    @Override
    @UnmodifiableView
    public Map<UUID, EntityPlayer> getPlayers() {
        return Collections.unmodifiableMap(players);
    }

    @Override
    public void setMaxPlayerCount(int value) {
        networkInterface.setMaxPlayerCount(value);
    }

    @Override
    public int getMaxPlayerCount() {
        return networkInterface.getMaxPlayerCount();
    }

    @Override
    public void savePlayerData() {
        players.values().stream().filter(EntityPlayer::isInitialized).forEach(playerStorage::savePlayerData);
    }

    @Override
    public void broadcastPacket(BedrockPacket packet) {
        players.values().forEach(player -> player.sendPacket(packet));
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
                .filter(player -> AllayStringUtils.fastTwoPartSplit(player.getClientSession().getSocketAddress().toString().substring(1), ":", "")[0].equals(ip))
                .forEach(player -> player.disconnect("Your IP is banned!"));
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
                .forEach(player -> player.disconnect(TrKeys.M_DISCONNECTIONSCREEN_NOTALLOWED));
        return true;
    }

    @Override
    public @UnmodifiableView Set<String> getWhitelistedPlayers() {
        return Collections.unmodifiableSet(whitelist.whitelist());
    }

    public void startNetworkInterface() {
        this.networkInterface.start();
    }

    public void shutdownNetworkInterface() {
        this.networkInterface.shutdown();
    }

    public synchronized void onLoggedIn(EntityPlayer player) {
        players.put(player.getLoginData().getUuid(), player);
        networkInterface.setPlayerCount(players.size());
    }

    public synchronized void onDisconnect(EntityPlayer player) {
        Server.getInstance().sendTr(TrKeys.A_NETWORK_CLIENT_DISCONNECTED, player.getClientSession().getSocketAddress().toString());

        // At this time the client have disconnected
        if (player.getLastClientStatus().ordinal() >= ClientStatus.LOGGED_IN.ordinal()) {
            var event = new PlayerQuitEvent(player, TextFormat.YELLOW + "%" + TrKeys.M_MULTIPLAYER_PLAYER_LEFT);
            event.call();
            Server.getInstance().broadcastTr(event.getQuitMessage(), player.getOriginName());
            players.remove(player.getLoginData().getUuid());

            // The player is added to the world and loaded data during the LOGGED_IN status, while he can log off
            // the server without waiting for the status change to IN_GAME, which is why the session remains and the
            // server thinks that the player is still on the server, but after such manipulations, the player client
            // will crash every time he logs on to the server
            if (player.getDimension() != null) {
                // The dimension of the player may be null, that because the client is still handling resource packs
                // and is not added or going to be added (willBeSpawnedNextTick == true) to any dimension. After handled
                // resources packs, the dimension of the player should always be non-null regardless the status of the
                // player because there is a check in EntityPlayerBaseComponentImpl#setLocationBeforeSpawn()
                player.getDimension().removePlayer(player);
                playerStorage.savePlayerData(player);
                removeFromPlayerList(player);
            }
        }

        networkInterface.setPlayerCount(players.size());
    }

    public synchronized void addToPlayerList(EntityPlayer player) {
        addToPlayerList(
                player.getLoginData().getUuid(),
                player.getRuntimeId(),
                player.getOriginName(),
                player.getLoginData().getDeviceInfo(),
                player.getLoginData().getXuid(),
                player.getLoginData().getSkin()
        );
    }

    private void addToPlayerList(UUID uuid, long entityId, String name, DeviceInfo deviceInfo, String xuid, SerializedSkin skin) {
        var playerListPacket = new PlayerListPacket();
        playerListPacket.setAction(PlayerListPacket.Action.ADD);

        var entry = new PlayerListPacket.Entry(uuid);
        entry.setEntityId(entityId);
        entry.setName(name);
        entry.setXuid(xuid);
        entry.setPlatformChatId(deviceInfo.deviceName());
        entry.setBuildPlatform(deviceInfo.device().getId());
        entry.setSkin(skin);
        entry.setTrustedSkin(Server.SETTINGS.resourcePackSettings().trustAllSkins());
        entry.setColor(new Color(name.hashCode() & 0xFFFFFF));

        playerListPacket.getEntries().add(entry);
        playerListEntries.put(uuid, entry);

        broadcastPacket(playerListPacket);
    }

    private void removeFromPlayerList(EntityPlayer player) {
        removeFromPlayerList(player.getLoginData().getUuid());
    }

    private void removeFromPlayerList(UUID uuid) {
        var playerListPacket = new PlayerListPacket();
        playerListPacket.setAction(PlayerListPacket.Action.REMOVE);
        playerListPacket.getEntries().add(playerListEntries.remove(uuid));
        broadcastPacket(playerListPacket);
    }

    public void sendFullPlayerListInfoTo(EntityPlayer player) {
        var playerListPacket = new PlayerListPacket();
        playerListPacket.setAction(PlayerListPacket.Action.ADD);
        playerListEntries.forEach((uuid, entry) -> {
            if (uuid != player.getLoginData().getUuid()) {
                playerListPacket.getEntries().add(entry);
            }
        });
        player.sendPacket(playerListPacket);
    }

    public void onSkinUpdate(EntityPlayer player) {
        this.playerListEntries.get(player.getLoginData().getUuid()).setSkin(player.getSkin());
    }
}
