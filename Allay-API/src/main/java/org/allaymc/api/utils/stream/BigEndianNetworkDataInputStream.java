package org.allaymc.api.utils.stream;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.nbt.util.VarInts;
import org.cloudburstmc.nbt.util.stream.LimitedDataInput;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class BigEndianNetworkDataInputStream extends LimitedDataInput {
    public BigEndianNetworkDataInputStream(InputStream stream) {
        this(stream, NbtUtils.MAX_READ_SIZE);
    }

    public BigEndianNetworkDataInputStream(InputStream stream, long maxReadSize) {
        super(new DataInputStream(stream), maxReadSize);
    }

    public BigEndianNetworkDataInputStream(DataInputStream stream) {
        this(stream, NbtUtils.MAX_READ_SIZE);
    }

    public BigEndianNetworkDataInputStream(DataInputStream stream, long maxReadSize) {
        super(stream, maxReadSize);
    }

    @Override
    public int readInt() throws IOException {
        this.tryRead(4); // assume that the int is 4 bytes
        return VarInts.readInt(this.delegate());
    }

    @Override
    public long readLong() throws IOException {
        this.tryRead(8); // assume that the long is 8 bytes
        return VarInts.readLong(this.delegate());
    }

    @Override
    @NonNull
    public String readUTF() throws IOException {
        int length = VarInts.readUnsignedInt(this.delegate());
        this.tryRead(length + 4);

        byte[] bytes = new byte[length];
        readFully(bytes);
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
