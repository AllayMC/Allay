package org.allaymc.api.entity.component.player;

import org.allaymc.api.common.data.DeviceInfo;
import org.allaymc.api.common.data.LoginData;
import org.allaymc.api.entity.component.EntityComponent;
import org.allaymc.api.i18n.LangCode;
import org.allaymc.api.i18n.MayContainTrKey;
import org.allaymc.api.network.processor.PacketProcessorHolder;
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

    PacketProcessorHolder getPacketProcessorHolder();

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

    boolean isDisconnected();

    boolean isNetworkEncryptionEnabled();

    @ApiStatus.Internal
    void setNetworkEncryptionEnabled(boolean enabled);

    SecretKey getEncryptionSecretKey();

    @ApiStatus.Internal
    void setEncryptionSecretKey(SecretKey secretKey);

    @ApiStatus.Internal
    void handleDisconnect();

    /**
     * Has the player been fully initialized?
     * (This represents whether the player's client can see the world and proceed with gameplay.)
     */
    boolean isInitialized();

    boolean shouldHandleDisconnect();

    @ApiStatus.Internal
    void setInitialized();

    boolean isLoggedIn();

    @ApiStatus.Internal
    void handleDataPacket(BedrockPacket packet);

    @ApiStatus.Internal
    void setClientSession(BedrockServerSession session);

    BedrockServerSession getClientSession();

    @ApiStatus.Internal
    void onChunkInRangeSent();

    @ApiStatus.Internal
    void initializePlayer();

    @ApiStatus.Internal
    void completeLogin();

    @ApiStatus.Internal
    void setLoginData(LoginData loginData);

    @ApiStatus.Internal
    int getPing();
}
