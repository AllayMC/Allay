package org.allaymc.api.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.nbt.*;
import org.joml.*;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * {@code AllayNBTUtils} converts compound NBT to binary or Base64 representations and reads
 * vector components from typed NBT lists. Binary helpers distinguish little-endian storage
 * from big-endian encoding; vector readers use the origin for missing or empty lists but
 * still require enough components in nonempty lists.
 *
 * @author daoge_cmd
 */
@Slf4j
public final class AllayNBTUtils {
    /**
     * Encodes a compound as Base64 containing little-endian binary NBT. Serialization failures
     * are logged and return {@code null}, so callers must check that result before storing or
     * decoding the encoded text.
     */
    public static String nbtToBase64LE(NbtMap nbtMap) {
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream(); NBTOutputStream writer = org.cloudburstmc.nbt.NbtUtils.createWriterLE(stream)) {
            writer.writeTag(nbtMap);
            return Base64.getEncoder().encodeToString(stream.toByteArray());
        } catch (Exception e) {
            // Serialization produced no usable encoded representation. Report the failure
            // and let the null result distinguish it from a successfully encoded compound.
            log.error("Failed to convert NBT to Base64", e);
        }
        return null;
    }

    /**
     * Encodes a compound as Base64 containing big-endian binary NBT. Serialization failures
     * are logged and return {@code null}, so callers must check that result before storing or
     * decoding the encoded text.
     */
    public static String nbtToBase64(NbtMap nbtMap) {
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream(); NBTOutputStream writer = org.cloudburstmc.nbt.NbtUtils.createWriter(stream)) {
            writer.writeTag(nbtMap);
            return Base64.getEncoder().encodeToString(stream.toByteArray());
        } catch (Exception e) {
            // Serialization produced no usable encoded representation. Report the failure
            // and let the null result distinguish it from a successfully encoded compound.
            log.error("Failed to convert NBT to Base64", e);
        }
        return null;
    }

    /**
     * Decodes the first compound from Base64 containing little-endian binary NBT. Invalid
     * Base64, unreadable NBT, or a non-compound root propagates a failure rather than returning
     * the encoding helpers' {@code null} sentinel.
     */
    @SneakyThrows
    public static NbtMap base64ToNbtLE(String base64) {
        return (NbtMap) org.cloudburstmc.nbt.NbtUtils.createReaderLE(new ByteArrayInputStream(Base64.getDecoder().decode(base64))).readTag();
    }

    /**
     * Decodes the first compound from Base64 containing big-endian binary NBT. Invalid
     * Base64, unreadable NBT, or a non-compound root propagates a failure rather than returning
     * the encoding helpers' {@code null} sentinel.
     */
    @SneakyThrows
    public static NbtMap base64ToNbt(String base64) {
        return (NbtMap) org.cloudburstmc.nbt.NbtUtils.createReader(new ByteArrayInputStream(Base64.getDecoder().decode(base64))).readTag();
    }

    /**
     * Reads consecutive little-endian compound roots until the byte input is exhausted. The result
     * is a mutable list in stream order; malformed trailing bytes or non-compound roots fail
     * the conversion rather than returning a partial list.
     */
    @SneakyThrows
    public static List<NbtMap> bytesToNbtListLE(byte[] bytes) {
        List<NbtMap> tags = new ArrayList<>();
        try (var stream = new BufferedInputStream(new ByteArrayInputStream(bytes));
             var readerLE = NbtUtils.createReaderLE(stream)) {
            while (stream.available() > 0) {
                tags.add((NbtMap) readerLE.readTag());
            }
        }

        return tags;
    }

    /**
     * Writes compounds consecutively as little-endian root tags in list order. The result contains
     * no enclosing NBT list tag, and {@code bytesToNbtListLE} reads that same sequence until
     * the encoded bytes are exhausted.
     */
    @SneakyThrows
    public static byte[] nbtListToBytesLE(List<NbtMap> tags) {
        try (var stream = new ByteArrayOutputStream();
             var writerLE = NbtUtils.createWriterLE(stream)) {
            for (NbtMap tag : tags) {
                writerLE.writeTag(tag);
            }
            return stream.toByteArray();
        }
    }

    /**
     * Reads the first little-endian compound root from the supplied bytes. Additional trailing
     * data is not decoded by this operation, and malformed NBT or a non-compound root propagates
     * a conversion failure.
     */
    @SneakyThrows
    public static NbtMap bytesToNbtLE(byte[] bytes) {
        try (var stream = new BufferedInputStream(new ByteArrayInputStream(bytes));
             var readerLE = NbtUtils.createReaderLE(stream)) {
            return (NbtMap) readerLE.readTag();
        }
    }

    /**
     * Writes one compound root to a new little-endian binary NBT array.
     */
    @SneakyThrows
    public static byte[] nbtToBytesLE(NbtMap nbt) {
        try (var stream = new ByteArrayOutputStream();
             var writerLE = NbtUtils.createWriterLE(stream)) {
            writerLE.writeTag(nbt);
            return stream.toByteArray();
        }
    }

    /**
     * Reads the first 3 float components from the named typed list. Missing, mistyped,
     * or empty lists yield a new origin vector; nonempty lists with fewer than 3 entries
     * still fail during indexed component access.
     */
    public static Vector3f readVector3f(NbtMap nbt, String rootName) {
        var pos = nbt.getList(rootName, NbtType.FLOAT);
        // Missing or mistyped vector lists use the origin rather than retaining an absent
        // position, while a nonempty list still needs every coordinate expected by this reader.
        if (pos == null || pos.isEmpty()) {
            return new Vector3f(0, 0, 0);
        }
        return new Vector3f(pos.get(0), pos.get(1), pos.get(2));
    }

    /**
     * Writes the vector's three current float components to the named NBT list.
     */
    public static void writeVector3f(NbtMapBuilder nbt, String rootName, Vector3fc vector3f) {
        writeVector3f(nbt, rootName, vector3f.x(), vector3f.y(), vector3f.z());
    }

    /**
     * Writes three float coordinates in their axis order to the named list.
     */
    public static void writeVector3f(NbtMapBuilder nbt, String rootName, float x, float y, float z) {
        nbt.putList(rootName, NbtType.FLOAT, x, y, z);
    }

    /**
     * Reads the first 3 integer components from the named typed list. Missing, mistyped,
     * or empty lists yield a new origin vector; nonempty lists with fewer than 3 entries
     * still fail during indexed component access.
     */
    public static Vector3i readVector3i(NbtMap nbt, String rootName) {
        var pos = nbt.getList(rootName, NbtType.INT);
        // Missing or mistyped vector lists use the origin rather than retaining an absent
        // position, while a nonempty list still needs every coordinate expected by this reader.
        if (pos == null || pos.isEmpty()) {
            return new Vector3i(0, 0, 0);
        }
        return new Vector3i(pos.get(0), pos.get(1), pos.get(2));
    }

    /**
     * Writes the vector's three current integer components to the named NBT list.
     */
    public static void writeVector3i(NbtMapBuilder nbt, String rootName, Vector3ic vector3i) {
        nbt.putList(rootName, NbtType.INT, vector3i.x(), vector3i.y(), vector3i.z());
    }

    /**
     * Reads the first 2 float components from the named typed list. Missing, mistyped,
     * or empty lists yield a new origin vector; nonempty lists with fewer than 2 entries
     * still fail during indexed component access.
     */
    public static Vector2f readVector2f(NbtMap nbt, String rootName) {
        var pos = nbt.getList(rootName, NbtType.FLOAT);
        // Missing or mistyped vector lists use the origin rather than retaining an absent
        // position, while a nonempty list still needs every coordinate expected by this reader.
        if (pos == null || pos.isEmpty()) {
            return new Vector2f(0, 0);
        }
        return new Vector2f(pos.get(0), pos.get(1));
    }

    /**
     * Writes two float coordinates in their axis order to the named list.
     */
    public static void writeVector2f(NbtMapBuilder nbt, String rootName, float x, float y) {
        nbt.putList(rootName, NbtType.FLOAT, x, y);
    }

    /**
     * Writes the vector's two current float components to the named NBT list.
     */
    public static void writeVector2f(NbtMapBuilder nbt, String rootName, Vector2fc vector2f) {
        nbt.putList(rootName, NbtType.FLOAT, vector2f.x(), vector2f.y());
    }
}
