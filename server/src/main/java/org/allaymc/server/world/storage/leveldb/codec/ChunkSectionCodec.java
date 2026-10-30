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
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.server.datastruct.palette.Palette;
import org.allaymc.server.datastruct.palette.PaletteException;
import org.allaymc.server.datastruct.palette.PaletteUtils;
import org.allaymc.server.network.ProtocolInfo;
import org.allaymc.server.world.chunk.AllayChunkSection;
import org.allaymc.server.world.storage.leveldb.LevelDBUtils;
import org.allaymc.updater.block.BlockStateUpdaters;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.util.stream.LittleEndianDataInputStream;

import java.io.IOException;
import java.util.Arrays;
import java.util.TreeMap;

/**
 * {@code ChunkSectionCodec} converts
 * chunk block palettes between runtime states and the persistent
 * subchunk representation. Stored versions 1, 8, and 9 share palette decoding,
 * with version-specific header bytes consumed before palette data. Unknown
 * versions are reported to the storage caller as {@code null} so the caller can
 * substitute an empty section.
 *
 * <p>
 * Block states are resolved through the runtime hash registry. The fast reader
 * handles current state data directly; older data passes through the
 * block-state updater and a canonical property ordering before hashing.
 * Serialization compacts palettes and clears their dirty flags as bytes are
 * produced, before any caller commits those bytes to the database.
 */
@Slf4j
@UtilityClass
public final class ChunkSectionCodec {

    /**
     * Encodes the current section format and its supported block layers into a
     * new array. The section coordinate is narrowed to one byte without
     * validation.
     *
     * <p>
     * Serialization compacts each palette and clears its dirty flag after
     * encoding that layer. This codec does not commit the result to storage,
     * so callers must coordinate access and account for a later write failure.
     * A serialization failure can also leave earlier layers compacted or clean
     * while later layers remain unprocessed.
     */
    public static byte[] serialize(AllayChunkSection section, int ySection) {
        return LevelDBUtils.withByteBufToArray(buffer -> {
            buffer.writeByte(AllayChunkSection.CURRENT_CHUNK_SECTION_VERSION);
            buffer.writeByte(AllayChunkSection.LAYER_COUNT);
            // The format stores section height in one signed byte.
            buffer.writeByte(ySection);
            for (int i = 0; i < AllayChunkSection.LAYER_COUNT; i++) {
                var palette = section.blockLayers()[i];
                palette.compact();
                palette.writeToStorage(buffer, BlockState::getBlockStateNBT);
                // The encoded palette is marked clean immediately, so the
                // caller must account for a database failure occurring after
                // serialization succeeds.
                palette.setDirty(false);
            }
        });
    }

    /**
     * Decodes a stored subchunk using the section coordinate supplied by its
     * database key. The embedded version-9 height byte is consumed but does not
     * replace that coordinate, and an unsupported format version returns {@code null}.
     *
     * <p>
     * Versions 8 and 9 carry a layer count; version 1 uses the configured
     * runtime layer count. Excess layers take a compatibility path with a
     * larger array, whose entries share one palette instance throughout
     * decoding. Each layer read mutates that same palette, so later layer data
     * also becomes visible through earlier array entries. Decoded palettes are
     * marked clean because loading alone should not schedule a
     * block-layer rewrite. Malformed palette data can still propagate a
     * decoding failure.
     */
    public static AllayChunkSection deserialize(byte[] data, int sectionY, int chunkX, int chunkZ) {
        var byteBuf = Unpooled.wrappedBuffer(data);
        var subChunkVersion = byteBuf.readByte();
        var layers = AllayChunkSection.LAYER_COUNT;

        switch (subChunkVersion) {
            case 9, 8:
                layers = byteBuf.readByte();
                if (subChunkVersion == 9) {
                    // Version 9 adds a height byte before palette data; the database key
                    // already supplies the section coordinate used by this loader.
                    byteBuf.readByte();
                }
            // The version-specific headers are now consumed; deliberate
            // fallthrough sends all supported representations through the same
            // palette decoder without reading another header.
            case 1:
                AllayChunkSection section;
                if (layers <= AllayChunkSection.LAYER_COUNT) {
                    // The standard constructor creates the configured block layers, leaving
                    // unused layers empty when the stored count is smaller than that capacity.
                    section = new AllayChunkSection((byte) sectionY);
                } else {
                    // Preserve the stored layer count for this compatibility path rather than
                    // rejecting the whole section; the fill below shares one palette instance.
                    log.warn("Loading chunk section ({}, {}, {}) with {} layers, which might mean that this chunk is corrupted!", chunkX, sectionY, chunkZ, layers);
                    @SuppressWarnings("rawtypes") Palette[] palettes = new Palette[layers];
                    Arrays.fill(palettes, new Palette<>(BlockTypes.AIR.getDefaultState()));
                    section = new AllayChunkSection((byte) sectionY, palettes);
                }

                for (int layer = 0; layer < layers; layer++) {
                    var palette = section.blockLayers()[layer];
                    // Loading populates the palette through an operation that
                    // marks it dirty; clear that marker afterward so an
                    // unchanged loaded section does not require saving.
                    palette.readFromStorage(byteBuf, ChunkSectionCodec::fastBlockStateDeserializer);
                    palette.setDirty(false);
                }
                return section;
            default:
                // Unsupported headers have no reliable palette layout here;
                // return null so the storage loader can replace this section
                // without decoding unknown bytes.
                log.warn("Unknown subchunk version {} at ({}, {}, {})", subChunkVersion, chunkX, sectionY, chunkZ);
                return null;
        }
    }

    /**
     * Reads one block-state value from the palette buffer and resolves its
     * runtime state. Current-format values use the fast hash reader, while
     * values requiring conversion are read as NBT and upgraded to the
     * configured block-state version.
     *
     * <p>
     * Converted property names are sorted, and {@code "name"} precedes
     * {@code "states"} when hashing, matching the registry's canonical
     * representation. A missing registry hash is logged and replaced by the
     * unknown block state. I/O failures become palette exceptions; this
     * fallback does not recover arbitrary malformed NBT.
     */
    private static BlockState fastBlockStateDeserializer(ByteBuf buffer) {
        int blockStateHash;
        try (var bufInputStream = new ByteBufInputStream(buffer);
             var input = new LittleEndianDataInputStream(bufInputStream);
             var nbtInputStream = new NBTInputStream(input)) {
            blockStateHash = PaletteUtils.fastReadBlockStateHash(input, buffer);
            // The fast reader requests conversion for noncurrent state data, so
            // restore a runtime registry identity from upgraded NBT instead of
            // using an obsolete hash.
            if (blockStateHash == PaletteUtils.HASH_NOT_LATEST) {
                var oldNbtMap = (NbtMap) nbtInputStream.readTag();
                var newNbtMap = BlockStateUpdaters.updateBlockState(oldNbtMap, ProtocolInfo.BLOCK_STATE_UPDATER.getVersion());
                // Registry hashes depend on canonical property order, so sort the upgraded
                // states before rebuilding the name-and-states compound used for lookup.
                var states = new TreeMap<>(newNbtMap.getCompound("states"));
                // The registry hash serializes name before states; preserving that ordering
                // keeps an upgraded value associated with the corresponding runtime block state.
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

        // A decoded but unregistered state cannot resolve to a runtime block;
        // substitute the unknown state so other palette values remain loadable.
        log.error("Unknown block state hash {} while loading chunk section", blockStateHash);
        return BlockTypes.UNKNOWN.getDefaultState();
    }

    /**
     * Fills missing array entries with empty sections using the dimension's
     * minimum section coordinate. The supplied array is modified and returned
     * directly; existing section objects are retained without replacement.
     */
    public static AllayChunkSection[] fillNullSections(AllayChunkSection[] sections, DimensionType dimensionType) {
        for (int i = 0; i < sections.length; i++) {
            if (sections[i] == null) {
                sections[i] = new AllayChunkSection((byte) (i + dimensionType.minSectionY()));
            }
        }
        return sections;
    }
}
