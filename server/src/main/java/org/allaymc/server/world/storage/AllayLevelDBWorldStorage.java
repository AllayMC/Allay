package org.allaymc.server.world.storage;

import io.netty.buffer.*;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.network.ProtocolInfo;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.HashUtils;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.Difficulty;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.WorldData;
import org.allaymc.api.world.biome.BiomeId;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.chunk.ChunkState;
import org.allaymc.api.world.chunk.UnsafeChunk;
import org.allaymc.api.world.gamerule.GameRules;
import org.allaymc.api.world.storage.WorldStorage;
import org.allaymc.api.world.storage.WorldStorageException;
import org.allaymc.server.world.AllayWorldData;
import org.allaymc.server.world.HeightMap;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.allaymc.server.world.chunk.ChunkSection;
import org.allaymc.server.world.palette.Palette;
import org.allaymc.server.world.palette.PaletteException;
import org.allaymc.server.world.palette.PaletteUtils;
import org.allaymc.updater.block.BlockStateUpdaters;
import org.cloudburstmc.nbt.*;
import org.cloudburstmc.nbt.util.stream.LittleEndianDataInputStream;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.iq80.leveldb.CompressionType;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.WriteBatch;
import org.iq80.leveldb.impl.Iq80DBFactory;
import org.joml.Vector3i;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * Special thanks to PMMP team.
 *
 * @author Cool_Loong | daoge_cmd
 */
@Slf4j
public class AllayLevelDBWorldStorage implements WorldStorage {

    private static final String FILE_LEVEL_DAT = "level.dat";
    private static final String FILE_LEVEL_DAT_OLD = "level.dat_old";

    private static final String DIR_DB = "db";

    private static final int STORAGE_VERSION = 10;

    private static final int CHUNK_VERSION = 41;

    private static final int VANILLA_CHUNK_STATE_NEEDS_INSTA_TICK = 0;
    private static final int VANILLA_CHUNK_STATE_NEEDS_POPULATION = 1;
    private static final int VANILLA_CHUNK_STATE_DONE = 2;

    private static final String TAG_DIFFICULTY = "Difficulty";
    private static final String TAG_GAME_TYPE = "GameType";
    private static final String TAG_DISPLAY_NAME = "LevelName";
    private static final String TAG_SPAWN_X = "SpawnX";
    private static final String TAG_SPAWN_Y = "SpawnY";
    private static final String TAG_SPAWN_Z = "SpawnZ";
    private static final String TAG_TOTAL_TIME = "Time";
    private static final String TAG_TIME_OF_DAY = "TimeOfDay";
    private static final String TAG_WORLD_START_COUNT = "WorldStartCount";

    // The following keys are only used in this class.
    // Some of them are written to make the vanilla client
    // load the world correctly, they are not used in other places
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

    private final Path path;
    private final String worldName;
    private final DB db;

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

    @Override
    public CompletableFuture<Chunk> readChunk(int x, int z, DimensionInfo dimensionInfo) {
        return CompletableFuture
                .supplyAsync(() -> readChunkSync(x, z, dimensionInfo), Server.getInstance().getVirtualThreadPool())
                .exceptionally(e -> {
                    log.error("Failed to read chunk {}, {}", x, z, e);
                    return AllayUnsafeChunk.builder().newChunk(x, z, dimensionInfo).toSafeChunk();
                });
    }

    @Override
    public Chunk readChunkSync(int x, int z, DimensionInfo dimensionInfo) {
        var builder = AllayUnsafeChunk.builder()
                .chunkX(x)
                .chunkZ(z)
                .dimensionInfo(dimensionInfo)
                .state(ChunkState.NEW);
        if (!containChunk(x, z, dimensionInfo)) {
            return builder.build().toSafeChunk();
        }

        var versionValue = this.db.get(LevelDBKey.VERSION.getKey(x, z, dimensionInfo));
        if (versionValue == null) {
            versionValue = this.db.get(LevelDBKey.LEGACY_VERSION.getKey(x, z, dimensionInfo));
        }
        if (versionValue == null) {
            // This might be a slightly-corrupted chunk with a missing version field
            // However we can still try to load it
            log.warn("Chunk at {}, {} is missing version field", x, z);
        }

        var chunkState = this.db.get(LevelDBKey.CHUNK_FINALIZED_STATE.getKey(x, z, dimensionInfo));
        if (chunkState != null && Unpooled.wrappedBuffer(chunkState).readByte() != VANILLA_CHUNK_STATE_DONE) {
            // Older versions didn't have CHUNK_FINALIZED_STATE data, so we still load this chunk
            // TODO: check VANILLA_CHUNK_STATE_NEEDS_INSTA_TICK
            return builder.build().toSafeChunk();
        }

        builder.state(ChunkState.FINISHED);
        deserializeBlock(this.db, builder);
        deserializeHeightAndBiome(this.db, builder);
        deserializeEntityAndBlockEntity(this.db, builder);
        return builder.build().toSafeChunk();
    }

    @Override
    public CompletableFuture<Void> writeChunk(Chunk chunk) {
        return CompletableFuture
                .runAsync(() -> writeChunkSync(chunk), Server.getInstance().getVirtualThreadPool())
                .exceptionally(e -> {
                    log.error("Failed to write chunk {}, {}", chunk.getX(), chunk.getZ(), e);
                    return null;
                });
    }

    @Override
    public void writeChunkSync(Chunk chunk) {
        if (chunk.getState() != ChunkState.FINISHED) {
            log.warn("Cannot save unfinished chunk at {}, {}", chunk.getX(), chunk.getZ());
            return;
        }
        try (var writeBatch = this.db.createWriteBatch()) {
            writeBatch.put(LevelDBKey.VERSION.getKey(chunk.getX(), chunk.getZ(), chunk.getDimensionInfo()), new byte[]{CHUNK_VERSION});
            writeBatch.put(
                    LevelDBKey.CHUNK_FINALIZED_STATE.getKey(chunk.getX(), chunk.getZ(), chunk.getDimensionInfo()),
                    Unpooled.buffer(1)
                            .writeByte(VANILLA_CHUNK_STATE_DONE)
                            .array()
            );
            chunk.batchProcess(c -> {
                serializeBlock(writeBatch, (AllayUnsafeChunk) c);
                serializeHeightAndBiome(writeBatch, (AllayUnsafeChunk) c);
                serializeEntityAndBlockEntity(writeBatch, (AllayUnsafeChunk) c);
            });
            this.db.write(writeBatch);
        } catch (IOException e) {
            throw new WorldStorageException(e);
        }
    }

    @Override
    public boolean containChunk(int x, int z, DimensionInfo dimensionInfo) {
        for (int ySection = dimensionInfo.minSectionY(); ySection <= dimensionInfo.maxSectionY(); ySection++) {
            var bytes = db.get(LevelDBKey.CHUNK_SECTION_PREFIX.getKey(x, z, ySection, dimensionInfo));
            if (bytes != null) return true;
        }
        return false;
    }

    @Override
    public void writeWorldData(WorldData worldData) {
        if (!(worldData instanceof AllayWorldData allayWorldData)) {
            log.error("");
            return;
        }
        var levelDat = path.resolve(FILE_LEVEL_DAT).toFile();
        try (var output = new FileOutputStream(levelDat)) {
            if (levelDat.exists()) {
                Files.copy(path.resolve(FILE_LEVEL_DAT), path.resolve(FILE_LEVEL_DAT_OLD), StandardCopyOption.REPLACE_EXISTING);
            }

            // 1.Current version
            output.write(int2ByteArrayLE(STORAGE_VERSION));

            var byteArrayOutputStream = new ByteArrayOutputStream();
            var nbtOutputStream = NbtUtils.createWriterLE(byteArrayOutputStream);
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
        if (!levelDat.exists()) return createWorldData(worldName);
        try (var input = new FileInputStream(levelDat)) {
            // current_version + data length
            // noinspection ResultOfMethodCallIgnored
            input.skip(8);
            NBTInputStream readerLE = NbtUtils.createReaderLE(new ByteArrayInputStream(input.readAllBytes()));
            NbtMap nbt = (NbtMap) readerLE.readTag();
            readerLE.close();
            return readWorldDataFromNBT(nbt);
        } catch (IOException e) {
            throw new WorldStorageException(e);
        }
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

    private static AllayWorldData readWorldDataFromNBT(NbtMap nbt) {
        var storageVersion = nbt.getInt(TAG_STORAGE_VERSION, Integer.MAX_VALUE);
        if (storageVersion == Integer.MAX_VALUE) {
            log.warn("Missing " + TAG_STORAGE_VERSION + " field in " + FILE_LEVEL_DAT);
            storageVersion = STORAGE_VERSION;
        }
        if (storageVersion > STORAGE_VERSION) {
            throw new WorldStorageException("LevelDB world storage version " + storageVersion + " is currently unsupported");
        }

        var networkVersion = nbt.getInt(TAG_NETWORK_VERSION, Integer.MAX_VALUE);
        if (networkVersion == Integer.MAX_VALUE) {
            log.warn("Missing " + TAG_NETWORK_VERSION + " field in " + FILE_LEVEL_DAT);
            networkVersion = ProtocolInfo.PACKET_CODEC.getProtocolVersion();
        }
        if (networkVersion > ProtocolInfo.PACKET_CODEC.getProtocolVersion()) {
            throw new WorldStorageException("LevelDB world storage network version " + networkVersion + " is currently unsupported");
        }

        return AllayWorldData.builder()
                .difficulty(Difficulty.from(nbt.getInt(TAG_DIFFICULTY, Server.SETTINGS.genericSettings().defaultDifficulty().ordinal())))
                .gameType(GameType.from(nbt.getInt(TAG_GAME_TYPE, Server.SETTINGS.genericSettings().defaultGameType().ordinal())))
                .displayName(nbt.getString(TAG_DISPLAY_NAME, WorldData.DEFAULT_WORLD_DISPLAY_NAME))
                .spawnPoint(new Vector3i(nbt.getInt(TAG_SPAWN_X, 0), nbt.getInt(TAG_SPAWN_Y, 64), nbt.getInt(TAG_SPAWN_Z, 0)))
                .totalTime(nbt.getLong(TAG_TOTAL_TIME, 0))
                .timeOfDay(nbt.getInt(TAG_TIME_OF_DAY, WorldData.TIME_SUNRISE))
                .worldStartCount(nbt.getLong(TAG_WORLD_START_COUNT, 0))
                .gameRules(GameRules.readFromNBT(nbt))
                .build();
    }

    private static NbtMap writeWorldDataToNBT(AllayWorldData worldData) {
        var builder = NbtMap.builder();

        builder.putInt(TAG_DIFFICULTY, worldData.getDifficulty().ordinal());
        builder.putInt(TAG_GAME_TYPE, worldData.getGameType().ordinal());
        builder.putString(TAG_DISPLAY_NAME, worldData.getDisplayName());
        builder.putInt(TAG_SPAWN_X, worldData.getSpawnPoint().x());
        builder.putInt(TAG_SPAWN_Y, worldData.getSpawnPoint().y());
        builder.putInt(TAG_SPAWN_Z, worldData.getSpawnPoint().z());
        builder.putLong(TAG_TOTAL_TIME, worldData.getTotalTime());
        builder.putInt(TAG_TIME_OF_DAY, worldData.getTimeOfDay());
        builder.putLong(TAG_WORLD_START_COUNT, worldData.getWorldStartCount());

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
        builder.putInt(TAG_STORAGE_VERSION, STORAGE_VERSION);
        // StorageVersion is rarely updated. Instead, the game relies on the NetworkVersion tag,
        // which is synced with the network protocol version for that version
        builder.putInt(TAG_NETWORK_VERSION, ProtocolInfo.PACKET_CODEC.getProtocolVersion());
        builder.putLong(TAG_LAST_PLAYED, System.currentTimeMillis() / 1000);
        // Badly named, this actually determines whether achievements can be earned in this world...
        builder.putByte(TAG_ACHIEVEMENTS_DISABLED, (byte) 1);
        builder.putByte(TAG_COMMANDS_ENABLED, (byte) 1);
        builder.putList(TAG_LAST_OPENED_WITH_VERSION, NbtType.INT, ProtocolInfo.MINECRAFT_VERSION.toBoxedArray());
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

    private static void serializeBlock(WriteBatch writeBatch, AllayUnsafeChunk chunk) {
        for (int ySection = chunk.getDimensionInfo().minSectionY(); ySection <= chunk.getDimensionInfo().maxSectionY(); ySection++) {
            ChunkSection section = chunk.getSection(ySection);
            ByteBuf buffer = ByteBufAllocator.DEFAULT.ioBuffer();
            try {
                buffer.writeByte(ChunkSection.CHUNK_SECTION_VERSION);
                buffer.writeByte(ChunkSection.LAYER_COUNT);
                buffer.writeByte(ySection);
                for (int i = 0; i < ChunkSection.LAYER_COUNT; i++) {
                    section.blockLayers()[i].writeToStoragePersistent(buffer, BlockState::getBlockStateTag);
                }
                writeBatch.put(LevelDBKey.CHUNK_SECTION_PREFIX.getKey(chunk.getX(), chunk.getZ(), ySection, chunk.getDimensionInfo()), Utils.convertByteBuf2Array(buffer));
            } finally {
                buffer.release();
            }
        }
    }

    private static void deserializeBlock(DB db, AllayUnsafeChunk.Builder builder) {
        DimensionInfo dimensionInfo = builder.getDimensionInfo();
        ChunkSection[] sections = new ChunkSection[dimensionInfo.chunkSectionCount()];
        var minSectionY = dimensionInfo.minSectionY();
        for (int ySection = minSectionY; ySection <= dimensionInfo.maxSectionY(); ySection++) {
            byte[] bytes = db.get(LevelDBKey.CHUNK_SECTION_PREFIX.getKey(builder.getChunkX(), builder.getChunkZ(), ySection, dimensionInfo));
            if (bytes == null) continue;
            ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer(bytes.length);
            try {
                byteBuf.writeBytes(bytes);
                byte subChunkVersion = byteBuf.readByte();
                int layers = 2;
                switch (subChunkVersion) {
                    case 9, 8:
                        // Layers
                        layers = byteBuf.readByte();
                        if (subChunkVersion == 9) {
                            // Extra section y value in version 9
                            byteBuf.readByte();
                        }
                    case 1:
                        ChunkSection section;
                        if (layers <= ChunkSection.LAYER_COUNT) {
                            // This is the normal situation where the chunk section is loaded correctly,
                            // and we use the single-arg constructor of ChunkSection directly to avoid
                            // using Arrays.fill(), which will be slower
                            section = new ChunkSection((byte) ySection);
                        } else {
                            // Currently only two layers are used in minecraft, so that might mean this chunk is corrupted
                            // However we can still load it c:
                            log.warn("Loading chunk section ({}, {}, {}) with {} layers, which might mean that this chunk is corrupted!", builder.getChunkX(), ySection, builder.getChunkZ(), layers);
                            @SuppressWarnings("rawtypes") Palette[] palettes = new Palette[layers];
                            Arrays.fill(palettes, new Palette<>(BlockTypes.AIR.getDefaultState()));
                            section = new ChunkSection((byte) ySection, palettes);
                        }
                        for (int layer = 0; layer < layers; layer++) {
                            section.blockLayers()[layer].readFromStoragePersistent(byteBuf, AllayLevelDBWorldStorage::fastBlockStateDeserializer);
                        }
                        sections[ySection - minSectionY] = section;
                        break;
                }
            } finally {
                byteBuf.release();
            }
        }
        builder.sections(fillNullSections(sections, dimensionInfo));
    }

    private static BlockState fastBlockStateDeserializer(ByteBuf buffer) {
        // Get block state hash
        int blockStateHash;
        try (var bufInputStream = new ByteBufInputStream(buffer);
             var input = new LittleEndianDataInputStream(bufInputStream);
             var nbtInputStream = new NBTInputStream(input)) {
            blockStateHash = PaletteUtils.fastReadBlockStateHash(input, buffer);
            if (blockStateHash == PaletteUtils.HASH_NOT_LATEST) {
                var oldNbtMap = (NbtMap) nbtInputStream.readTag();
                var newNbtMap = BlockStateUpdaters.updateBlockState(oldNbtMap, BlockStateUpdaters.LATEST_VERSION);
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

        // Get block state by hash
        BlockState blockState = Registries.BLOCK_STATE_PALETTE.get(blockStateHash);
        if (blockState != null) {
            return blockState;
        }

        log.error("Find unknown block state hash {} while loading chunk section", blockStateHash);
        return BlockTypes.UNKNOWN.getDefaultState();
    }

    private static ChunkSection[] fillNullSections(ChunkSection[] sections, DimensionInfo dimensionInfo) {
        for (int i = 0; i < sections.length; i++) {
            if (sections[i] == null) {
                sections[i] = new ChunkSection((byte) (i + dimensionInfo.minSectionY()));
            }
        }
        return sections;
    }

    /*
     * Bedrock Edition 3d-data saves the height map starting from index 0, so adjustments are made here to accommodate the world's minimum height. For details, see:
     * See https://github.com/bedrock-dev/bedrock-level/blob/main/src/include/data_3d.h#L115
     */

    private static void serializeHeightAndBiome(WriteBatch writeBatch, AllayUnsafeChunk chunk) {
        ByteBuf heightAndBiomesBuffer = ByteBufAllocator.DEFAULT.ioBuffer();
        try {
            // Serialize height map
            for (short height : chunk.getHeightMap().getHeights()) {
                heightAndBiomesBuffer.writeShortLE(height - chunk.getDimensionInfo().minHeight());
            }
            // Serialize biome
            Palette<BiomeType> lastPalette = null;
            for (int y = chunk.getDimensionInfo().minSectionY(); y <= chunk.getDimensionInfo().maxSectionY(); y++) {
                ChunkSection section = chunk.getSection(y);
                section.biomes().writeToStorageRuntime(heightAndBiomesBuffer, BiomeType::getId, lastPalette);
                lastPalette = section.biomes();
            }
            writeBatch.put(LevelDBKey.DATA_3D.getKey(chunk.getX(), chunk.getZ(), chunk.getDimensionInfo()), Utils.convertByteBuf2Array(heightAndBiomesBuffer));
        } finally {
            heightAndBiomesBuffer.release();
        }
    }

    private static void deserializeHeightAndBiome(DB db, AllayUnsafeChunk.Builder builder) {
        ByteBuf heightAndBiomesBuffer = null;
        try {
            // Try load data_3d
            byte[] bytes = db.get(LevelDBKey.DATA_3D.getKey(builder.getChunkX(), builder.getChunkZ(), builder.getDimensionInfo()));
            if (bytes != null) {
                // Height map
                heightAndBiomesBuffer = Unpooled.wrappedBuffer(bytes);
                short[] heights = new short[256];
                for (int i = 0; i < 256; i++) {
                    heights[i] = (short) (heightAndBiomesBuffer.readUnsignedShortLE() + builder.getDimensionInfo().minHeight());
                }
                builder.heightMap(new HeightMap(heights));

                // Biomes
                Palette<BiomeType> lastPalette = null;
                var minSectionY = builder.getDimensionInfo().minSectionY();
                for (int y = minSectionY; y <= builder.getDimensionInfo().maxSectionY(); y++) {
                    ChunkSection section = builder.getSections()[y - minSectionY];
                    if (section == null) continue;
                    section.biomes().readFromStorageRuntime(heightAndBiomesBuffer, AllayLevelDBWorldStorage::getBiomeByIdNonNull, lastPalette);
                    lastPalette = section.biomes();
                }

                return;
            }

            // Try load data_2d if data_3d is not found
            byte[] bytes2D = db.get(LevelDBKey.DATA_2D.getKey(builder.getChunkX(), builder.getChunkZ(), builder.getDimensionInfo()));
            if (bytes2D == null) return;
            heightAndBiomesBuffer = Unpooled.wrappedBuffer(bytes2D);
            short[] heights = new short[256];
            for (int i = 0; i < 256; i++) {
                heights[i] = heightAndBiomesBuffer.readShortLE();
            }
            builder.heightMap(new HeightMap(heights));
            byte[] biomes = new byte[256];
            heightAndBiomesBuffer.readBytes(biomes);

            var minSectionY = builder.getDimensionInfo().minSectionY();
            for (int y = minSectionY; y <= builder.getDimensionInfo().maxSectionY(); y++) {
                ChunkSection section = builder.getSections()[y - minSectionY];
                if (section == null) continue;
                final Palette<BiomeType> biomePalette = section.biomes();
                for (int x = 0; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        for (int sy = 0; sy < 16; sy++) {
                            biomePalette.set(UnsafeChunk.index(x, sy, z), getBiomeByIdNonNull(biomes[x + 16 * z]));
                        }
                    }
                }
            }
        } finally {
            if (heightAndBiomesBuffer != null) {
                heightAndBiomesBuffer.release();
            }
        }
    }

    private static void serializeEntityAndBlockEntity(WriteBatch writeBatch, AllayUnsafeChunk chunk) {
        // Write blockEntities
        Collection<BlockEntity> blockEntities = chunk.getBlockEntities().values();
        ByteBuf tileBuffer = ByteBufAllocator.DEFAULT.ioBuffer();
        try (var bufStream = new ByteBufOutputStream(tileBuffer)) {
            byte[] key = LevelDBKey.BLOCK_ENTITIES.getKey(chunk.getX(), chunk.getZ(), chunk.getDimensionInfo());
            if (blockEntities.isEmpty()) {
                writeBatch.delete(key);
            } else {
                for (BlockEntity blockEntity : blockEntities) {
                    NBTOutputStream writerLE = NbtUtils.createWriterLE(bufStream);
                    writerLE.writeTag(blockEntity.saveNBT());
                }
                writeBatch.put(key, Utils.convertByteBuf2Array(tileBuffer));
            }
        } catch (IOException e) {
            throw new WorldStorageException(e);
        } finally {
            tileBuffer.release();
        }

        // Write entities
        Collection<Entity> entities = chunk.getEntities().values();
        ByteBuf entityBuffer = ByteBufAllocator.DEFAULT.ioBuffer();
        try (var bufStream = new ByteBufOutputStream(entityBuffer)) {
            byte[] key = LevelDBKey.ENTITIES.getKey(chunk.getX(), chunk.getZ(), chunk.getDimensionInfo());
            if (entities.isEmpty()) {
                writeBatch.delete(key);
                return;
            }
            for (Entity e : entities) {
                // Player entity won't be saved to chunk
                // As we will save player data through player storage
                if (e instanceof EntityPlayer) continue;
                NBTOutputStream writerLE = NbtUtils.createWriterLE(bufStream);
                writerLE.writeTag(e.saveNBT());
            }
            writeBatch.put(key, Utils.convertByteBuf2Array(entityBuffer));
        } catch (IOException e) {
            throw new WorldStorageException(e);
        } finally {
            entityBuffer.release();
        }
    }

    private static void deserializeEntityAndBlockEntity(DB db, AllayUnsafeChunk.Builder builder) {
        DimensionInfo dimensionInfo = builder.getDimensionInfo();
        byte[] tileBytes = db.get(LevelDBKey.BLOCK_ENTITIES.getKey(builder.getChunkX(), builder.getChunkZ(), dimensionInfo));
        if (tileBytes != null) {
            builder.blockEntities(deserializeNbtTagsFromBytes(tileBytes));
        }
        byte[] key = LevelDBKey.ENTITIES.getKey(builder.getChunkX(), builder.getChunkZ(), dimensionInfo);
        byte[] entityBytes = db.get(key);
        if (entityBytes != null) {
            builder.entities(deserializeNbtTagsFromBytes(entityBytes));
        }
    }

    private static List<NbtMap> deserializeNbtTagsFromBytes(byte[] bytes) {
        List<NbtMap> tags = new ArrayList<>();
        try (BufferedInputStream stream = new BufferedInputStream(new ByteArrayInputStream(bytes))) {
            while (stream.available() > 0) {
                tags.add((NbtMap) NbtUtils.createReaderLE(stream).readTag());
            }
        } catch (IOException e) {
            throw new WorldStorageException(e);
        }
        return tags;
    }

    private static BiomeType getBiomeByIdNonNull(int id) {
        try {
            return BiomeId.fromId(id);
        } catch (ArrayIndexOutOfBoundsException e) {
            log.warn("Unknown biome id: {}", id);
            return BiomeId.PLAINS;
        }
    }

    private static byte[] int2ByteArrayLE(int value) {
        return ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(value).array();
    }
}
