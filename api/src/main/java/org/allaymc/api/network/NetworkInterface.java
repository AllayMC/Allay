package org.allaymc.api.network;

/**
 * NetworkInterface is used to open an interface on the network that the client can connect to.
 * It also holds the information that will be shown to the client in the server list.
 * <p>
 * Please note that the changes in network interface won't be saved to server settings.
 * For example if you set the motd on network interface, it will only put into effect before stop the server.
 * That make it suitable for making dynamic motd and dynamic max player count.
 *
 * @author daoge_cmd
 */
public interface NetworkInterface {
    /**
     * Set the current motd of the server.
     *
     * @param motd the new motd.
     */
    void setMotd(String motd);

    /**
     * Get the current motd of the server.
     *
     * @return the current motd.
     */
    String getMotd();

    /**
     * Set the current sub-motd of the server.
     *
     * @param subMotd the new sub-motd.
     */
    void setSubMotd(String subMotd);

    /**
     * Get the current sub-motd of the server.
     *
     * @return the current sub-motd.
     */
    String getSubMotd();
}
