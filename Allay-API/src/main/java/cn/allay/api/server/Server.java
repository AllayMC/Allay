package cn.allay.api.server;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.client.Client;
import cn.allay.api.client.info.DeviceInfo;
import cn.allay.api.client.skin.Skin;
import cn.allay.api.network.NetworkServer;
import cn.allay.api.scheduler.taskcreator.TaskCreator;
import cn.allay.api.world.World;
import cn.allay.api.world.WorldPool;
import cn.allay.api.world.storage.ClientStorage;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayerListPacket;
import org.cloudburstmc.protocol.bedrock.packet.TextPacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

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

    ClientStorage getClientStorage();

    int getOnlineClientCount();

    NetworkServer getNetworkServer();

    @UnmodifiableView
    Map<String, Client> getOnlineClients();

    void onClientConnect(BedrockServerSession session);

    void onLoginFinish(Client client);

    void onClientDisconnect(Client client);

    WorldPool getWorldPool();

    default World getDefaultWorld() {
        return getWorldPool().getDefaultWorld();
    }

    long getTicks();

    void addToPlayerList(Client client);

    void addToPlayerList(UUID uuid, long entityId, String name, DeviceInfo deviceInfo, String xuid, Skin skin);

    void removeFromPlayerList(Client client);

    void removeFromPlayerList(UUID uuid);

    Map<UUID, PlayerListPacket.Entry> getPlayerListEntryMap();

    default void sendFullPlayerListInfoTo(Client client) {
        var playerListPacket = new PlayerListPacket();
        playerListPacket.setAction(PlayerListPacket.Action.ADD);
        getPlayerListEntryMap().forEach((uuid, entry) -> {
            if (uuid != client.getUuid()) {
                playerListPacket.getEntries().add(entry);
            }
        });
        client.sendPacket(playerListPacket);
    }

    void broadcastPacket(BedrockPacket packet);

    ForkJoinPool getComputeThreadPool();

    ExecutorService getVirtualThreadPool();

    default void broadcastChat(Client sender, String message) {
        var pk = new TextPacket();
        pk.setType(TextPacket.Type.CHAT);
        pk.setMessage(message);
        pk.setSourceName(sender.getDisplayName());
        pk.setXuid(sender.getLoginData().getXuid());
        broadcastPacket(pk);
    }
}
