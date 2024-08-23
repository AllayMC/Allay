package org.allaymc.api.world.palette;

import com.google.common.base.Objects;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import it.unimi.dsi.fastutil.objects.ReferenceArrayList;
import org.allaymc.api.utils.HashUtils;
import org.allaymc.api.utils.PaletteUtils;
import org.allaymc.api.world.bitarray.BitArray;
import org.allaymc.api.world.bitarray.BitArrayVersion;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.updater.block.BlockStateUpdaters;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.nbt.util.stream.LittleEndianDataInputStream;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * Allay Project 2023/4/14
 *
 * @author JukeboxMC | daoge_cmd | CoolLoong
 */
public final class Palette<V> {
    private static final byte COPY_LAST_FLAG_HEADER = (byte) (0x7F << 1) | 1;

    private final List<V> palette;
    private BitArray bitArray;

    public Palette(V first) {
        this(first, BitArrayVersion.V2);
    }

    public Palette(V first, BitArrayVersion version) {
        this(first, new ReferenceArrayList<>(16), version);
    }

    public Palette(V first, List<V> palette, BitArrayVersion version) {
        this.bitArray = version.createArray(Chunk.SECTION_SIZE);
        this.palette = palette;
        this.palette.add(first);
    }

    private static boolean hasCopyLastFlag(short header) {
        return (header >> 1) == 0x7F;
    }

    private static int getPaletteHeader(BitArrayVersion version, boolean runtime) {
        return (version.bits << 1) | (runtime ? 1 : 0);
    }

    private static BitArrayVersion getVersionFromPaletteHeader(short header) {
        return BitArrayVersion.get(header >> 1, true);
    }

    private static boolean isPersistent(short header) {
        return (header & 1) == 0;
    }

    public V get(int index) {
        return this.palette.get(this.bitArray.get(index));
    }

    public void set(int index, V value) {
        var paletteIndex = this.paletteIndexFor(value);
        this.bitArray.set(index, paletteIndex);
    }

    /**
     * Write the Palette data to the network buffer
     *
     * @param byteBuf    the byte buf
     * @param serializer the serializer
     */
    public void writeToNetwork(ByteBuf byteBuf, RuntimeDataSerializer<V> serializer) {
        writeWords(byteBuf, serializer);
    }

    public void readFromNetwork(ByteBuf byteBuf, RuntimeDataDeserializer<V> deserializer) {
        readWords(byteBuf, readBitArrayVersion(byteBuf));
        var size = this.bitArray.readSizeFromNetwork(byteBuf);
        for (int i = 0; i < size; i++) this.palette.add(deserializer.deserialize(VarInts.readInt(byteBuf)));
    }

    public void writeToStoragePersistent(ByteBuf byteBuf, PersistentDataSerializer<V> serializer) {
        byteBuf.writeByte(Palette.getPaletteHeader(this.bitArray.version(), false));
        for (int word : this.bitArray.words()) byteBuf.writeIntLE(word);

        byteBuf.writeIntLE(this.palette.size());

        try (var bufOutputStream = new ByteBufOutputStream(byteBuf);
             var outputStream = NbtUtils.createWriterLE(bufOutputStream)) {
            for (V value : this.palette) outputStream.writeTag(serializer.serialize(value));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToStorageRuntime(ByteBuf byteBuf, RuntimeDataSerializer<V> serializer, Palette<V> last) {
        if (writeLast(byteBuf, last)) return;
        if (writeEmpty(byteBuf)) return;

        byteBuf.writeByte(Palette.getPaletteHeader(this.bitArray.version(), true));
        for (int word : this.bitArray.words())
            byteBuf.writeIntLE(word);

        byteBuf.writeIntLE(this.palette.size());
        for (V value : this.palette) {
            byteBuf.writeIntLE(serializer.serialize(value));
        }
    }

    public void readFromStorageRuntime(ByteBuf byteBuf, RuntimeDataDeserializer<V> deserializer, Palette<V> last) {
        var header = byteBuf.readUnsignedByte();
        if (hasCopyLastFlag(header)) {
            last.copyTo(this);
            return;
        }

        var version = Palette.getVersionFromPaletteHeader(header);
        if (version == BitArrayVersion.V0) {
            this.bitArray = version.createArray(Chunk.SECTION_SIZE, null);
            this.palette.clear();
            this.palette.add(deserializer.deserialize(byteBuf.readIntLE()));
            this.onResize(BitArrayVersion.V2);
            return;
        }

        readWords(byteBuf, version);

        var paletteSize = byteBuf.readIntLE();
        for (int i = 0; i < paletteSize; i++) this.palette.add(deserializer.deserialize(byteBuf.readIntLE()));
    }

    public void readFromStoragePersistent(ByteBuf byteBuf, RuntimeDataDeserializer<V> deserializer) {
        try (var bufInputStream = new ByteBufInputStream(byteBuf);
             var input = new LittleEndianDataInputStream(bufInputStream);
             var nbtInputStream = new NBTInputStream(input)) {
            var bitArrayVersion = readBitArrayVersion(byteBuf);
            if (bitArrayVersion == BitArrayVersion.V0) {
                this.bitArray = bitArrayVersion.createArray(Chunk.SECTION_SIZE, null);
                this.palette.clear();
                addBlockPalette(byteBuf, deserializer, input, nbtInputStream);
                this.onResize(BitArrayVersion.V2);
                return;
            }

            readWords(byteBuf, bitArrayVersion);
            var paletteSize = byteBuf.readIntLE();
            for (int i = 0; i < paletteSize; i++) {
                addBlockPalette(byteBuf, deserializer, input, nbtInputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addBlockPalette(
            ByteBuf byteBuf,
            RuntimeDataDeserializer<V> deserializer,
            LittleEndianDataInputStream input,
            NBTInputStream nbtInputStream
    ) throws IOException {
        var blockStateHash = PaletteUtils.fastReadBlockHash(input, byteBuf);
        if (blockStateHash == PaletteUtils.HASH_NOT_LATEST) {
            var oldNbtMap = (NbtMap) nbtInputStream.readTag();
            var newNbtMap = BlockStateUpdaters.updateBlockState(oldNbtMap, BlockStateUpdaters.LATEST_VERSION);
            // Make sure that tree map is used
            // If the map inside states nbt is not tree map
            // the block state hash will be wrong!
            var states = new TreeMap<>(newNbtMap.getCompound("states"));
            // To calculate the hash of the block state
            // "name" field must be in the first place
            var tag = NbtMap.builder()
                    .putString("name", newNbtMap.getString("name"))
                    .putCompound("states", NbtMap.fromMap(states))
                    .build();
            this.palette.add(deserializer.deserialize(HashUtils.fnv1a_32_nbt(tag)));
        } else {
            this.palette.add(deserializer.deserialize(blockStateHash));
        }
    }

    public int paletteIndexFor(V value) {
        var index = this.palette.indexOf(value);
        if (index != -1) return index;

        index = this.palette.size();
        this.palette.add(value);

        var version = this.bitArray.version();
        if (index > version.maxEntryValue) {
            var next = version.next;
            if (next != null) this.onResize(next);
        }

        return index;
    }

    public boolean isEmpty() {
        if (this.palette.size() == 1)
            return Arrays.stream(this.bitArray.words()).noneMatch(word -> Integer.toUnsignedLong(word) != 0L);
        else return false;
    }

    public void copyTo(Palette<V> palette) {
        palette.bitArray = this.bitArray.copy();
        palette.palette.clear();
        palette.palette.addAll(this.palette);
    }

    private boolean writeLast(ByteBuf byteBuf, Palette<V> last) {
        if (last != null && last.palette.equals(this.palette)) {
            byteBuf.writeByte(COPY_LAST_FLAG_HEADER);
            return true;
        }

        return false;
    }

    private boolean writeEmpty(ByteBuf byteBuf) {
        if (this.isEmpty()) {
            byteBuf.writeByte(Palette.getPaletteHeader(BitArrayVersion.V0, true));
            byteBuf.writeIntLE(0);
            return true;
        }

        return false;
    }

    private void writeWords(ByteBuf byteBuf, RuntimeDataSerializer<V> serializer) {
        byteBuf.writeByte(getPaletteHeader(this.bitArray.version(), true));
        for (int word : this.bitArray.words()) byteBuf.writeIntLE(word);

        if (this.bitArray.writeSizeToNetwork(byteBuf, this.palette.size())) {
            this.palette.forEach(value -> VarInts.writeInt(byteBuf, serializer.serialize(value)));
        }
    }

    private BitArrayVersion readBitArrayVersion(ByteBuf byteBuf) {
        var header = byteBuf.readUnsignedByte();
        return Palette.getVersionFromPaletteHeader(header);
    }

    private void readWords(ByteBuf byteBuf, BitArrayVersion version) {
        var wordCount = version.getWordsForSize(Chunk.SECTION_SIZE);
        var words = new int[wordCount];
        Arrays.setAll(words, i -> byteBuf.readIntLE());

        this.bitArray = version.createArray(Chunk.SECTION_SIZE, words);
        this.palette.clear();
    }

    private void onResize(BitArrayVersion version) {
        var newBitArray = version.createArray(Chunk.SECTION_SIZE);
        for (int i = 0; i < Chunk.SECTION_SIZE; i++)
            newBitArray.set(i, this.bitArray.get(i));

        this.bitArray = newBitArray;
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