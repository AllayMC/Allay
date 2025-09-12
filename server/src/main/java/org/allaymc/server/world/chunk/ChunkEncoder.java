package org.allaymc.server.world.chunk;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.server.datastruct.palette.Palette;
import org.cloudburstmc.nbt.NbtUtils;

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
            blockLayer.writeToNetwork(byteBuf, BlockState::blockStateHash, null);
        }
    }

    private static void writeBlocks(AllayUnsafeChunk chunk, ByteBuf byteBuf) {
        var dimensionInfo = chunk.getDimensionInfo();
        for (int i = dimensionInfo.minSectionY(); i <= dimensionInfo.maxSectionY(); i++) {
            ChunkEncoder.writeToNetwork(chunk.getSection(i), byteBuf);
        }
    }

    private static void writeBiomes(AllayUnsafeChunk chunk, ByteBuf byteBuf) {
        Palette<BiomeType> last = null;
        for (var s : chunk.getSections()) {
            var section = (AllayChunkSection) s;
            section.biomes().writeToNetwork(byteBuf, BiomeType::getId, last);
            // TODO: fix copy last flag
            // last = section.biomes();
        }
    }

    private static void writeBlockEntities(AllayUnsafeChunk chunk, ByteBuf byteBuf) {
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
}
