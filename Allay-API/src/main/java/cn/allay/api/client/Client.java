package cn.allay.api.client;

import cn.allay.api.client.data.AdventureSettings;
import cn.allay.api.client.data.LoginData;
import cn.allay.api.client.info.DeviceInfo;
import cn.allay.api.client.movement.ClientMovementValidator;
import cn.allay.api.client.skin.Skin;
import cn.allay.api.container.processor.ContainerActionProcessorHolder;
import cn.allay.api.entity.impl.EntityPlayer;
import cn.allay.api.math.location.Location3fc;
import cn.allay.api.server.Server;
import cn.allay.api.world.World;
import cn.allay.api.world.chunk.ChunkLoader;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayStatusPacket;

import javax.annotation.Nullable;
import javax.crypto.SecretKey;
import java.util.UUID;

/**
 * 描述一个玩家客户端，玩家客户端可以持有一个EntityHuman并操控它
 * <p>
 * Allay Project 2023/6/23
 *
 * @author daoge_cmd
 */
public interface Client extends ChunkLoader {
    Server getServer();

    LoginData getLoginData();

    boolean isFirstSpawned();

    void sendPacket(BedrockPacket packet);

    void sendPlayStatus(PlayStatusPacket.Status status);

    void sendPacketImmediately(BedrockPacket packet);

    void disconnect(String reason);

    void disconnect(String reason, boolean hideReason);

    void initializePlayer();

    Skin getSkin();

    void setSkin(Skin skin);

    String getDisplayName();

    void setDisplayName(String displayName);

    EntityPlayer getPlayerEntity();

    boolean isOp();

    void setOp(boolean op);

    GameType getGameType();

    void setGameType(GameType gameType);

    AdventureSettings getAdventureSettings();

    boolean isNetworkEncryptionEnabled();

    ContainerActionProcessorHolder getContainerActionProcessorHolder();

    @Nullable
    SecretKey getEncryptionSecretKey();

    ClientMovementValidator getMovementValidator();

    boolean computeMovementServerSide();

    boolean isLocalInitialized();

    void setMovementValidator(ClientMovementValidator validator);

    default String getXuid() {
        return getLoginData().getXuid();
    }

    default UUID getUuid() {
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

    default String getName() {
        return getLoginData().getDisplayName();
    }

    @Override
    default Location3fc getLocation() {
        return getPlayerEntity().getLocation();
    }

    default World getWorld() {
        return getLocation().world();
    }
}
