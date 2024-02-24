package org.allaymc.server.world.storage.leveldb;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.palette.BlockStateHashPalette;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.data.VanillaBiomeId;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.world.DimensionInfo;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.api.world.chunk.ChunkSection;
import org.allaymc.api.world.chunk.UnsafeChunk;
import org.allaymc.api.world.heightmap.HeightMap;
import org.allaymc.api.world.palette.Palette;
import org.allaymc.server.utils.LevelDBKeyUtils;
import org.allaymc.server.world.chunk.AllayUnsafeChunk;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.WriteBatch;

import java.util.Arrays;

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
        //todo Entity and Block Entity
    }

    public void deserialize(DB db, AllayUnsafeChunk.Builder builder) {
        deserializeBlock(db, builder);
        deserializeHeightAndBiome(db, builder);
        //todo Entity and Block Entity
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
            if (bytes != null) {
                ByteBuf byteBuf = null;
                try {
                    byteBuf = ByteBufAllocator.DEFAULT.ioBuffer(bytes.length);
                    byteBuf.writeBytes(bytes);
                    byte subChunkVersion = byteBuf.readByte();
                    int layers = 2;
                    switch (subChunkVersion) {
                        case 9:
                        case 8:
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
            Palette<BiomeType> last = null;
            Palette<BiomeType> biomePalette;
            for (int y = chunk.getDimensionInfo().minSectionY(); y <= chunk.getDimensionInfo().maxSectionY(); y++) {
                ChunkSection section = chunk.getSection(y);
                if (section == null) continue;
                biomePalette = section.biomes();
                biomePalette.writeToStorageRuntime(heightAndBiomesBuffer, BiomeType::getId, last);
                last = biomePalette;
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
            byte[] bytes = db.get(LevelDBKeyUtils.DATA_3D.getKey(builder.getChunkX(), builder.getChunkZ()));
            if (bytes != null) {
                heightAndBiomesBuffer = Unpooled.wrappedBuffer(bytes);
                short[] heights = new short[256];
                for (int i = 0; i < 256; i++) {
                    heights[i] = heightAndBiomesBuffer.readShortLE();
                }
                builder.heightMap(new HeightMap(heights));
                Palette<BiomeType> last = null;
                Palette<BiomeType> biomePalette;
                var minSectionY = builder.getDimensionInfo().minSectionY();
                for (int y = minSectionY; y <= builder.getDimensionInfo().maxSectionY(); y++) {
                    ChunkSection section = builder.getSections()[y - minSectionY];
                    if (section == null) continue;
                    biomePalette = section.biomes();
                    biomePalette.readFromStorageRuntime(heightAndBiomesBuffer, LevelDBChunkSerializer::getBiomeByIdNonNull, last);
                    last = biomePalette;
                }
            }
        } finally {
            if (heightAndBiomesBuffer != null) {
                heightAndBiomesBuffer.release();
            }
        }
    }

    private static BiomeType getBiomeByIdNonNull(int id) {
        var biome = VanillaBiomeId.fromId(id);
        if (biome == null) {
            log.warn("Unknown biome id: {}", id);
            biome = VanillaBiomeId.PLAINS;
        }
        return biome;
    }
}
