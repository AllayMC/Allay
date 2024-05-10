package org.allaymc.api.server;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.CustomKey;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.allaymc.api.i18n.LangCode;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.PacketCompressionAlgorithm;
import org.cloudburstmc.protocol.bedrock.data.PlayerPermission;

/**
 * Server settings
 */
@Getter
@Accessors(fluent = true)
public class ServerSettings extends OkaeriConfig {

    @CustomKey("generic-settings")
    private final GenericSettings genericSettings = new GenericSettings();
    @CustomKey("network-settings")
    private final NetworkSettings networkSettings = new NetworkSettings();
    @CustomKey("world-settings")
    private final WorldConfig worldSettings = new WorldConfig();
    @CustomKey("entity-settings")
    private final EntitySettings entitySettings = new EntitySettings();
    @CustomKey("storage-settings")
    private final StorageSettings storageSettings = new StorageSettings();
    @CustomKey("resource-pack-settings")
    private final ResourcePackSettings resourcePackSettings = new ResourcePackSettings();

    @Getter
    @Setter
    @Accessors(fluent = true)
    public static class GenericSettings extends OkaeriConfig {

        private String motd = "An allay-powered server";

        @CustomKey("sub-motd")
        @Comment("Usually only visible on the LAN interface")
        private String subMotd = "https://github.com/AllayMC/Allay";

        @CustomKey("max-client-count")
        private int maxClientCount = 20;

        @CustomKey("game-type")
        @Comment("Possible values: SURVIVAL, CREATIVE, SPECTATOR")
        private GameType defaultGameType = GameType.CREATIVE;

        @CustomKey("default-permission")
        @Comment("Possible values: VISITOR, MEMBER, OPERATOR")
        private PlayerPermission defaultPermission = PlayerPermission.OPERATOR;

        @Comment("The language used by console")
        private LangCode language = LangCode.en_US;

        @Comment("Open debug mode")
        @Comment("If debug mode is enabled, the console will outputs more detailed information")
        private boolean debug = false;

        @CustomKey("is-whitelisted")
        private boolean isWhitelisted = false;
    }

    @Getter
    @Accessors(fluent = true)
    public static class NetworkSettings extends OkaeriConfig {

        private final String ip = "127.0.0.1";

        private final int port = 19132;

        @CustomKey("xbox-auth")
        private final boolean xboxAuth = true;

        @Comment("Turning this on is highly recommended for security reasons")
        @CustomKey("enable-network-encryption")
        private final boolean enableNetworkEncryption = true;

        @Comment("Possible values: ZLIB, SNAPPY")
        @CustomKey("compression-algorithm")
        private final PacketCompressionAlgorithm compressionAlgorithm = PacketCompressionAlgorithm.ZLIB;

        @CustomKey("network-thread-number")
        @Comment("0 == the server will automatically determine the number of netty threads")
        private final int networkThreadNumber = 0;

        @CustomKey("max-synced-packets-handle-count-once")
        @Comment("The maximum number of packets that can be processed at once")
        private final int maxSyncedPacketsHandleCountAtOnce = 128;
    }

    @Getter
    @Accessors(fluent = true)
    public static class WorldConfig extends OkaeriConfig {

        @CustomKey("ticking-radius")
        private final int tickingRadius = 8;

        @CustomKey("view-distance")
        private final int viewDistance = 16;

        @CustomKey("chunk-try-send-count-per-tick")
        private final int chunkTrySendCountPerTick = 16;

        @CustomKey("use-sub-chunk-sending-system")
        private final boolean useSubChunkSendingSystem = false;

        @Comment("Possible values: ASYNC, SYNC")
        @Comment("This only works if sub-chunk sending system is not enabled")
        @Comment("And will be forced to SYNC if sub-chunk sending system is enabled")
        @CustomKey("chunk-sending-strategy")
        private final ChunkSendingStrategy chunkSendingStrategy = ChunkSendingStrategy.ASYNC;

        @CustomKey("do-first-spawn-chunk-threshold")
        private final int doFirstSpawnChunkThreshold = 56;

        @Comment("Determines how long a chunk without chunk loaders will remain loaded (gt)")
        @CustomKey("remove-unneeded-chunk-cycle")
        private final int removeUnneededChunkCycle = 1200;

        public enum ChunkSendingStrategy {
            ASYNC,
            SYNC
        }
    }

    @Getter
    @Accessors(fluent = true)
    public static class EntitySettings extends OkaeriConfig {

        @Comment("Entity physics engine settings")
        @Comment("Do not change them if you don't know what you are doing!")
        @CustomKey("physics-engine-settings")
        private final PhysicsEngineSettings physicsEngineSettings = new PhysicsEngineSettings();

        @Getter
        @Accessors(fluent = true)
        public static class PhysicsEngineSettings extends OkaeriConfig {
            @Comment("Send packets to the client when the amount of position change accumulates")
            @Comment("This threshold acts on each axis individually")
            @Comment("Increasing this threshold will reduce bandwidth pressure, but may result in untimely motion updates")
            @CustomKey("diff-position-threshold")
            private final float diffPositionThreshold = 0.0001f;

            @Comment("Similar to \"diffPositionThreshold\"")
            @CustomKey("diff-rotation-threshold")
            private final float diffRotationThreshold = 0.1f;

            @Comment("When the motion falls below this value, its motion is zeroed")
            @CustomKey("motion-threshold")
            private final float motionThreshold = 0.003f;

            @Comment("Walk assist offset")
            @CustomKey("stepping-offset")
            private final float steppingOffset = 0.05f;

            @Comment("This usually determines how quickly an entity item is moved when getting stuck in a block")
            @CustomKey("block-collision-motion")
            private final float blockCollisionMotion = 0.2f;

            @CustomKey("fat-aabb-margin")
            private final float fatAABBMargin = 0.0005f;

            @Comment("Delta move packet will reduce the network pressure if there are a lot of entities")
            @CustomKey("use-delta-move-packet")
            private final boolean useDeltaMovePacket = false;
        }
    }

    @Getter
    @Accessors(fluent = true)
    public static class StorageSettings extends OkaeriConfig {
        @CustomKey("save-player-data")
        @Comment("If set to false, the player's data will not be saved")
        private final boolean savePlayerData = true;

        @CustomKey("save-player-data-cycle")
        @Comment("Determines the cycle of player data auto saving")
        private final int playerDataAutoSaveCycle = 20 * 60;
    }

    @Getter
    @Accessors(fluent = true)
    public static class ResourcePackSettings extends OkaeriConfig {

        @CustomKey("auto-encrypt")
        @Comment("If set to true, packs will be automatically encrypted")
        private final boolean autoEncrypt = true;

        @CustomKey("max-chunk-size")
        @Comment("The maximum size of a resource pack chunk (unit: KB)")
        @Comment("Decrease this value may reduce the pressure on the network when sending packs to multiple clients")
        @Comment("However, it may also increase the time it takes to send the packs")
        private final int maxChunkSize = 100; // 100KB, from BDS

        @Comment("true - the player must accept resource packs, otherwise he will not log in to the server")
        @Comment("false - the player can log in to the server without accepting resource packs")
        @CustomKey("force-resource-packs")
        private final boolean forceResourcePacks = false;

        // TODO: URL packs configuration
    }
}
