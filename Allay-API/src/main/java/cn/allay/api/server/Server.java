package cn.allay.api.server;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.api.network.NetworkProcessor;
import cn.allay.api.network.NetworkSettings;
import cn.allay.api.scheduler.taskcreator.TaskCreator;

/**
 * The server interface
 */
public interface Server extends TaskCreator, NetworkProcessor {
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

    @Override
    default NetworkSettings getNetworkSetting() {
        return getServerSettings().networkSettings();
    }
}
