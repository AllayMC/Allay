package org.allaymc.api.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.nbt.NBTOutputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
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
     * @param f1       the x field.
     * @param f2       the y field.
     * @param f3       the z field.
     *
     * @return the vector3.
     */
    public static Vector3f readVector3f(NbtMap nbt, String rootName, String f1, String f2, String f3) {
        var pos = nbt.getCompound(rootName);
        return new Vector3f(pos.getFloat(f1), pos.getFloat(f2), pos.getFloat(f3));
    }

    /**
     * Write a vector3 to NBT.
     *
     * @param nbt      the NBT builder.
     * @param rootName the root name.
     * @param f1       the x field.
     * @param f2       the y field.
     * @param f3       the z field.
     * @param vector3f the vector3.
     */
    public static void writeVector3f(NbtMapBuilder nbt, String rootName, String f1, String f2, String f3, Vector3fc vector3f) {
        writeVector3f(nbt, rootName, f1, f2, f3, vector3f.x(), vector3f.y(), vector3f.z());
    }

    /**
     * Write a vector3 to NBT.
     *
     * @param nbt      the NBT builder.
     * @param rootName the root name.
     * @param f1       the x field.
     * @param f2       the y field.
     * @param f3       the z field.
     * @param x        the x value.
     * @param y        the y value.
     * @param z        the z value.
     */
    public static void writeVector3f(NbtMapBuilder nbt, String rootName, String f1, String f2, String f3, float x, float y, float z) {
        var pos = NbtMap.builder()
                .putFloat(f1, x)
                .putFloat(f2, y)
                .putFloat(f3, z)
                .build();
        nbt.putCompound(rootName, pos);
    }

    /**
     * Read a vector3 from NBT.
     *
     * @param nbt      the NBT map.
     * @param rootName the root name.
     * @param f1       the x field.
     * @param f2       the y field.
     * @param f3       the z field.
     *
     * @return the vector3.
     */
    public static Vector3i readVector3i(NbtMap nbt, String rootName, String f1, String f2, String f3) {
        var pos = nbt.getCompound(rootName);
        return new Vector3i(pos.getInt(f1), pos.getInt(f2), pos.getInt(f3));
    }

    /**
     * Write a vector3 to NBT.
     *
     * @param nbt      the NBT builder.
     * @param rootName the root name.
     * @param f1       the x field.
     * @param f2       the y field.
     * @param f3       the z field.
     * @param vector3i the vector3.
     */
    public static void writeVector3i(NbtMapBuilder nbt, String rootName, String f1, String f2, String f3, Vector3ic vector3i) {
        var pos = NbtMap.builder()
                .putInt(f1, vector3i.x())
                .putInt(f2, vector3i.y())
                .putInt(f3, vector3i.z())
                .build();
        nbt.putCompound(rootName, pos);
    }

    /**
     * Read a vector2 from NBT.
     *
     * @param nbt      the NBT map.
     * @param rootName the root name.
     * @param f1       the x field.
     * @param f2       the y field.
     *
     * @return the vector2.
     */
    public static Vector2f readVector2f(NbtMap nbt, String rootName, String f1, String f2) {
        var pos = nbt.getCompound(rootName);
        return new Vector2f(pos.getFloat(f1), pos.getFloat(f2));
    }

    /**
     * Write a vector2 to NBT.
     *
     * @param nbt      the NBT builder.
     * @param rootName the root name.
     * @param f1       the x field.
     * @param f2       the y field.
     * @param vector2f the vector2.
     */
    public static void writeVector2f(NbtMapBuilder nbt, String rootName, String f1, String f2, Vector2fc vector2f) {
        var pos = NbtMap.builder()
                .putFloat(f1, vector2f.x())
                .putFloat(f2, vector2f.y())
                .build();
        nbt.putCompound(rootName, pos);
    }
}
