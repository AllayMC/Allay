package cn.allay.api.server;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.client.Client;
import cn.allay.api.client.info.DeviceInfo;
import cn.allay.api.client.skin.Skin;
import cn.allay.api.network.NetworkServer;
import cn.allay.api.scheduler.taskcreator.TaskCreator;
import cn.allay.api.world.World;
import cn.allay.api.world.WorldPool;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayerListPacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;
import java.util.UUID;

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

    void broadcastPacket(BedrockPacket packet);
}
