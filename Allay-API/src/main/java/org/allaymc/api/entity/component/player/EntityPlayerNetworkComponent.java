package org.allaymc.api.entity.component.player;

import org.allaymc.api.client.data.DeviceInfo;
import org.allaymc.api.client.data.LoginData;
import org.allaymc.api.entity.component.EntityComponent;
import org.allaymc.api.i18n.LangCode;
import org.allaymc.api.i18n.MayContainTrKey;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;

import javax.crypto.SecretKey;
import java.util.UUID;

/**
 * Allay Project 2023/10/14
 *
 * @author daoge_cmd
 */
public interface EntityPlayerNetworkComponent extends EntityComponent {
    LoginData getLoginData();

    default String getXUID() {
        return getLoginData().getXuid();
    }

    default UUID getUUID() {
        return getLoginData().getUuid();
    }

    default DeviceInfo getDeviceInfo() {
        return getLoginData().getDeviceInfo();
    }

    default LangCode getLangCode() {
        return getLoginData().getLangCode();
    }

    default boolean isXboxAuthenticated() {
        return getLoginData().isXboxAuthenticated();
    }

    /**
     * Origin name is the xbox id of a player, which won't be changed unless the player do changes in his xbox account <br>
     * This name is used for identifying a player in the server. Check display name if you want to edit the appearance of player's name
     *
     * @return the origin name of the player
     */
    default String getOriginName() {
        return getLoginData().getDisplayName();
    }

    void sendPacket(BedrockPacket packet);

    void sendPacketImmediately(BedrockPacket packet);

    void disconnect(@MayContainTrKey String reason);

    boolean isDisconnected();

    boolean isNetworkEncryptionEnabled();

    SecretKey getEncryptionSecretKey();

    /**
     * Has the player been fully initialized?
     * (This represents whether the player's client can see the world and proceed with gameplay.)
     */
    boolean isInitialized();

    boolean isLoggedIn();

    BedrockServerSession getClientSession();

    int getPing();
}
