package org.allaymc.server.world.chunk;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.world.biome.BiomeType;
import org.allaymc.server.datastruct.palette.Palette;
import org.allaymc.server.network.protocol.NetworkBlockPalette;
import org.cloudburstmc.nbt.NbtUtils;

/**
 * {@code ChunkEncoder} encodes
 * chunk sections, biomes, and block entities into Bedrock network
 * payloads. Full payloads concatenate block sections, biome palettes, a zero
 * border-block count, and network NBT; cache payloads separate section and
 * biome blobs from the remaining chunk data.
 *
 * <p>
 * The encoder borrows mutable chunk data without acquiring locks, so callers
 * establish a stable view while encoding. Methods returning allocated buffers
 * transfer release responsibility to the caller, while blob methods return
 * independent byte arrays. Top-level buffer encoders log failures and return an
 * empty buffer after releasing partial output. Block-entity encoding suppresses
 * its own failure, however, so a payload can retain only the block entities
 * written before that failure.
 *
 * @author daoge_cmd
 */
@Slf4j
public final class ChunkEncoder {
    /**
     * Returns a full chunk payload whose allocated buffer must be released by
     * its consumer. Callers must stabilize chunk data during encoding; an outer
     * failure is logged, releases partial output, and returns the shared empty
     * buffer. Block-entity failures are handled internally and may instead
     * leave partial NBT in the result.
     * {@code blockPalette} selects the block states the target protocol knows.
     */
    public static ByteBuf writeToNetwork(AllayUnsafeChunk chunk, NetworkBlockPalette blockPalette) {
        var byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        try {
            writeBlocks(chunk, byteBuf, blockPalette);
            writeBiomes(chunk, byteBuf);
            // This encoder emits no border-block entries, but their count still
            // terminates the biome portion of the payload expected by the client.
            byteBuf.writeByte(0);
            writeBlockEntities(chunk, byteBuf);
            return byteBuf;
        } catch (Throwable t) {
            // Failed top-level encoding transfers no partial buffer to the caller;
            // release it before returning the empty payload used by this fallback.
            log.error("Error while encoding chunk(x={}, z={})!", chunk.getX(), chunk.getZ(), t);
            byteBuf.release();
            return Unpooled.EMPTY_BUFFER;
        }
    }

    /**
     * Returns biome palettes followed by a zero border-block count in a
     * caller-owned buffer. Callers stabilize biome data during encoding; a
     * failure is logged and returns the shared empty buffer after releasing
     * partial output.
     */
    public static ByteBuf writeToNetworkBiomeOnly(AllayUnsafeChunk chunk) {
        var byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        try {
            writeBiomes(chunk, byteBuf);
            // This encoder emits no border-block entries, but their count still
            // terminates the biome portion of the payload expected by the client.
            byteBuf.writeByte(0);
            return byteBuf;
        } catch (Throwable t) {
            // Failed top-level encoding transfers no partial buffer to the caller;
            // release it before returning the empty payload used by this fallback.
            log.error("Error while encoding chunk(x={}, z={})!", chunk.getX(), chunk.getZ(), t);
            byteBuf.release();
            return Unpooled.EMPTY_BUFFER;
        }
    }

    /**
     * Appends one section's version, layer count, absolute Y offset, and block
     * palettes to the supplied buffer. The buffer remains caller-owned, and
     * callers must stabilize section palettes while encoding. The section must
     * contain exactly {@code AllayChunkSection.LAYER_COUNT} block palettes because the header uses
     * that constant while the loop writes every supplied array entry.
     * {@code blockPalette} selects the block states the target protocol knows.
     */
    public static void writeToNetwork(AllayChunkSection section, ByteBuf byteBuf, NetworkBlockPalette blockPalette) {
        byteBuf.writeByte(AllayChunkSection.CURRENT_CHUNK_SECTION_VERSION);
        // The header advertises LAYER_COUNT even though the loop consumes the entire
        // supplied array, so callers must keep its length consistent with that count.
        byteBuf.writeByte(AllayChunkSection.LAYER_COUNT);
        // Offset-aware section format carries absolute Y, preserving section identity
        // when dimensions include negative heights or nonzero minimum sections.
        byteBuf.writeByte(section.sectionY());

        for (var blockLayer : section.blockLayers()) {
            blockLayer.writeToNetwork(byteBuf, blockPalette::toNetwork, null);
        }
    }

    /**
     * Returns an independent byte array containing one section's block payload
     * without block entities. The temporary buffer is released even when
     * encoding fails; callers must stabilize the section during access.
     * {@code blockPalette} selects the block states the target protocol knows.
     */
    public static byte[] encodeSectionBlob(AllayChunkSection section, NetworkBlockPalette blockPalette) {
        var byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        try {
            writeToNetwork(section, byteBuf, blockPalette);
            byte[] data = new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(data);
            return data;
        } finally {
            byteBuf.release();
        }
    }

    /**
     * Returns an independent byte array containing only the chunk's biome
     * palettes, without a border-block count. The temporary buffer is released
     * even when encoding fails; callers must stabilize biome access.
     */
    public static byte[] encodeBiomesBlob(AllayUnsafeChunk chunk) {
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
     * Returns a caller-owned buffer containing the zero border-block count and
     * block-entity NBT for cached chunk delivery. Outer failures release
     * partial output and return the shared empty buffer; suppressed
     * block-entity failures can leave partial NBT.
     */
    public static ByteBuf writeCachedChunkData(AllayUnsafeChunk chunk) {
        var byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        try {
            // Cached section blobs exclude the border-block count; write it here so
            // the client can locate the block-entity sequence in the remaining payload.
            byteBuf.writeByte(0);
            writeBlockEntities(chunk, byteBuf);
            return byteBuf;
        } catch (Throwable t) {
            // Failed top-level encoding transfers no partial buffer to the caller;
            // release it before returning the empty payload used by this fallback.
            log.error("Error while encoding cached chunk data(x={}, z={})!", chunk.getX(), chunk.getZ(), t);
            byteBuf.release();
            return Unpooled.EMPTY_BUFFER;
        }
    }

    /**
     * Appends sections in ascending absolute Y order across the dimension's
     * complete vertical range. The supplied buffer remains caller-owned, and
     * all section data must remain stable throughout traversal.
     * {@code blockPalette} selects the block states the target protocol knows.
     */
    private static void writeBlocks(AllayUnsafeChunk chunk, ByteBuf byteBuf, NetworkBlockPalette blockPalette) {
        var dimensionType = chunk.getDimensionType();
        for (int i = dimensionType.minSectionY(); i <= dimensionType.maxSectionY(); i++) {
            ChunkEncoder.writeToNetwork(chunk.getSection(i), byteBuf, blockPalette);
        }
    }

    /**
     * Appends every section's biome palette in ascending section order to the
     * caller's buffer. Previous-palette reuse remains disabled, so each palette
     * is encoded without depending on the preceding section's palette.
     */
    static void writeBiomes(AllayUnsafeChunk chunk, ByteBuf byteBuf) {
        Palette<BiomeType> last = null;
        for (var s : chunk.getSections()) {
            var section = (AllayChunkSection) s;
            section.biomes().writeToNetwork(byteBuf, BiomeType::getId, last);
            // TODO: Validate the previous-palette flag with Bedrock clients before
            // enabling reuse; independent palettes currently keep each section decodable.
        }
    }

    /**
     * Appends current block-entity NBT compounds to the supplied buffer when
     * entries exist. Serialization failures are logged and suppressed without
     * undoing bytes already written, so callers can receive a partial
     * block-entity sequence.
     */
    static void writeBlockEntities(AllayUnsafeChunk chunk, ByteBuf byteBuf) {
        var blockEntities = chunk.getBlockEntities().values();
        if (!blockEntities.isEmpty()) {
            try (var writer = NbtUtils.createNetworkWriter(new ByteBufOutputStream(byteBuf))) {
                for (var blockEntity : blockEntities) {
                    writer.writeTag(blockEntity.saveNBT());
                }
            } catch (Throwable t) {
                // Earlier compounds already occupy the caller's buffer. Logging here
                // preserves those bytes and hides this failure from outer encoders.
                log.error("Error while encoding block entities in chunk {}, {}", chunk.getX(), chunk.getZ(), t);
            }
        }
    }
}
