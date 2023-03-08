package cn.allay.nbt.stream;

import java.io.DataOutput;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class LittleEndianDataOutputStream extends FilterOutputStream implements DataOutput {
    public LittleEndianDataOutputStream(OutputStream out) {
        super(out);
    }

    @Override
    public synchronized void write(int b) throws IOException {
        this.out.write(b);
    }

    @Override
    public synchronized void write(byte[] b, int off, int len) throws IOException {
        this.out.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override
    public void writeBoolean(boolean b) throws IOException {
        this.out.write(b ? 1 : 0);
    }

    @Override
    public void writeByte(int b) throws IOException {
        this.out.write(b);
    }

    @Override
    public void writeShort(int s) throws IOException {
        this.out.write(s & 0xFF);
        this.out.write((s >>> 8) & 0xFF);
    }

    @Override
    public void writeChar(int c) throws IOException {
        this.out.write(c & 0xFF);
        this.out.write((c >>> 8) & 0xFF);
    }

    @Override
    public void writeInt(int i) throws IOException {
        this.out.write(i & 0xFF);
        this.out.write((i >>> 8) & 0xFF);
        this.out.write((i >>> 16) & 0xFF);
        this.out.write((i >>> 24) & 0xFF);
    }

    @Override
    public void writeLong(long l) throws IOException {
        this.out.write((int) (l & 0xFF));
        this.out.write((int) ((l >>> 8) & 0xFF));
        this.out.write((int) ((l >>> 16) & 0xFF));
        this.out.write((int) ((l >>> 24) & 0xFF));
        this.out.write((int) ((l >>> 32) & 0xFF));
        this.out.write((int) ((l >>> 40) & 0xFF));
        this.out.write((int) ((l >>> 48) & 0xFF));
        this.out.write((int) ((l >>> 56) & 0xFF));
    }

    @Override
    public void writeFloat(float f) throws IOException {
        this.writeInt(Float.floatToIntBits(f));
    }

    @Override
    public void writeDouble(double d) throws IOException {
        this.writeLong(Double.doubleToLongBits(d));
    }

    @Override
    public void writeBytes(String s) throws IOException {
        int len = s.length();
        for (int index = 0; index < len; index++) {
            this.out.write((byte) s.charAt(index));
        }
    }

    @Override
    public void writeChars(String s) throws IOException {
        int len = s.length();
        for (int index = 0; index < len; index++) {
            char c = s.charAt(index);
            this.out.write(c & 0xFF);
            this.out.write((c >>> 8) & 0xFF);
        }
    }

    @Override
    public void writeUTF(String s) throws IOException {
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);

        this.writeShort(bytes.length);
        this.write(bytes);
    }
}
