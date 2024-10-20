package org.allaymc.api.entity.component.player;

import org.allaymc.api.client.data.DeviceInfo;
import org.allaymc.api.client.data.LoginData;
import org.allaymc.api.entity.component.EntityComponent;
import org.allaymc.api.i18n.LangCode;
import org.allaymc.api.i18n.MayContainTrKey;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.network.PacketReceiver;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;

import javax.crypto.SecretKey;
import java.util.UUID;

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
     * Get the XUID of the player.
     *
     * @return the XUID of the player.
     */
    default String getXUID() {
        return getLoginData().getXuid();
    }

    /**
     * Get the UUID of the player.
     *
     * @return the UUID of the player.
     */
    default UUID getUUID() {
        return getLoginData().getUuid();
    }

    /**
     * Get the device info of the player.
     *
     * @return the device info of the player.
     */
    default DeviceInfo getDeviceInfo() {
        return getLoginData().getDeviceInfo();
    }

    /**
     * Get the language code of the player.
     *
     * @return the language code of the player.
     */
    default LangCode getLangCode() {
        return getLoginData().getLangCode();
    }

    /**
     * Check if the player is Xbox authenticated.
     *
     * @return {@code true} if the player is Xbox authenticated, {@code true} otherwise.
     */
    default boolean isXboxAuthenticated() {
        return getLoginData().isXboxAuthenticated();
    }

    /**
     * Get the origin name of the player.
     * <p>
     * Origin name is the xbox id of a player, which won't be changed unless the player do changes in his xbox account
     * This name is used for identifying a player in the server. Check display name if you want to edit the appearance of player's name.
     *
     * @return the origin name of the player.
     */
    default String getOriginName() {
        return getLoginData().getDisplayName();
    }

    /**
     * Disconnect the player.
     */
    default void disconnect() {
        disconnect(TrKeys.M_DISCONNECT_CLOSED);
    }

    /**
     * Disconnect the player with the given reason.
     *
     * @param reason the reason for disconnecting the player.
     */
    void disconnect(@MayContainTrKey String reason);

    /**
     * Check if the player is disconnected.
     *
     * @return {@code true} if the player is disconnected, {@code false} otherwise.
     */
    boolean isDisconnected();

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
     * Check if the player has been fully initialized.
     * <p>
     * This represents whether the player's client can see the world and proceed with gameplay.
     *
     * @return {@code true} if the player has been fully initialized, {@code false} otherwise.
     */
    boolean isInitialized();

    /**
     * Check if the player is logged in.
     *
     * @return {@code true} if the player is logged in, {@code false} otherwise.
     */
    boolean isLoggedIn();

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
}
