package org.allaymc.api.server;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.CustomKey;
import lombok.Getter;
import lombok.experimental.Accessors;
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
    private GenericSettings genericSettings = new GenericSettings();

    @Getter
    @Accessors(fluent = true)
    public static class GenericSettings extends OkaeriConfig {

        private String motd = "An allay-powered server";

        @Comment("Usually only visible on the LAN interface")
        @CustomKey("sub-motd")
        private String subMotd = "https://github.com/AllayMC/Allay";

        @CustomKey("max-client-count")
        private int maxClientCount = 20;

        @CustomKey("game-type")
        @Comment("Possible values: SURVIVAL, CREATIVE, SPECTATOR")
        private GameType defaultGameType = GameType.CREATIVE;

        @CustomKey("default-permission")
        @Comment("Possible values: VISITOR, MEMBER, OPERATOR")
        private PlayerPermission defaultPermission = PlayerPermission.OPERATOR;
    }

    @CustomKey("network-settings")
    private NetworkSettings networkSettings = new NetworkSettings();

    @Getter
    @Accessors(fluent = true)
    public static class NetworkSettings extends OkaeriConfig {

        private String ip = "127.0.0.1";

        private int port = 19132;

        @CustomKey("xbox-auth")
        private boolean xboxAuth = true;

        @Comment("Turning this on is highly recommended for security reasons")
        @CustomKey("enable-network-encryption")
        private boolean enableNetworkEncryption = true;

        @Comment("Possible values: ZLIB, SNAPPY")
        @CustomKey("compression-algorithm")
        private PacketCompressionAlgorithm compressionAlgorithm = PacketCompressionAlgorithm.ZLIB;

        @CustomKey("compression-threshold")
        private int compressionThreshold = 0;

        @CustomKey("network-thread-number")
        private int networkThreadNumber = 1;
    }

    @CustomKey("world-settings")
    private WorldSettings worldSettings = new WorldSettings();

    @Getter
    @Accessors(fluent = true)
    public static class WorldSettings extends OkaeriConfig {

        @CustomKey("ticking-radius")
        private int tickingRadius = 8;

        @CustomKey("view-distance")
        private int viewDistance = 16;

        @CustomKey("chunk-try-send-count-per-tick")
        private int chunkTrySendCountPerTick = 4;

        @CustomKey("use-sub-chunk-sending-system")
        private boolean useSubChunkSendingSystem = false;

        @Comment("Possible values: ASYNC, SYNC, PARALLEL")
        @Comment("This only works if sub-chunk sending system is not enabled")
        @Comment("And will be forced to SYNC if sub-chunk sending system is enabled")
        @CustomKey("chunk-sending-strategy")
        private ChunkSendingStrategy chunkSendingStrategy = ChunkSendingStrategy.ASYNC;

        @Comment("If the number of chunks to be sent in one batch is more than this value, the chunks will be sent in parallel")
        @CustomKey("chunk-min-parallel-sending-threshold")
        private int chunkMinParallelSendingThreshold = 4;

        @CustomKey("do-first-spawn-chunk-threshold")
        private int doFirstSpawnChunkThreshold = 36;

        @Comment("Determines how long a chunk without chunk loaders will remain loaded (gt)")
        @CustomKey("remove-unneeded-chunk-cycle")
        private int removeUnneededChunkCycle = 600;

        public enum ChunkSendingStrategy {
            ASYNC,
            SYNC,
            PARALLEL
        }
    }

    @CustomKey("entity-settings")
    private EntitySettings entitySettings = new EntitySettings();

    @Getter
    @Accessors(fluent = true)
    public static class EntitySettings extends OkaeriConfig {

        @Comment("Entity physics engine settings")
        @Comment("Do not change them if you don't know what you are doing!")
        @CustomKey("physics-engine-settings")
        private PhysicsEngineSettings physicsEngineSettings = new PhysicsEngineSettings();

        @Getter
        @Accessors(fluent = true)
        public static class PhysicsEngineSettings extends OkaeriConfig {
            @Comment("Send packets to the client when the amount of position change accumulates")
            @Comment("This threshold acts on each axis individually")
            @Comment("Increasing this threshold will reduce bandwidth pressure, but may result in untimely motion updates")
            @CustomKey("diff-position-threshold")
            private float diffPositionThreshold = 0.0001f;

            @Comment("Similar to \"diffPositionThreshold\"")
            @CustomKey("diff-rotation-threshold")
            private float diffRotationThreshold = 0.1f;

            @Comment("When the motion falls below this value, its motion is zeroed")
            @CustomKey("motion-threshold")
            private float motionThreshold = 0.003f;

            @Comment("Walk assist offset")
            @CustomKey("stepping-offset")
            private float steppingOffset = 0.05f;

            @Comment("This usually determines how quickly an entity item is moved when getting stuck in a block")
            @CustomKey("block-collision-motion")
            private float blockCollisionMotion = 0.2f;

            @CustomKey("fat-aabb-margin")
            private float fatAABBMargin = 0.0005f;
        }
    }
}
