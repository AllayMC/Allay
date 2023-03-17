package cn.allay.server;

import cn.allay.api.ApiInstanceHolder;
import cn.allay.scheduler.taskcreator.TaskCreator;

/**
 * The server interface
 */
public interface Server extends TaskCreator {

    ApiInstanceHolder<Server> INSTANCE = ApiInstanceHolder.create();

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

    int getOnlinePlayerCount();
}
