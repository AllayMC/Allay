package org.allaymc.server.world.storage.leveldb;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.palette.BlockStateHashPalette;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.data.VanillaBiomeId;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.chunk.ChunkSection;
import org.allaymc.api.world.chunk.UnsafeChunk;
import org.allaymc.api.world.heightmap.HeightMap;
import org.allaymc.api.world.palette.Palette;
import org.allaymc.server.utils.LevelDBKeyUtils;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.cloudburstmc.nbt.NBTOutputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.WriteBatch;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.allaymc.api.block.type.BlockTypes.AIR_TYPE;
import static org.allaymc.api.block.type.BlockTypes.UNKNOWN_TYPE;

/**
 * Allay Project 8/23/2023
 *
 * @author Cool_Loong
 */
@Slf4j
public class LevelDBChunkSerializer {
    public static final LevelDBChunkSerializer INSTANCE = new LevelDBChunkSerializer();

    private LevelDBChunkSerializer() {
    }

    public void serialize(WriteBatch writeBatch, UnsafeChunk chunk) {
        serializeBlock(writeBatch, chunk);
        writeBatch.put(LevelDBKeyUtils.VERSION.getKey(chunk.getX(), chunk.getZ(), chunk.getDimensionInfo()), new byte[]{UnsafeChunk.CHUNK_VERSION});
        serializeHeightAndBiome(writeBatch, chunk);
        serializeTileAndEntity(writeBatch,chunk);
    }

    public void deserialize(DB db, AllayUnsafeChunk.Builder builder) {
        deserializeBlock(db, builder);
        deserializeHeightAndBiome(db, builder);
        deserializeTileAndEntity(db,builder);
    }

    //serialize chunk section
    private void serializeBlock(WriteBatch writeBatch, UnsafeChunk chunk) {
        for (int ySection = chunk.getDimensionInfo().minSectionY(); ySection <= chunk.getDimensionInfo().maxSectionY(); ySection++) {
            ChunkSection section = chunk.getSection(ySection);
            if (section == null) {
                continue;
            }
            ByteBuf buffer = ByteBufAllocator.DEFAULT.ioBuffer();
            try {
                buffer.writeByte(ChunkSection.VERSION);
                buffer.writeByte(ChunkSection.LAYER_COUNT);
                buffer.writeByte(ySection);
                for (int i = 0; i < ChunkSection.LAYER_COUNT; i++) {
                    section.blockLayer()[i].writeToStoragePersistent(buffer, BlockState::getBlockStateTag);
                }
                writeBatch.put(LevelDBKeyUtils.CHUNK_SECTION_PREFIX.getKey(chunk.getX(), chunk.getZ(), ySection, chunk.getDimensionInfo()), Utils.convertByteBuf2Array(buffer));
            } finally {
                buffer.release();
            }
        }
    }

    //serialize chunk section
    private void deserializeBlock(DB db, AllayUnsafeChunk.Builder builder) {
        DimensionInfo dimensionInfo = builder.getDimensionInfo();
        ChunkSection[] sections = new ChunkSection[dimensionInfo.chunkSectionSize()];
        var minSectionY = dimensionInfo.minSectionY();
        for (int ySection = minSectionY; ySection <= dimensionInfo.maxSectionY(); ySection++) {
            byte[] bytes = db.get(LevelDBKeyUtils.CHUNK_SECTION_PREFIX.getKey(builder.getChunkX(), builder.getChunkZ(), ySection, dimensionInfo));
            if (bytes == null) continue;
            ByteBuf byteBuf = null;
            try {
                byteBuf = ByteBufAllocator.DEFAULT.ioBuffer(bytes.length);
                byteBuf.writeBytes(bytes);
                byte subChunkVersion = byteBuf.readByte();
                int layers = 2;
                switch (subChunkVersion) {
                    case 9, 8:
                        layers = byteBuf.readByte();//layers
                        if (subChunkVersion == 9) {
                            byteBuf.readByte();//sectionY not use
                        }
                    case 1:
                        ChunkSection section;
                        if (layers <= 2) {
                            section = new ChunkSection((byte) ySection);
                        } else {
                            @SuppressWarnings("rawtypes") Palette[] palettes = new Palette[layers];
                            Arrays.fill(palettes, new Palette<>(AIR_TYPE.getDefaultState()));
                            section = new ChunkSection((byte) ySection, palettes);
                        }
                        for (int layer = 0; layer < layers; layer++) {
                            section.blockLayer()[layer].readFromStoragePersistent(byteBuf, hash -> {
                                BlockState blockState = BlockStateHashPalette.getRegistry().get(hash);
                                if (blockState == null) {
                                    log.error("Unknown block state hash: " + hash);
                                    blockState = UNKNOWN_TYPE.getDefaultState();
                                }
                                return blockState;
                            });
                        }
                        sections[ySection - minSectionY] = section;
                        break;
                }
            } finally {
                if (byteBuf != null) {
                    byteBuf.release();
                }
            }
        }
        builder.sections(sections);
    }

    //write biomeAndHeight
    private void serializeHeightAndBiome(WriteBatch writeBatch, UnsafeChunk chunk) {
        //Write biomeAndHeight
        ByteBuf heightAndBiomesBuffer = ByteBufAllocator.DEFAULT.ioBuffer();
        try {
            for (short height : chunk.getHeightArray()) {
                heightAndBiomesBuffer.writeShortLE(height);
            }
            Palette<BiomeType> biomePalette;
            for (int y = chunk.getDimensionInfo().minSectionY(); y <= chunk.getDimensionInfo().maxSectionY(); y++) {
                ChunkSection section = chunk.getSection(y);
                if (section == null) continue;
                biomePalette = section.biomes();
                biomePalette.writeToStorageRuntime(heightAndBiomesBuffer, BiomeType::getId);
            }
            writeBatch.put(LevelDBKeyUtils.DATA_3D.getKey(chunk.getX(), chunk.getZ(), chunk.getDimensionInfo()), Utils.convertByteBuf2Array(heightAndBiomesBuffer));
        } finally {
            heightAndBiomesBuffer.release();
        }
    }

    //read biomeAndHeight
    private void deserializeHeightAndBiome(DB db, AllayUnsafeChunk.Builder builder) {
        ByteBuf heightAndBiomesBuffer = null;
        try {
            byte[] bytes = db.get(LevelDBKeyUtils.DATA_3D.getKey(builder.getChunkX(), builder.getChunkZ(), builder.getDimensionInfo()));
            if (bytes != null) {
                heightAndBiomesBuffer = Unpooled.wrappedBuffer(bytes);
                short[] heights = new short[256];
                for (int i = 0; i < 256; i++) {
                    heights[i] = heightAndBiomesBuffer.readShortLE();
                }
                builder.heightMap(new HeightMap(heights));
                Palette<BiomeType> biomePalette;
                var minSectionY = builder.getDimensionInfo().minSectionY();
                for (int y = minSectionY; y <= builder.getDimensionInfo().maxSectionY(); y++) {
                    ChunkSection section = builder.getSections()[y - minSectionY];
                    if (section == null) continue;
                    biomePalette = section.biomes();
                    biomePalette.readFromStorageRuntime(heightAndBiomesBuffer, this::getBiomeByIdNonNull);
                }
                return;
            }
            byte[] bytes2D = db.get(LevelDBKeyUtils.DATA_2D.getKey(builder.getChunkX(), builder.getChunkZ(), builder.getDimensionInfo()));
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

    private void deserializeTileAndEntity(DB db, AllayUnsafeChunk.Builder builder) {
        DimensionInfo dimensionInfo = builder.getDimensionInfo();
        byte[] tileBytes = db.get(LevelDBKeyUtils.BLOCK_ENTITIES.getKey(builder.getChunkX(), builder.getChunkZ(), dimensionInfo));
        if (tileBytes != null) {
            builder.blockEntities(deserializeNbtTagsFromBytes(tileBytes));
        }
        byte[] key = LevelDBKeyUtils.ENTITIES.getKey(builder.getChunkX(), builder.getChunkZ(), dimensionInfo);
        byte[] entityBytes = db.get(key);
        if (entityBytes != null) {
            builder.entities(deserializeNbtTagsFromBytes(entityBytes));
        }
    }

    private List<NbtMap> deserializeNbtTagsFromBytes(byte[] bytes) {
        List<NbtMap> tags = new ArrayList<>();
        try (BufferedInputStream stream = new BufferedInputStream(new ByteArrayInputStream(bytes))) {
            while (stream.available() > 0) {
                tags.add((NbtMap) NbtUtils.createReaderLE(stream).readTag());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tags;
    }

    private void serializeTileAndEntity(WriteBatch writeBatch, UnsafeChunk chunk) {
        //Write blockEntities
        Collection<BlockEntity> blockEntities = chunk.getBlockEntities().values();
        ByteBuf tileBuffer = ByteBufAllocator.DEFAULT.ioBuffer();
        try (var bufStream = new ByteBufOutputStream(tileBuffer)) {
            byte[] key = LevelDBKeyUtils.BLOCK_ENTITIES.getKey(chunk.getX(), chunk.getZ(), chunk.getDimensionInfo());
            if (blockEntities.isEmpty()) {
                writeBatch.delete(key);
                return;
            }
            for (BlockEntity blockEntity : blockEntities) {
                NBTOutputStream writerLE = NbtUtils.createWriterLE(bufStream);
                writerLE.writeTag(blockEntity.saveNBT());
            }
            writeBatch.put(key, Utils.convertByteBuf2Array(tileBuffer));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            tileBuffer.release();
        }

        Collection<Entity> entities = chunk.getEntities().values();
        ByteBuf entityBuffer = ByteBufAllocator.DEFAULT.ioBuffer();
        try (var bufStream = new ByteBufOutputStream(entityBuffer)) {
            byte[] key = LevelDBKeyUtils.ENTITIES.getKey(chunk.getX(), chunk.getZ(), chunk.getDimensionInfo());
            if (entities.isEmpty()) {
                writeBatch.delete(key);
                return;
            }
            for (Entity e : entities) {
                if (e instanceof EntityPlayer) continue;
                NBTOutputStream writerLE = NbtUtils.createWriterLE(bufStream);
                writerLE.writeTag(e.saveNBT());
            }
            writeBatch.put(key, Utils.convertByteBuf2Array(entityBuffer));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            entityBuffer.release();
        }
    }

    private BiomeType getBiomeByIdNonNull(int id) {
        var biome = VanillaBiomeId.fromId(id);
        if (biome == null) {
            log.warn("Unknown biome id: {}", id);
            biome = VanillaBiomeId.PLAINS;
        }
        return biome;
    }
}
