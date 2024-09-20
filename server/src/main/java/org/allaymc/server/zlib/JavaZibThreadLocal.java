package org.allaymc.server.zlib;

import it.unimi.dsi.fastutil.io.FastByteArrayOutputStream;

import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * @author Cool_Loong
 */
public final class JavaZibThreadLocal implements ZlibProvider {
    private static final ThreadLocal<FastByteArrayOutputStream> FBAO = ThreadLocal.withInitial(() -> new FastByteArrayOutputStream(1024));
    private static final ThreadLocal<byte[]> BUFFER = ThreadLocal.withInitial(() -> new byte[8192]);
    private final ThreadLocal<Inflater> INFLATER = ThreadLocal.withInitial(Inflater::new);
    private int level;
    private final ThreadLocal<Deflater> DEFLATER = ThreadLocal.withInitial(() -> new Deflater(level));
    private CompressionType type;

    JavaZibThreadLocal(CompressionType type, int level) {
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
        try (var bos = FBAO.get()) {
            if (type == CompressionType.GZIP) {
                throw new UnsupportedOperationException(this.getClass().getSimpleName() + " dont support GZIP");
            } else {
                Deflater deflater = DEFLATER.get();
                try {
                    deflater.reset();
                    deflater.setInput(data);
                    deflater.finish();
                    bos.reset();
                    byte[] buffer = BUFFER.get();
                    int length = 0;
                    while (!deflater.finished()) {
                        int i = deflater.deflate(buffer);
                        bos.write(buffer, 0, i);
                        length += i;
                    }
                    byte[] output = new byte[length];
                    System.arraycopy(bos.array, 0, output, 0, length);
                    return output;
                } finally {
                    deflater.reset();
                }
            }
        }
    }

    @Override
    public byte[] inflate(byte[] data, int maxSize) throws IOException {
        try (var bos = FBAO.get()) {
            if (type == CompressionType.GZIP) {
                throw new UnsupportedOperationException(this.getClass().getSimpleName() + " dont support GZIP");
            } else {
                Inflater inflater = INFLATER.get();
                try {
                    inflater.reset();
                    inflater.setInput(data);
                    bos.reset();
                    byte[] buffer = BUFFER.get();
                    try {
                        int length = 0;
                        while (!inflater.finished()) {
                            int i = inflater.inflate(buffer);
                            length += i;
                            if (maxSize > 0 && length > maxSize) {
                                throw new IOException("Inflated data exceeds maximum size");
                            }
                            bos.write(buffer, 0, i);
                        }
                        byte[] output = new byte[length];
                        System.arraycopy(bos.array, 0, output, 0, length);
                        return output;
                    } catch (DataFormatException e) {
                        throw new IOException("Unable to inflate zlib stream", e);
                    }
                } finally {
                    inflater.end();
                }
            }
        }
    }
}
