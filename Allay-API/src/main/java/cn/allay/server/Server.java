package cn.allay.server;

import cn.allay.api.AllayAPI;

/**
 * The server interface
 */
public interface Server {

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
