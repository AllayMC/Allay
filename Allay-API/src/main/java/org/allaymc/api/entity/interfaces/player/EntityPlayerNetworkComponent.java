package org.allaymc.api.entity.interfaces.player;

import org.allaymc.api.client.data.LoginData;
import org.allaymc.api.client.info.DeviceInfo;
import org.allaymc.api.entity.component.EntityComponent;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.jetbrains.annotations.ApiStatus;

import javax.annotation.Nullable;
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

    default String getLanguageCode() {
        return getLoginData().getLanguageCode();
    }

    default boolean isXboxAuthenticated() {
        return getLoginData().isXboxAuthenticated();
    }

    default String getOriginName() {
        return getLoginData().getDisplayName();
    }

    void sendPacket(BedrockPacket packet);

    void sendPacketImmediately(BedrockPacket packet);

    void disconnect(String reason);

    void disconnect(String reason, boolean hideReason);

    boolean isNetworkEncryptionEnabled();

    @Nullable
    SecretKey getEncryptionSecretKey();

    /**
     * Has the player been fully initialized?
     * (This represents whether the player's client can see the world and proceed with gameplay.)
     */
    boolean isInitialized();

    @ApiStatus.Internal
    void handleDataPacket();

    @ApiStatus.Internal
    void setClientSession(BedrockServerSession session);

    @ApiStatus.Internal
    void onChunkInRangeSent();
}
