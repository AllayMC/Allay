package org.allaymc.server.world.chunk;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.server.datastruct.palette.IntSerializer;
import org.allaymc.server.datastruct.palette.Palette;
import org.cloudburstmc.nbt.NbtUtils;

import java.util.Arrays;

/**
 * @author daoge_cmd
 */
@Slf4j
public final class ChunkEncoder {
    public static ByteBuf writeToNetwork(AllayUnsafeChunk chunk) {
        var byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        try {
            writeBlocks(chunk, byteBuf);
            writeBiomes(chunk, byteBuf);
            // Length of 1 byte for the border block count
            byteBuf.writeByte(0);
            writeBlockEntities(chunk, byteBuf);
            return byteBuf;
        } catch (Throwable t) {
            log.error("Error while encoding chunk(x={}, z={})!", chunk.getX(), chunk.getZ(), t);
            byteBuf.release();
            return Unpooled.EMPTY_BUFFER;
        }
    }

    public static ByteBuf writeToNetworkBiomeOnly(AllayUnsafeChunk chunk) {
        var byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        try {
            writeBiomes(chunk, byteBuf);
            // Length of 1 byte for the border block count
            byteBuf.writeByte(0);
            return byteBuf;
        } catch (Throwable t) {
            log.error("Error while encoding chunk(x={}, z={})!", chunk.getX(), chunk.getZ(), t);
            byteBuf.release();
            return Unpooled.EMPTY_BUFFER;
        }
    }

    public static void writeToNetwork(AllayChunkSection section, ByteBuf byteBuf) {
        byteBuf.writeByte(AllayChunkSection.CURRENT_CHUNK_SECTION_VERSION);
        // Block layer count
        byteBuf.writeByte(AllayChunkSection.LAYER_COUNT);
        // Extra byte since version 9
        byteBuf.writeByte(section.sectionY());

        for (var blockLayer : section.blockLayers()) {
            byteBuf.writeBytes(encodeLayerNetworkBytes(blockLayer, BlockState::blockStateHash));
        }
    }

    /**
     * Encode a single section as byte[] blob (blocks only, no block entities).
     */
    public static byte[] encodeSectionBlob(AllayChunkSection section) {
        var byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        try {
            writeToNetwork(section, byteBuf);
            byte[] data = new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(data);
            return data;
        } finally {
            byteBuf.release();
        }
    }

    public static byte[] encodeSectionBlob(AllayUnsafeChunk chunk, int sectionY) {
        var section = chunk.getSection(sectionY);
        return chunk.getNetworkCache().sectionBlob(section, sectionY - chunk.getDimensionType().minSectionY());
    }

    /**
     * Encode biomes as byte[] blob (PURE biomes only, NO border block count).
     */
    public static byte[] encodeBiomesBlob(AllayUnsafeChunk chunk) {
        return chunk.getNetworkCache().biomesBlob(chunk);
    }

    private static byte[] encodeBiomesBlobUncached(AllayUnsafeChunk chunk) {
        var byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        try {
            writeBiomes(chunk, byteBuf);
            byte[] data = new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(data);
            return data;
        } finally {
            byteBuf.release();
        }
    }

    /**
     * Encode border block count (0) + block entities as ByteBuf.
     * Used as LevelChunkPacket.data in full-chunk cache mode.
     */
    public static ByteBuf writeCachedChunkData(AllayUnsafeChunk chunk) {
        var byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        try {
            // Border block count
            byteBuf.writeByte(0);
            writeBlockEntities(chunk, byteBuf);
            return byteBuf;
        } catch (Throwable t) {
            log.error("Error while encoding cached chunk data(x={}, z={})!", chunk.getX(), chunk.getZ(), t);
            byteBuf.release();
            return Unpooled.EMPTY_BUFFER;
        }
    }

    private static void writeBlocks(AllayUnsafeChunk chunk, ByteBuf byteBuf) {
        var dimensionType = chunk.getDimensionType();
        for (int i = dimensionType.minSectionY(); i <= dimensionType.maxSectionY(); i++) {
            ChunkEncoder.writeToNetwork(chunk.getSection(i), byteBuf);
        }
    }

    static void writeBiomes(AllayUnsafeChunk chunk, ByteBuf byteBuf) {
        for (var s : chunk.getSections()) {
            var section = (AllayChunkSection) s;
            byteBuf.writeBytes(encodeLayerNetworkBytes(section.biomes(), BiomeType::getId));
        }
    }

    static void writeBlockEntities(AllayUnsafeChunk chunk, ByteBuf byteBuf) {
        var blockEntities = chunk.getBlockEntities().values();
        if (!blockEntities.isEmpty()) {
            try (var writer = NbtUtils.createNetworkWriter(new ByteBufOutputStream(byteBuf))) {
                for (var blockEntity : blockEntities) {
                    writer.writeTag(blockEntity.saveNBT());
                }
            } catch (Throwable t) {
                log.error("Error while encoding block entities in chunk {}, {}", chunk.getX(), chunk.getZ(), t);
            }
        }
    }

    private static <V> byte[] encodeLayerNetworkBytes(Palette<V> palette, IntSerializer<V> serializer) {
        byte[] cached = palette.getCachedNetworkBytes();
        if (cached != null) {
            return cached;
        }
        var byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        byte[] bytes;
        try {
            palette.writeToNetwork(byteBuf, serializer, null);
            bytes = new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(bytes);
        } finally {
            byteBuf.release();
        }
        palette.installNetworkSnapshot(bytes);
        return bytes;
    }

    static final class NetworkCache {
        private final Object lock = new Object();
        private final SectionEntry[] sections;
        private BiomesEntry biomes;

        NetworkCache(int sectionCount) {
            this.sections = new SectionEntry[sectionCount];
        }

        byte[] sectionBlob(AllayChunkSection section, int index) {
            synchronized (lock) {
                var entry = sections[index];
                long layer0Revision = section.blockLayers()[0].revision();
                long layer1Revision = section.blockLayers()[1].revision();
                if (entry != null && entry.layer0Revision == layer0Revision && entry.layer1Revision == layer1Revision) {
                    return entry.blob;
                }
                byte[] blob = ChunkEncoder.encodeSectionBlob(section);
                sections[index] = new SectionEntry(layer0Revision, layer1Revision, blob);
                return blob;
            }
        }

        byte[] biomesBlob(AllayUnsafeChunk chunk) {
            synchronized (lock) {
                var chunkSections = chunk.getSections();
                int count = chunkSections.size();
                long[] revisions = new long[count];
                for (int i = 0; i < count; i++) {
                    revisions[i] = ((AllayChunkSection) chunkSections.get(i)).biomes().revision();
                }
                if (biomes != null && Arrays.equals(biomes.revisions, revisions)) {
                    return biomes.blob;
                }
                byte[] blob = ChunkEncoder.encodeBiomesBlobUncached(chunk);
                biomes = new BiomesEntry(revisions, blob);
                return blob;
            }
        }

        int cachedSectionEntryCount() {
            synchronized (lock) {
                int count = 0;
                for (var entry : sections) {
                    if (entry != null) {
                        count++;
                    }
                }
                return count;
            }
        }

        static final class SectionEntry {
            final long layer0Revision;
            final long layer1Revision;
            final byte[] blob;

            SectionEntry(long layer0Revision, long layer1Revision, byte[] blob) {
                this.layer0Revision = layer0Revision;
                this.layer1Revision = layer1Revision;
                this.blob = blob;
            }
        }

        static final class BiomesEntry {
            final long[] revisions;
            final byte[] blob;

            BiomesEntry(long[] revisions, byte[] blob) {
                this.revisions = revisions;
                this.blob = blob;
            }
        }
    }
}