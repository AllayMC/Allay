package org.allaymc.server;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.CustomKey;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.allaymc.api.message.LangCode;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.world.data.Difficulty;

import java.util.UUID;

/**
 * ServerSettings store the settings of the server.
 *
 * @author daoge_cmd
 */
@SuppressWarnings("ALL")
@Getter
@Accessors(fluent = true)
public class ServerSettings extends OkaeriConfig {

    @CustomKey("generic-settings")
    private GenericSettings genericSettings = new GenericSettings();
    @CustomKey("network-settings")
    private NetworkSettings networkSettings = new NetworkSettings();
    @CustomKey("world-settings")
    private WorldSettings worldSettings = new WorldSettings();
    @CustomKey("entity-settings")
    private EntitySettings entitySettings = new EntitySettings();
    @CustomKey("storage-settings")
    private StorageSettings storageSettings = new StorageSettings();
    @CustomKey("resource-pack-settings")
    private ResourcePackSettings resourcePackSettings = new ResourcePackSettings();
    @CustomKey("bstats-settings")
    private BStatsSettings bStatsSettings = new BStatsSettings();

    @Getter
    @Setter
    @Accessors(fluent = true)
    public static class GenericSettings extends OkaeriConfig {

        private String motd = "An allay-powered server";

        @CustomKey("sub-motd")
        @Comment("Usually only visible on the LAN interface")
        private String subMotd = "https://github.com/AllayMC/Allay";

        @CustomKey("max-player-count")
        private int maxPlayerCount = 20;

        @CustomKey("default-game-mode")
        @Comment("Determines the default game mode of a world when it is created")
        @Comment("Possible values: SURVIVAL, CREATIVE, ADVENTURE, SPECTATOR")
        private GameMode defaultGameMode = GameMode.CREATIVE;

        @CustomKey("default-difficulty")
        @Comment("Determines the default difficulty of a world when it is created")
        @Comment("Possible values: PEACEFUL, EASY, NORMAL, HARD")
        private Difficulty defaultDifficulty = Difficulty.NORMAL;

        @CustomKey("default-permission")
        @Comment("Possible values: VISITOR, MEMBER, OPERATOR")
        private String defaultPermission = "MEMBER";

        @Comment("The language used by console")
        private LangCode language = LangCode.en_US;

        @Comment("If debug mode is enabled, the console will output more detailed information")
        private boolean debug = false;

        @Comment("If whitelist is enabled, only players in the whitelist can join the server")
        @CustomKey("enable-whitelist")
        private boolean enableWhitelist = false;

        @CustomKey("enable-gui")
        private boolean enableGui = true;

        @CustomKey("max-compute-thread-count")
        @Comment("Decide the maximum count of threads in compute thread pool")
        @Comment("If the value <= 0, the count will be same to the count of available processors")
        private int maxComputeThreadCount = 0;

        @CustomKey("force-enable-sentry")
        @Comment("Sentry is an error tracking and performance monitoring platform, which is disabled by")
        @Comment("default in development build, however you can force turn it on by setting this to true")
        private boolean forceEnableSentry = false;
    }

    @Getter
    @Accessors(fluent = true)
    public static class NetworkSettings extends OkaeriConfig {
        @Comment("The IPv4 address of this server")
        private String ip = "0.0.0.0";

        @Comment("The IPv4 port of this server")
        private int port = 19132;

        @Comment("Whether IPv6 is enabled")
        private boolean enablev6 = true;

        @Comment("The IPv6 address of this server. Works only when enablev6 is true")
        private String ipv6 = "::";

        @Comment("The IPv6 port of this server. Works only when enablev6 is true")
        private int portv6 = 19133;

        @CustomKey("xbox-auth")
        private boolean xboxAuth = true;

        @Comment("Turning this on is highly recommended for security reasons")
        @CustomKey("enable-network-encryption")
        private boolean enableNetworkEncryption = true;

        @Comment("The compression algorithm used for network data streams. Possible values: ZLIB, SNAPPY")
        @Comment("ZLIB has better compression ratios and SNAPPY has better compression performance")
        @CustomKey("compression-algorithm")
        private CompressionAlgorithm compressionAlgorithm = CompressionAlgorithm.ZLIB;

        @CustomKey("network-thread-number")
        @Comment("The number of network threads. If put zero here, the server will automatically determine the number of network threads")
        private int networkThreadNumber = 0;

        @CustomKey("max-synced-packets-handle-count-once")
        @Comment("The maximum number of packets that can be processed at once")
        private int maxSyncedPacketsHandleCountAtOnce = 128;

        @Comment("The maximum number of datagram packets each address can send within one RakNet tick (10ms)")
        @Comment("Default value is 120 packets, and will be Integer.MAX_VALUE if the server is running in dev mode")
        @CustomKey("raknet-packet-limit")
        private int raknetPacketLimit = 120;

        @Comment("A number of all datagrams that will be handled within one RakNet tick before server starts dropping any incoming data")
        @Comment("Default value is 100000 (raknetPacketLimit * 0.56 * 1500 different connections), and will be Integer.MAX_VALUE if the server is running in dev mode")
        @CustomKey("raknet-global-packet-limit")
        private int raknetGlobalPacketLimit = 100000;

        @Comment("Whether to send a cookie to the client during the connection process for additional security")
        @Comment("Default value is true")
        @CustomKey("raknet-send-cookie")
        private boolean raknetSendCookie = true;

        @Comment("Maximum allowed MTU that the RakNet server connection can use")
        @Comment("The internet supports a maximum MTU of 1492 but could cause issues with packet fragmentation.")
        @Comment("Default value is 1400")
        @CustomKey("raknet-max-mtu")
        private int raknetMaxMtu = 1400;

        @Comment("The max time (unit: gt) that a client can have in login stage")
        @Comment("This would prevent the server from being stuck by a lot")
        @Comment("of fake clients that are keep in login stage maliciously")
        @Comment("To disable it, just make the value <= 0")
        @CustomKey("max-login-time")
        private int maxLoginTime = 90 * 20;

        @Comment("Encoding protection will prevent client from sending large garbage data")
        @Comment("It is recommended to enable this feature, however if clients are kicked due")
        @Comment("to misjudgment (usually when changing skin), disable this feature will help you")
        @CustomKey("enable-encoding-protection")
        private boolean enableEncodingProtection = true;

        @Comment("Enable support for NetEase (China) Minecraft clients")
        @CustomKey("netease-client-support")
        private boolean neteaseClientSupport = false;

        @Comment("If set to true, only NetEase clients can join the server")
        @Comment("This option only takes effect when netease-client-support is enabled")
        @CustomKey("only-allow-netease-client")
        private boolean onlyAllowNeteaseClient = false;

        public enum CompressionAlgorithm {
            ZLIB,
            SNAPPY
        }
    }

    @Getter
    @Accessors(fluent = true)
    public static class WorldSettings extends OkaeriConfig {

        @Comment("Determines how far away from the chunk loader chunks will be ticked")
        @CustomKey("tick-radius")
        private int tickRadius = 4;

        @Comment("Determines how far away from the chunk loader chunks will be loaded and sent")
        @CustomKey("view-distance")
        private int viewDistance = 8;

        @Comment("Determines the maximum number of chunks that can be sent during a tick (per chunk loader)")
        @CustomKey("chunk-max-send-count-per-tick")
        private int chunkMaxSendCountPerTick = 16;

        @CustomKey("use-sub-chunk-sending-system")
        private boolean useSubChunkSendingSystem = false;

        @Comment("Possible values: ASYNC, SYNC")
        @CustomKey("chunk-sending-strategy")
        private ChunkSendingStrategy chunkSendingStrategy = ChunkSendingStrategy.ASYNC;

        @Comment("Determines the minimum number of chunks that must be sent to the client which is joining the server")
        @Comment("Decrease this value may reduce the time on joining server. However, client may see a lot of unloaded chunks if the value is too low")
        @CustomKey("fully-join-chunk-threshold")
        private int fullyJoinChunkThreshold = 30;

        @Comment("Determines how long a chunk without chunk loaders will remain loaded (gt)")
        @CustomKey("remove-unused-full-chunk-cycle")
        private int removeUnusedFullChunkCycle = 60 * 20;

        @Comment("Determines how long a proto chunk will remain in memory (gt)")
        @CustomKey("remove-unused-proto-chunk-cycle")
        private int removeUnusedProtoChunkCycle = 30 * 20;

        @Comment("If set to true, the server will load chunks around the spawn point")
        @Comment("Which will reduce the time on joining server")
        @Comment("However, this will increase the server's memory usage")
        @CustomKey("load-spawn-point-chunks")
        private boolean loadSpawnPointChunks = true;

        @Comment("Determines how many chunks around the spawn point will be loaded")
        @CustomKey("spawn-point-chunk-radius")
        private int spawnPointChunkRadius = 3;

        @Comment("If set to true, dimensions in the same world will be ticked in parallel during world tick")
        @CustomKey("tick-dimension-in-parallel")
        private boolean tickDimensionInParallel = true;

        @Comment("Indicate the max light update count per dimension, if the count is exceeded, light in")
        @Comment("newly loaded chunks won't be calculated immediately to avoid taking too much memory")
        @CustomKey("max-light-update-count")
        private int maxLightUpdateCountPerDimension = 1280000;

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

            @Comment("This usually determines how quickly an entity item is moved when getting stuck in a block")
            @CustomKey("block-collision-motion")
            private float blockCollisionMotion = 0.2f;

            @Comment("Delta move packet will reduce the network pressure if there are a lot of entities")
            @CustomKey("use-delta-move-packet")
            private boolean useDeltaMovePacket = false;
        }
    }

    @Getter
    @Accessors(fluent = true)
    public static class StorageSettings extends OkaeriConfig {
        @CustomKey("save-player-data")
        @Comment("If set to false, the player's data will not be saved")
        private boolean savePlayerData = true;

        @CustomKey("player-data-auto-save-cycle")
        @Comment("Determines the cycle of player data auto saving")
        private int playerDataAutoSaveCycle = 20 * 60 * 5;

        @CustomKey("chunk-auto-save-cycle")
        @Comment("Determines the cycle of chunk auto saving")
        private int chunkAutoSaveCycle = 20 * 60 * 5;

        @CustomKey("entity-auto-save-cycle")
        @Comment("Determines the cycle of entity auto saving. When entity auto saving is triggered, the")
        @Comment("entity manager will find all savable entities in unloaded chunks and save them")
        private int entityAutoSaveCycle = 20 * 60;
    }

    @Getter
    @Accessors(fluent = true)
    public static class ResourcePackSettings extends OkaeriConfig {

        @CustomKey("auto-encrypt-packs")
        @Comment("If set to true, packs will be automatically encrypted")
        @Comment("Notes that Vibrant Visuals will be disabled if this is set to true")
        private boolean autoEncryptPacks = true;

        @CustomKey("max-chunk-size")
        @Comment("The maximum size of a resource pack chunk (unit: KB)")
        @Comment("Decrease this value may reduce the pressure on the network when sending packs to multiple clients")
        @Comment("However, it may also increase the time it takes to send the packs")
        private int maxChunkSize = 100; // 100KB, from BDS

        @Comment("true - the player must accept resource packs, otherwise he will not log in to the server")
        @Comment("false - the player can log in to the server without accepting resource packs")
        @CustomKey("force-resource-packs")
        private boolean forceResourcePacks = false;

        @Comment("If set to true, the client resource packs will be allowed")
        @CustomKey("allow-client-resource-packs")
        private boolean allowClientResourcePacks = false;

        @Comment("If set to true, all skins will be marked as trusted")
        @CustomKey("trust-all-skins")
        private boolean trustAllSkins = true;

        @Comment("If set to true, Vibrant Visuals will be disabled")
        @CustomKey("disable-vibrant-visuals")
        private boolean disableVibrantVisuals = false;

        // TODO: URL packs configuration
    }

    @Getter
    @Accessors(fluent = true)
    public static class BStatsSettings extends OkaeriConfig {
        @Comment("bStats (https://bStats.org) collects some basic information for plugin authors, like how")
        @Comment("many people use their plugin and their total player count. It's recommended to keep bStats")
        @Comment("enabled, but if you're not comfortable with this, you can turn this setting off. There is no")
        @Comment("performance penalty associated with having metrics enabled, and data sent to bStats is fully anonymous.")
        private boolean enable = true;

        @CustomKey("server-uuid")
        private String serverUUID = UUID.randomUUID().toString();

        @CustomKey("log-failed-requests")
        private boolean logFailedRequests = false;

        @CustomKey("log-sent-data")
        private boolean logSentData = false;

        @CustomKey("log-response-status-text")
        private boolean logResponseStatusText = false;
    }
}
