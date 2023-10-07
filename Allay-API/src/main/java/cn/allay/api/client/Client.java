package cn.allay.api.client;

import cn.allay.api.client.data.AdventureSettings;
import cn.allay.api.client.data.LoginData;
import cn.allay.api.client.info.DeviceInfo;
import cn.allay.api.client.movement.ClientMovementValidator;
import cn.allay.api.client.skin.Skin;
import cn.allay.api.container.processor.ContainerActionProcessorHolder;
import cn.allay.api.entity.interfaces.player.EntityPlayer;
import cn.allay.api.math.location.Location3fc;
import cn.allay.api.server.Server;
import cn.allay.api.world.World;
import cn.allay.api.world.chunk.ChunkLoader;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.TextPacket;

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

    boolean isLoggedIn();

    boolean isFirstSpawned();

    boolean isOnline();

    void sendPacket(BedrockPacket packet);

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

    default void sendChat(Client sender, String message) {
        var pk = new TextPacket();
        pk.setType(TextPacket.Type.CHAT);
        pk.setMessage(message);
        pk.setSourceName(sender.getDisplayName());
        pk.setXuid(sender.getLoginData().getXuid());
        pk.setPlatformChatId(sender.getLoginData().getDeviceInfo().getDeviceId());
        sendPacket(pk);
    }

    default void sendRawMessage(String message) {
        sendSimpleMessage(message, TextPacket.Type.RAW);
    }

    default void sendTip(String message) {
        sendSimpleMessage(message, TextPacket.Type.TIP);
    }

    default void sendPopup(String message) {
        sendSimpleMessage(message, TextPacket.Type.POPUP);
    }

    private void sendSimpleMessage(String message, TextPacket.Type type) {
        var pk = new TextPacket();
        pk.setType(TextPacket.Type.RAW);
        pk.setXuid(getLoginData().getXuid());
        pk.setMessage(message);
        sendPacket(pk);
    }
}
