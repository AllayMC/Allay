package org.allaymc.api.utils;

import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author daoge_cmd
 */
class AllayNbtUtilsTest {
    static String NBT_BASE64_LE = "CgAAAQgAdGVzdEZsYWcBAA==";
    static String NBT_BASE64 = "CgAAAQAIdGVzdEZsYWcBAA==";

    @Test
    void testNbtToBase64() {
        var nbtMap = NbtMap.builder().putBoolean("testFlag", true).build();
        var nbtB64 = AllayNbtUtils.nbtToBase64(nbtMap);
        assertEquals(NBT_BASE64, nbtB64);
    }

    @Test
    void testNbtToBase64LE() {
        var nbtMap = NbtMap.builder().putBoolean("testFlag", true).build();
        var nbtB64 = AllayNbtUtils.nbtToBase64LE(nbtMap);
        assertEquals(NBT_BASE64_LE, nbtB64);
    }

    @Test
    void testBase64ToNbt() {
        var nbtMap = AllayNbtUtils.base64ToNbt(NBT_BASE64);
        assertTrue(nbtMap.getBoolean("testFlag"));
    }

    @Test
    void testBase64ToNbtLE() {
        var nbtMap = AllayNbtUtils.base64ToNbtLE(NBT_BASE64_LE);
        assertTrue(nbtMap.getBoolean("testFlag"));
    }

    @Test
    void testWriteVector3f() {
        var nbt = NbtMap.builder();
        AllayNbtUtils.writeVector3f(nbt, "test", new org.joml.Vector3f(1, 2, 3));
        var nbtMap = nbt.build();
        var list = nbtMap.getList("test", NbtType.FLOAT);
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void testReadVector3f() {
        var nbt = NbtMap.builder();
        nbt.putList("test", NbtType.FLOAT, 1f, 2f, 3f);
        var vector3f = AllayNbtUtils.readVector3f(nbt.build(), "test");
        assertEquals(1, vector3f.x);
        assertEquals(2, vector3f.y);
        assertEquals(3, vector3f.z);
    }
}