package org.allaymc.api.utils;

import org.cloudburstmc.nbt.NbtMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Allay Project 2023/12/2
 *
 * @author daoge_cmd
 */
class NbtUtilsTest {

    static String NBT_BASE_64 = "CgAAAQgAdGVzdEZsYWcBAA==";

    @Test
    void nbtToBase64() {
        var nbtMap = NbtMap.builder().putBoolean("testFlag", true).build();
        var nbtB64 = NbtUtils.nbtToBase64(nbtMap);
        assertEquals("CgAAAQgAdGVzdEZsYWcBAA==", nbtB64);
    }

    @Test
    void base64ToNbt() {
        var nbtMap = NbtUtils.base64ToNbt(NBT_BASE_64);
        assertTrue(nbtMap.getBoolean("testFlag"));
    }
}