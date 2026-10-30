package org.allaymc.server.datastruct.palette;

import io.netty.buffer.ByteBuf;
import lombok.SneakyThrows;
import org.allaymc.api.utils.SemVersion;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.server.network.ProtocolInfo;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.nbt.util.stream.LittleEndianDataInputStream;

import java.io.IOException;

/**
 * {@code PaletteUtils} derives current block-state hashes from encoded little-endian NBT
 * and signals when a slower upgrade path is needed. The fast path expects canonical
 * block-state ordering with the {@code "version"} member last, and its stream must read
 * the same {@code ByteBuf} whose reader index is marked and reset.
 *
 * <p>
 * Hash input keeps the encoded prefix before {@code "version"}, replacing that member's
 * tag-type byte with a compound end marker so the bytes match the version-free registry
 * representation. A noncurrent version rewinds to the mark and returns
 * {@code HASH_NOT_LATEST}, allowing the storage codec to deserialize and upgrade the
 * original tag. Successful hashing consumes the entry; other sentinel exits do not
 * universally restore the starting position.
 * @author Cool_Loong
 */
public class PaletteUtils {

    /**
     * {@code HASH_NOT_LATEST} requests the storage codec's full NBT upgrade path instead
     * of a current-state hash. Only the noncurrent-version branch guarantees a
     * reader-index rewind before returning it.
     */
    public static final int HASH_NOT_LATEST = Integer.MAX_VALUE;

    /**
     * Reads a block-state tag and returns its current registry hash when the canonical
     * fast path succeeds. {@code input} and {@code byteBuf} must share one reader
     * position, and the compound must place {@code "version"} after the hash-relevant
     * entries.
     *
     * <p>
     * The method marks the starting index before reading the root type and name. A
     * noncurrent version resets that index and returns {@code HASH_NOT_LATEST}, allowing
     * the caller to parse the original tag again. Successful hashing consumes the version
     * field and end tag. Other unsupported structures can return the sentinel after
     * consuming bytes, so callers cannot assume every sentinel leaves the buffer rewound.
     */
    @SneakyThrows
    public static int fastReadBlockStateHash(LittleEndianDataInputStream input, ByteBuf byteBuf) {
        byteBuf.markReaderIndex();
        int typeId = input.readUnsignedByte();
        NbtType<?> type = NbtType.byId(typeId);
        // Root names do not enter the block-state payload, so skip their encoded bytes before
        // traversing members.
        input.skipBytes(input.readUnsignedShort());
        // Limit recursive NBT nesting while scanning the canonical block-state compound.
        return deserialize(input, byteBuf, type, 16);
    }

    /**
     * Traverses one NBT payload and extracts the canonical hash when a compound member
     * named {@code "version"} is encountered. {@code maxDepth} limits recursive nesting,
     * and a negative remaining depth throws {@code IllegalArgumentException}.
     *
     * <p>
     * The version branch resets the shared buffer before either returning the upgrade
     * sentinel or rebuilding the version-free prefix. For a current version, the copied
     * prefix ends at the member's tag type; replacing that byte with an end marker
     * preserves the hash representation while excluding version metadata. The routine then
     * skips the version payload and compound end. This assumes canonical field ordering
     * and matching stream and buffer positions, rather than handling arbitrary NBT
     * compound layouts.
     */
    private static int deserialize(LittleEndianDataInputStream input, ByteBuf byteBuf, NbtType<?> type, int maxDepth) throws IOException {
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
            case BYTE_ARRAY -> input.skipBytes(input.readInt());
            case STRING -> input.skipBytes(input.readUnsignedShort());
            case COMPOUND -> {
                NbtType<?> nbtType;
                while ((nbtType = NbtType.byId(input.readUnsignedByte())) != NbtType.END) {
                    int end = byteBuf.readerIndex();
                    String name = input.readUTF();
                    if (name.equals("version")) {
                        int version = input.readInt();
                        // Restore the original tag position before deciding between full NBT
                        // upgrade and byte-prefix hashing, because both paths need the encoded
                        // prefix.
                        byteBuf.resetReaderIndex();
                        if (version != ProtocolInfo.BLOCK_STATE_VERSION_NUM) {
                            return HASH_NOT_LATEST;
                        }
                        byte[] result = new byte[end - byteBuf.readerIndex()];
                        input.readFully(result);
                        // Replace the version member type with END so the prefix hashes exactly
                        // as a compound without the trailing version member.
                        result[result.length - 1] = 0;

                        input.skipBytes(input.readUnsignedShort());
                        deserialize(input, byteBuf, nbtType, maxDepth - 1);
                        input.skipBytes(1);
                        int hash = HashUtils.fnv1a_32(result);
                        if (hash == 147887818) {
                            // The runtime registry reserves -2 for minecraft:unknown, so
                            // translate its canonical encoded hash before returning a registry
                            // lookup identifier.
                            hash = -2;
                        }
                        return hash;
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
        return HASH_NOT_LATEST;
    }

    /**
     * Returns a semantic version from four unsigned bytes of the packed integer. The bytes
     * supply major, minor, patch, and revision in descending significance, while the fifth
     * semantic-version component is fixed at zero.
     */
    private static SemVersion toSemVersion(int version) {
        // Unpack high-to-low version bytes without sign extension so every semantic component
        // retains the unsigned byte value encoded in the packed integer.
        int major = (version >> 24) & 0xFF;
        int minor = (version >> 16) & 0xFF;
        int patch = (version >> 8) & 0xFF;
        int revision = version & 0xFF;
        return new SemVersion(major, minor, patch, revision, 0);
    }
}
