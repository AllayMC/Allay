package org.allaymc.api.common.utils;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.Pair;
import org.allaymc.api.datastruct.SemVersion;
import org.allaymc.api.network.ProtocolInfo;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.util.stream.LittleEndianDataInputStream;

import java.io.IOException;

/**
 * Allay Project 11/12/2023
 *
 * @author Cool_Loong
 */
public class PaletteUtils {
    public static Pair<Integer, SemVersion> fastReadBlockHash(LittleEndianDataInputStream input, ByteBuf byteBuf) {
        try {
            byteBuf.markReaderIndex();
            int typeId = input.readUnsignedByte();
            NbtType<?> type = NbtType.byId(typeId);
            input.skipBytes(input.readUnsignedShort()); // Root tag name
            return deserialize(input, byteBuf, type, 16);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Pair<Integer, SemVersion> deserialize(LittleEndianDataInputStream input,
                                                         ByteBuf byteBuf,
                                                         NbtType<?> type,
                                                         int maxDepth
    ) throws IOException {
        if (maxDepth < 0) {
            throw new IllegalArgumentException("NBT compound is too deeply nested");
        }
        switch (type.getEnum()) {
            case END -> {
            }
            case BYTE -> input.skipBytes(1);
            case SHORT -> input.skipBytes(2);
            case INT, FLOAT -> input.skipBytes(4);
            case LONG, DOUBLE -> input.skipBytes(8);
            case BYTE_ARRAY -> {
                input.skipBytes(input.readInt());
            }
            case STRING -> input.skipBytes(input.readUnsignedShort());
            case COMPOUND -> {
                NbtType<?> nbtType;
                while ((nbtType = NbtType.byId(input.readUnsignedByte())) != NbtType.END) {
                    String name;
                    int end = byteBuf.readerIndex();
                    name = input.readUTF();
                    if (name.equals("version")) {
                        int version = input.readInt();
                        byteBuf.resetReaderIndex();
                        if (version != ProtocolInfo.BLOCK_STATE_VERSION) {
                            return Pair.of(null, getSemVersion(version));
                        }
                        byte[] result = new byte[end - byteBuf.readerIndex()];
                        input.readFully(result);
                        result[result.length - 1] = 0;//because an End Tag be put when at the end serialize tag

                        input.skipBytes(input.readUnsignedShort());//UTF
                        deserialize(input, byteBuf, nbtType, maxDepth - 1);//Value
                        input.skipBytes(1);//end tag
                        int i = HashUtils.fnv1a_32(result);
                        if (i == 147887818) i = -2;//minecraft:unknown
                        return Pair.of(i, null);
                    }
                    deserialize(input, byteBuf, nbtType, maxDepth - 1);
                }
            }
            case LIST -> {
                int typeId = input.readUnsignedByte();
                NbtType<?> listType = NbtType.byId(typeId);
                int listLength = input.readInt();
                for (int i = 0; i < listLength; i++) {
                    deserialize(input, byteBuf, listType, maxDepth - 1);
                }
            }
            case INT_ARRAY -> input.skipBytes(input.readInt() * 4);
            case LONG_ARRAY -> input.skipBytes(input.readInt() * 8);
        }
        return null;
    }

    public static SemVersion getSemVersion(int version) {
        int major = (version >> 24) & 0xFF;
        int minor = (version >> 16) & 0xFF;
        int patch = (version >> 8) & 0xFF;
        int revision = version & 0xFF;
        return new SemVersion(major, minor, patch, revision, 0);
    }
}
