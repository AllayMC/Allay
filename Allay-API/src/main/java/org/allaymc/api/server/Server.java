package org.allaymc.api.server;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.snakeyaml.YamlSnakeYamlConfigurer;
import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.client.data.DeviceInfo;
import org.allaymc.api.client.skin.Skin;
import org.allaymc.api.client.storage.PlayerStorage;
import org.allaymc.api.command.CommandRegistry;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.TrContainer;
import org.allaymc.api.network.NetworkServer;
import org.allaymc.api.plugin.PluginManager;
import org.allaymc.api.scheduler.Scheduler;
import org.allaymc.api.scheduler.taskcreator.TaskCreator;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldPool;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayerListPacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * The server interface
 */
public interface Server extends TaskCreator, CommandSender {

    ApiInstanceHolder<Server> INSTANCE = ApiInstanceHolder.of();

    String SETTINGS_FILE_NAME = "server-settings.yml";

    ServerSettings SETTINGS = ConfigManager.create(ServerSettings.class, it -> {
        it.withConfigurer(new YamlSnakeYamlConfigurer()); // specify configurer implementation, optionally additional serdes packages
        it.withBindFile(SETTINGS_FILE_NAME); // specify Path, File or pathname
        it.withRemoveOrphans(true); // automatic removal of undeclared keys
        it.saveDefaults(); // save file if it does not exist
        it.load(true); // load and save to update comments/new fields
    });

    static Server getInstance() {
        return INSTANCE.get();
    }

    /**
     * Start the server
     */
    void start(long timeMillis);

    long getStartTime();

    void kickAllPlayersAndBlock();

    Scheduler getScheduler();

    PluginManager getPluginManager();

    void tick(long currentTick);

    long getTick();

    void shutdown();

    boolean isRunning();

    PlayerStorage getPlayerStorage();

    int getOnlinePlayerCount();

    NetworkServer getNetworkServer();

    @UnmodifiableView
    Map<UUID, EntityPlayer> getOnlinePlayers();

    default EntityPlayer findOnlinePlayerByName(String playerName) {
        for (var player : getOnlinePlayers().values()) {
            if (player.getCommandSenderName().equals(playerName)) {
                return player;
            }
        }
        return null;
    }

    void onConnect(BedrockServerSession session);

    void onLoggedIn(EntityPlayer player);

    void onDisconnect(EntityPlayer player, String reason);

    WorldPool getWorldPool();

    CommandRegistry getCommandRegistry();

    default World getDefaultWorld() {
        return getWorldPool().getDefaultWorld();
    }

    void addToPlayerList(EntityPlayer player);

    void addToPlayerList(UUID uuid, long entityId, String name, DeviceInfo deviceInfo, String xuid, Skin skin);

    void removeFromPlayerList(EntityPlayer player);

    void removeFromPlayerList(UUID uuid);

    Map<UUID, PlayerListPacket.Entry> getPlayerListEntryMap();

    default void sendFullPlayerListInfoTo(EntityPlayer player) {
        var playerListPacket = new PlayerListPacket();
        playerListPacket.setAction(PlayerListPacket.Action.ADD);
        getPlayerListEntryMap().forEach((uuid, entry) -> {
            if (uuid != player.getUUID()) {
                playerListPacket.getEntries().add(entry);
            }
        });
        player.sendPacket(playerListPacket);
    }

    void broadcastPacket(BedrockPacket packet);

    ThreadPoolExecutor getComputeThreadPool();

    ExecutorService getVirtualThreadPool();

    default void broadcastMessage(String message) {
        getOnlinePlayers().values().forEach(player -> player.sendText(message));
        sendText(message);
    }

    void broadcastTr(String tr);

    void broadcastTr(String tr, String... args);

    @Override
    default void setOp(boolean value) {
        throw new UnsupportedOperationException("Cannot call setOp() on server instance!");
    }

    default void broadcastCommandOutputs(CommandSender sender, int status, TrContainer... outputs) {
        sendCommandOutputs(sender, status, outputs);
        getOnlinePlayers().values().forEach(player -> player.sendCommandOutputs(sender, status, outputs));
    }

    void savePlayerData();

    @Override
    default boolean hasPerm(String perm) {
        return true;
    }

    boolean isBanned(String uuidOrName);

    boolean ban(String uuidOrName);

    boolean unban(String uuidOrName);

    @UnmodifiableView
    Set<String> getBannedPlayers();

    boolean isIPBanned(String ip);

    boolean banIP(String ip);

    boolean unbanIP(String ip);

    @UnmodifiableView
    Set<String> getBannedIPs();

    boolean isWhitelisted(String uuidOrName);

    boolean addToWhitelist(String uuidOrName);

    boolean removeFromWhitelist(String uuidOrName);

    @UnmodifiableView
    Set<String> getWhitelistedPlayers();
}
