package cn.allay.api.world.palette;

import cn.allay.api.world.chunk.Chunk;
import cn.allay.api.world.palette.bitarray.BitArray;
import cn.allay.api.world.palette.bitarray.BitArrayVersion;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import it.unimi.dsi.fastutil.objects.ReferenceArrayList;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NBTOutputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.io.IOException;
import java.util.List;

/**
 * Allay Project 2023/4/14
 *
 * @author JukeboxMC | daoge_cmd
 */
@Getter
@EqualsAndHashCode
public final class Palette<V> {
    public static final int COPY_LAST_FLAG_HEADER = (0x7F << 1) | 1;// 11111111b (1byte)

    private final List<V> palette;
    private BitArray bitArray;

    public Palette(V first) {
        this(first, BitArrayVersion.V2);
    }

    public Palette(V first, BitArrayVersion version) {
        this.bitArray = version.createArray(Chunk.SECTION_SIZE);
        this.palette = new ReferenceArrayList<>(16);
        this.palette.add(first);
    }

    private static int getPaletteHeader(BitArrayVersion version, boolean runtime) {
        return (version.bits << 1) | (runtime ? 1 : 0);
    }

    private static BitArrayVersion getVersionFromPaletteHeader(short header) {
        return BitArrayVersion.get(header >> 1, true);
    }

    private static boolean hasCopyLastFlag(short header) {
        return (header >> 1) == 0x7F;
    }

    private static boolean isPersistent(short header) {
        return (header & 1) == 0;
    }

    public V get(int index) {
        return this.palette.get(this.bitArray.get(index));
    }

    public void set(int index, V value) {
        final int paletteIndex = this.paletteIndexFor(value);
        this.bitArray.set(index, paletteIndex);
    }

    public void writeToNetwork(ByteBuf byteBuf, RuntimeDataSerializer<V> serializer) {
        writeWords(byteBuf, serializer);
    }

    public void writeToNetwork(ByteBuf byteBuf, RuntimeDataSerializer<V> serializer, Palette<V> last) {
        if (writeLast(byteBuf, last)) return;
        if (writeEmpty(byteBuf, serializer)) return;

        writeWords(byteBuf, serializer);
    }

    public void readFromNetwork(ByteBuf byteBuf, RuntimeDataDeserializer<V> deserializer) {
        readWords(byteBuf, readBitArrayVersion(byteBuf));

        final int size = this.bitArray.readSizeFromNetwork(byteBuf);
        for (int i = 0; i < size; i++) this.palette.add(deserializer.deserialize(VarInts.readInt(byteBuf)));
    }

    public void writeToStoragePersistent(ByteBuf byteBuf, PersistentDataSerializer<V> serializer) {
        byteBuf.writeByte(Palette.getPaletteHeader(this.bitArray.getVersion(), false));

        for (int word : this.bitArray.getWords()) byteBuf.writeIntLE(word);

        byteBuf.writeIntLE(this.palette.size());

        try (final ByteBufOutputStream bufOutputStream = new ByteBufOutputStream(byteBuf);
             final NBTOutputStream outputStream = NbtUtils.createWriterLE(bufOutputStream)) {
            for (V value : this.palette) outputStream.writeTag(serializer.serialize(value));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToStorageRuntime(ByteBuf byteBuf, RuntimeDataSerializer<V> serializer, Palette<V> last) {
        if (writeLast(byteBuf, last)) return;
        if (writeEmpty(byteBuf, serializer)) return;

        byteBuf.writeByte(Palette.getPaletteHeader(this.bitArray.getVersion(), true));
        for (int word : this.bitArray.getWords()) byteBuf.writeIntLE(word);
        byteBuf.writeIntLE(this.palette.size());
        for (V value : this.palette) byteBuf.writeIntLE(serializer.serialize(value));
    }

    public void readFromStoragePersistent(ByteBuf byteBuf, PersistentDataDeserializer<V> deserializer) {
        readWords(byteBuf, readBitArrayVersion(byteBuf));

        final int paletteSize = byteBuf.readIntLE();
        try (final ByteBufInputStream bufInputStream = new ByteBufInputStream(byteBuf);
             final NBTInputStream inputStream = NbtUtils.createReaderLE(bufInputStream)) {
            for (int i = 0; i < paletteSize; i++)
                this.palette.add(deserializer.deserialize((NbtMap) inputStream.readTag()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readFromStorageRuntime(ByteBuf byteBuf, RuntimeDataDeserializer<V> deserializer, Palette<V> last) {
        final short header = byteBuf.readUnsignedByte();

        if (Palette.hasCopyLastFlag(header)) {
            last.copyTo(this);
            return;
        }

        final BitArrayVersion version = Palette.getVersionFromPaletteHeader(header);
        if (version == BitArrayVersion.V0) {
            this.bitArray = version.createArray(Chunk.SECTION_SIZE, null);
            this.palette.clear();
            this.palette.add(deserializer.deserialize(byteBuf.readIntLE()));

            this.onResize(BitArrayVersion.V2);
            return;
        }

        readWords(byteBuf, version);

        final int paletteSize = byteBuf.readIntLE();
        for (int i = 0; i < paletteSize; i++) this.palette.add(deserializer.deserialize(byteBuf.readIntLE()));
    }

    private BitArrayVersion readBitArrayVersion(ByteBuf byteBuf) {
        short header = byteBuf.readUnsignedByte();
        return Palette.getVersionFromPaletteHeader(header);
    }

    private void readWords(ByteBuf byteBuf, BitArrayVersion version) {
        final int wordCount = version.getWordsForSize(Chunk.SECTION_SIZE);
        final int[] words = new int[wordCount];
        for (int i = 0; i < wordCount; i++) words[i] = byteBuf.readIntLE();

        this.bitArray = version.createArray(Chunk.SECTION_SIZE, words);
        this.palette.clear();
    }

    private void onResize(BitArrayVersion version) {
        final BitArray newBitArray = version.createArray(Chunk.SECTION_SIZE);
        for (int i = 0; i < Chunk.SECTION_SIZE; i++)
            newBitArray.set(i, this.bitArray.get(i));

        this.bitArray = newBitArray;
    }

    public int paletteIndexFor(V value) {
        int index = this.palette.indexOf(value);
        if (index != -1) return index;

        index = this.palette.size();
        this.palette.add(value);

        final BitArrayVersion version = this.bitArray.getVersion();
        if (index > version.maxEntryValue) {
            final BitArrayVersion next = version.next;
            if (next != null) this.onResize(next);
        }

        return index;
    }

    public boolean isEmpty() {
        boolean result = true;
        if (this.palette.size() > 1) result = false;
        for (int word : this.bitArray.getWords())
            if (Integer.toUnsignedLong(word) != 0L)
                result = false;
        return result;
    }

    public void copyTo(Palette<V> palette) {
        palette.bitArray = this.bitArray.copy();
        palette.palette.clear();
        palette.palette.addAll(this.palette);
    }

    private boolean writeEmpty(ByteBuf byteBuf, RuntimeDataSerializer<V> serializer) {
        if (this.isEmpty()) {
            byteBuf.writeByte(Palette.getPaletteHeader(BitArrayVersion.V0, true));
            byteBuf.writeIntLE(serializer.serialize(this.palette.get(0)));
            return true;
        }
        return false;
    }

    private boolean writeLast(ByteBuf byteBuf, Palette<V> last) {
        if (last != null && last.palette.equals(this.palette)) {
            byteBuf.writeByte(COPY_LAST_FLAG_HEADER);
            return true;
        }
        return false;
    }

    private void writeWords(ByteBuf byteBuf, RuntimeDataSerializer<V> serializer) {
        byteBuf.writeByte(getPaletteHeader(this.bitArray.getVersion(), true));

        for (int word : this.bitArray.getWords()) byteBuf.writeIntLE(word);

        this.bitArray.writeSizeToNetwork(byteBuf, this.palette.size());
        for (V value : this.palette) VarInts.writeInt(byteBuf, serializer.serialize(value));
    }
}