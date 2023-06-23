package cn.allay.api.utils.zlib;

import it.unimi.dsi.fastutil.io.FastByteArrayOutputStream;

import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * @author Cool_Loong <br>
 * @date 6/6/2023 <br>
 * Allay Project
 */
public final class ZlibThreadLocal implements ZlibProvider {
    private static final ThreadLocal<FastByteArrayOutputStream> FBAO = ThreadLocal.withInitial(() -> new FastByteArrayOutputStream(1024));
    private static final ThreadLocal<byte[]> BUFFER = ThreadLocal.withInitial(() -> new byte[8192]);
    private int level;
    private CompressionType type;
    private final ThreadLocal<Inflater> INFLATER = ThreadLocal.withInitial(() -> new Inflater(type == CompressionType.GZIP));
    private final ThreadLocal<Deflater> DEFLATER = ThreadLocal.withInitial(() -> new Deflater(level, type == CompressionType.GZIP));

    ZlibThreadLocal(CompressionType type, int level) {
        this.type = type;
        this.level = level;
    }

    @Override
    public void setCompressionType(CompressionType type) {
        this.type = type;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public byte[] deflate(byte[] data) throws IOException {
        Deflater deflater = DEFLATER.get();
        FastByteArrayOutputStream bos = FBAO.get();
        try {
            deflater.reset();
            deflater.setInput(data);
            deflater.finish();
            bos.reset();
            byte[] buffer = BUFFER.get();
            while (!deflater.finished()) {
                int i = deflater.deflate(buffer);
                bos.write(buffer, 0, i);
            }
        } finally {
            deflater.reset();
        }
        return bos.array;
    }

    @Override
    public byte[] inflate(byte[] data, int maxSize) throws IOException {
        Inflater inflater = INFLATER.get();
        try {
            inflater.reset();
            inflater.setInput(data);
            inflater.finished();
            FastByteArrayOutputStream bos = FBAO.get();
            bos.reset();

            byte[] buffer = BUFFER.get();
            try {
                int length = 0;
                while (!inflater.finished()) {
                    int i = inflater.inflate(buffer);
                    length += i;
                    if (maxSize > 0 && length >= maxSize) {
                        throw new IOException("Inflated data exceeds maximum size");
                    }
                    bos.write(buffer, 0, i);
                }
                return bos.array;
            } catch (DataFormatException e) {
                throw new IOException("Unable to inflate zlib stream", e);
            }
        } finally {
            inflater.reset();
        }
    }
}
