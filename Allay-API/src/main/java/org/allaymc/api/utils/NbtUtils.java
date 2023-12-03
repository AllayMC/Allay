package org.allaymc.api.utils;

import lombok.SneakyThrows;
import org.cloudburstmc.nbt.NBTOutputStream;
import org.cloudburstmc.nbt.NbtMap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

/**
 * Allay Project 2023/12/2
 *
 * @author daoge_cmd
 */
public final class NbtUtils {
    public static String nbtToBase64(NbtMap nbtMap) {
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream(); NBTOutputStream writer = org.cloudburstmc.nbt.NbtUtils.createWriterLE(stream)) {
            writer.writeTag(nbtMap);
            return Base64.getEncoder().encodeToString(stream.toByteArray());
        } catch (Exception e) {
            // Handle exceptions accordingly
            e.printStackTrace();
        }
        return null;
    }

    @SneakyThrows
    public static NbtMap base64ToNbt(String base64) {
        return (NbtMap) org.cloudburstmc.nbt.NbtUtils.createReaderLE(new ByteArrayInputStream(Base64.getDecoder().decode(base64))).readTag();
    }
}
