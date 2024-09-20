package org.allaymc.api.utils;

import org.cloudburstmc.nbt.NbtMap;
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
        AllayNbtUtils.writeVector3f(nbt, "test", "x", "y", "z", new org.joml.Vector3f(1, 2, 3));
        var nbtMap = nbt.build();
        assertEquals(1, nbtMap.getCompound("test").getFloat("x"));
        assertEquals(2, nbtMap.getCompound("test").getFloat("y"));
        assertEquals(3, nbtMap.getCompound("test").getFloat("z"));
    }

    @Test
    void testReadVector3f() {
        var nbt = NbtMap.builder();
        nbt.putCompound("test", NbtMap.builder().putFloat("x", 1).putFloat("y", 2).putFloat("z", 3).build());
        var vector3f = AllayNbtUtils.readVector3f(nbt.build(), "test", "x", "y", "z");
        assertEquals(1, vector3f.x);
        assertEquals(2, vector3f.y);
        assertEquals(3, vector3f.z);
    }
}