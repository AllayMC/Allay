package cn.allay.api.server;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.client.info.DeviceInfo;
import cn.allay.api.client.skin.Skin;
import cn.allay.api.entity.interfaces.player.EntityPlayer;
import cn.allay.api.network.NetworkServer;
import cn.allay.api.scheduler.Scheduler;
import cn.allay.api.scheduler.taskcreator.TaskCreator;
import cn.allay.api.world.World;
import cn.allay.api.world.WorldPool;
import cn.allay.api.world.storage.PlayerStorage;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayerListPacket;
import org.cloudburstmc.protocol.bedrock.packet.TextPacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * The server interface
 */
public interface Server extends TaskCreator {
    ApiInstanceHolder<Server> INSTANCE = ApiInstanceHolder.of();

    static Server getInstance() {
        return INSTANCE.get();
    }

    /**
     * Start the server
     */
    void start();

    void shutdown();

    boolean isRunning();

    /**
     * Get the server settings
     *
     * @return the server settings
     */
    ServerSettings getServerSettings();

    PlayerStorage getPlayerStorage();

    int getOnlinePlayerCount();

    NetworkServer getNetworkServer();

    Scheduler getServerScheduler();

    @UnmodifiableView
    Map<UUID, EntityPlayer> getOnlinePlayers();

    void onConnect(BedrockServerSession session);

    void onLoggedIn(EntityPlayer player);

    void onDisconnect(EntityPlayer player);

    WorldPool getWorldPool();

    default World getDefaultWorld() {
        return getWorldPool().getDefaultWorld();
    }

    long getTicks();

    void addToPlayerList(EntityPlayer player);

    void addToPlayerList(UUID uuid, long entityId, String name, DeviceInfo deviceInfo, String xuid, Skin skin);

    void removeFromPlayerList(EntityPlayer player);

    void removeFromPlayerList(UUID uuid);

    Map<UUID, PlayerListPacket.Entry> getPlayerListEntryMap();

    default void sendFullPlayerListInfoTo(EntityPlayer player) {
        var playerListPacket = new PlayerListPacket();
        playerListPacket.setAction(PlayerListPacket.Action.ADD);
        getPlayerListEntryMap().forEach((uuid, entry) -> {
            if (uuid != player.getUuid()) {
                playerListPacket.getEntries().add(entry);
            }
        });
        player.sendPacket(playerListPacket);
    }

    void broadcastPacket(BedrockPacket packet);

    ThreadPoolExecutor getComputeThreadPool();

    ExecutorService getVirtualThreadPool();

    default void broadcastChat(EntityPlayer sender, String message) {
        var pk = new TextPacket();
        pk.setType(TextPacket.Type.CHAT);
        pk.setMessage(message);
        pk.setSourceName(sender.getDisplayName());
        pk.setXuid(sender.getLoginData().getXuid());
        broadcastPacket(pk);
    }
}
