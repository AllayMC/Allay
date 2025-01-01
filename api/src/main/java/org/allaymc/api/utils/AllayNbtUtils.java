package org.allaymc.api.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.nbt.NBTOutputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.cloudburstmc.nbt.NbtType;
import org.joml.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

/**
 * @author daoge_cmd
 */
@Slf4j
public final class AllayNbtUtils {
    /**
     * Convert NBT to Base64 (LE).
     *
     * @param nbtMap the NBT map.
     *
     * @return the Base64 string.
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
     * @param nbtMap the NBT map.
     *
     * @return the Base64 string.
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
     * @param base64 the Base64 string.
     *
     * @return the NBT map.
     */
    @SneakyThrows
    public static NbtMap base64ToNbtLE(String base64) {
        return (NbtMap) org.cloudburstmc.nbt.NbtUtils.createReaderLE(new ByteArrayInputStream(Base64.getDecoder().decode(base64))).readTag();
    }

    /**
     * Convert Base64 to NBT.
     *
     * @param base64 the Base64 string.
     *
     * @return the NBT map.
     */
    @SneakyThrows
    public static NbtMap base64ToNbt(String base64) {
        return (NbtMap) org.cloudburstmc.nbt.NbtUtils.createReader(new ByteArrayInputStream(Base64.getDecoder().decode(base64))).readTag();
    }

    /**
     * Read a vector3 from NBT.
     *
     * @param nbt      the NBT map.
     * @param rootName the root name.
     *
     * @return the vector3.
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
     * @param nbt      the NBT builder.
     * @param rootName the root name.
     * @param vector3f the vector3.
     */
    public static void writeVector3f(NbtMapBuilder nbt, String rootName, Vector3fc vector3f) {
        writeVector3f(nbt, rootName, vector3f.x(), vector3f.y(), vector3f.z());
    }

    /**
     * Write a vector3 to NBT.
     *
     * @param nbt      the NBT builder.
     * @param rootName the root name.
     * @param x        the x value.
     * @param y        the y value.
     * @param z        the z value.
     */
    public static void writeVector3f(NbtMapBuilder nbt, String rootName, float x, float y, float z) {
        nbt.putList(rootName, NbtType.FLOAT, x, y, z);
    }

    /**
     * Read a vector3 from NBT.
     *
     * @param nbt      the NBT map.
     * @param rootName the root name.
     *
     * @return the vector3.
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
     * @param nbt      the NBT builder.
     * @param rootName the root name.
     * @param vector3i the vector3.
     */
    public static void writeVector3i(NbtMapBuilder nbt, String rootName, Vector3ic vector3i) {
        nbt.putList(rootName, NbtType.INT, vector3i.x(), vector3i.y(), vector3i.z());
    }

    /**
     * Read a vector2 from NBT.
     *
     * @param nbt      the NBT map.
     * @param rootName the root name.
     *
     * @return the vector2.
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
     * @param nbt      the NBT builder.
     * @param rootName the root name.
     * @param vector2f the vector2.
     */
    public static void writeVector2f(NbtMapBuilder nbt, String rootName, Vector2fc vector2f) {
        nbt.putList(rootName, NbtType.FLOAT, vector2f.x(), vector2f.y());
    }
}
