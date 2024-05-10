package org.allaymc.api.zlib;

import java.io.IOException;

/**
 * Allay Project 2023/6/6
 *
 * @author Cool_Loong
 */
public interface ZlibProvider {

    /**
     * Compress the specified byte array
     *
     * @param data the array to compress
     *
     * @return compressed byte array
     *
     * @throws IOException compress Exception
     */
    byte[] deflate(byte[] data) throws IOException;

    /**
     * Decompress the specified byte array
     *
     * @param data    the array to be decompressed
     * @param maxSize The maximum length of the estimated result byte array
     *
     * @return decompressed byte array
     *
     * @throws IOException Decompress Exception
     */
    byte[] inflate(byte[] data, int maxSize) throws IOException;

    /**
     * set compression level
     *
     * @param level the level
     */
    void setCompressionLevel(int level);

    /**
     * Set compression type.
     *
     * @param type the type
     */
    void setCompressionType(CompressionType type);
}
