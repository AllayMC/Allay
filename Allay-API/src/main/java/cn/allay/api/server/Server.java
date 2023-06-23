package cn.allay.api.server;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.network.Client;
import cn.allay.api.network.NetworkServer;
import cn.allay.api.scheduler.taskcreator.TaskCreator;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * The server interface
 */
public interface Server extends TaskCreator {
    ApiInstanceHolder<Server> INSTANCE = ApiInstanceHolder.of();

    static Server getInstance() {
        return INSTANCE.get();
    }

    /**
     * Initialize the server
     */
    void initServer();

    /**
     * Start the server main loop
     */
    void startMainLoop();

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
}
