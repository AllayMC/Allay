package org.allaymc.api.utils.hash;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.chunk.ChunkSection;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;
import org.jetbrains.annotations.Range;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

/**
 * {@code HashUtils} computes block-state hashes and packs coordinates into keys
 * used by chunk storage. State hashing sorts serialized property names before
 * writing little-endian NBT, making serialized key order independent of input
 * order. The unknown block uses its reserved signed hash instead of that
 * serialization. General NBT hashing preserves the supplied tag's
 * representation and does not perform the property normalization used by the
 * block-state helpers.
 *
 * <p>
 * Coordinate keys use distinct layouts for their consumers. Chunk X/Z pairs
 * preserve two full signed integers in one long, while local chunk positions
 * combine two four-bit horizontal coordinates with a biased signed height.
 * Section indexes instead use four bits per coordinate in X/Z/Y order for
 * palette access. Matching decoders must use the layout selected by the
 * encoder.
 *
 * @author Cool_Loong | daoge_cmd
 */
@Slf4j
public final class HashUtils {

    /**
     * {@code UNKNOWN_ID} identifies the block whose runtime state hash is
     * always the reserved value {@code -2}, matching the unsigned hash in its
     * resource record.
     */
    private static final Identifier UNKNOWN_ID = new Identifier("minecraft:unknown");

    //https://gist.github.com/Alemiz112/504d0f79feac7ef57eda174b668dd345
    // FNV1_32_INIT initializes the 32-bit FNV-1a calculation, and FNV1_PRIME_32
    // advances it. Java integer overflow retains the low 32 multiplication bits.
    /** FNV-1a initial offset basis. */
    private static final int FNV1_32_INIT = 0x811c9dc5;
    /** FNV-1a per-byte multiplier. */
    private static final int FNV1_PRIME_32 = 0x01000193;

    /**
     * Rejects construction because all hashing and packing operations are
     * static.
     */
    private HashUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Computes a state hash from the identifier and serialized property values.
     * {@code UNKNOWN_ID} selects {@code -2} without inspecting
     * {@code propertyValues}. Other identifiers produce a little-endian NBT
     * compound containing only the name and states, then hash those bytes with
     * 32-bit FNV-1a.
     *
     * <p>
     * Property names are sorted so input iteration order does not change their
     * serialized order. Duplicate names retain the last supplied value; the
     * hash therefore represents that resulting map rather than every list
     * entry.
     */
    public static int computeBlockStateHash(Identifier identifier, List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues) {
        if (identifier.equals(UNKNOWN_ID)) {
            // Resource metadata stores this reserved hash as unsigned 4294967294;
            // the runtime uses its signed representation instead of hashing unknown properties.
            return -2;
        }

        // Lexically order distinct property names before serialization; caller
        // ordering must not give the same named property values different state hashes.
        var states = new TreeMap<String, Object>();
        for (var value : propertyValues) {
            states.put(value.getPropertyType().getName(), value.getSerializedValue());
        }

        var tag = NbtMap.builder()
                .putString("name", identifier.toString())
                .putCompound("states", NbtMap.fromMap(states))
                .build();

        return fnv1a_32_nbt(tag);
    }

    /**
     * Computes a state hash from the identifier and serialized property array.
     * {@code UNKNOWN_ID} selects {@code -2} without inspecting
     * {@code propertyValues}. Other identifiers produce a little-endian NBT
     * compound containing only the name and states, then hash those bytes with
     * 32-bit FNV-1a.
     *
     * <p>
     * Property names are sorted so array order does not change their serialized
     * order. Duplicate names retain the last supplied value; the hash therefore
     * represents that resulting map rather than every array element.
     */
    public static int computeBlockStateHash(Identifier identifier, BlockPropertyType.BlockPropertyValue<?, ?, ?>[] propertyValues) {
        if (identifier.equals(UNKNOWN_ID)) {
            // Resource metadata stores this reserved hash as unsigned 4294967294;
            // the runtime uses its signed representation instead of hashing unknown properties.
            return -2;
        }

        // Lexically order distinct property names before serialization; caller
        // ordering must not give the same named property values different state hashes.
        var states = new TreeMap<String, Object>();
        for (var value : propertyValues) {
            states.put(value.getPropertyType().getName(), value.getSerializedValue());
        }

        var tag = NbtMap.builder()
                .putString("name", identifier.toString())
                .putCompound("states", NbtMap.fromMap(states))
                .build();

        return fnv1a_32_nbt(tag);
    }

    /**
     * Hashes the supplied compound's little-endian NBT serialization with
     * FNV-1a. Serialization preserves the supplied compound representation;
     * this helper does not sort property maps or apply the unknown-block
     * special case.
     *
     * @throws HashException if NBT serialization reports an I/O failure
     */
    public static int fnv1a_32_nbt(NbtMap tag) {
        byte[] bytes;
        try (var stream = new ByteArrayOutputStream();
             var outputStream = NbtUtils.createWriterLE(stream)) {
            outputStream.writeTag(tag);
            bytes = stream.toByteArray();
        } catch (IOException e) {
            log.error("Failed to covert NBT into bytes", e);
            throw new HashException(e);
        }

        return fnv1a_32(bytes);
    }

    /**
     * Computes a 32-bit FNV-1a hash from the supplied bytes. Bytes contribute
     * unsigned values, multiplication wraps to 32 bits, and an empty array
     * returns the initial offset basis unchanged.
     */
    public static int fnv1a_32(byte[] data) {
        int hash = FNV1_32_INIT;
        for (byte datum : data) {
            // Unsigned bytes avoid sign extension changing the hash input.
            hash ^= (datum & 0xff);
            hash *= FNV1_PRIME_32;
        }
        return hash;
    }

    /**
     * Packs two signed coordinates into a reversible 64-bit chunk key.
     * {@code x} occupies the upper 32 bits and {@code z} occupies the lower 32
     * bits, preserving each input's complete integer representation. Masking
     * {@code z} prevents a negative coordinate from sign-extending into the
     * stored {@code x} bits.
     *
     * <p>
     * Callers decode these keys with {@link #getXFromHashXZ(long)} and
     * {@link #getZFromHashXZ(long)}. For example, {@code x = 0} and
     * {@code z = -1} produce a {@code long} with only its lower 32 bits set, so
     * decoding still returns {@code 0} for {@code x}.
     */
    public static long hashXZ(int x, int z) {
        // Z must not sign-extend into X's half of the key.
        return ((long) x << 32) | (z & 0xffffffffL);
    }

    /**
     * Returns the signed X coordinate from a packed chunk X/Z key.
     */
    public static int getXFromHashXZ(long hashXZ) {
        return (int) (hashXZ >> 32);
    }

    /**
     * Returns the signed Z coordinate from a packed chunk X/Z key.
     */
    public static int getZFromHashXZ(long hashXZ) {
        return (int) hashXZ;
    }

    /**
     * Packs chunk-local X/Z and an absolute Y coordinate into one integer.
     * {@code x} and {@code z} must be between zero and fifteen; {@code y} must
     * fit the inclusive range {@code -8388608} through {@code 8388607}.
     * Out-of-range coordinates cause {@link IllegalArgumentException} rather
     * than being silently truncated.
     *
     * <p>
     * The upper four bits store {@code x}, the middle 24 store {@code y} biased
     * by {@code 8388608}, and the lower four store {@code z}. The bias retains
     * negative heights for the matching decoder, while the horizontal
     * coordinates identify a position inside the already selected chunk.
     */
    public static int hashChunkXYZ(@Range(from = 0, to = 15) int x, @Range(from = -8388608, to = 8388607) int y, @Range(from = 0, to = 15) int z) {
        Preconditions.checkArgument(x >= 0 && x <= 15);
        Preconditions.checkArgument(y >= -8388608 && y <= 8388607);
        Preconditions.checkArgument(z >= 0 && z <= 15);
        // Bias signed Y into its unsigned 24-bit slot before placing the two
        // local horizontal coordinates, so negative heights cannot overwrite their bits.
        return (x << 28) | (((y + 8388608) & 0xFFFFFF) << 4) | z;
    }

    /**
     * Returns local X from the upper four bits of a chunk position key.
     */
    public static int getXFromHashChunkXYZ(int encoded) {
        return (encoded >>> 28);
    }

    /**
     * Restores signed Y from the biased middle 24 bits of a chunk position key.
     */
    public static int getYFromHashChunkXYZ(int encoded) {
        // Remove the encoding bias to recover negative absolute heights.
        return ((encoded >>> 4) & 0xFFFFFF) - 8388608;
    }

    /**
     * Returns local Z from the lower four bits of a chunk position key.
     */
    public static int getZFromHashChunkXYZ(int encoded) {
        return encoded & 0xF;
    }

    /**
     * Packs three section-local coordinates into the palette's X/Z/Y index.
     * Each coordinate must be between zero and fifteen or an
     * {@link IllegalArgumentException} is thrown; Y varies fastest within the
     * resulting range from zero through 4095.
     */
    public static int hashChunkSectionXYZ(@Range(from = 0, to = 15) int x, @Range(from = 0, to = 15) int y, @Range(from = 0, to = 15) int z) {
        Preconditions.checkArgument(x >= 0 && x <= 15);
        Preconditions.checkArgument(y >= 0 && y <= 15);
        Preconditions.checkArgument(z >= 0 && z <= 15);
        return (x << 8) + (z << 4) + y;
    }
}
