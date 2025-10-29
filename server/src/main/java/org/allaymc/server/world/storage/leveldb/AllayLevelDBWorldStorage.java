package org.allaymc.server.world.storage.leveldb;

import com.google.common.base.Preconditions;
import io.netty.buffer.*;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.AllayNBTUtils;
import org.allaymc.api.utils.NBTIO;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.api.world.World;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.biome.BiomeTypes;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.ChunkState;
import org.allaymc.api.world.chunk.OperationType;
import org.allaymc.api.world.data.Difficulty;
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.api.world.storage.WorldStorage;
import org.allaymc.api.world.storage.WorldStorageException;
import org.allaymc.server.AllayServer;
import org.allaymc.server.datastruct.palette.Palette;
import org.allaymc.server.datastruct.palette.PaletteException;
import org.allaymc.server.datastruct.palette.PaletteUtils;
import org.allaymc.server.network.NetworkHelper;
import org.allaymc.server.network.ProtocolInfo;
import org.allaymc.server.pdc.AllayPersistentDataContainer;
import org.allaymc.server.world.AllayWorldData;
import org.allaymc.server.world.chunk.*;
import org.allaymc.server.world.gamerule.AllayGameRules;
import org.allaymc.updater.block.BlockStateUpdaters;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.nbt.util.stream.LittleEndianDataInputStream;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.iq80.leveldb.CompressionType;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.WriteBatch;
import org.iq80.leveldb.impl.Iq80DBFactory;
import org.jctools.maps.NonBlockingHashMap;
import org.joml.Vector3i;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static org.allaymc.server.network.NetworkHelper.toNetwork;

/**
 * An implementation of {@link WorldStorage} which add support for the LevelDB world
 * format that is used in Minecraft: Bedrock Edition.
 * <p>
 * Special thanks to PMMP team.
 *
 * @author Cool_Loong | daoge_cmd
 */
@Slf4j
public class AllayLevelDBWorldStorage implements WorldStorage {

    private static final String FILE_LEVEL_DAT = "level.dat";
    private static final String FILE_LEVEL_DAT_OLD = "level.dat_old";

    private static final String DIR_DB = "db";

    private static final int CURRENT_STORAGE_VERSION = StorageVersion.LEVEL_DATA_STRICT_SIZE.ordinal();
    private static final int CURRENT_CHUNK_VERSION = ChunkVersion.V1_21_40.ordinal();

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

    // Keys used in scheduled updates
    private static final String TAG_CURRENT_TICK = "currentTick";
    private static final String TAG_TICK_LIST = "tickList";

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

    private static final int HEIGHTMAP_SIZE = 256;

    private final Path path;
    private final String worldName;
    private final DB db;

    private World world;

    public AllayLevelDBWorldStorage(Path path) {
        this(path, new Options().createIfMissing(true).compressionType(CompressionType.ZLIB_RAW).blockSize(64 * 1024));
    }

    public AllayLevelDBWorldStorage(Path path, Options options) {
        this.path = path;
        this.worldName = path.getName(path.getNameCount() - 1).toString();

        var file = path.toFile();
        if (!file.exists() && !file.mkdirs()) {
            throw new WorldStorageException("Failed to create world directory!");
        }

        var dbFolder = path.resolve(DIR_DB).toFile();
        try {
            if (!dbFolder.exists() && !dbFolder.mkdirs()) {
                throw new WorldStorageException("Failed to create world database directory!");
            }
            db = new Iq80DBFactory().open(dbFolder, options);
        } catch (IOException e) {
            throw new WorldStorageException(e);
        }
    }

    private static AllayWorldData readWorldDataFromNBT(NbtMap nbt) {
        var storageVersion = nbt.getInt(TAG_STORAGE_VERSION, Integer.MAX_VALUE);
        if (storageVersion == Integer.MAX_VALUE) {
            log.warn("Missing " + TAG_STORAGE_VERSION + " field in " + FILE_LEVEL_DAT);
            storageVersion = CURRENT_STORAGE_VERSION;
        }
        if (storageVersion > CURRENT_STORAGE_VERSION) {
            throw new WorldStorageException("LevelDB world storage version " + storageVersion + " is currently unsupported");
        }

        var networkVersion = nbt.getInt(TAG_NETWORK_VERSION, Integer.MAX_VALUE);
        if (networkVersion == Integer.MAX_VALUE) {
            log.warn("Missing " + TAG_NETWORK_VERSION + " field in " + FILE_LEVEL_DAT);
            networkVersion = ProtocolInfo.FEATURE_VERSION.getProtocolVersion();
        }
        if (networkVersion > ProtocolInfo.FEATURE_VERSION.getProtocolVersion()) {
            log.warn("LevelDB world storage network version {} is currently unsupported", networkVersion);
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

    private static void serializeSections(WriteBatch writeBatch, AllayUnsafeChunk chunk) {
        for (int ySection = chunk.getDimensionInfo().minSectionY(); ySection <= chunk.getDimensionInfo().maxSectionY(); ySection++) {
            var section = chunk.getSection(ySection);
            if (!section.hasDirtyBlockLayer()) {
                continue;
            }

            int finalYSection = ySection;
            writeBatch.put(
                    LevelDBKey.CHUNK_SECTION_PREFIX.createKey(chunk.getX(), chunk.getZ(), ySection, chunk.getDimensionInfo()),
                    withByteBufToArray(buffer -> {
                        buffer.writeByte(AllayChunkSection.CURRENT_CHUNK_SECTION_VERSION);
                        buffer.writeByte(AllayChunkSection.LAYER_COUNT);
                        buffer.writeByte(finalYSection);
                        for (int i = 0; i < AllayChunkSection.LAYER_COUNT; i++) {
                            var palette = section.blockLayers()[i];
                            palette.compact();
                            palette.writeToStorage(buffer, BlockState::getBlockStateNBT);
                            palette.setDirty(false);
                        }
                    })
            );
        }
    }

    private static void deserializeSections(DB db, AllayChunkBuilder builder) {
        var dimensionInfo = builder.getDimensionInfo();
        var sections = new AllayChunkSection[dimensionInfo.chunkSectionCount()];
        var minSectionY = dimensionInfo.minSectionY();

        for (int ySection = minSectionY; ySection <= dimensionInfo.maxSectionY(); ySection++) {
            var sectionData = db.get(LevelDBKey.CHUNK_SECTION_PREFIX.createKey(builder.getChunkX(), builder.getChunkZ(), ySection, dimensionInfo));
            if (sectionData == null) {
                continue;
            }

            var byteBuf = Unpooled.wrappedBuffer(sectionData);
            var subChunkVersion = byteBuf.readByte();
            var layers = AllayChunkSection.LAYER_COUNT;

            switch (subChunkVersion) {
                case 9, 8:
                    layers = byteBuf.readByte();
                    if (subChunkVersion == 9) {
                        // Extra section y value in version 9
                        byteBuf.readByte();
                    }
                case 1:
                    AllayChunkSection section;
                    if (layers <= AllayChunkSection.LAYER_COUNT) {
                        // This is the normal situation where the chunk section is loaded correctly,
                        // and we use the single-arg constructor of ChunkSection directly to avoid
                        // using Arrays.fill(), which will be slower
                        section = new AllayChunkSection((byte) ySection);
                    } else {
                        // Currently only two layers are used in minecraft, so that might mean this chunk is corrupted
                        // However we can still load it c:
                        log.warn("Loading chunk section ({}, {}, {}) with {} layers, which might mean that this chunk is corrupted!", builder.getChunkX(), ySection, builder.getChunkZ(), layers);
                        @SuppressWarnings("rawtypes") Palette[] palettes = new Palette[layers];
                        Arrays.fill(palettes, new Palette<>(BlockTypes.AIR.getDefaultState()));
                        section = new AllayChunkSection((byte) ySection, palettes);
                    }

                    for (int layer = 0; layer < layers; layer++) {
                        var palette = section.blockLayers()[layer];
                        palette.readFromStorage(byteBuf, AllayLevelDBWorldStorage::fastBlockStateDeserializer);
                        palette.setDirty(false);
                    }
                    sections[ySection - minSectionY] = section;
                    break;
                default:
                    log.warn("Unknown subchunk version {} at ({}, {}, {})", subChunkVersion, builder.getChunkX(), ySection, builder.getChunkZ());
            }
        }
        builder.sections(fillNullSections(sections, dimensionInfo));
    }

    private static BlockState fastBlockStateDeserializer(ByteBuf buffer) {
        int blockStateHash;
        try (var bufInputStream = new ByteBufInputStream(buffer);
             var input = new LittleEndianDataInputStream(bufInputStream);
             var nbtInputStream = new NBTInputStream(input)) {
            blockStateHash = PaletteUtils.fastReadBlockStateHash(input, buffer);
            if (blockStateHash == PaletteUtils.HASH_NOT_LATEST) {
                var oldNbtMap = (NbtMap) nbtInputStream.readTag();
                var newNbtMap = BlockStateUpdaters.updateBlockState(oldNbtMap, ProtocolInfo.BLOCK_STATE_UPDATER.getVersion());
                // Make sure that tree map is used
                // If the map inside states nbt is not tree map
                // the block state hash will be wrong!
                var states = new TreeMap<>(newNbtMap.getCompound("states"));
                // To calculate the hash of the block state
                // "name" field must be in the first place
                var tag = NbtMap.builder()
                        .putString("name", newNbtMap.getString("name"))
                        .putCompound("states", NbtMap.fromMap(states))
                        .build();
                blockStateHash = HashUtils.fnv1a_32_nbt(tag);
            }
        } catch (IOException e) {
            throw new PaletteException(e);
        }

        BlockState blockState = Registries.BLOCK_STATE_PALETTE.get(blockStateHash);
        if (blockState != null) {
            return blockState;
        }

        log.error("Unknown block state hash {} while loading chunk section", blockStateHash);
        return BlockTypes.UNKNOWN.getDefaultState();
    }

    private static AllayChunkSection[] fillNullSections(AllayChunkSection[] sections, DimensionInfo dimensionInfo) {
        for (int i = 0; i < sections.length; i++) {
            if (sections[i] == null) {
                sections[i] = new AllayChunkSection((byte) (i + dimensionInfo.minSectionY()));
            }
        }
        return sections;
    }

    /**
     * Bedrock Edition 3d-data saves the height map starting from index 0,
     * so adjustments are made here to accommodate the world's minimum height.
     *
     * @see <a href="https://github.com/bedrock-dev/bedrock-level/blob/main/src/include/data_3d.h#L115">Biome 3d</a>
     */
    private static void serializeHeightAndBiome(WriteBatch writeBatch, AllayUnsafeChunk chunk) {
        writeBatch.put(LevelDBKey.DATA_3D.createKey(chunk.getX(), chunk.getZ(), chunk.getDimensionInfo()), withByteBufToArray(heightAndBiomesBuffer -> {
            // Serialize height map
            for (var height : chunk.calculateAndGetHeightMap().getHeights()) {
                heightAndBiomesBuffer.writeShortLE(height - chunk.getDimensionInfo().minHeight());
            }

            // Serialize biomes
            Palette<BiomeType> lastPalette = null;
            for (int y = chunk.getDimensionInfo().minSectionY(); y <= chunk.getDimensionInfo().maxSectionY(); y++) {
                AllayChunkSection section = chunk.getSection(y);
                section.biomes().compact();
                section.biomes().writeToStorage(heightAndBiomesBuffer, BiomeType::getId, lastPalette);
                // TODO: Fix client crash due to biome copy flag
                // lastPalette = section.biomes();
            }
        }));
    }

    private static void deserializeHeightAndBiome(DB db, AllayChunkBuilder builder) {
        var data3d = db.get(LevelDBKey.DATA_3D.createKey(builder.getChunkX(), builder.getChunkZ(), builder.getDimensionInfo()));
        if (data3d == null) {
            // Try load data_2d if data_3d is not found
            deserializeHeightAndBiomeOld(db, builder);
            return;
        }

        ByteBuf heightAndBiomesBuffer = Unpooled.wrappedBuffer(data3d);

        // Height map
        short[] heights = new short[HEIGHTMAP_SIZE];
        for (int i = 0; i < HEIGHTMAP_SIZE; i++) {
            heights[i] = (short) (heightAndBiomesBuffer.readUnsignedShortLE() + builder.getDimensionInfo().minHeight());
        }
        builder.heightMap(new HeightMap(heights));

        // Biomes
        Palette<BiomeType> lastPalette = null;
        var minSectionY = builder.getDimensionInfo().minSectionY();
        for (int y = minSectionY; y <= builder.getDimensionInfo().maxSectionY(); y++) {
            AllayChunkSection section = builder.getSections()[y - minSectionY];
            if (section == null) {
                continue;
            }

            section.biomes().readFromStorage(heightAndBiomesBuffer, AllayLevelDBWorldStorage::getBiomeByIdNonNull, lastPalette);
            lastPalette = section.biomes();
        }
    }

    private static void deserializeHeightAndBiomeOld(DB db, AllayChunkBuilder builder) {
        var bytes2D = db.get(LevelDBKey.DATA_2D.createKey(builder.getChunkX(), builder.getChunkZ(), builder.getDimensionInfo()));
        if (bytes2D == null) {
            return;
        }

        ByteBuf heightAndBiomesBuffer = Unpooled.wrappedBuffer(bytes2D);
        short[] heights = new short[HEIGHTMAP_SIZE];
        for (int i = 0; i < HEIGHTMAP_SIZE; i++) {
            heights[i] = heightAndBiomesBuffer.readShortLE();
        }
        builder.heightMap(new HeightMap(heights));

        byte[] biomes = new byte[HEIGHTMAP_SIZE];
        heightAndBiomesBuffer.readBytes(biomes);

        var minSectionY = builder.getDimensionInfo().minSectionY();
        for (int y = minSectionY; y <= builder.getDimensionInfo().maxSectionY(); y++) {
            var section = builder.getSections()[y - minSectionY];
            if (section == null) {
                continue;
            }

            var biomePalette = section.biomes();
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    for (int sy = 0; sy < 16; sy++) {
                        biomePalette.set(HashUtils.hashChunkSectionXYZ(x, sy, z), getBiomeByIdNonNull(biomes[x + 16 * z]));
                    }
                }
            }
        }
    }

    private static void serializeBlockEntities(WriteBatch writeBatch, AllayUnsafeChunk chunk) {
        var blockEntities = chunk.getBlockEntities().values();
        var blockEntitiesKey = LevelDBKey.BLOCK_ENTITIES.createKey(chunk.getX(), chunk.getZ(), chunk.getDimensionInfo());
        if (blockEntities.isEmpty()) {
            writeBatch.delete(blockEntitiesKey);
            return;
        }

        writeBatch.put(blockEntitiesKey, withByteBufToArray(blockEntitiesBuffer -> {
            try (var writerLE = NbtUtils.createWriterLE(new ByteBufOutputStream(blockEntitiesBuffer))) {
                for (BlockEntity blockEntity : blockEntities) {
                    writerLE.writeTag(blockEntity.saveNBT());
                }
            } catch (IOException e) {
                throw new WorldStorageException(e);
            }
        }));
    }

    private static void serializeScheduledUpdates(WriteBatch writeBatch, AllayUnsafeChunk chunk, World world) {
        var scheduledUpdates = chunk.getScheduledUpdates().values();
        var key = LevelDBKey.PENDING_TICKS.createKey(chunk.getX(), chunk.getZ(), chunk.getDimensionInfo());
        if (scheduledUpdates.isEmpty()) {
            writeBatch.delete(key);
            return;
        }

        writeBatch.put(key, withByteBufToArray(scheduledUpdatesBuffer -> {
            try (var writerLE = NbtUtils.createWriterLE(new ByteBufOutputStream(scheduledUpdatesBuffer))) {
                var nbt = NbtMap.builder()
                        .putInt(TAG_CURRENT_TICK, (int) world.getTick())
                        .putList(TAG_TICK_LIST, NbtType.COMPOUND, scheduledUpdates.stream().map(ScheduledUpdateInfo::toNBT).toList())
                        .build();
                writerLE.writeTag(nbt);
            } catch (IOException e) {
                throw new WorldStorageException(e);
            }
        }));
    }

    private static void deserializeScheduledUpdates(DB db, AllayChunkBuilder builder) {
        DimensionInfo dimensionInfo = builder.getDimensionInfo();
        var scheduledUpdatesBytes = db.get(LevelDBKey.PENDING_TICKS.createKey(builder.getChunkX(), builder.getChunkZ(), dimensionInfo));
        if (scheduledUpdatesBytes == null) {
            return;
        }

        var nbt = AllayNBTUtils.bytesToNbtLE(scheduledUpdatesBytes);
        var tickList = nbt.getList(TAG_TICK_LIST, NbtType.COMPOUND);
        var scheduledUpdates = new NonBlockingHashMap<Integer, ScheduledUpdateInfo>(tickList.size());
        for (var entry : tickList) {
            var info = ScheduledUpdateInfo.fromNBT(entry);
            var pos = info.getPos();
            scheduledUpdates.put(HashUtils.hashChunkXYZ(pos.x() & 15, pos.y(), pos.z() & 15), info);
        }

        builder.scheduledUpdates(scheduledUpdates);
    }

    private static BiomeType getBiomeByIdNonNull(int id) {
        try {
            return Registries.BIOMES.getByK1(id);
        } catch (ArrayIndexOutOfBoundsException e) {
            log.warn("Unknown biome id: {}", id);
            return BiomeTypes.PLAINS;
        }
    }

    private static byte[] int2ByteArrayLE(int value) {
        return ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(value).array();
    }

    private static byte[] withByteBufToArray(Consumer<ByteBuf> writer) {
        var buf = ByteBufAllocator.DEFAULT.buffer();
        try {
            writer.accept(buf);
            return ByteBufUtil.getBytes(buf);
        } finally {
            buf.release();
        }
    }

    @Override
    public void setWorld(World world) {
        Preconditions.checkState(this.world == null, "World has already been set");
        this.world = world;
    }

    @Override
    public CompletableFuture<Chunk> readChunk(int chunkX, int chunkZ, DimensionInfo dimensionInfo) {
        return CompletableFuture
                .supplyAsync(() -> readChunkSync(chunkX, chunkZ, dimensionInfo), Server.getInstance().getVirtualThreadPool())
                .exceptionally(t -> {
                    log.error("Failed to read chunk ({}, {})", chunkX, chunkZ, t);
                    return AllayUnsafeChunk.builder().newChunk(chunkX, chunkZ, dimensionInfo).toSafeChunk();
                });
    }

    @Override
    public Chunk readChunkSync(int chunkX, int chunkZ, DimensionInfo dimensionInfo) {
        var builder = AllayUnsafeChunk.builder()
                .chunkX(chunkX)
                .chunkZ(chunkZ)
                .dimensionInfo(dimensionInfo)
                .state(ChunkState.NEW);
        if (!containChunk(chunkX, chunkZ, dimensionInfo)) {
            return builder.build().toSafeChunk();
        }

        var versionValue = this.db.get(LevelDBKey.VERSION.createKey(chunkX, chunkZ, dimensionInfo));
        if (versionValue == null) {
            versionValue = this.db.get(LevelDBKey.LEGACY_VERSION.createKey(chunkX, chunkZ, dimensionInfo));
        }
        if (versionValue == null) {
            // This might be a slightly-corrupted chunk with a missing version field
            // However we can still try to load it
            log.warn("Chunk at ({}, {}) is missing version field", chunkX, chunkZ);
        }

        var chunkFinalizedState = this.db.get(LevelDBKey.CHUNK_FINALIZED_STATE.createKey(chunkX, chunkZ, dimensionInfo));
        if (chunkFinalizedState != null && Unpooled.wrappedBuffer(chunkFinalizedState).readByte() != VanillaChunkState.DONE.ordinal()) {
            // Older versions didn't have CHUNK_FINALIZED_STATE data, so we still load this chunk
            return builder.build().toSafeChunk();
        }

        var chunkStateBytes = this.db.get(LevelDBKey.ALLAY_CHUNK_STATE.createKey(chunkX, chunkZ, dimensionInfo));
        if (chunkStateBytes != null) {
            var chunkStateName = new String(chunkStateBytes);
            try {
                builder.state(ChunkState.valueOf(chunkStateName));
            } catch (IllegalArgumentException e) {
                log.warn("Unknown chunk state {} for chunk at ({}, {})!", chunkStateName, chunkX, chunkZ);
                builder.state(ChunkState.FULL);
            }
        } else {
            builder.state(ChunkState.FULL);
        }

        deserializeSections(this.db, builder);
        deserializeHeightAndBiome(this.db, builder);
        deserializeBlockEntities(this.db, builder);
        deserializeScheduledUpdates(this.db, builder);
        return builder.build().toSafeChunk();
    }

    @Override
    public CompletableFuture<Void> writeChunk(Chunk chunk) {
        return CompletableFuture
                .runAsync(() -> writeChunkSync(chunk), Server.getInstance().getVirtualThreadPool())
                .exceptionally(t -> {
                    log.error("Failed to write chunk ({}, {})", chunk.getX(), chunk.getZ(), t);
                    return null;
                });
    }

    @Override
    public void writeChunkSync(Chunk chunk) {
        try (var writeBatch = this.db.createWriteBatch()) {
            writeBatch.put(LevelDBKey.VERSION.createKey(chunk.getX(), chunk.getZ(), chunk.getDimensionInfo()), new byte[]{(byte) CURRENT_CHUNK_VERSION});
            writeBatch.put(
                    LevelDBKey.CHUNK_FINALIZED_STATE.createKey(chunk.getX(), chunk.getZ(), chunk.getDimensionInfo()),
                    withByteBufToArray(buf -> buf.writeByte(VanillaChunkState.DONE.ordinal()))
            );
            writeBatch.put(
                    LevelDBKey.ALLAY_CHUNK_STATE.createKey(chunk.getX(), chunk.getZ(), chunk.getDimensionInfo()),
                    chunk.getState().name().getBytes()
            );
            chunk.applyOperation(c -> {
                var allayUnsafeChunk = (AllayUnsafeChunk) c;
                serializeSections(writeBatch, allayUnsafeChunk);
                serializeHeightAndBiome(writeBatch, allayUnsafeChunk);
                serializeBlockEntities(writeBatch, allayUnsafeChunk);
                serializeScheduledUpdates(writeBatch, allayUnsafeChunk, world);
            }, OperationType.READ, OperationType.READ);
            this.db.write(writeBatch);
        } catch (IOException e) {
            throw new WorldStorageException(e);
        }
    }

    @Override
    public CompletableFuture<Map<Long, Entity>> readEntities(int chunkX, int chunkZ, DimensionInfo dimensionInfo) {
        return CompletableFuture
                .supplyAsync(() -> readEntitiesSync(chunkX, chunkZ, dimensionInfo), Server.getInstance().getVirtualThreadPool())
                .exceptionally(t -> {
                    log.error("Failed to read entities in chunk ({}, {})", chunkX, chunkZ, t);
                    return Collections.emptyMap();
                });
    }

    @Override
    public Map<Long, Entity> readEntitiesSync(int chunkX, int chunkZ, DimensionInfo dimensionInfo) {
        var ids = this.db.get(LevelDBKey.createEntityIdsKey(chunkX, chunkZ, dimensionInfo));
        if (ids == null) {
            // Try to load entities through the old method
            return readEntitiesOldSync(chunkX, chunkZ, dimensionInfo);
        }

        var map = new Long2ObjectOpenHashMap<Entity>();
        var idsBuf = Unpooled.wrappedBuffer(ids);
        for (var i = 0; i < ids.length; i += Long.BYTES) {
            var id = idsBuf.readLongLE();
            var nbt = this.db.get(LevelDBKey.indexEntity(id));
            if (nbt == null) {
                log.error("NBT data for existing entity unique id {} is missing!", id);
                continue;
            }

            var entity = NBTIO.getAPI().fromEntityNBT(world.getDimension(dimensionInfo.dimensionId()), AllayNBTUtils.bytesToNbtLE(nbt));
            if (entity == null) {
                log.error("Failed to load entity from NBT {} in chunk ({}, {})", nbt, chunkX, chunkZ);
                continue;
            }

            map.put(entity.getUniqueId(), entity);
        }

        return map;
    }

    protected Map<Long, Entity> readEntitiesOldSync(int chunkX, int chunkZ, DimensionInfo dimensionInfo) {
        var entityBytes = db.get(LevelDBKey.ENTITIES.createKey(chunkX, chunkZ, dimensionInfo));
        if (entityBytes == null) {
            return Collections.emptyMap();
        }

        var map = new Long2ObjectOpenHashMap<Entity>();
        for (var nbt : AllayNBTUtils.bytesToNbtListLE(entityBytes)) {
            var entity = NBTIO.getAPI().fromEntityNBT(world.getDimension(dimensionInfo.dimensionId()), nbt);
            if (entity == null) {
                log.error("Failed to load entity from NBT {} in chunk ({}, {})", nbt, chunkX, chunkZ);
                continue;
            }

            map.put(entity.getUniqueId(), entity);
        }

        return map;
    }

    @Override
    public CompletableFuture<Void> writeEntities(int chunkX, int chunkZ, DimensionInfo dimensionInfo, Map<Long, Entity> entities) {
        return writeEntities0(chunkX, chunkZ, dimensionInfo, entities, true);
    }

    @Override
    public void writeEntitiesSync(int chunkX, int chunkZ, DimensionInfo dimensionInfo, Map<Long, Entity> entities) {
        writeEntities0(chunkX, chunkZ, dimensionInfo, entities, false);
    }

    protected CompletableFuture<Void> writeEntities0(int chunkX, int chunkZ, DimensionInfo dimensionInfo, Map<Long, Entity> entities, boolean asyncWrite) {
        var idsBuf = ByteBufAllocator.DEFAULT.buffer();
        try (var writeBatch = this.db.createWriteBatch()) {
            var idsKey = LevelDBKey.createEntityIdsKey(chunkX, chunkZ, dimensionInfo);

            // Delete the old entities in this chunk
            var oldIds = this.db.get(idsKey);
            if (oldIds != null) {
                var oldIdsBuf = Unpooled.wrappedBuffer(oldIds);
                for (var i = 0; i < oldIds.length; i += Long.BYTES) {
                    writeBatch.delete(LevelDBKey.indexEntity(oldIdsBuf.readLongLE()));
                }
            }

            // Write the new entities
            for (var entry : entities.entrySet()) {
                var entity = entry.getValue();
                if (!entity.willBeSaved()) {
                    continue;
                }

                idsBuf.writeLongLE(entry.getKey());
                writeBatch.put(LevelDBKey.indexEntity(entry.getKey()), AllayNBTUtils.nbtToBytesLE(entity.saveNBT()));
            }

            writeBatch.put(idsKey, ByteBufUtil.getBytes(idsBuf));
            return handleEntitiesWriteBatch(chunkX, chunkZ, writeBatch, asyncWrite);
        } catch (IOException e) {
            throw new WorldStorageException(e);
        } finally {
            idsBuf.release();
        }
    }

    protected CompletableFuture<Void> handleEntitiesWriteBatch(int chunkX, int chunkZ, WriteBatch writeBatch, boolean asyncWrite) {
        if (asyncWrite) {
            return CompletableFuture
                    .runAsync(() -> this.db.write(writeBatch), Server.getInstance().getVirtualThreadPool())
                    .exceptionally(t -> {
                        log.error("Failed to write entities in chunk ({}, {})", chunkX, chunkZ, t);
                        return null;
                    });
        } else {
            this.db.write(writeBatch);
            return null;
        }
    }

    @Override
    public boolean containChunk(int x, int z, DimensionInfo dimensionInfo) {
        for (int ySection = dimensionInfo.minSectionY(); ySection <= dimensionInfo.maxSectionY(); ySection++) {
            var bytes = db.get(LevelDBKey.CHUNK_SECTION_PREFIX.createKey(x, z, ySection, dimensionInfo));
            if (bytes != null) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void writeWorldData(WorldData worldData) {
        var allayWorldData = (AllayWorldData) worldData;
        var levelDat = path.resolve(FILE_LEVEL_DAT).toFile();
        try (var output = new FileOutputStream(levelDat);
             var byteArrayOutputStream = new ByteArrayOutputStream();
             var nbtOutputStream = NbtUtils.createWriterLE(byteArrayOutputStream)) {
            if (levelDat.exists()) {
                Files.copy(path.resolve(FILE_LEVEL_DAT), path.resolve(FILE_LEVEL_DAT_OLD), StandardCopyOption.REPLACE_EXISTING);
            }

            // 1.Current version
            output.write(int2ByteArrayLE(CURRENT_STORAGE_VERSION));

            nbtOutputStream.writeTag(writeWorldDataToNBT(allayWorldData));
            var data = byteArrayOutputStream.toByteArray();

            // 2.Data length
            output.write(int2ByteArrayLE(data.length));

            // 3.Data
            output.write(data);
        } catch (IOException e) {
            throw new WorldStorageException(e);
        }
    }

    @Override
    public WorldData readWorldData() {
        var levelDat = path.resolve(FILE_LEVEL_DAT).toFile();
        if (!levelDat.exists()) {
            return createWorldData(worldName);
        }

        try (var input = new FileInputStream(levelDat)) {
            // current_version + data length
            // noinspection ResultOfMethodCallIgnored
            input.skip(8);
            try (NBTInputStream readerLE = NbtUtils.createReaderLE(new ByteArrayInputStream(input.readAllBytes()))) {
                NbtMap nbt = (NbtMap) readerLE.readTag();
                return readWorldDataFromNBT(nbt);
            }
        } catch (IOException e) {
            throw new WorldStorageException(e);
        }
    }

    @Override
    public String getName() {
        return "LEVELDB";
    }

    private WorldData createWorldData(String worldName) {
        var levelDat = path.resolve(FILE_LEVEL_DAT).toFile();
        try {
            // noinspection ResultOfMethodCallIgnored
            levelDat.createNewFile();
            var worldData = AllayWorldData
                    .builder()
                    .displayName(worldName)
                    .build();
            writeWorldData(worldData);
            Files.copy(levelDat.toPath(), path.resolve(FILE_LEVEL_DAT_OLD), StandardCopyOption.REPLACE_EXISTING);
            return worldData;
        } catch (IOException e) {
            throw new WorldStorageException(e);
        }
    }

    private NbtMap writeWorldDataToNBT(AllayWorldData worldData) {
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

    @Override
    public void shutdown() {
        try {
            this.db.close();
        } catch (IOException e) {
            throw new WorldStorageException(e);
        }
    }

    private void deserializeBlockEntities(DB db, AllayChunkBuilder builder) {
        byte[] tileBytes = db.get(LevelDBKey.BLOCK_ENTITIES.createKey(builder.getChunkX(), builder.getChunkZ(), builder.getDimensionInfo()));
        if (tileBytes == null) {
            return;
        }

        var blockEntities = new NonBlockingHashMap<Integer, BlockEntity>();
        for (var nbt : AllayNBTUtils.bytesToNbtListLE(tileBytes)) {
            BlockEntity blockEntity;
            try {
                blockEntity = NBTIO.getAPI().fromBlockEntityNBT(world.getDimension(builder.getDimensionInfo().dimensionId()), nbt);
            } catch (Throwable t) {
                log.error("Error while loading block entity from NBT", t);
                continue;
            }

            if (blockEntity == null) {
                // blockEntity will be null if the entity type is unknown
                continue;
            }

            var position = blockEntity.getPosition();
            var key = HashUtils.hashChunkXYZ(position.x() & 15, position.y(), position.z() & 15);
            blockEntities.put(key, blockEntity);
        }

        builder.blockEntities(blockEntities);
    }
}
