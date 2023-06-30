package cn.allay.api.utils.zlib;

import java.io.IOException;

/**
 * Allay Project 2023/6/6
 *
 * @author Cool_Loong
 */
public interface ZlibProvider {
    byte[] deflate(byte[] data) throws IOException;

    byte[] inflate(byte[] data, int maxSize) throws IOException;

    void setLevel(int level);

    void setCompressionType(CompressionType type);
}
