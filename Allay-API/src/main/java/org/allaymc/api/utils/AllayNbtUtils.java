package org.allaymc.api.utils;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.cloudburstmc.nbt.NBTOutputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtMapBuilder;
import org.joml.Vector2f;
import org.joml.Vector2fc;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

/**
 * Allay Project 2023/12/2
 *
 * @author daoge_cmd
 */
@Slf4j
public final class AllayNbtUtils {
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

    @SneakyThrows
    public static NbtMap base64ToNbtLE(String base64) {
        return (NbtMap) org.cloudburstmc.nbt.NbtUtils.createReaderLE(new ByteArrayInputStream(Base64.getDecoder().decode(base64))).readTag();
    }

    @SneakyThrows
    public static NbtMap base64ToNbt(String base64) {
        return (NbtMap) org.cloudburstmc.nbt.NbtUtils.createReader(new ByteArrayInputStream(Base64.getDecoder().decode(base64))).readTag();
    }

    public static Vector3f readVector3f(NbtMap nbt, String rootName, String f1, String f2, String f3) {
        var pos = nbt.getCompound(rootName);
        return new Vector3f(pos.getFloat(f1), pos.getFloat(f2), pos.getFloat(f3));
    }

    public static void writeVector3f(NbtMapBuilder nbt, String rootName, String f1, String f2, String f3, Vector3fc vector3f) {
        var pos = NbtMap.builder()
                .putFloat(f1, vector3f.x())
                .putFloat(f2, vector3f.y())
                .putFloat(f3, vector3f.z())
                .build();
        nbt.putCompound(rootName, pos);
    }

    public static Vector2f readVector2f(NbtMap nbt, String rootName, String f1, String f2) {
        var pos = nbt.getCompound(rootName);
        return new Vector2f(pos.getFloat(f1), pos.getFloat(f2));
    }

    public static void writeVector2f(NbtMapBuilder nbt, String rootName, String f1, String f2, Vector2fc vector2f) {
        var pos = NbtMap.builder()
                .putFloat(f1, vector2f.x())
                .putFloat(f2, vector2f.y())
                .build();
        nbt.putCompound(rootName, pos);
    }
}
