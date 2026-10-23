package org.allaymc.api.utils;

import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@code AllayNBTUtilsTest} verifies binary/Base64 compound conversion and typed vector component
 * order.
 *
 * @author daoge_cmd
 */
class AllayNBTUtilsTest {
    // NBT_BASE64_LE and NBT_BASE64 encode the same boolean compound using opposite byte
    // orders, so encoder fixtures also provide known input for the corresponding decoder tests.
    /**
     * Little-endian compound Base64 fixture.
     */
    static String NBT_BASE64_LE = "CgAAAQgAdGVzdEZsYWcBAA==";
    /**
     * Big-endian compound Base64 fixture.
     */
    static String NBT_BASE64 = "CgAAAQAIdGVzdEZsYWcBAA==";

    /**
     * Encodes a compound containing one boolean flag using big-endian NBT. The resulting Base64
     * text must match the fixed representation used by the decoder test.
     */
    @Test
    void testNbtToBase64() {
        var nbtMap = NbtMap.builder().putBoolean("testFlag", true).build();
        var nbtB64 = AllayNBTUtils.nbtToBase64(nbtMap);
        assertEquals(NBT_BASE64, nbtB64);
    }

    /**
     * Encodes a compound containing one boolean flag using little-endian NBT. The resulting Base64
     * text must match the fixed representation used by the decoder test.
     */
    @Test
    void testNbtToBase64LE() {
        var nbtMap = NbtMap.builder().putBoolean("testFlag", true).build();
        var nbtB64 = AllayNBTUtils.nbtToBase64LE(nbtMap);
        assertEquals(NBT_BASE64_LE, nbtB64);
    }

    /**
     * Decodes the fixed big-endian compound fixture from Base64. The stored boolean flag must be
     * available with its original {@code true} value after decoding.
     */
    @Test
    void testBase64ToNbt() {
        var nbtMap = AllayNBTUtils.base64ToNbt(NBT_BASE64);
        assertTrue(nbtMap.getBoolean("testFlag"));
    }

    /**
     * Decodes the fixed little-endian compound fixture from Base64. The stored boolean flag must
     * be available with its original {@code true} value after decoding.
     */
    @Test
    void testBase64ToNbtLE() {
        var nbtMap = AllayNBTUtils.base64ToNbtLE(NBT_BASE64_LE);
        assertTrue(nbtMap.getBoolean("testFlag"));
    }

    /**
     * Writes two separate compound roots to one little-endian byte sequence. Reading the sequence
     * must restore both compounds in their original list order.
     */
    @Test
    void testBytesAndNbtListConverter() {
        var nbtList = List.of(
                NbtMap.builder().putBoolean("testFlag1", true).build(),
                NbtMap.builder().putBoolean("testFlag2", true).build()
        );
        assertEquals(nbtList, AllayNBTUtils.bytesToNbtListLE(AllayNBTUtils.nbtListToBytesLE(nbtList)));
    }

    /**
     * Writes a compound containing a boolean flag to little-endian bytes. Reading that compound
     * must produce equal NBT rather than changing its stored representation.
     */
    @Test
    void testBytesAndNbtConverter() {
        var nbt = NbtMap.builder().putBoolean("testFlag", true).build();
        assertEquals(nbt, AllayNBTUtils.bytesToNbtLE(AllayNBTUtils.nbtToBytesLE(nbt)));
    }

    /**
     * Writes a three-component float vector into a typed NBT list. Indexed reads must expose the
     * original x, y, and z values in that order.
     */
    @Test
    void testWriteVector3f() {
        var nbt = NbtMap.builder();
        AllayNBTUtils.writeVector3f(nbt, "test", new org.joml.Vector3f(1, 2, 3));
        var nbtMap = nbt.build();
        var list = nbtMap.getList("test", NbtType.FLOAT);
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    /**
     * Reads a typed float list containing three coordinates into a vector. The vector must retain
     * each supplied component on its corresponding x, y, or z axis.
     */
    @Test
    void testReadVector3f() {
        var nbt = NbtMap.builder();
        nbt.putList("test", NbtType.FLOAT, 1f, 2f, 3f);
        var vector3f = AllayNBTUtils.readVector3f(nbt.build(), "test");
        assertEquals(1, vector3f.x);
        assertEquals(2, vector3f.y);
        assertEquals(3, vector3f.z);
    }
}