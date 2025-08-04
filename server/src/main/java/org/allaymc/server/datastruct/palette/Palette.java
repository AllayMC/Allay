package org.allaymc.server.datastruct.palette;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import it.unimi.dsi.fastutil.objects.ReferenceArrayList;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.server.datastruct.bitarray.BitArray;
import org.allaymc.server.datastruct.bitarray.BitArrayVersion;
import org.allaymc.server.datastruct.bitarray.SingletonBitArray;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author JukeboxMC | daoge_cmd | CoolLoong
 */
@Slf4j
public final class Palette<V> {

    private static final int SECTION_SIZE = 16 * 16 * 16;
    private static final BitArrayVersion INITIAL_VERSION = BitArrayVersion.V0;

    private List<V> palette;
    private BitArray bitArray;

    public Palette(V first) {
        this(first, INITIAL_VERSION);
    }

    public Palette(V first, BitArrayVersion version) {
        this(first, new ReferenceArrayList<>(version.maxEntryIndex + 1), version);
    }

    public Palette(V first, List<V> palette, BitArrayVersion version) {
        this.bitArray = version.createArray(SECTION_SIZE);
        this.palette = palette;
        // Please note that the first entry shouldn't be changed
        this.palette.add(first);
    }

    public V get(int index) {
        return this.palette.get(this.bitArray.get(index));
    }

    public void set(int index, V value) {
        var paletteIndex = this.paletteIndexFor(value);
        this.bitArray.set(index, paletteIndex);
    }

    public void writeToNetwork(ByteBuf byteBuf, IntSerializer<V> serializer, Palette<V> last) {
        if (last != null && last.equals(this)) {
            byteBuf.writeByte(createCopyLastFlag(false));
            return;
        }

        if (oneEntryOnly()) {
            byteBuf.writeByte(createPaletteHeader(BitArrayVersion.V0, false));
            VarInts.writeInt(byteBuf, serializer.serialize(this.palette.getFirst()));
            return;
        }

        byteBuf.writeByte(createPaletteHeader(this.bitArray.version(), false));
        for (int word : this.bitArray.words()) {
            byteBuf.writeIntLE(word);
        }
        VarInts.writeInt(byteBuf, this.palette.size());
        this.palette.forEach(value -> VarInts.writeInt(byteBuf, serializer.serialize(value)));
    }

    public void readFromNetwork(ByteBuf byteBuf, IntDeserializer<V> deserializer, Palette<V> last) {
        var header = byteBuf.readUnsignedByte();
        if (isNBT(header)) {
            throw new PaletteException("Reading nbt palette data with non-nbt method!");
        }

        if (hasCopyLastFlag(header)) {
            if (last == null) {
                throw new PaletteException("Find copy last flag but last palette is null!");
            }
            last.copyTo(this);
            return;
        }

        this.palette.clear();
        var version = getVersionFromPaletteHeader(header);
        readWords(byteBuf, version);
        if (version == BitArrayVersion.V0) {
            this.palette.add(deserializer.deserialize(VarInts.readInt(byteBuf)));
            return;
        }

        var paletteSize = VarInts.readInt(byteBuf);
        checkVersion(version, paletteSize);
        for (int i = 0; i < paletteSize; i++) {
            this.palette.add(deserializer.deserialize(VarInts.readInt(byteBuf)));
        }
    }

    // TODO: Maybe we can convert and cache the byte array of every block state tag, which will make chunk saving faster
    public void writeToStorage(ByteBuf byteBuf, NBTSerializer<V> serializer) {
        if (oneEntryOnly()) {
            byteBuf.writeByte(Palette.createPaletteHeader(BitArrayVersion.V0, true));
            try (var outputStream = NbtUtils.createWriterLE(new ByteBufOutputStream(byteBuf))) {
                outputStream.writeTag(serializer.serialize(this.palette.getFirst()));
            } catch (IOException e) {
                throw new PaletteException(e);
            }
            return;
        }

        byteBuf.writeByte(Palette.createPaletteHeader(this.bitArray.version(), true));
        for (int word : this.bitArray.words()) {
            byteBuf.writeIntLE(word);
        }
        byteBuf.writeIntLE(this.palette.size());
        try (var outputStream = NbtUtils.createWriterLE(new ByteBufOutputStream(byteBuf))) {
            for (V value : this.palette) {
                outputStream.writeTag(serializer.serialize(value));
            }
        } catch (IOException e) {
            throw new PaletteException(e);
        }
    }

    public void readFromStorage(ByteBuf byteBuf, NBTDeserializer<V> deserializer) {
        var header = byteBuf.readUnsignedByte();
        if (!isNBT(header)) {
            throw new PaletteException("Reading non-nbt palette data with nbt method!");
        }

        this.palette.clear();
        var version = getVersionFromPaletteHeader(header);
        readWords(byteBuf, version);
        if (version == BitArrayVersion.V0) {
            this.palette.add(deserializer.deserialize(byteBuf));
            return;
        }

        var paletteSize = byteBuf.readIntLE();
        checkVersion(version, paletteSize);
        for (int i = 0; i < paletteSize; i++) {
            this.palette.add(deserializer.deserialize(byteBuf));
        }
    }

    public void writeToStorage(ByteBuf byteBuf, IntSerializer<V> serializer, Palette<V> last) {
        if (last != null && last.equals(this)) {
            byteBuf.writeByte(createCopyLastFlag(false));
            return;
        }

        if (oneEntryOnly()) {
            byteBuf.writeByte(createPaletteHeader(BitArrayVersion.V0, false));
            byteBuf.writeIntLE(serializer.serialize(this.palette.getFirst()));
            return;
        }

        byteBuf.writeByte(Palette.createPaletteHeader(this.bitArray.version(), false));
        for (int word : this.bitArray.words()) {
            byteBuf.writeIntLE(word);
        }
        byteBuf.writeIntLE(this.palette.size());
        for (V value : this.palette) {
            byteBuf.writeIntLE(serializer.serialize(value));
        }
    }

    public void readFromStorage(ByteBuf byteBuf, IntDeserializer<V> deserializer, Palette<V> last) {
        var header = byteBuf.readUnsignedByte();
        if (isNBT(header)) {
            throw new PaletteException("Reading nbt palette data with non-nbt method!");
        }

        if (hasCopyLastFlag(header)) {
            if (last == null) {
                throw new PaletteException("Find copy last flag but last palette is null!");
            }
            last.copyTo(this);
            return;
        }

        this.palette.clear();
        var version = getVersionFromPaletteHeader(header);
        readWords(byteBuf, version);
        if (version == BitArrayVersion.V0) {
            this.palette.add(deserializer.deserialize(byteBuf.readIntLE()));
            return;
        }

        var paletteSize = byteBuf.readIntLE();
        checkVersion(version, paletteSize);
        for (int i = 0; i < paletteSize; i++) {
            this.palette.add(deserializer.deserialize(byteBuf.readIntLE()));
        }
    }

    public boolean oneEntryOnly() {
        if (this.palette.size() == 1) {
            return true;
        }

        // The palette list may contain more than one entry,
        // but the words are all point to the first entry.
        // In this case, the palette is still one entry only.

        // The reason why the bit array version is not V0 when
        // the palette size is one is that the bit array version
        // won't downgrade to V0 when the palette is cleared.
        for (int word : this.bitArray.words()) {
            // Do not use stream, this will be quicker
            if (Integer.toUnsignedLong(word) != 0L) {
                // The word is not point to the first entry,
                // so this palette shouldn't be empty
                return false;
            }
        }

        return true;
    }

    public boolean allEntriesMatch(Predicate<V> predicate) {
        for (var entry : palette) {
            if (!predicate.test(entry)) {
                return false;
            }
        }
        return true;
    }

    public void copyTo(Palette<V> palette) {
        palette.bitArray = this.bitArray.copy();
        palette.palette.clear();
        palette.palette.addAll(this.palette);
    }

    public BitArrayVersion getVersion() {
        return bitArray.version();
    }

    public void compact() {
        var newPalette = new ReferenceArrayList<V>();
        // Make sure the first entry won't be changed
        newPalette.add(palette.getFirst());
        var indexMapping = new int[SECTION_SIZE];
        var paletteIndex = 1;

        for (int index = 0; index < SECTION_SIZE; index++) {
            var entry = get(index);
            var newIndex = newPalette.indexOf(entry);
            if (newIndex == -1) {
                newIndex = paletteIndex++;
                newPalette.add(entry);
            }
            indexMapping[index] = newIndex;
        }

        var newbitArray = BitArrayVersion.getMinimalVersion(paletteIndex).createArray(SECTION_SIZE);
        for (int index = 0; index < SECTION_SIZE; index++) {
            newbitArray.set(index, indexMapping[index]);
        }

        this.palette = newPalette;
        this.bitArray = newbitArray;
    }

    private void readWords(ByteBuf byteBuf, BitArrayVersion version) {
        if (version == BitArrayVersion.V0) {
            this.bitArray = SingletonBitArray.INSTANCE;
            return;
        }

        var words = new int[version.getWordsForSize(SECTION_SIZE)];
        Arrays.setAll(words, i -> byteBuf.readIntLE());
        this.bitArray = version.createArray(SECTION_SIZE, words);
    }

    private void onResize(BitArrayVersion version) {
        var newBitArray = version.createArray(SECTION_SIZE);
        for (int i = 0; i < SECTION_SIZE; i++) {
            newBitArray.set(i, this.bitArray.get(i));
        }

        this.bitArray = newBitArray;
    }

    private int paletteIndexFor(V value) {
        var index = this.palette.indexOf(value);
        if (index != -1) {
            return index;
        }

        index = this.palette.size();
        this.palette.add(value);

        var version = this.bitArray.version();
        if (index > version.maxEntryIndex) {
            var next = version.next;
            if (next != null) {
                this.onResize(next);
            } else {
                throw new PaletteException("Palette have reached the max bit array version");
            }
        }

        return index;
    }

    private static boolean hasCopyLastFlag(short header) {
        return (header >> 1) == 0x7F;
    }

    private static short createPaletteHeader(BitArrayVersion version, boolean nbt) {
        return (short) ((version.bits << 1) | (nbt ? 0 : 1));
    }

    private static BitArrayVersion getVersionFromPaletteHeader(short header) {
        return BitArrayVersion.get(header >> 1, true);
    }

    private static void checkVersion(BitArrayVersion version, int paletteSize) {
        if (version.maxEntryIndex < paletteSize - 1) {
            throw new PaletteException("Palette (version " + version.name() + ") is too large. Max size " + version.maxEntryIndex + ". Actual size " + paletteSize);
        }
    }

    private static boolean isNBT(short header) {
        return (header & 1) == 0;
    }

    @SuppressWarnings("ALL")
    private static byte createCopyLastFlag(boolean nbt) {
        return (byte) ((0x7F << 1) | (nbt ? 0 : 1));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Palette<?> palette1)) return false;
        return Objects.equal(palette, palette1.palette) && Objects.equal(bitArray, palette1.bitArray);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(palette, bitArray);
    }
}