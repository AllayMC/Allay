package org.allaymc.api.server;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.snakeyaml.YamlSnakeYamlConfigurer;
import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.client.info.DeviceInfo;
import org.allaymc.api.client.skin.Skin;
import org.allaymc.api.command.CommandRegistry;
import org.allaymc.api.command.CommandResult;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.TextReceiver;
import org.allaymc.api.i18n.TrContainer;
import org.allaymc.api.network.NetworkServer;
import org.allaymc.api.perm.Permissible;
import org.allaymc.api.scheduler.taskcreator.TaskCreator;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldPool;
import org.allaymc.api.world.storage.PlayerStorage;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayerListPacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * The server interface
 */
public interface Server extends TaskCreator, CommandSender {
    ApiInstanceHolder<Server> INSTANCE = ApiInstanceHolder.of();

    static Server getInstance() {
        return INSTANCE.get();
    }

    String SETTINGS_FILE_NAME = "server-settings.yml";

    ServerSettings SETTINGS =
            ConfigManager.create(ServerSettings.class, it -> {
                it.withConfigurer(new YamlSnakeYamlConfigurer()); // specify configurer implementation, optionally additional serdes packages
                it.withBindFile(SETTINGS_FILE_NAME); // specify Path, File or pathname
                it.withRemoveOrphans(true); // automatic removal of undeclared keys
                it.saveDefaults(); // save file if does not exists
                it.load(true); // load and save to update comments/new fields
            });

    /**
     * Start the server
     */
    void start(long timeMillis);

    void shutdown();

    boolean isRunning();

    PlayerStorage getPlayerStorage();

    int getOnlinePlayerCount();

    NetworkServer getNetworkServer();

    @UnmodifiableView
    Map<UUID, EntityPlayer> getOnlinePlayers();

    void onConnect(BedrockServerSession session);

    void onLoggedIn(EntityPlayer player);

    void onDisconnect(EntityPlayer player);

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

    default void broadcastChat(EntityPlayer sender, String message) {
        getOnlinePlayers().values().forEach(player -> player.sendChat(sender, message));
        sendChat(sender, message);
    }

    void broadcastTr(String tr);

    void broadcastTr(String tr, String... args);

    @Override
    default boolean isOp() {
        return true;
    }

    @Override
    default void setOp(boolean value) {

    }

    @Override
    default boolean hasPerm(String perm) {
        return true;
    }

    @Override
    default Permissible addPerm(String perm) {
        return this;
    }

    @Override
    default Permissible removePerm(String perm) {
        return this;
    }

    default void broadcastCommandOutputs(CommandSender sender, TrContainer... outputs) {
        sendCommandOutputs(sender, outputs);
        getOnlinePlayers().values().forEach(player -> player.sendCommandOutputs(sender, outputs));
    }

    // CommandSender override
    @Override
    default boolean isServer() {
        return true;
    }
}
