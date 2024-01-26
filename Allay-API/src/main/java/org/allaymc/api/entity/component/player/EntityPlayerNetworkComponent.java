package org.allaymc.api.entity.component.player;

import org.allaymc.api.client.data.LoginData;
import org.allaymc.api.client.data.DeviceInfo;
import org.allaymc.api.entity.component.EntityComponent;
import org.allaymc.api.i18n.LangCode;
import org.allaymc.api.i18n.MayContainTrKey;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.jetbrains.annotations.ApiStatus;

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

    default String getOriginName() {
        return getLoginData().getDisplayName();
    }

    void sendPacket(BedrockPacket packet);

    void sendPacketImmediately(BedrockPacket packet);

    default void disconnect(@MayContainTrKey String reason) {
        disconnect(reason, false);
    }

    void disconnect(@MayContainTrKey String reason, boolean hideReason);

    boolean isNetworkEncryptionEnabled();


    SecretKey getEncryptionSecretKey();

    /**
     * Has the player been fully initialized?
     * (This represents whether the player's client can see the world and proceed with gameplay.)
     */
    boolean isInitialized();

    boolean isLoggedIn();

    @ApiStatus.Internal
    void handleDataPacket();

    @ApiStatus.Internal
    void setClientSession(BedrockServerSession session);

    BedrockServerSession getClientSession();

    @ApiStatus.Internal
    void onChunkInRangeSent();
}
