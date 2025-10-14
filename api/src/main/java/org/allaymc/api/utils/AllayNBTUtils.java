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
 * @author daoge_cmd
 */
@Slf4j
public final class AllayNBTUtils {
    /**
     * Convert NBT to Base64 (LE).
     *
     * @param nbtMap the NBT map
     * @return the Base64 string
     */
    public static String nbtToBase64LE(NbtMap nbtMap) {
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream(); NBTOutputStream writer = org.cloudburstmc.nbt.NbtUtils.createWriterLE(stream)) {
            writer.writeTag(nbtMap);
            return Base64.getEncoder().encodeToString(stream.toByteArray());
        } catch (Exception e) {
            // Handle exceptions accordingly
            log.error("Failed to convert NBT to Base64", e);
        }
        return null;
    }

    /**
     * Convert NBT to Base64.
     *
     * @param nbtMap the NBT map
     * @return the Base64 string
     */
    public static String nbtToBase64(NbtMap nbtMap) {
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream(); NBTOutputStream writer = org.cloudburstmc.nbt.NbtUtils.createWriter(stream)) {
            writer.writeTag(nbtMap);
            return Base64.getEncoder().encodeToString(stream.toByteArray());
        } catch (Exception e) {
            // Handle exceptions accordingly
            log.error("Failed to convert NBT to Base64", e);
        }
        return null;
    }

    /**
     * Convert Base64 to NBT (LE).
     *
     * @param base64 the Base64 string
     * @return the NBT map
     */
    @SneakyThrows
    public static NbtMap base64ToNbtLE(String base64) {
        return (NbtMap) org.cloudburstmc.nbt.NbtUtils.createReaderLE(new ByteArrayInputStream(Base64.getDecoder().decode(base64))).readTag();
    }

    /**
     * Convert Base64 to NBT.
     *
     * @param base64 the Base64 string
     * @return the NBT map
     */
    @SneakyThrows
    public static NbtMap base64ToNbt(String base64) {
        return (NbtMap) org.cloudburstmc.nbt.NbtUtils.createReader(new ByteArrayInputStream(Base64.getDecoder().decode(base64))).readTag();
    }

    /**
     * Convert bytes to nbt list.
     *
     * @param bytes the bytes
     * @return the nbt list
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
     * Convert nbt list to bytes.
     *
     * @param tags the nbt list
     * @return the bytes
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
     * Convert bytes to nbt.
     *
     * @param bytes the bytes
     * @return the nbt
     */
    @SneakyThrows
    public static NbtMap bytesToNbtLE(byte[] bytes) {
        try (var stream = new BufferedInputStream(new ByteArrayInputStream(bytes));
             var readerLE = NbtUtils.createReaderLE(stream)) {
            return (NbtMap) readerLE.readTag();
        }
    }

    /**
     * Convert nbt to bytes.
     *
     * @param nbt the nbt
     * @return the bytes
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
     * Read a vector3 from NBT.
     *
     * @param nbt      the NBT map
     * @param rootName the root name
     * @return the vector3
     */
    public static Vector3f readVector3f(NbtMap nbt, String rootName) {
        var pos = nbt.getList(rootName, NbtType.FLOAT);
        if (pos == null) {
            return new Vector3f(0, 0, 0);
        }
        return new Vector3f(pos.get(0), pos.get(1), pos.get(2));
    }

    /**
     * Write a vector3 to NBT.
     *
     * @param nbt      the NBT builder
     * @param rootName the root name
     * @param vector3f the vector3
     */
    public static void writeVector3f(NbtMapBuilder nbt, String rootName, Vector3fc vector3f) {
        writeVector3f(nbt, rootName, vector3f.x(), vector3f.y(), vector3f.z());
    }

    /**
     * Write a vector3 to NBT.
     *
     * @param nbt      the NBT builder
     * @param rootName the root name
     * @param x        the x value
     * @param y        the y value
     * @param z        the z value
     */
    public static void writeVector3f(NbtMapBuilder nbt, String rootName, float x, float y, float z) {
        nbt.putList(rootName, NbtType.FLOAT, x, y, z);
    }

    /**
     * Read a vector3 from NBT.
     *
     * @param nbt      the NBT map
     * @param rootName the root name
     * @return the vector3
     */
    public static Vector3i readVector3i(NbtMap nbt, String rootName) {
        var pos = nbt.getList(rootName, NbtType.INT);
        if (pos == null) {
            return new Vector3i(0, 0, 0);
        }
        return new Vector3i(pos.get(0), pos.get(1), pos.get(2));
    }

    /**
     * Write a vector3 to NBT.
     *
     * @param nbt      the NBT builder
     * @param rootName the root name
     * @param vector3i the vector3
     */
    public static void writeVector3i(NbtMapBuilder nbt, String rootName, Vector3ic vector3i) {
        nbt.putList(rootName, NbtType.INT, vector3i.x(), vector3i.y(), vector3i.z());
    }

    /**
     * Read a vector2 from NBT.
     *
     * @param nbt      the NBT map
     * @param rootName the root name
     * @return the vector2
     */
    public static Vector2f readVector2f(NbtMap nbt, String rootName) {
        var pos = nbt.getList(rootName, NbtType.FLOAT);
        if (pos == null) {
            return new Vector2f(0, 0);
        }
        return new Vector2f(pos.get(0), pos.get(1));
    }

    /**
     * @see #writeVector2f(NbtMapBuilder, String, Vector2fc)
     */
    public static void writeVector2f(NbtMapBuilder nbt, String rootName, float x, float y) {
        nbt.putList(rootName, NbtType.FLOAT, x, y);
    }

    /**
     * Write a vector2 to NBT.
     *
     * @param nbt      the NBT builder
     * @param rootName the root name
     * @param vector2f the vector2
     */
    public static void writeVector2f(NbtMapBuilder nbt, String rootName, Vector2fc vector2f) {
        nbt.putList(rootName, NbtType.FLOAT, vector2f.x(), vector2f.y());
    }
}
