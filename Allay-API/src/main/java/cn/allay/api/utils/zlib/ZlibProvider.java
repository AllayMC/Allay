package cn.allay.api.utils.zlib;

import java.io.IOException;

/**
 * @author Cool_Loong <br>
 * @date 6/6/2023 <br>
 * Allay Project
 */
public interface ZlibProvider {
    byte[] deflate(byte[] data) throws IOException;

    byte[] inflate(byte[] data, int maxSize) throws IOException;

    void setLevel(int level);

    void setCompressionType(CompressionType type);
}
