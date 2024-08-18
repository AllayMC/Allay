package org.allaymc.server.world.storage;

import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.datastruct.SemVersion;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.exception.WorldStorageException;
import org.allaymc.api.world.Difficulty;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.ChunkState;
import org.allaymc.api.world.gamerule.GameRules;
import org.allaymc.api.world.storage.NativeFileWorldStorage;
import org.allaymc.server.utils.LevelDBKeyUtils;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.iq80.leveldb.CompressionType;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3i;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CompletableFuture;

/**
 * Allay Project 8/22/2023
 *
 * @author Cool_Loong
 */
@Slf4j
public class AllayLevelDBWorldStorage implements NativeFileWorldStorage {
//    private static final byte[] LEVEL_DAT_MAGIC = new byte[]{10, 0, 0, 0, 68, 11, 0, 0};
    private static final int CURRENT_LEVEL_DAT_VERSION = 10;

    private static final int LATEST_CHUNK_VERSION = 40;

    private static final int VANILLA_CHUNK_STATE_NEW = 0;
    private static final int VANILLA_CHUNK_STATE_GENERATED = 1;
    private static final int VANILLA_CHUNK_STATE_POPULATED = 2;
    private static final int VANILLA_CHUNK_STATE_FINISHED = 3;

    private final Path path;
    private final DB db;
    private final String worldName;

    public AllayLevelDBWorldStorage(Path path) throws WorldStorageException {
        this(path, new Options()
                .createIfMissing(true)
                .compressionType(CompressionType.ZLIB_RAW)
                .blockSize(64 * 1024)
        );
    }
    public AllayLevelDBWorldStorage(Path path, Options options) throws WorldStorageException {
        worldName = path.getName(path.getNameCount() - 1).toString();
        var file = path.toFile();
        if (!file.exists() && !file.mkdirs()) {
            throw new WorldStorageException("Failed to create world directory!");
        }
        this.path = path;

        var dbFolder = path.resolve("db").toFile();
        try {
            if (!dbFolder.exists() && !dbFolder.mkdirs()) {
                throw new WorldStorageException("Failed to create world database directory!");
            }
            db = net.daporkchop.ldbjni.LevelDB.PROVIDER.open(dbFolder, options);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private WorldData createWorldData(String worldName) {
        var levelDat = path.resolve("level.dat").toFile();
        try {
            // noinspection ResultOfMethodCallIgnored
            levelDat.createNewFile();
            var worldData = WorldData
                    .builder()
                    .name(worldName)
                    .build();
            writeWorldData(worldData);
            Files.copy(levelDat.toPath(), path.resolve("level.dat_old"), StandardCopyOption.REPLACE_EXISTING);
            Files.writeString(path.resolve("levelname.txt"), worldName, StandardOpenOption.CREATE);
            return worldData;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CompletableFuture<Chunk> readChunk(int x, int z, DimensionInfo dimensionInfo) throws WorldStorageException {
        return CompletableFuture.supplyAsync(() -> readChunkSync(x, z, dimensionInfo), Server.getInstance().getVirtualThreadPool());
    }

    @Override
    public Chunk readChunkSync(int x, int z, DimensionInfo dimensionInfo) throws WorldStorageException {
        var builder = AllayUnsafeChunk.builder()
                .chunkX(x)
                .chunkZ(z)
                .dimensionInfo(dimensionInfo);
        var versionValue = this.db.get(LevelDBKeyUtils.VERSION.getKey(x, z, dimensionInfo));
        if (versionValue == null)
            versionValue = this.db.get(LevelDBKeyUtils.LEGACY_VERSION.getKey(x, z, dimensionInfo));
        if (versionValue == null) return builder.build().toSafeChunk();

        var chunkState = this.db.get(LevelDBKeyUtils.CHUNK_FINALIZED_STATE.getKey(x, z, dimensionInfo));
        if (chunkState == null) {
            builder.state(ChunkState.FINISHED);
        } else {
            // NOTICE: Chunk states used in allay are different from vanilla state
            // We just regenerate the chunk unless the state value is FINISHED
            builder.state((Unpooled.wrappedBuffer(chunkState).readIntLE() + 1) == VANILLA_CHUNK_STATE_FINISHED ? ChunkState.FINISHED : ChunkState.EMPTY);
        }

        LevelDBChunkSerializer.INSTANCE.deserialize(this.db, builder);
        return builder.build().toSafeChunk();
    }

    @Override
    public CompletableFuture<Void> writeChunk(Chunk chunk) throws WorldStorageException {
        return CompletableFuture.runAsync(() -> writeChunkSync(chunk), Server.getInstance().getVirtualThreadPool());
    }

    @Override
    public void writeChunkSync(Chunk chunk) throws WorldStorageException {
        try (var writeBatch = this.db.createWriteBatch()) {
            writeBatch.put(LevelDBKeyUtils.VERSION.getKey(chunk.getX(), chunk.getZ(), chunk.getDimensionInfo()), new byte[]{LATEST_CHUNK_VERSION});
            writeBatch.put(
                    LevelDBKeyUtils.CHUNK_FINALIZED_STATE.getKey(chunk.getX(), chunk.getZ(), chunk.getDimensionInfo()),
                    Unpooled.buffer(4)
                            .writeIntLE((chunk.getState() == ChunkState.FINISHED ? VANILLA_CHUNK_STATE_FINISHED : VANILLA_CHUNK_STATE_NEW) - 1)
                            .array()
            );
            chunk.batchProcess(c -> LevelDBChunkSerializer.INSTANCE.serialize(writeBatch, c));
            this.db.write(writeBatch);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean containChunk(int x, int z, DimensionInfo dimensionInfo) {
        for (int ySection = dimensionInfo.minSectionY(); ySection <= dimensionInfo.maxSectionY(); ySection++) {
            var bytes = db.get(LevelDBKeyUtils.CHUNK_SECTION_PREFIX.getKey(x, z, ySection, dimensionInfo));
            if (bytes != null) return true;
        }
        return false;
    }

    @Override
    public void writeWorldData(WorldData worldData) {
        var levelDat = path.resolve("level.dat").toFile();
        try (var output = new FileOutputStream(levelDat)) {
            if (levelDat.exists())
                Files.copy(path.resolve("level.dat"), path.resolve("level.dat_old"), StandardCopyOption.REPLACE_EXISTING);

            // 1.Current version
            output.write(int2ByteArrayLE(CURRENT_LEVEL_DAT_VERSION));

            var byteArrayOutputStream = new ByteArrayOutputStream();
            var nbtOutputStream = NbtUtils.createWriterLE(byteArrayOutputStream);
            nbtOutputStream.writeTag(writeWorldDataToNBT(worldData));

            var data = byteArrayOutputStream.toByteArray();
            // 2.Data length
            output.write(int2ByteArrayLE(data.length));

            // 3.Data
            output.write(data);
        } catch (IOException e) {
            throw new WorldStorageException(e);
        }
    }

    private static byte[] int2ByteArrayLE(int value) {
        return ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(value).array();
    }

    @Override
    public WorldData readWorldData() throws WorldStorageException {
        var levelDat = path.resolve("level.dat").toFile();
        if (!levelDat.exists()) return createWorldData(worldName);
        try (var input = new FileInputStream(levelDat)) {
            // current_version + data length
            // noinspection ResultOfMethodCallIgnored
            input.skip(8);
            NBTInputStream readerLE = NbtUtils.createReaderLE(new ByteArrayInputStream(input.readAllBytes()));
            NbtMap nbt = (NbtMap) readerLE.readTag();
            readerLE.close();
            return readWorldDataFromNBT(nbt);
        } catch (FileNotFoundException e) {
            log.error("level.dat file does not exist!");
        } catch (IOException e) {
            log.error("level.dat file is broken!");
            return null;
        }
        throw new RuntimeException("level.dat is null!");
    }

    private WorldData readWorldDataFromNBT(NbtMap nbt) {
        return WorldData.builder()
                .biomeOverride(nbt.getString("BiomeOverride"))
                .centerMapsToOrigin(nbt.getBoolean("CenterMapsToOrigin"))
                .confirmedPlatformLockedContent(nbt.getBoolean("ConfirmedPlatformLockedContent"))
                .difficulty(Difficulty.from(nbt.getInt("Difficulty")))
                .flatWorldLayers(nbt.getString("FlatWorldLayers"))
                .forceGameType(nbt.getBoolean("ForceGameType"))
                .gameType(GameType.from(nbt.getInt("GameType")))
                .generator(nbt.getInt("Generator"))
                .inventoryVersion(nbt.getString("InventoryVersion"))
                .LANBroadcast(nbt.getBoolean("LANBroadcast"))
                .LANBroadcastIntent(nbt.getBoolean("LANBroadcastIntent"))
                .lastPlayed(nbt.getLong("LastPlayed"))
                .name(nbt.getString("LevelName"))
                .limitedWorldOriginPoint(new Vector3i(nbt.getInt("LimitedWorldOriginX"), nbt.getInt("LimitedWorldOriginY"), nbt.getInt("LimitedWorldOriginZ")))
                .minimumCompatibleClientVersion(SemVersion.from(nbt.getIntArray("MinimumCompatibleClientVersion")))
                .multiplayerGame(nbt.getBoolean("MultiplayerGame"))
                .multiplayerGameIntent(nbt.getBoolean("MultiplayerGameIntent"))
                .netherScale(nbt.getInt("NetherScale"))
                .networkVersion(nbt.getInt("NetworkVersion"))
                .platform(nbt.getInt("Platform"))
                .platformBroadcastIntent(nbt.getInt("PlatformBroadcastIntent"))
                .randomSeed(nbt.getLong("RandomSeed"))
                .spawnV1Villagers(nbt.getBoolean("SpawnV1Villagers"))
                .spawnPoint(new Vector3i(nbt.getInt("SpawnX"), nbt.getInt("SpawnY"), nbt.getInt("SpawnZ")))
                .storageVersion(nbt.getInt("StorageVersion"))
                .time(nbt.getLong("Time"))
                .worldVersion(nbt.getInt("WorldVersion"))
                .XBLBroadcastIntent(nbt.getInt("XBLBroadcastIntent"))
                .abilities(readAbilities(nbt.getCompound("abilities")))
                .baseGameVersion(nbt.getString("baseGameVersion"))
                .bonusChestEnabled(nbt.getBoolean("bonusChestEnabled"))
                .bonusChestSpawned(nbt.getBoolean("bonusChestSpawned"))
                .cheatsEnabled(nbt.getBoolean("cheatsEnabled"))
                .commandsEnabled(nbt.getBoolean("commandsEnabled"))
                .gameRules(GameRules.readFromNBT(nbt))
                .currentTick(nbt.getLong("currentTick"))
                .daylightCycle(nbt.getInt("daylightCycle"))
                .editorWorldType(nbt.getInt("editorWorldType"))
                .eduOffer(nbt.getInt("eduOffer"))
                .educationFeaturesEnabled(nbt.getBoolean("educationFeaturesEnabled"))
                .experiments(readExperiments(nbt.getCompound("experiments")))
                .hasBeenLoadedInCreative(nbt.getBoolean("hasBeenLoadedInCreative"))
                .hasLockedBehaviorPack(nbt.getBoolean("hasLockedBehaviorPack"))
                .hasLockedResourcePack(nbt.getBoolean("hasLockedResourcePack"))
                .immutableWorld(nbt.getBoolean("immutableWorld"))
                .isCreatedInEditor(nbt.getBoolean("isCreatedInEditor"))
                .isExportedFromEditor(nbt.getBoolean("isExportedFromEditor"))
                .isFromLockedTemplate(nbt.getBoolean("isFromLockedTemplate"))
                .isFromWorldTemplate(nbt.getBoolean("isFromWorldTemplate"))
                .isRandomSeedAllowed(nbt.getBoolean("isRandomSeedAllowed"))
                .isSingleUseWorld(nbt.getBoolean("isSingleUseWorld"))
                .isWorldTemplateOptionLocked(nbt.getBoolean("isWorldTemplateOptionLocked"))
                .lastOpenedWithVersion(SemVersion.from(nbt.getIntArray("lastOpenedWithVersion")))
                .lightningLevel(nbt.getFloat("lightningLevel"))
                .lightningTime(nbt.getInt("lightningTime"))
                .limitedWorldDepth(nbt.getInt("limitedWorldDepth"))
                .limitedWorldWidth(nbt.getInt("limitedWorldWidth"))
                .permissionsLevel(nbt.getInt("permissionsLevel"))
                .playerPermissionsLevel(nbt.getInt("playerPermissionsLevel"))
                .playersSleepingPercentage(nbt.getInt("playerssleepingpercentage"))
                .prid(nbt.getString("prid"))
                .rainLevel(nbt.getFloat("rainLevel"))
                .rainTime(nbt.getInt("rainTime"))
                .randomTickSpeed(nbt.getInt("randomtickspeed"))
                .recipesUnlock(nbt.getBoolean("recipesunlock"))
                .requiresCopiedPackRemovalCheck(nbt.getBoolean("requiresCopiedPackRemovalCheck"))
                .serverChunkTickRange(nbt.getInt("serverChunkTickRange"))
                .spawnMobs(nbt.getBoolean("spawnMobs"))
                .startWithMapEnabled(nbt.getBoolean("startWithMapEnabled"))
                .texturePacksRequired(nbt.getBoolean("texturePacksRequired"))
                .useMsaGamertagsOnly(nbt.getBoolean("useMsaGamertagsOnly"))
                .worldStartCount(nbt.getLong("worldStartCount"))
                .worldPolicies(WorldData.WorldPolicies.builder().build())
                .build();
    }

    private WorldData.Experiments readExperiments(NbtMap experiments) {
        return WorldData.Experiments.builder()
                .cameras(experiments.getBoolean("cameras"))
                .dataDrivenBiomes(experiments.getBoolean("data_driven_biomes"))
                .dataDrivenItems(experiments.getBoolean("data_driven_items"))
                .experimentalMolangFeatures(experiments.getBoolean("experimental_molang_features"))
                .experimentsEverUsed(experiments.getBoolean("experiments_ever_used"))
                .savedWithToggledExperiments(experiments.getBoolean("saved_with_toggled_experiments"))
                .upcomingCreatorFeatures(experiments.getBoolean("upcoming_creator_features"))
                .villagerTradesRebalance(experiments.getBoolean("villager_trades_rebalance"))
                .build();
    }

    private WorldData.Abilities readAbilities(NbtMap abilities) {
        return WorldData.Abilities.builder()
                .attackMobs(abilities.getBoolean("attackmobs"))
                .attackPlayers(abilities.getBoolean("attackplayers"))
                .build(abilities.getBoolean("build"))
                .doorsAndSwitches(abilities.getBoolean("doorsandswitches"))
                .flySpeed(abilities.getFloat("flySpeed"))
                .flying(abilities.getBoolean("flying"))
                .instaBuild(abilities.getBoolean("instabuild"))
                .invulnerable(abilities.getBoolean("invulnerable"))
                .lightning(abilities.getBoolean("lightning"))
                .mayFly(abilities.getBoolean("mayfly"))
                .mine(abilities.getBoolean("mine"))
                .op(abilities.getBoolean("op"))
                .openContainers(abilities.getBoolean("opencontainers"))
                .teleport(abilities.getBoolean("teleport"))
                .walkSpeed(abilities.getFloat("walkSpeed"))
                .build();
    }

    public void shutdown() {
        try {
            this.db.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private NbtMap writeWorldDataToNBT(WorldData worldData) {
        var builder = NbtMap.builder();
        builder.putString("BiomeOverride", worldData.getBiomeOverride());
        builder.putBoolean("CenterMapsToOrigin", worldData.isCenterMapsToOrigin());
        builder.putBoolean("ConfirmedPlatformLockedContent", worldData.isConfirmedPlatformLockedContent());
        builder.putInt("Difficulty", worldData.getDifficulty().ordinal());
        builder.putString("FlatWorldLayers", worldData.getFlatWorldLayers());
        builder.putBoolean("ForceGameType", worldData.isForceGameType());
        builder.putInt("GameType", worldData.getGameType().ordinal());
        builder.putInt("Generator", worldData.getGenerator());
        builder.putString("InventoryVersion", worldData.getInventoryVersion());
        builder.putBoolean("LANBroadcast", worldData.isLANBroadcast());
        builder.putBoolean("LANBroadcastIntent", worldData.isLANBroadcastIntent());
        builder.putLong("LastPlayed", worldData.getLastPlayed());
        builder.putString("LevelName", worldData.getName());
        builder.putInt("LimitedWorldOriginX", worldData.getLimitedWorldOriginPoint().x());
        builder.putInt("LimitedWorldOriginY", worldData.getLimitedWorldOriginPoint().y());
        builder.putInt("LimitedWorldOriginZ", worldData.getLimitedWorldOriginPoint().z());
        builder.putIntArray("MinimumCompatibleClientVersion", worldData.getMinimumCompatibleClientVersion().toArray());
        builder.putBoolean("MultiplayerGame", worldData.isMultiplayerGame());
        builder.putBoolean("MultiplayerGameIntent", worldData.isMultiplayerGameIntent());
        builder.putInt("NetherScale", worldData.getNetherScale());
        builder.putInt("NetworkVersion", worldData.getNetworkVersion());
        builder.putInt("Platform", worldData.getPlatform());
        builder.putInt("PlatformBroadcastIntent", worldData.getPlatformBroadcastIntent());
        builder.putLong("RandomSeed", worldData.getRandomSeed());
        builder.putBoolean("SpawnV1Villagers", worldData.isSpawnV1Villagers());
        builder.putInt("SpawnX", worldData.getSpawnPoint().x());
        builder.putInt("SpawnY", worldData.getSpawnPoint().y());
        builder.putInt("SpawnZ", worldData.getSpawnPoint().z());
        builder.putInt("StorageVersion", worldData.getStorageVersion());
        builder.putLong("Time", worldData.getTime());
        builder.putInt("WorldVersion", worldData.getWorldVersion());
        builder.putInt("XBLBroadcastIntent", worldData.getXBLBroadcastIntent());
        builder.put("abilities", writeAbilities(worldData));
        builder.put("experiments", writeExperiments(worldData));

        builder.putBoolean("bonusChestEnabled", worldData.isBonusChestEnabled());
        builder.putBoolean("bonusChestSpawned", worldData.isBonusChestSpawned());
        builder.putBoolean("cheatsEnabled", worldData.isCheatsEnabled());
        builder.putBoolean("commandsEnabled", worldData.isCommandsEnabled());
        builder.putLong("currentTick", worldData.getCurrentTick());
        builder.putInt("daylightCycle", worldData.getDaylightCycle());
        builder.putInt("editorWorldType", worldData.getEditorWorldType());
        builder.putInt("eduOffer", worldData.getEduOffer());
        builder.putBoolean("educationFeaturesEnabled", worldData.isEducationFeaturesEnabled());

        worldData.getGameRules().writeToNBT(builder);
        return builder.build();
    }

    private NbtMap writeExperiments(WorldData worldData) {
        return NbtMap.builder()
                .putBoolean("cameras", worldData.getExperiments().isCameras())
                .putBoolean("data_driven_biomes", worldData.getExperiments().isDataDrivenBiomes())
                .putBoolean("data_driven_items", worldData.getExperiments().isDataDrivenItems())
                .putBoolean("experimental_molang_features", worldData.getExperiments().isExperimentalMolangFeatures())
                .putBoolean("experiments_ever_used", worldData.getExperiments().isExperimentsEverUsed())
                .putBoolean("gametest", worldData.getExperiments().isGametest())
                .putBoolean("saved_with_toggled_experiments", worldData.getExperiments().isSavedWithToggledExperiments())
                .putBoolean("upcoming_creator_features", worldData.getExperiments().isUpcomingCreatorFeatures())
                .putBoolean("villager_trades_rebalance", worldData.getExperiments().isVillagerTradesRebalance())
                .build();
    }

    private NbtMap writeAbilities(WorldData worldData) {
        return NbtMap.builder()
                .putBoolean("attackmobs", worldData.getAbilities().isAttackMobs())
                .putBoolean("attackplayers", worldData.getAbilities().isAttackPlayers())
                .putBoolean("build", worldData.getAbilities().isBuild())
                .putBoolean("doorsandswitches", worldData.getAbilities().isDoorsAndSwitches())
                .putBoolean("flying", worldData.getAbilities().isFlying())
                .putBoolean("instabuild", worldData.getAbilities().isInstaBuild())
                .putBoolean("invulnerable", worldData.getAbilities().isInvulnerable())
                .putBoolean("lightning", worldData.getAbilities().isLightning())
                .putBoolean("mayfly", worldData.getAbilities().isMayFly())
                .putBoolean("mine", worldData.getAbilities().isMine())
                .putBoolean("op", worldData.getAbilities().isOp())
                .putBoolean("opencontainers", worldData.getAbilities().isOpenContainers())
                .putBoolean("teleport", worldData.getAbilities().isTeleport())
                .putFloat("flySpeed", worldData.getAbilities().getFlySpeed())
                .putFloat("walkSpeed", worldData.getAbilities().getWalkSpeed())
                .build();
    }

    @Override
    public Path getWorldFolderPath() {
        return path;
    }
}
