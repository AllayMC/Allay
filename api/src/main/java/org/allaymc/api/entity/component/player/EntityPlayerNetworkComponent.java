package org.allaymc.api.entity.component.player;

import org.allaymc.api.client.data.LoginData;
import org.allaymc.api.entity.component.EntityComponent;
import org.allaymc.api.i18n.MayContainTrKey;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.network.ClientStatus;
import org.allaymc.api.network.PacketReceiver;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;

import javax.crypto.SecretKey;

/**
 * EntityPlayerNetworkComponent is the component that manages network related things of a player.
 *
 * @author daoge_cmd
 */
public interface EntityPlayerNetworkComponent extends EntityComponent, PacketReceiver {
    /**
     * Get the login data of the player.
     *
     * @return the login data of the player.
     */
    LoginData getLoginData();

    /**
     * Get the origin name of the player.
     * <p>
     * Origin name is the xbox id of a player, which won't be changed unless the player do changes in his xbox account
     * This name is used for identifying a player in the server. Check display name if you want to edit the appearance of player's name.
     *
     * @return the origin name of the player.
     */
    default String getOriginName() {
        return getLoginData().getXname();
    }

    /**
     * Disconnect the player.
     */
    default void disconnect() {
        disconnect(TrKeys.MC_DISCONNECT_DISCONNECTED);
    }

    /**
     * Disconnect the player with the given reason.
     *
     * @param reason the reason for disconnecting the player.
     */
    void disconnect(@MayContainTrKey String reason);

    /**
     * Get the client status of this player.
     *
     * @return the client status of this player.
     */
    ClientStatus getClientStatus();

    /**
     * Get the last client status of this player.
     *
     * @return the last client status of this player.
     */
    ClientStatus getLastClientStatus();

    /**
     * Check if the player is connected.
     *
     * @return {@code true} if the player is connected, {@code false} otherwise.
     */
    default boolean isConnected() {
        return getClientStatus().ordinal() >= ClientStatus.CONNECTED.ordinal();
    }

    /**
     * Check if the player is logged in.
     *
     * @return {@code true} if the player is logged in, {@code false} otherwise.
     */
    default boolean isLoggedIn() {
        return getClientStatus().ordinal() >= ClientStatus.LOGGED_IN.ordinal();
    }

    /**
     * Check if the player has been fully initialized.
     * <p>
     * This represents whether the player's client can see the world and proceed with gameplay.
     *
     * @return {@code true} if the player has been fully initialized, {@code false} otherwise.
     */
    default boolean isInitialized() {
        return getClientStatus().ordinal() >= ClientStatus.IN_GAME.ordinal();
    }

    /**
     * Check if the player is disconnected.
     *
     * @return {@code true} if the player is disconnected, {@code false} otherwise.
     */
    default boolean isDisconnected() {
        return getClientStatus() == ClientStatus.DISCONNECTED;
    }

    /**
     * Check if network encryption is enabled for the player.
     *
     * @return {@code true} if network encryption is enabled, {@code false} otherwise.
     */
    boolean isNetworkEncryptionEnabled();

    /**
     * Get the encryption secret key for the player.
     *
     * @return the encryption secret key for the player.
     */
    SecretKey getEncryptionSecretKey();

    /**
     * Get the client session for the player.
     *
     * @return the client session for the player.
     */
    BedrockServerSession getClientSession();

    /**
     * Get the ping of the player.
     *
     * @return the ping of the player.
     */
    int getPing();

    /**
     * Check if the client cache is enabled.
     *
     * @return {@code true} if the client cache is enabled, {@code false} otherwise.
     */
    boolean isClientCacheEnabled();
}
