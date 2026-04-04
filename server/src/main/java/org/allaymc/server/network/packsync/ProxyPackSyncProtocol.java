package org.allaymc.server.network.packsync;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public final class ProxyPackSyncProtocol {

    public static final byte VERSION = 1;

    public static final byte PACK_LIST_REQUEST = 1;
    public static final byte PACK_LIST_RESPONSE = 2;
    public static final byte PACK_CHUNK_REQUEST = 3;
    public static final byte PACK_CHUNK_RESPONSE = 4;
    public static final byte ERROR_RESPONSE = 127;

    private ProxyPackSyncProtocol() {
    }

    public static void writeHeader(DataOutputStream output, byte packetType) throws IOException {
        output.writeByte(VERSION);
        output.writeByte(packetType);
    }

    public static byte readAndValidateVersion(DataInputStream input) throws IOException {
        byte version = input.readByte();
        if (version != VERSION) {
            throw new IOException("Unsupported proxy pack sync protocol version: " + version);
        }
        return input.readByte();
    }

    public static void writeUuid(DataOutputStream output, UUID uuid) throws IOException {
        output.writeLong(uuid.getMostSignificantBits());
        output.writeLong(uuid.getLeastSignificantBits());
    }

    public static UUID readUuid(DataInputStream input) throws IOException {
        return new UUID(input.readLong(), input.readLong());
    }

    public static void writeString(DataOutputStream output, String value) throws IOException {
        byte[] bytes = value == null ? new byte[0] : value.getBytes(StandardCharsets.UTF_8);
        output.writeInt(bytes.length);
        output.write(bytes);
    }

    public static String readString(DataInputStream input) throws IOException {
        int length = input.readInt();
        if (length < 0) {
            throw new IOException("Negative string length");
        }
        byte[] bytes = input.readNBytes(length);
        if (bytes.length != length) {
            throw new IOException("Unexpected EOF while reading string");
        }
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static void writeBytes(DataOutputStream output, byte[] bytes) throws IOException {
        output.writeInt(bytes.length);
        output.write(bytes);
    }

    public static byte[] readBytes(DataInputStream input) throws IOException {
        int length = input.readInt();
        if (length < 0) {
            throw new IOException("Negative byte array length");
        }
        byte[] bytes = input.readNBytes(length);
        if (bytes.length != length) {
            throw new IOException("Unexpected EOF while reading byte array");
        }
        return bytes;
    }
}
