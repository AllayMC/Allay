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
import org.allaymc.server.world.gamerule.AllayGameRules;
import org.allaymc.server.world.storage.leveldb.data.StorageVersion;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.joml.Vector3i;

import static org.allaymc.server.network.NetworkHelper.toNetwork;

/**
 * Codec for reading/writing world-level data (level.dat NBT) in LevelDB format.
 */
@Slf4j
@UtilityClass
public final class WorldDataCodec {

    public static final int CURRENT_STORAGE_VERSION = StorageVersion.LEVEL_DATA_STRICT_SIZE.ordinal();

    // Keys used in world data
    private static final String TAG_DIFFICULTY = "Difficulty";
    private static final String TAG_GAME_TYPE = "GameType";
    private static final String TAG_DISPLAY_NAME = "LevelName";
    private static final String TAG_SPAWN_X = "SpawnX";
    private static final String TAG_SPAWN_Y = "SpawnY";
    private static final String TAG_SPAWN_Z = "SpawnZ";
    private static final String TAG_TOTAL_TIME = "Time";
    private static final String TAG_TIME_OF_DAY = "TimeOfDay";
    private static final String TAG_WORLD_START_COUNT = "WorldStartCount";
    private static final String TAG_PDC = "PDC";

    // The following keys are written to make the vanilla client
    // load the world correctly, they aren't used in other places
    private static final String TAG_GENERATOR = "Generator";
    private static final String TAG_RANDOM_SEED = "RandomSeed";
    private static final String TAG_STORAGE_VERSION = "StorageVersion";
    private static final String TAG_NETWORK_VERSION = "NetworkVersion";
    private static final String TAG_LAST_PLAYED = "LastPlayed";
    private static final String TAG_ACHIEVEMENTS_DISABLED = "hasBeenLoadedInCreative";
    private static final String TAG_COMMANDS_ENABLED = "commandsEnabled";
    private static final String TAG_LAST_OPENED_WITH_VERSION = "lastOpenedWithVersion";
    private static final String TAG_IS_EDU = "eduLevel";
    private static final String TAG_FORCE_GAME_TYPE = "ForceGameType";

    public static AllayWorldData readWorldDataFromNBT(NbtMap nbt) {
        var storageVersion = nbt.getInt(TAG_STORAGE_VERSION, Integer.MAX_VALUE);
        if (storageVersion == Integer.MAX_VALUE) {
            log.warn("Missing " + TAG_STORAGE_VERSION + " field in level.dat");
            storageVersion = CURRENT_STORAGE_VERSION;
        }
        if (storageVersion > CURRENT_STORAGE_VERSION) {
            throw new WorldStorageException("LevelDB world storage version " + storageVersion + " is currently unsupported");
        }

        var networkVersion = nbt.getInt(TAG_NETWORK_VERSION, Integer.MAX_VALUE);
        if (networkVersion == Integer.MAX_VALUE) {
            log.warn("Missing " + TAG_NETWORK_VERSION + " field in level.dat");
            networkVersion = ProtocolInfo.FEATURE_VERSION.getProtocolVersion();
        }
        if (networkVersion > ProtocolInfo.FEATURE_VERSION.getProtocolVersion()) {
            throw new WorldStorageException("LevelDB world storage network version " + networkVersion + " is currently unsupported");
        }

        var pdc = new AllayPersistentDataContainer(Registries.PERSISTENT_DATA_TYPES);
        nbt.listenForCompound(TAG_PDC, pdc::putAll);

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

        // NOTICE: the following values are written to let
        // the vanilla client load the world more easily

        // Set generator type to "void" so that the vanilla won't generate unexpected chunks
        // Refer to org.allaymc.api.world.generator.WorldGeneratorType:
        // 0 -     old, 1 - infinite
        // 2 -    flat, 3 -   nether
        // 4 - the_end, 5 -     void
        builder.putInt(TAG_GENERATOR, 5);
        builder.putLong(TAG_RANDOM_SEED, 0);
        // The client will crash if this field is not exist
        builder.putInt(TAG_STORAGE_VERSION, CURRENT_STORAGE_VERSION);
        // StorageVersion is rarely updated. Instead, the game relies on the NetworkVersion tag,
        // which is synced with the network protocol version for that version
        builder.putInt(TAG_NETWORK_VERSION, ProtocolInfo.FEATURE_VERSION.getProtocolVersion());
        builder.putLong(TAG_LAST_PLAYED, System.currentTimeMillis() / 1000);
        // Badly named, this actually determines whether achievements can be earned in this world...
        builder.putByte(TAG_ACHIEVEMENTS_DISABLED, (byte) 1);
        builder.putByte(TAG_COMMANDS_ENABLED, (byte) 1);
        builder.putList(TAG_LAST_OPENED_WITH_VERSION, NbtType.INT, ProtocolInfo.getFeatureMinecraftVersion().toBoxedArray());
        builder.putByte(TAG_IS_EDU, (byte) 0);
        builder.putByte(TAG_FORCE_GAME_TYPE, (byte) 0);

        worldData.getGameRules().writeToNBT(builder);
        return builder.build();
    }
}
