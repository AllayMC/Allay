package org.allaymc.server.world.storage.leveldb.codec;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.data.Difficulty;
import org.allaymc.api.world.storage.WorldStorageException;
import org.allaymc.server.AllayServer;
import org.allaymc.server.network.NetworkHelper;
import org.allaymc.server.network.ProtocolInfo;
import org.allaymc.server.pdc.AllayPersistentDataContainer;
import org.allaymc.server.world.AllayWorldData;
import org.allaymc.server.world.dimension.VanillaGeneratorType;
import org.allaymc.server.world.gamerule.AllayGameRules;
import org.allaymc.server.world.storage.leveldb.data.StorageVersion;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.joml.Vector3i;

import static org.allaymc.server.network.NetworkHelper.toNetwork;

/**
 * {@code WorldDataCodec} maps
 * {@code level.dat} NBT to Allay world metadata and writes the compatibility
 * fields used alongside that metadata. Storage and network versions are checked
 * before reconstruction; missing version fields and explicit {@link Integer#MAX_VALUE}
 * sentinels select the current supported versions. Other values above those
 * versions are rejected.
 *
 * <p>
 * Runtime fields use distinct representations where necessary: game modes pass
 * through network enum conversion, spawn coordinates remain separate integer
 * tags, and persistent data and gamerules share the root compound. Writing also
 * adds fixed compatibility values such as the void generator selection and the
 * feature protocol version. Those fixed fields are not recovered as
 * configurable Allay world properties when the record is read again.
 */
@Slf4j
@UtilityClass
public final class WorldDataCodec {

    /**
     * {@code CURRENT_STORAGE_VERSION} selects
     * the storage-format ordinal written to {@code level.dat} and accepted as
     * the newest supported metadata version.
     */
    public static final int CURRENT_STORAGE_VERSION = StorageVersion.LEVEL_DATA_STRICT_SIZE.ordinal();

    // These tags name the runtime world properties reconstructed below. Spawn coordinates
    // form one position, while the two time fields retain total ticks and time of day
    // separately; persistent plugin data occupies its own compound in the same root.
    /**
     * Saved difficulty ordinal.
     */
    private static final String TAG_DIFFICULTY = "Difficulty";
    /**
     * Network game-mode ordinal.
     */
    private static final String TAG_GAME_TYPE = "GameType";
    /**
     * World display name.
     */
    private static final String TAG_DISPLAY_NAME = "LevelName";
    // TAG_SPAWN_X, TAG_SPAWN_Y, and TAG_SPAWN_Z store one block position as
    // separate integer tags. The reader supplies each missing coordinate
    // independently when reconstructing the spawn point.
    /**
     * Spawn block X.
     */
    private static final String TAG_SPAWN_X = "SpawnX";
    /**
     * Spawn block Y.
     */
    private static final String TAG_SPAWN_Y = "SpawnY";
    /**
     * Spawn block Z.
     */
    private static final String TAG_SPAWN_Z = "SpawnZ";
    // TAG_TOTAL_TIME and TAG_TIME_OF_DAY represent different clocks:
    // accumulated world ticks and the current daily time. Saving and loading
    // retain both instead of deriving either clock from the other.
    /**
     * Elapsed world ticks.
     */
    private static final String TAG_TOTAL_TIME = "Time";
    /**
     * Daily world time.
     */
    private static final String TAG_TIME_OF_DAY = "TimeOfDay";
    /**
     * World startup count.
     */
    private static final String TAG_WORLD_START_COUNT = "WorldStartCount";
    /**
     * Persistent plugin-data compound.
     */
    private static final String TAG_PDC = "PDC";

    // These tags describe the saved world to other Bedrock readers. Storage and network
    // versions also gate loading here; the remaining compatibility values are emitted
    // by this writer without becoming configurable fields of AllayWorldData.
    /**
     * External generator selection.
     */
    private static final String TAG_GENERATOR = "Generator";
    /**
     * External generator seed.
     */
    private static final String TAG_RANDOM_SEED = "RandomSeed";
    /**
     * Storage format version.
     */
    private static final String TAG_STORAGE_VERSION = "StorageVersion";
    /**
     * Saved protocol version.
     */
    private static final String TAG_NETWORK_VERSION = "NetworkVersion";
    /**
     * Unix timestamp seconds.
     */
    private static final String TAG_LAST_PLAYED = "LastPlayed";
    /**
     * Saved achievement-disabling flag.
     */
    private static final String TAG_ACHIEVEMENTS_DISABLED = "hasBeenLoadedInCreative";
    /**
     * Saved command-enabling flag.
     */
    private static final String TAG_COMMANDS_ENABLED = "commandsEnabled";
    /**
     * Feature game-version components.
     */
    private static final String TAG_LAST_OPENED_WITH_VERSION = "lastOpenedWithVersion";
    /**
     * Education world flag.
     */
    private static final String TAG_IS_EDU = "eduLevel";
    /**
     * Forced game-mode flag.
     */
    private static final String TAG_FORCE_GAME_TYPE = "ForceGameType";

    /**
     * Reconstructs Allay world metadata after checking the saved format and
     * protocol versions. Missing version tags and explicit {@link Integer#MAX_VALUE}
     * values use the supported versions; other newer values throw
     * {@link WorldStorageException} before world metadata is created.
     *
     * <p>
     * Missing gameplay values use configured difficulty and game mode, the
     * default display name, spawn position zero-sixty-four-zero, sunrise time,
     * and zero counters. Persistent data and gamerules are reconstructed from
     * the same root compound. Network game-type values are converted to the API
     * game mode rather than treated as interchangeable enum ordinals.
     */
    public static AllayWorldData readWorldDataFromNBT(NbtMap nbt) {
        var storageVersion = nbt.getInt(TAG_STORAGE_VERSION, Integer.MAX_VALUE);
        if (storageVersion == Integer.MAX_VALUE) {
            // The sentinel conflates an absent storage version with an explicit maximum
            // integer. Both take the warned fallback before the upper-bound check.
            log.warn("Missing " + TAG_STORAGE_VERSION + " field in level.dat");
            storageVersion = CURRENT_STORAGE_VERSION;
        }
        if (storageVersion > CURRENT_STORAGE_VERSION) {
            throw new WorldStorageException("LevelDB world storage version " + storageVersion + " is currently unsupported");
        }

        var networkVersion = nbt.getInt(TAG_NETWORK_VERSION, Integer.MAX_VALUE);
        if (networkVersion == Integer.MAX_VALUE) {
            // The network-version sentinel also treats an explicit maximum integer as
            // missing, so that value takes the current-version fallback before validation.
            log.warn("Missing " + TAG_NETWORK_VERSION + " field in level.dat");
            networkVersion = ProtocolInfo.FEATURE_VERSION.getProtocolVersion();
        }
        if (networkVersion > ProtocolInfo.FEATURE_VERSION.getProtocolVersion()) {
            throw new WorldStorageException("LevelDB world storage network version " + networkVersion + " is currently unsupported");
        }

        var pdc = new AllayPersistentDataContainer(Registries.PERSISTENT_DATA_TYPES);
        nbt.listenForCompound(TAG_PDC, pdc::putAll);

        // The saved GameType value belongs to the network enum; convert through
        // NetworkHelper so API game modes are not reconstructed from an
        // unrelated ordinal ordering.
        return AllayWorldData.builder()
                .difficulty(Difficulty.from(nbt.getInt(TAG_DIFFICULTY, AllayServer.getSettings().genericSettings().defaultDifficulty().ordinal())))
                .gameMode(NetworkHelper.fromNetwork(GameType.from(nbt.getInt(TAG_GAME_TYPE, toNetwork(AllayServer.getSettings().genericSettings().defaultGameMode()).ordinal()))))
                .displayName(nbt.getString(TAG_DISPLAY_NAME, WorldData.DEFAULT_WORLD_DISPLAY_NAME))
                .spawnPoint(new Vector3i(nbt.getInt(TAG_SPAWN_X, 0), nbt.getInt(TAG_SPAWN_Y, 64), nbt.getInt(TAG_SPAWN_Z, 0)))
                .totalTime(nbt.getLong(TAG_TOTAL_TIME, 0))
                .timeOfDay(nbt.getInt(TAG_TIME_OF_DAY, WorldData.TIME_SUNRISE))
                .worldStartCount(nbt.getLong(TAG_WORLD_START_COUNT, 0))
                .persistentDataContainer(pdc)
                .gameRules(AllayGameRules.readFromNBT(nbt))
                .build();
    }

    /**
     * Builds a {@code level.dat} NBT compound containing the supplied runtime world
     * metadata. The source remains owned by the caller, and its values should
     * stay stable while spawn coordinates, counters, persistent data, and
     * gamerules are read.
     *
     * <p>
     * The writer converts game mode through the network representation and adds
     * fixed Bedrock compatibility settings, including the void generator, zero
     * seed, and current feature versions. {@code TAG_LAST_PLAYED} stores epoch
     * seconds. Empty persistent data is omitted; gamerules are appended to the same root after
     * the compatibility fields, matching the reader's shared-root
     * representation.
     */
    public static NbtMap writeWorldDataToNBT(AllayWorldData worldData) {
        var builder = NbtMap.builder();

        builder.putInt(TAG_DIFFICULTY, worldData.getDifficulty().ordinal());
        builder.putInt(TAG_GAME_TYPE, toNetwork(worldData.getGameMode()).ordinal());
        builder.putString(TAG_DISPLAY_NAME, worldData.getDisplayName());
        builder.putInt(TAG_SPAWN_X, worldData.getSpawnPoint().x());
        builder.putInt(TAG_SPAWN_Y, worldData.getSpawnPoint().y());
        builder.putInt(TAG_SPAWN_Z, worldData.getSpawnPoint().z());
        builder.putLong(TAG_TOTAL_TIME, worldData.getTotalTime());
        builder.putInt(TAG_TIME_OF_DAY, worldData.getTimeOfDay());
        builder.putLong(TAG_WORLD_START_COUNT, worldData.getWorldStartCount());

        var pdc = worldData.getPersistentDataContainer();
        if (!pdc.isEmpty()) {
            builder.put(TAG_PDC, pdc.toNbt());
        }

        // The saved generator selection is deliberately void instead of mirroring Allay
        // generators, whose configuration is not represented by these compatibility fields.
        builder.putInt(TAG_GENERATOR, VanillaGeneratorType.VOID.ordinal());
        builder.putLong(TAG_RANDOM_SEED, 0);
        // Store the explicit format version so this reader can compare the saved format
        // against its supported maximum instead of taking the missing-version fallback.
        builder.putInt(TAG_STORAGE_VERSION, CURRENT_STORAGE_VERSION);
        // The network version supplies a separate compatibility bound from the storage
        // layout version; this reader rejects a record newer than either supported value.
        builder.putInt(TAG_NETWORK_VERSION, ProtocolInfo.FEATURE_VERSION.getProtocolVersion());
        // Convert epoch milliseconds to seconds, discarding subsecond
        // precision.
        builder.putLong(TAG_LAST_PLAYED, System.currentTimeMillis() / 1000);
        // The hasBeenLoadedInCreative tag is emitted as true independently of the saved
        // game mode; it is a fixed compatibility flag rather than reconstructed runtime state.
        builder.putByte(TAG_ACHIEVEMENTS_DISABLED, (byte) 1);
        builder.putByte(TAG_COMMANDS_ENABLED, (byte) 1);
        builder.putList(TAG_LAST_OPENED_WITH_VERSION, NbtType.INT, ProtocolInfo.getFeatureMinecraftVersion().toBoxedArray());
        builder.putByte(TAG_IS_EDU, (byte) 0);
        builder.putByte(TAG_FORCE_GAME_TYPE, (byte) 0);

        worldData.getGameRules().writeToNBT(builder);
        return builder.build();
    }
}
