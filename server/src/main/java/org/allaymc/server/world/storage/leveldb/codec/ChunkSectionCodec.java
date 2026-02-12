package org.allaymc.server.world.storage.leveldb.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.Unpooled;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.server.datastruct.palette.Palette;
import org.allaymc.server.datastruct.palette.PaletteException;
import org.allaymc.server.datastruct.palette.PaletteUtils;
import org.allaymc.server.network.ProtocolInfo;
import org.allaymc.server.world.chunk.AllayChunkSection;
import org.allaymc.updater.block.BlockStateUpdaters;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.util.stream.LittleEndianDataInputStream;

import java.io.IOException;
import java.util.Arrays;
import java.util.TreeMap;

/**
 * Codec for serializing/deserializing chunk sections.
 */
@Slf4j
@UtilityClass
public final class ChunkSectionCodec {

    public static byte[] serialize(AllayChunkSection section, int ySection) {
        return LevelDBUtils.withByteBufToArray(buffer -> {
            buffer.writeByte(AllayChunkSection.CURRENT_CHUNK_SECTION_VERSION);
            buffer.writeByte(AllayChunkSection.LAYER_COUNT);
            buffer.writeByte(ySection);
            for (int i = 0; i < AllayChunkSection.LAYER_COUNT; i++) {
                var palette = section.blockLayers()[i];
                palette.compact();
                palette.writeToStorage(buffer, BlockState::getBlockStateNBT);
                palette.setDirty(false);
            }
        });
    }

    /**
     * Deserialize a chunk section from raw bytes.
     *
     * @return the deserialized section, or {@code null} if the sub-chunk version is unknown.
     */
    public static AllayChunkSection deserialize(byte[] data, int sectionY, int chunkX, int chunkZ) {
        var byteBuf = Unpooled.wrappedBuffer(data);
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
                    section = new AllayChunkSection((byte) sectionY);
                } else {
                    // Currently only two layers are used in minecraft, so that might mean this chunk is corrupted
                    // However we can still load it c:
                    log.warn("Loading chunk section ({}, {}, {}) with {} layers, which might mean that this chunk is corrupted!", chunkX, sectionY, chunkZ, layers);
                    @SuppressWarnings("rawtypes") Palette[] palettes = new Palette[layers];
                    Arrays.fill(palettes, new Palette<>(BlockTypes.AIR.getDefaultState()));
                    section = new AllayChunkSection((byte) sectionY, palettes);
                }

                for (int layer = 0; layer < layers; layer++) {
                    var palette = section.blockLayers()[layer];
                    palette.readFromStorage(byteBuf, ChunkSectionCodec::fastBlockStateDeserializer);
                    palette.setDirty(false);
                }
                return section;
            default:
                log.warn("Unknown subchunk version {} at ({}, {}, {})", subChunkVersion, chunkX, sectionY, chunkZ);
                return null;
        }
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

    public static AllayChunkSection[] fillNullSections(AllayChunkSection[] sections, DimensionInfo dimensionInfo) {
        for (int i = 0; i < sections.length; i++) {
            if (sections[i] == null) {
                sections[i] = new AllayChunkSection((byte) (i + dimensionInfo.minSectionY()));
            }
        }
        return sections;
    }
}
