package org.allaymc.api.zlib;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Allay Project 8/9/2023
 *
 * @author Cool_Loong
 */
public class ZlibTest {
    final ZlibProvider JAVA_ZLIB = ZlibProviderType.JavaZlibThreadLocal.of(CompressionType.ZLIB, 6);
    final ZlibProvider LIB_DEFLATE_ZLIB = ZlibProviderType.LibDeflateThreadLocal.of(CompressionType.ZLIB, 6);
    final ZlibProvider LIB_DEFLATE_GZIP = ZlibProviderType.LibDeflateThreadLocal.of(CompressionType.GZIP, 6);

    final byte[] TEST_DATA = new byte[]{122, 1, -23, 34, 123, 35, 65, 78, 91, 51, -12, 32, -4, 5, -65, -123, 12, 32, 45, 94, 123};
    final byte[] HELLO_WORLD_ZLIB = new byte[]{120, -100, -13, 72, -51, -55, -55, 87, 8, -49, 47, -54, 73, 81, 4, 0, 28, 73, 4, 62};
    final byte[] HELLO_WORLD_GZIP = new byte[]{31, -117, 8, 0, 0, 0, 0, 0, 0, -1, 1, 12, 0, -13, -1, 72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100, 33, -93, 28, 41, 28, 12, 0, 0, 0};

    @Test
    void testCompressZlib() {
        try {
            byte[] deflate = JAVA_ZLIB.deflate(TEST_DATA);
            byte[] inflate = JAVA_ZLIB.inflate(deflate, TEST_DATA.length);
            Assertions.assertArrayEquals(TEST_DATA, inflate);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            byte[] deflate = LIB_DEFLATE_ZLIB.deflate(TEST_DATA);
            byte[] inflate = LIB_DEFLATE_ZLIB.inflate(deflate, TEST_DATA.length);
            Assertions.assertArrayEquals(TEST_DATA, inflate);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testDecompressZlib() {
        try {
            byte[] inflate = JAVA_ZLIB.inflate(HELLO_WORLD_ZLIB, HELLO_WORLD_ZLIB.length);
            String s = new String(inflate);
            Assertions.assertEquals("Hello World!", s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            byte[] inflate = LIB_DEFLATE_ZLIB.inflate(HELLO_WORLD_ZLIB, HELLO_WORLD_ZLIB.length);
            String s = new String(inflate);
            Assertions.assertEquals("Hello World!", s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testCompressGZip() {
        try {
            byte[] deflate = LIB_DEFLATE_GZIP.deflate(TEST_DATA);
            byte[] inflate = LIB_DEFLATE_GZIP.inflate(deflate, TEST_DATA.length);
            Assertions.assertArrayEquals(TEST_DATA, inflate);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testDecompressGZip() {
        try {
            byte[] inflate = LIB_DEFLATE_GZIP.inflate(HELLO_WORLD_GZIP, HELLO_WORLD_GZIP.length);
            String s = new String(inflate);
            Assertions.assertEquals("Hello World!", s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
