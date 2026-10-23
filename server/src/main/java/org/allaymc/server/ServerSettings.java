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
 * {@code ServerSettings} defines the mutable configuration sections loaded from
 * {@code server-settings.yml}.
 * Field initializers provide defaults and Okaeri annotations control serialized
 * keys and generated configuration guidance. The generated getters expose
 * {@code genericSettings}, {@code networkSettings}, {@code worldSettings},
 * {@code entitySettings}, {@code storageSettings}, {@code resourcePackSettings},
 * and {@code bStatsSettings} as shared objects. Consumers may capture values
 * during startup, so later edits do not automatically reconfigure initialized
 * services.
 *
 * @author daoge_cmd
 */
@SuppressWarnings("ALL")
@Getter
@Accessors(fluent = true)
public class ServerSettings extends OkaeriConfig {

    /** General startup and gameplay defaults. */
    @CustomKey("generic-settings")
    private GenericSettings genericSettings = new GenericSettings();
    /** Network binding and protocol options. */
    @CustomKey("network-settings")
    private NetworkSettings networkSettings = new NetworkSettings();
    /** World ticking and chunk options. */
    @CustomKey("world-settings")
    private WorldSettings worldSettings = new WorldSettings();
    /** Entity simulation configuration section. */
    @CustomKey("entity-settings")
    private EntitySettings entitySettings = new EntitySettings();
    /** Persistence and autosave configuration section. */
    @CustomKey("storage-settings")
    private StorageSettings storageSettings = new StorageSettings();
    /** Resource-pack transfer and client options. */
    @CustomKey("resource-pack-settings")
    private ResourcePackSettings resourcePackSettings = new ResourcePackSettings();
    /** Metrics reporting configuration section. */
    @CustomKey("bstats-settings")
    private BStatsSettings bStatsSettings = new BStatsSettings();

    /**
     * {@code GenericSettings} supplies server identity, startup controls, and
     * defaults for new gameplay state.
     */
    @Getter
    @Setter
    @Accessors(fluent = true)
    public static class GenericSettings extends OkaeriConfig {

        /** Advertised primary server description. */
        private String motd = "An allay-powered server";

        /** Advertised secondary server description. */
        @CustomKey("sub-motd")
        @Comment("Usually only visible on the LAN interface")
        private String subMotd = "https://github.com/AllayMC/Allay";

        /** Initial online player limit. */
        @CustomKey("max-player-count")
        private int maxPlayerCount = 20;

        /** Game mode for newly created worlds. */
        @CustomKey("default-game-mode")
        @Comment("Determines the default game mode of a world when it is created")
        @Comment("Possible values: SURVIVAL, CREATIVE, ADVENTURE, SPECTATOR")
        private GameMode defaultGameMode = GameMode.CREATIVE;

        /** Difficulty for newly created worlds. */
        @CustomKey("default-difficulty")
        @Comment("Determines the default difficulty of a world when it is created")
        @Comment("Possible values: PEACEFUL, EASY, NORMAL, HARD")
        private Difficulty defaultDifficulty = Difficulty.NORMAL;

        /**
         * {@code defaultPermission} names the initial player permission. Packet
         * encoding expects an exact {@code PlayerPermission} enum name, although
         * player initialization uppercases its input.
         */
        @CustomKey("default-permission")
        @Comment("Possible values: VISITOR, MEMBER, OPERATOR")
        private String defaultPermission = "MEMBER";

        /** Default console translation language. */
        @Comment("The language used by console")
        private LangCode language = LangCode.en_US;

        /** Debug-level startup logging switch. */
        @Comment("If debug mode is enabled, the console will output more detailed information")
        private boolean debug = false;

        /** Current whitelist enforcement setting. */
        @Comment("If whitelist is enabled, only players in the whitelist can join the server")
        @CustomKey("enable-whitelist")
        private boolean enableWhitelist = false;

        /**
         * {@code enableGui} requests the optional dashboard. Bootstrap changes
         * this value to {@code false} when graphics are unavailable or dashboard
         * construction fails.
         */
        @CustomKey("enable-gui")
        private boolean enableGui = true;

        /**
         * {@code maxComputeThreadCount} sets compute-pool parallelism. Nonpositive
         * values select the available processor count when the server constructs
         * its shared fork-join pool.
         */
        @CustomKey("max-compute-thread-count")
        @Comment("Decide the maximum count of threads in compute thread pool")
        @Comment("If the value <= 0, the count will be same to the count of available processors")
        private int maxComputeThreadCount = 0;

        /**
         * {@code forceEnableSentry} keeps Sentry enabled for development builds.
         * Otherwise, bootstrap closes the reporting client for those builds before
         * normal initialization.
         */
        @CustomKey("force-enable-sentry")
        @Comment("Sentry is an error tracking and performance monitoring platform, which is disabled by")
        @Comment("default in development build, however you can force turn it on by setting this to true")
        private boolean forceEnableSentry = false;
    }

    /**
     * {@code NetworkSettings} supplies transport bindings, connection admission,
     * and packet-processing limits.
     */
    @Getter
    @Accessors(fluent = true)
    public static class NetworkSettings extends OkaeriConfig {
        /** IPv4 transport bind address. */
        @Comment("The IPv4 address of this server")
        private String ip = "0.0.0.0";

        /**
         * {@code port} specifies the IPv4 UDP bind port. RakNet startup requires
         * a value from one through 65535 before creating the listening address.
         */
        @Comment("The IPv4 port of this server")
        private int port = 19132;

        /** IPv6 listener enablement switch. */
        @Comment("Whether IPv6 is enabled")
        private boolean enablev6 = true;

        /**
         * {@code ipv6} supplies the IPv6 bind address when {@code enablev6} is
         * {@code true}. Otherwise, startup omits the additional listening address.
         */
        @Comment("The IPv6 address of this server. Works only when enablev6 is true")
        private String ipv6 = "::";

        /**
         * {@code portv6} specifies the UDP port when IPv6 is enabled. RakNet
         * startup requires one through 65535 and rejects equality with {@code port}.
         */
        @Comment("The IPv6 port of this server. Works only when enablev6 is true")
        private int portv6 = 19133;

        /** Authenticated login requirement switch. */
        @CustomKey("xbox-auth")
        private boolean xboxAuth = true;

        /** Negotiated session encryption switch. */
        @Comment("Turning this on is highly recommended for security reasons")
        @CustomKey("enable-network-encryption")
        private boolean enableNetworkEncryption = true;

        /** Per-session packet logging switch. */
        @Comment("Log detailed packet information for each player session")
        @CustomKey("debug-packets")
        private boolean debugPackets = false;

        /**
         * {@code compressionAlgorithm} requests stream compression for
         * international clients. NetEase negotiation selects {@code ZLIB} regardless
         * of this configured algorithm.
         */
        @Comment("The compression algorithm used for network data streams. Possible values: ZLIB, SNAPPY")
        @Comment("ZLIB has better compression ratios and SNAPPY has better compression performance")
        @CustomKey("compression-algorithm")
        private CompressionAlgorithm compressionAlgorithm = CompressionAlgorithm.ZLIB;

        /**
         * {@code networkThreadNumber} specifies the Netty event-loop thread
         * count. Zero delegates sizing to Netty, while RakNet startup rejects
         * negative values before constructing the group.
         */
        @CustomKey("network-thread-number")
        @Comment("The number of network threads. If put zero here, the server will automatically determine the number of network threads")
        private int networkThreadNumber = 0;

        /**
         * {@code maxSyncedPacketsHandleCountAtOnce} limits successful packet
         * dispatches per world queue-drain invocation. Skipped stale entries do
         * not consume this count, and nonpositive values prevent queued-packet handling.
         */
        @CustomKey("max-synced-packets-handle-count-once")
        @Comment("The maximum number of packets that can be processed at once")
        private int maxSyncedPacketsHandleCountAtOnce = 128;

        /**
         * {@code raknetPacketLimit} limits RakNet datagrams per address.
         * Development builds pass {@code Integer.MAX_VALUE} to the transport
         * instead of this configured limit.
         */
        @Comment("The maximum number of datagram packets each address can send within one RakNet tick (10ms)")
        @Comment("Default value is 120 packets, and will be Integer.MAX_VALUE if the server is running in dev mode")
        @CustomKey("raknet-packet-limit")
        private int raknetPacketLimit = 120;

        /**
         * {@code raknetGlobalPacketLimit} limits RakNet datagrams globally.
         * Development builds pass {@code Integer.MAX_VALUE} to the transport
         * instead of this configured limit during startup.
         */
        @Comment("A number of all datagrams that will be handled within one RakNet tick before server starts dropping any incoming data")
        @Comment("Default value is 100000 (raknetPacketLimit * 0.56 * 1500 different connections), and will be Integer.MAX_VALUE if the server is running in dev mode")
        @CustomKey("raknet-global-packet-limit")
        private int raknetGlobalPacketLimit = 100000;

        /** Maximum RakNet MTU in bytes. */
        @Comment("Maximum allowed MTU that the RakNet server connection can use")
        @Comment("The internet supports a maximum MTU of 1492 but could cause issues with packet fragmentation.")
        @Comment("Default value is 1400")
        @CustomKey("raknet-max-mtu")
        private int raknetMaxMtu = 1400;

        /**
         * {@code maxLoginTime} sets the login timeout in server ticks. Positive
         * values schedule a disconnect check for connections not yet in-game,
         * while nonpositive values disable it.
         */
        @Comment("The max time (unit: gt) that a client can have in login stage")
        @Comment("This would prevent the server from being stuck by a lot")
        @Comment("of fake clients that are keep in login stage maliciously")
        @Comment("To disable it, just make the value <= 0")
        @CustomKey("max-login-time")
        private int maxLoginTime = 90 * 20;

        /**
         * {@code enableEncodingProtection} retains protocol encoding limits
         * when enabled. Disabling it installs {@code EncodingSettings.UNLIMITED}
         * on the session helper during protocol negotiation.
         */
        @Comment("Encoding protection will prevent client from sending large garbage data")
        @Comment("It is recommended to enable this feature, however if clients are kicked due")
        @Comment("to misjudgment (usually when changing skin), disable this feature will help you")
        @CustomKey("enable-encoding-protection")
        private boolean enableEncodingProtection = true;

        /** Recognized NetEase client support. */
        @Comment("Enable support for NetEase (China) Minecraft clients")
        @CustomKey("netease-client-support")
        private boolean neteaseClientSupport = false;

        /**
         * {@code onlyAllowNeteaseClient} rejects international clients when
         * {@code neteaseClientSupport} is also enabled. It has no additional
         * admission effect when that support is disabled.
         */
        @Comment("If set to true, only NetEase clients can join the server")
        @Comment("This option only takes effect when netease-client-support is enabled")
        @CustomKey("only-allow-netease-client")
        private boolean onlyAllowNeteaseClient = false;

        /** Decompressed packet limit in bytes. */
        @Comment("Maximum size in bytes for decompressed packet data. Default is 50MB (52428800)")
        @Comment("Increase this if you encounter decompression errors with large packets")
        @CustomKey("max-decompressed-bytes")
        private int maxDecompressedBytes = 1024 * 1024 * 50;

        /**
         * {@code enableClientChunkCache} allows chunk blob caching when the
         * client has also enabled its cache. The server checks both settings
         * before using cached chunk encoding.
         */
        @Comment("Whether to enable client-side chunk caching using blob hashes, which can significantly reduce bandwidth when players revisit areas")
        @Comment("Please note that you should set network-settings.enable-encoding-protection to false to use this feature")
        @CustomKey("enable-client-chunk-cache")
        private boolean enableClientChunkCache = false;

        /** Global cached chunk-blob count limit. */
        @Comment("Maximum number of chunk blobs to cache globally across all players")
        @Comment("Each blob is typically 1-4KB. Higher values use more memory but improve cache hit rate")
        @Comment("Recommended: 4096 for small servers, 8192-16384 for larger servers")
        @CustomKey("max-chunk-cache-blobs")
        private int maxChunkCacheBlobs = 4096;

        /**
         * {@code CompressionAlgorithm} identifies the packet compression requested
         * during international-client negotiation from the stored server configuration.
         */
        public enum CompressionAlgorithm {
            /** {@code ZLIB} stream compression. */
            ZLIB,
            /** Snappy stream compression. */
            SNAPPY
        }
    }

    /**
     * {@code WorldSettings} supplies chunk admission, retention, sending,
     * lighting, and dimension tick options.
     */
    @Getter
    @Accessors(fluent = true)
    public static class WorldSettings extends OkaeriConfig {

        /** Chunk ticking radius in chunks. */
        @Comment("Determines how far away from the chunk loader chunks will be ticked")
        @CustomKey("tick-radius")
        private int tickRadius = 4;

        /** Player loading radius limit in chunks. */
        @Comment("Determines how far away from the chunk loader chunks will be loaded and sent")
        @CustomKey("view-distance")
        private int viewDistance = 8;

        /** Initial per-player chunk-send budget per tick. */
        @Comment("Determines the maximum number of chunks that can be sent during a tick (per chunk loader)")
        @CustomKey("chunk-max-send-count-per-tick")
        private int chunkMaxSendCountPerTick = 16;

        /** Subchunk-aware level-chunk encoding switch. */
        @CustomKey("use-sub-chunk-sending-system")
        private boolean useSubChunkSendingSystem = false;

        /** Execution strategy for player chunk sending. */
        @Comment("Possible values: ASYNC, SYNC")
        @CustomKey("chunk-sending-strategy")
        private ChunkSendingStrategy chunkSendingStrategy = ChunkSendingStrategy.ASYNC;

        /**
         * {@code fullyJoinChunkThreshold} sets the initial chunk-send countdown
         * before the fully-joined callback. The player decrements only positive
         * counts, so nonpositive values never trigger that callback through this countdown.
         */
        @Comment("Determines the minimum number of chunks that must be sent to the client which is joining the server")
        @Comment("Decrease this value may reduce the time on joining server. However, client may see a lot of unloaded chunks if the value is too low")
        @CustomKey("fully-join-chunk-threshold")
        private int fullyJoinChunkThreshold = 30;

        /**
         * {@code removeUnusedFullChunkCycle} sets full-chunk idle retention in
         * world ticks. Holders unload when their countdown reaches exactly zero
         * and do not clamp negative configured values.
         */
        @Comment("Determines how long a chunk without chunk loaders will remain loaded (gt)")
        @CustomKey("remove-unused-full-chunk-cycle")
        private int removeUnusedFullChunkCycle = 60 * 20;

        /**
         * {@code removeUnusedProtoChunkCycle} sets proto-chunk idle retention
         * in world ticks. It also initializes new holder countdowns, which rely
         * on reaching exactly zero for expiry.
         */
        @Comment("Determines how long a proto chunk will remain in memory (gt)")
        @CustomKey("remove-unused-proto-chunk-cycle")
        private int removeUnusedProtoChunkCycle = 30 * 20;

        /** Overworld spawn chunk loading switch. */
        @Comment("If set to true, the server will load chunks around the spawn point")
        @Comment("Which will reduce the time on joining server")
        @Comment("However, this will increase the server's memory usage")
        @CustomKey("load-spawn-point-chunks")
        private boolean loadSpawnPointChunks = true;

        /** Spawn loader radius in chunks. */
        @Comment("Determines how many chunks around the spawn point will be loaded")
        @CustomKey("spawn-point-chunk-radius")
        private int spawnPointChunkRadius = 3;

        /**
         * {@code tickDimensionInParallel} enables parallel ticking when a
         * world has multiple dimensions. World ticking joins all dimension work
         * before advancing its storage tick.
         */
        @Comment("If set to true, dimensions in the same world will be ticked in parallel during world tick")
        @CustomKey("tick-dimension-in-parallel")
        private boolean tickDimensionInParallel = true;

        /** Per-dimension light-update admission limit. */
        @Comment("Indicate the max light update count per dimension, if the count is exceeded, light in")
        @Comment("newly loaded chunks won't be calculated immediately to avoid taking too much memory")
        @CustomKey("max-light-update-count")
        private int maxLightUpdateCountPerDimension = 1280000;

        /**
         * {@code ChunkSendingStrategy} identifies whether player chunk sending
         * uses background work or the current chunk-loader update thread.
         */
        public enum ChunkSendingStrategy {
            /** Background player chunk sending. */
            ASYNC,
            /** Current-thread player chunk sending. */
            SYNC
        }
    }

    /**
     * {@code EntitySettings} groups entity simulation settings consumed by the
     * dimension physics engine.
     */
    @Getter
    @Accessors(fluent = true)
    public static class EntitySettings extends OkaeriConfig {

        /** Shared entity physics tuning section. */
        @Comment("Entity physics engine settings")
        @Comment("Do not change them if you don't know what you are doing!")
        @CustomKey("physics-engine-settings")
        private PhysicsEngineSettings physicsEngineSettings = new PhysicsEngineSettings();

        /**
         * {@code PhysicsEngineSettings} supplies motion thresholds captured when
         * the entity physics engine initializes.
         */
        @Getter
        @Accessors(fluent = true)
        public static class PhysicsEngineSettings extends OkaeriConfig {
            /**
             * {@code motionThreshold} sets the minimum absolute axis motion for
             * applying movement. Smaller motion remains stored so forces can
             * accumulate across later ticks.
             */
            @Comment("When the motion falls below this value, its motion is zeroed")
            @CustomKey("motion-threshold")
            private float motionThreshold = 0.003f;

            /** Block escape motion magnitude. */
            @Comment("This usually determines how quickly an entity item is moved when getting stuck in a block")
            @CustomKey("block-collision-motion")
            private float blockCollisionMotion = 0.2f;
        }
    }

    /**
     * {@code StorageSettings} supplies persistence selection and tick intervals
     * for player, chunk, and entity saves.
     */
    @Getter
    @Accessors(fluent = true)
    public static class StorageSettings extends OkaeriConfig {
        /**
         * {@code savePlayerData} selects file-backed player storage during
         * server construction. Disabling it selects empty storage rather than
         * changing each later save call.
         */
        @CustomKey("save-player-data")
        @Comment("If set to false, the player's data will not be saved")
        private boolean savePlayerData = true;

        /** Player autosave interval in server ticks. */
        @CustomKey("player-data-auto-save-cycle")
        @Comment("Determines the cycle of player data auto saving")
        private int playerDataAutoSaveCycle = 20 * 60 * 5;

        /** Chunk autosave interval in world ticks. */
        @CustomKey("chunk-auto-save-cycle")
        @Comment("Determines the cycle of chunk auto saving")
        private int chunkAutoSaveCycle = 20 * 60 * 5;

        /**
         * {@code entityAutoSaveCycle} sets the entity autosave interval in
         * world ticks. The manager saves and removes unloaded-chunk entities,
         * then separately saves remaining loaded-chunk entities.
         */
        @CustomKey("entity-auto-save-cycle")
        @Comment("Determines the cycle of entity auto saving. When entity auto saving is triggered, the")
        @Comment("entity manager will find all savable entities in unloaded chunks and save them")
        private int entityAutoSaveCycle = 20 * 60;
    }

    /**
     * {@code ResourcePackSettings} supplies resource-pack transfer options and
     * client-facing content preference flags.
     */
    @Getter
    @Accessors(fluent = true)
    public static class ResourcePackSettings extends OkaeriConfig {

        /** Pack-loader automatic encryption switch. */
        @CustomKey("auto-encrypt-packs")
        @Comment("If set to true, packs will be automatically encrypted")
        @Comment("Notes that Vibrant Visuals will be disabled if this is set to true")
        private boolean autoEncryptPacks = true;

        /** Resource-pack transfer chunk size in kibibytes. */
        @CustomKey("max-chunk-size")
        @Comment("The maximum size of a resource pack chunk (unit: KB)")
        @Comment("Decrease this value may reduce the pressure on the network when sending packs to multiple clients")
        @Comment("However, it may also increase the time it takes to send the packs")
        private int maxChunkSize = 100;

        /** Mandatory resource-pack acceptance advertisement. */
        @Comment("true - the player must accept resource packs, otherwise he will not log in to the server")
        @Comment("false - the player can log in to the server without accepting resource packs")
        @CustomKey("force-resource-packs")
        private boolean forceResourcePacks = false;

        /**
         * {@code allowClientResourcePacks} permits client packs by clearing
         * the resource-pack stack's forced acceptance flag, even when
         * {@code forceResourcePacks} is {@code true}.
         */
        @Comment("If set to true, the client resource packs will be allowed")
        @CustomKey("allow-client-resource-packs")
        private boolean allowClientResourcePacks = false;

        /** Transmitted player skin trust switch. */
        @Comment("If set to true, all skins will be marked as trusted")
        @CustomKey("trust-all-skins")
        private boolean trustAllSkins = true;

        /** Disabled Vibrant Visuals advertisement. */
        @Comment("If set to true, Vibrant Visuals will be disabled")
        @CustomKey("disable-vibrant-visuals")
        private boolean disableVibrantVisuals = false;

        // TODO: Add configuration fields and a loader path for URL-hosted packs.
    }

    /**
     * {@code BStatsSettings} supplies the reporter identity, enable switch, and
     * bStats diagnostic logging options.
     */
    @Getter
    @Accessors(fluent = true)
    public static class BStatsSettings extends OkaeriConfig {
        /** bStats reporter creation switch. */
        @Comment("bStats (https://bStats.org) collects some basic information for plugin authors, like how")
        @Comment("many people use their plugin and their total player count. It's recommended to keep bStats")
        @Comment("enabled, but if you're not comfortable with this, you can turn this setting off. There is no")
        @Comment("performance penalty associated with having metrics enabled, and data sent to bStats is fully anonymous.")
        private boolean enable = true;

        /**
         * {@code serverUUID} identifies the reporter. New configuration
         * instances generate this value, and settings persistence lets subsequent
         * starts reuse it.
         */
        @CustomKey("server-uuid")
        private String serverUUID = UUID.randomUUID().toString();

        /** Failed metrics-request logging switch. */
        @CustomKey("log-failed-requests")
        private boolean logFailedRequests = false;

        /** Metrics payload logging switch. */
        @CustomKey("log-sent-data")
        private boolean logSentData = false;

        /** Metrics response-status logging switch. */
        @CustomKey("log-response-status-text")
        private boolean logResponseStatusText = false;
    }
}
