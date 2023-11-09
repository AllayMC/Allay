package org.allaymc.api.zlib;


import cn.powernukkitx.libdeflate.LibdeflateCompressor;
import cn.powernukkitx.libdeflate.LibdeflateDecompressor;

import java.io.IOException;
import java.util.zip.DataFormatException;

/**
 * Allay Project 2023/6/6
 *
 * @author Cool_Loong
 */
public final class LibDeflateThreadLocal implements ZlibProvider {
    private final ThreadLocal<LibdeflateDecompressor> PNX_INFLATER = ThreadLocal.withInitial(LibdeflateDecompressor::new);
    private CompressionType type;
    private int level;
    private final ThreadLocal<LibdeflateCompressor> DEFLATER = ThreadLocal.withInitial(() -> new LibdeflateCompressor(level));

    LibDeflateThreadLocal(CompressionType type, int level) {
        this.type = type;
        this.level = level;
    }

    @Override
    public void setCompressionType(CompressionType type) {
        this.type = type;
    }

    @Override
    public void setCompressionLevel(int level) {
        this.level = level;
    }

    @Override
    public byte[] deflate(byte[] data) throws IOException {
        try (LibdeflateCompressor deflater = DEFLATER.get()) {
            var t = type == CompressionType.ZLIB ? cn.powernukkitx.libdeflate.CompressionType.ZLIB : cn.powernukkitx.libdeflate.CompressionType.GZIP;
            int compressUpperBound = (int) deflater.getCompressBound(data.length, t);
            byte[] buffer = new byte[compressUpperBound];
            int compressedSize = deflater.compress(data, buffer, t);
            byte[] output = new byte[compressedSize];
            System.arraycopy(buffer, 0, output, 0, compressedSize);
            return output;
        } catch (Exception e) {
            throw new IOException("Deflate error.");
        }
    }


    @Override
    public byte[] inflate(byte[] data, int maxSize) throws IOException {
        var t = type == CompressionType.ZLIB ? cn.powernukkitx.libdeflate.CompressionType.ZLIB : cn.powernukkitx.libdeflate.CompressionType.GZIP;
        try (LibdeflateDecompressor pnxInflater = PNX_INFLATER.get()) {
            byte[] buffer = new byte[maxSize];
            try {
                var result = pnxInflater.decompressUnknownSize(data, 0, data.length, buffer, 0, maxSize, t);
                if (maxSize > 0 && result > maxSize) {
                    throw new IOException("Inflated data exceeds maximum size");
                }
                byte[] output = new byte[(int) result];
                System.arraycopy(buffer, 0, output, 0, output.length);
                return output;
            } catch (DataFormatException e) {
                throw new IOException("Unable to inflate zlib stream", e);
            }
        }
    }
}
