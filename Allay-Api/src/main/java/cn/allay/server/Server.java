package cn.allay.server;

/**
 * The server interface
 */
public interface Server {
    /**
     * Initialize the server
     * @return true if success
     */
    void initServer();
    /**
     * Start the server main loop
     */
    void startMainLoop();

    /**
     * Get the server settings
     * @return the server settings
     */
    ServerSettings getServerSettings();

    int getOnlinePlayerCount();
}
