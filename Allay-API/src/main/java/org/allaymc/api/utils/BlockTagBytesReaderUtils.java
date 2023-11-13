package org.allaymc.api.utils;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.util.stream.LittleEndianDataInputStream;

import java.io.IOException;

/**
 * Allay Project 11/12/2023
 *
 * @author Cool_Loong
 */
public class BlockTagBytesReaderUtils {
    public static byte[] fastRead(LittleEndianDataInputStream input, ByteBuf byteBuf) {
        try {
            byteBuf.markReaderIndex();
            int start = byteBuf.readerIndex();
            int typeId = input.readUnsignedByte();
            NbtType<?> type = NbtType.byId(typeId);
            input.readUTF(); // Root tag name
            return deserialize(input, byteBuf, type, 16, start);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] deserialize(LittleEndianDataInputStream input,
                                      ByteBuf byteBuf,
                                      NbtType<?> type,
                                      int maxDepth,
                                      int start
    ) throws IOException {
        if (maxDepth < 0) {
            throw new IllegalArgumentException("NBT compound is too deeply nested");
        }
        switch (type.getEnum()) {
            case END -> {
            }
            case BYTE -> input.readByte();
            case SHORT -> input.readShort();
            case INT -> input.readInt();
            case LONG -> input.readLong();
            case FLOAT -> input.readFloat();
            case DOUBLE -> input.readDouble();
            case BYTE_ARRAY -> {
                int arraySize = input.readInt();
                byte[] bytes = new byte[arraySize];
                input.readFully(bytes);
            }
            case STRING -> input.readUTF();
            case COMPOUND -> {
                NbtType<?> nbtType;
                while ((nbtType = NbtType.byId(input.readUnsignedByte())) != NbtType.END) {
                    String name;
                    int end = byteBuf.readerIndex();
                    name = input.readUTF();
                    if (name.equals("version")) {
                        byteBuf.resetReaderIndex();
                        byte[] result = new byte[end - start];
                        byteBuf.readBytes(result);
                        result[result.length - 1] = 0;//because an End Tag be put when at the end serialize tag
                        input.readUTF();
                        deserialize(input, byteBuf, nbtType, maxDepth - 1, start);
                        input.readUnsignedByte();
                        return result;
                    }
                    deserialize(input, byteBuf, nbtType, maxDepth - 1, start);
                }
            }
            case LIST -> {
                int typeId = input.readUnsignedByte();
                NbtType<?> listType = NbtType.byId(typeId);
                int listLength = input.readInt();
                for (int i = 0; i < listLength; i++) {
                    deserialize(input, byteBuf, listType, maxDepth - 1, start);
                }
            }
            case INT_ARRAY -> {
                int arraySize = input.readInt();
                for (int i = 0; i < arraySize; i++) {
                    input.readInt();
                }
            }
            case LONG_ARRAY -> {
                int arraySize = input.readInt();
                for (int i = 0; i < arraySize; i++) {
                    input.readLong();
                }
            }
        }
        return null;
    }
}
