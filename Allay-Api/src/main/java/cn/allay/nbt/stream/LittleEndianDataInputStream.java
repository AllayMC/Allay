package cn.allay.nbt.stream;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class LittleEndianDataInputStream extends FilterInputStream implements DataInput {
    public LittleEndianDataInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read(byte[] b) throws IOException {
        return this.in.read(b, 0, b.length);

    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return this.in.read(b, off, len);
    }

    @Override
    public void readFully(byte[] b) throws IOException {
        this.readFully(b, 0, b.length);
    }

    @Override
    public void readFully(byte[] b, int off, int len) throws IOException {
        if (len < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            int read;
            for (int pos = 0; pos < len; pos += read) {
                read = this.in.read(b, off + pos, len - pos);
                if (read < 0) {
                    throw new EOFException();
                }
            }
        }
    }

    @Override
    public int skipBytes(int n) throws IOException {
        int total = 0;
        int skipped = 0;
        while (total < n && (skipped = (int) this.in.skip(n - total)) > 0) {
            total += skipped;
        }

        return total;
    }

    @Override
    public boolean readBoolean() throws IOException {
        int val = this.in.read();
        if (val < 0) {
            throw new EOFException();
        }

        return val != 0;
    }

    @Override
    public byte readByte() throws IOException {
        int val = this.in.read();
        if (val < 0) {
            throw new EOFException();
        }

        return (byte) val;
    }

    @Override
    public int readUnsignedByte() throws IOException {
        int val = this.in.read();
        if (val < 0) {
            throw new EOFException();
        }

        return val;
    }

    @Override
    public short readShort() throws IOException {
        int b1 = this.in.read();
        int b2 = this.in.read();
        if ((b1 | b2) < 0) {
            throw new EOFException();
        }

        return (short) (b1 | (b2 << 8));
    }

    @Override
    public int readUnsignedShort() throws IOException {
        int b1 = this.in.read();
        int b2 = this.in.read();
        if ((b1 | b2) < 0) {
            throw new EOFException();
        }

        return b1 | (b2 << 8);
    }

    @Override
    public char readChar() throws IOException {
        int b1 = this.in.read();
        int b2 = this.in.read();
        if ((b1 | b2) < 0) {
            throw new EOFException();
        }

        return (char) (b1 | (b2 << 8));
    }

    @Override
    public int readInt() throws IOException {
        int b1 = this.in.read();
        int b2 = this.in.read();
        int b3 = this.in.read();
        int b4 = this.in.read();
        if ((b1 | b2 | b3 | b4) < 0) {
            throw new EOFException();
        }

        return b1 | (b2 << 8) | (b3 << 16) | (b4 << 24);
    }

    @Override
    public long readLong() throws IOException {
        long b1 = this.in.read();
        long b2 = this.in.read();
        long b3 = this.in.read();
        long b4 = this.in.read();
        long b5 = this.in.read();
        long b6 = this.in.read();
        long b7 = this.in.read();
        long b8 = this.in.read();
        if ((b1 | b2 | b3 | b4 | b5 | b6 | b7 | b8) < 0) {
            throw new EOFException();
        }

        return b1 | (b2 << 8) | (b3 << 16) | (b4 << 24) | (b5 << 32) | (b6 << 40) | (b7 << 48) | (b8 << 56);
    }

    @Override
    public float readFloat() throws IOException {
        return Float.intBitsToFloat(this.readInt());
    }

    @Override
    public double readDouble() throws IOException {
        return Double.longBitsToDouble(this.readLong());
    }

    @Override
    public String readLine() throws IOException {
        throw new UnsupportedOperationException("Use readUTF.");
    }

    @Override
    public String readUTF() throws IOException {
        byte[] bytes = new byte[this.readUnsignedShort()];
        this.readFully(bytes);

        return new String(bytes, StandardCharsets.UTF_8);
    }
}
