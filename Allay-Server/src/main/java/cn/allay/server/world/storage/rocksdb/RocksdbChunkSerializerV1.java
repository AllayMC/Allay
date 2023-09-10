package cn.allay.server.world.storage.rocksdb;

import cn.allay.api.block.palette.BlockStateHashPalette;
import cn.allay.api.block.type.BlockState;
import cn.allay.api.data.VanillaBiomeId;
import cn.allay.api.datastruct.collections.nb.Long2ObjectNonBlockingMap;
import cn.allay.api.utils.Utils;
import cn.allay.api.world.biome.BiomeType;
import cn.allay.api.world.chunk.ChunkSection;
import cn.allay.api.world.chunk.UnsafeChunk;
import cn.allay.api.world.heightmap.HeightMap;
import cn.allay.api.world.palette.Palette;
import cn.allay.server.utils.LevelDBKeyUtils;
import cn.allay.server.world.chunk.AllayUnsafeChunk;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.rocksdb.WriteBatch;

/**
 * Allay Project 8/23/2023
 *
 * @author Cool_Loong
 */
public class RocksdbChunkSerializerV1 implements RocksdbChunkSerializer {
    public static final RocksdbChunkSerializer INSTANCE = new RocksdbChunkSerializerV1();

    private RocksdbChunkSerializerV1() {
    }

    //todo Entity and Block Entity
    @Override
    public void serialize(WriteBatch writeBatch, UnsafeChunk chunk) {
        serializeBlock(writeBatch, chunk);
        serializeHeightAndBiome(writeBatch, chunk);
    }

    @Override
    public void deserialize(RocksDB db, AllayUnsafeChunk.Builder builder) {
        deserializeBlock(db, builder);
        deserializeHeightAndBiome(db, builder);
        //todo Entity and Block Entity
        builder.entities(new Long2ObjectNonBlockingMap<>());
    }

    private void serializeBlock(WriteBatch writeBatch, UnsafeChunk chunk) {
        Palette<BlockState>[] lastLayers = new Palette[ChunkSection.LAYER_COUNT];
        // serialize chunk section
        for (int ySection = 0; ySection < chunk.getDimensionInfo().chunkSectionSize(); ySection++) {
            ChunkSection section = chunk.getSection(ySection);
            if (section == null) {
                continue;
            }

            ByteBuf buffer = ByteBufAllocator.DEFAULT.ioBuffer();
            try {
                buffer.writeByte(ChunkSection.VERSION);
                //block data
                buffer.writeByte(ChunkSection.LAYER_COUNT);
                buffer.writeByte(ySection);
                for (int i = 0; i < ChunkSection.LAYER_COUNT; i++) {
                    section.blockLayer()[i].writeToStorageRuntime(buffer, BlockState::blockStateHash, lastLayers[i]);
                    lastLayers[i] = section.blockLayer()[i];
                }
                writeBatch.put(LevelDBKeyUtils.CHUNK_SECTION_PREFIX.getKey(chunk.getX(), chunk.getZ(), ySection), Utils.convertByteBuf2Array(buffer));
            } catch (RocksDBException e) {
                throw new RuntimeException(e);
            } finally {
                buffer.release();
            }
        }
    }

    private void deserializeBlock(RocksDB db, AllayUnsafeChunk.Builder builder) {
        Palette<BlockState>[] lastLayers = new Palette[ChunkSection.LAYER_COUNT];
        ChunkSection[] sections = new ChunkSection[builder.getDimensionInfo().chunkSectionSize()];
        // serialize chunk section
        for (int ySection = 0; ySection < builder.getDimensionInfo().chunkSectionSize(); ySection++) {
            ByteBuf buf = null;
            try {
                byte[] bytes = db.get(LevelDBKeyUtils.CHUNK_SECTION_PREFIX.getKey(builder.getChunkX(), builder.getChunkZ(), ySection));
                if (bytes != null) {
                    ChunkSection section = new ChunkSection(ySection);
                    buf = Unpooled.wrappedBuffer(bytes);
                    buf.readByte();//version
                    byte layerCount = buf.readByte();
                    buf.readByte();//section Y
                    for (int i = 0; i < layerCount; i++) {
                        section.blockLayer()[i].readFromStorageRuntime(buf, (hash) -> BlockStateHashPalette.getRegistry().get(hash), lastLayers[i]);
                        lastLayers[i] = section.blockLayer()[i];
                    }
                    sections[ySection] = section;
                }
            } catch (RocksDBException e) {
                throw new RuntimeException(e);
            } finally {
                if (buf != null) {
                    buf.release();
                }
            }
        }
        builder.sections(sections);
    }

    private void serializeHeightAndBiome(WriteBatch writeBatch, UnsafeChunk chunk) {
        //Write biomeAndHeight
        ByteBuf heightAndBiomesBuffer = ByteBufAllocator.DEFAULT.ioBuffer();
        try {
            for (short height : chunk.getHeights()) {
                heightAndBiomesBuffer.writeShortLE(height);
            }
            Palette<BiomeType> last = null;
            Palette<BiomeType> biomePalette;
            for (int y = 0; y < chunk.getDimensionInfo().chunkSectionSize(); y++) {
                ChunkSection section = chunk.getSection(y);
                if (section == null) continue;
                biomePalette = section.biomes();
                biomePalette.writeToStorageRuntime(heightAndBiomesBuffer, BiomeType::getId, last);
                last = biomePalette;
            }
            writeBatch.put(LevelDBKeyUtils.DATA_3D.getKey(chunk.getX(), chunk.getZ()), Utils.convertByteBuf2Array(heightAndBiomesBuffer));
        } catch (RocksDBException e) {
            throw new RuntimeException(e);
        } finally {
            heightAndBiomesBuffer.release();
        }
    }

    private void deserializeHeightAndBiome(RocksDB db, AllayUnsafeChunk.Builder builder) {
        //Write biomeAndHeight
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
                for (int y = 0; y < builder.getDimensionInfo().chunkSectionSize(); y++) {
                    ChunkSection section = builder.getSections()[y];
                    if (section == null) continue;
                    biomePalette = section.biomes();
                    biomePalette.readFromStorageRuntime(heightAndBiomesBuffer, VanillaBiomeId::fromId, last);
                    last = biomePalette;
                }
            }
        } catch (RocksDBException e) {
            throw new RuntimeException(e);
        } finally {
            if (heightAndBiomesBuffer != null) {
                heightAndBiomesBuffer.release();
            }
        }
    }
}
