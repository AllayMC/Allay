package cn.allay.server;

import cn.allay.api.AllayAPI;
import cn.allay.scheduler.taskcreator.TaskCreator;

/**
 * The server interface
 */
public interface Server extends TaskCreator {

    Server INSTANCE = AllayAPI.getInstance().getAPIInstance(Server.class);

    static Server getInstance() {
        return INSTANCE;
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
