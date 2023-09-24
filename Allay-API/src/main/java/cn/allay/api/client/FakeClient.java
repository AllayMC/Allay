package cn.allay.api.client;

import cn.allay.api.client.data.LoginData;
import cn.allay.api.client.info.Device;
import cn.allay.api.client.info.DeviceInfo;
import cn.allay.api.client.info.UIProfile;
import cn.allay.api.client.skin.Skin;
import cn.allay.api.container.SimpleContainerActionProcessorHolder;
import cn.allay.api.container.processor.ContainerActionProcessorHolder;
import cn.allay.api.entity.interfaces.EntityPlayer;
import cn.allay.api.server.Server;
import cn.allay.api.world.chunk.Chunk;
import lombok.Getter;
import lombok.Setter;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.SubChunkPacket;
import org.cloudburstmc.protocol.bedrock.packet.SubChunkRequestPacket;

import javax.annotation.Nullable;
import javax.crypto.SecretKey;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;

/**
 * Allay Project 2023/8/4
 *
 * @author daoge_cmd
 */
@Getter
public class FakeClient extends BaseClient {

    protected static final ContainerActionProcessorHolder EMPTY_CONTAINER_ACTION_PROCESSOR_HOLDER = new SimpleContainerActionProcessorHolder();

    @Setter
    protected boolean loaderActive;
    @Setter
    protected boolean computeMovement;

    protected FakeClient(LoginData loginData, boolean loaderActive,
                         Skin skin, String displayName,
                         boolean op, GameType gameType,
                         EntityPlayer playerEntity,
                         boolean computeMovement) {
        this.loginData = loginData;
        this.loaderActive = loaderActive;
        this.skin = skin;
        this.displayName = displayName;
        this.op = op;
        this.gameType = gameType;
        this.playerEntity = playerEntity;
        this.computeMovement = computeMovement;
    }

    public static FakeClientBuilder builder() {
        return new FakeClientBuilder();
    }

    @Override
    public boolean computeMovementServerSide() {
        return computeMovement;
    }

    @Override
    public boolean isLocalInitialized() {
        return true;
    }

    @Override
    public boolean isFirstSpawned() {
        return true;
    }

    @Override
    public boolean isOnline() {
        return true;
    }

    @Override
    public void preSendChunks(Set<Long> chunkHashes) {
    }

    @Override
    public void notifyChunkLoaded(Chunk chunk) {
    }

    @Override
    public void unloadChunks(Set<Long> chunkHashes) {
    }

    @Override
    public void setSubChunkRequestHandler(Function<SubChunkRequestPacket, SubChunkPacket> handler) {
    }

    @Override
    public void sendPacket(BedrockPacket packet) {
    }

    @Override
    public void sendPacketImmediately(BedrockPacket packet) {
    }

    @Override
    public void disconnect(String reason) {
    }

    @Override
    public void disconnect(String reason, boolean hideReason) {
    }

    @Override
    public void initializePlayer() {
    }

    @Override
    public boolean isXboxAuthenticated() {
        return true;
    }

    @Override
    public ContainerActionProcessorHolder getContainerActionProcessorHolder() {
        return EMPTY_CONTAINER_ACTION_PROCESSOR_HOLDER;
    }

    @Override
    @Nullable
    public SecretKey getEncryptionSecretKey() {
        return null;
    }

    public static class FakeClientBuilder {
        private String name;
        private String displayName;
        private Skin skin;
        private EntityPlayer playerEntity;
        private boolean op = false;
        private boolean loaderActive = true;
        private boolean xboxAuthenticated = true;
        private GameType gameType = GameType.CREATIVE;
        private UUID uuid = UUID.randomUUID();
        private String xuid = uuid.toString();
        private DeviceInfo deviceInfo = new DeviceInfo(
                "", "", -1L,
                Device.UNKNOWN, UIProfile.CLASSIC
        );
        private String languageCode = "en_US";
        private String gameVersion = Server.getInstance().getNetworkServer().getCodec().getMinecraftVersion();
        private String identityPublicKey = "";
        private boolean computeMovement = true;

        FakeClientBuilder() {
        }

        public FakeClientBuilder loaderActive(boolean loaderActive) {
            this.loaderActive = loaderActive;
            return this;
        }

        public FakeClientBuilder xboxAuthenticated(boolean xboxAuthenticated) {
            this.xboxAuthenticated = xboxAuthenticated;
            return this;
        }

        public FakeClientBuilder name(String name) {
            this.name = name;
            return this;
        }

        public FakeClientBuilder displayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public FakeClientBuilder skin(Skin skin) {
            this.skin = skin;
            return this;
        }

        public FakeClientBuilder uuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public FakeClientBuilder xuid(String xuid) {
            this.xuid = xuid;
            return this;
        }

        public FakeClientBuilder deviceInfo(DeviceInfo deviceInfo) {
            this.deviceInfo = deviceInfo;
            return this;
        }

        public FakeClientBuilder languageCode(String languageCode) {
            this.languageCode = languageCode;
            return this;
        }

        public FakeClientBuilder gameVersion(String gameVersion) {
            this.gameVersion = gameVersion;
            return this;
        }

        public FakeClientBuilder identityPublicKey(String identityPublicKey) {
            this.identityPublicKey = identityPublicKey;
            return this;
        }

        public FakeClientBuilder op(boolean op) {
            this.op = op;
            return this;
        }

        public FakeClientBuilder gameType(GameType gameType) {
            this.gameType = gameType;
            return this;
        }

        public FakeClientBuilder playerEntity(EntityPlayer playerEntity) {
            this.playerEntity = playerEntity;
            return this;
        }

        public FakeClientBuilder computeMovement(boolean computeMovement) {
            this.computeMovement = computeMovement;
            return this;
        }

        public FakeClient build() {
            return new FakeClient(
                    buildLoginData(),
                    loaderActive, skin,
                    displayName, op,
                    gameType, playerEntity,
                    computeMovement
            );
        }

        private LoginData buildLoginData() {
            return new LoginData(
                    this.xboxAuthenticated,
                    this.name,
                    this.xuid,
                    this.uuid,
                    this.deviceInfo,
                    this.languageCode,
                    this.gameVersion,
                    this.skin,
                    this.identityPublicKey
            );
        }
    }
}
