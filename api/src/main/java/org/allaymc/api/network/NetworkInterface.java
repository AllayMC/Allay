package org.allaymc.api.network;

/**
 * {@code NetworkInterface} exposes the primary and secondary descriptions
 * advertised by a server transport in the client server list. Changes affect
 * the running interface rather than persisted server settings, allowing plugins
 * to update displayed descriptions without rewriting the configuration used at
 * the next startup.
 *
 * @author daoge_cmd
 */
public interface NetworkInterface {
    /**
     * Returns the primary server-list description currently advertised by this
     * interface.
     */
    String getMotd();

    /**
     * Changes the primary description advertised by this running interface. The
     * change does not update persisted server settings, so plugins needing the
     * same text after restart must configure or apply it again.
     */
    void setMotd(String motd);

    /**
     * Returns the secondary server-list description currently advertised by
     * this interface.
     */
    String getSubMotd();

    /**
     * Changes the secondary description advertised by this running interface.
     * The change affects runtime advertisement rather than persisted server
     * settings, so restart behavior continues to depend on the configured
     * description.
     */
    void setSubMotd(String subMotd);
}
