package org.allaymc.server.datastruct.palette;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import it.unimi.dsi.fastutil.objects.ReferenceArrayList;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.server.datastruct.bitarray.BitArray;
import org.allaymc.server.datastruct.bitarray.BitArrayVersion;
import org.allaymc.server.datastruct.bitarray.SingletonBitArray;
import org.cloudburstmc.nbt.NbtUtils;
import org.cloudburstmc.protocol.bedrock.util.VarInts;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * {@code Palette} stores a chunk section's logical values as entries in a value list plus
 * packed indices into that list. The first value is the default for every initially zero
 * index. Default constructors use reference-based list lookup, so equal but distinct value
 * objects can occupy separate entries; a caller-supplied list supplies its own lookup
 * semantics.
 *
 * <p>
 * Palette growth preserves existing packed indices while selecting a wider bit-array
 * layout. Compaction instead scans all section positions, retains the original first
 * entry, and remaps used values into new storage. The value objects themselves remain
 * shared. The list-taking constructor retains and appends to its supplied list, which must
 * be empty to make the supplied first value index zero.
 *
 * <p>
 * Network and storage encodings carry a header, packed words, and serialized values, with
 * a zero-bit shortcut for all-default sections. Integer encodings can reference an equal
 * preceding palette through a copy-last flag. Reading replaces state incrementally and can
 * leave partial changes after malformed input or callback failure. Mutations and reads
 * mark {@code dirty}; writing does not clear it, and callers control save bookkeeping and
 * synchronization.
 * @author JukeboxMC | daoge_cmd | CoolLoong
 */
@Slf4j
public final class Palette<V> {

    /**
     * {@code SECTION_SIZE} fixes the logical section entry count at sixteen cubed.
     * Resizing, word decoding, and compaction use this count regardless of the current
     * bit-array layout.
     */
    private static final int SECTION_SIZE = 16 * 16 * 16;
    /**
     * {@code INITIAL_VERSION} selects the zero-bit singleton for default palette
     * construction. All initial indices therefore select the first value without
     * allocating any packed words.
     */
    private static final BitArrayVersion INITIAL_VERSION = BitArrayVersion.V0;

    /**
     * {@code palette} stores values addressed by {@code bitArray}. The first entry remains
     * the default, and external list sharing can invalidate indices if callers reorder or
     * remove entries.
     */
    private List<V> palette;
    /**
     * {@code bitArray} stores indices into {@code palette} using the selected layout.
     * Resizing preserves index values, while compaction replaces both structures with a
     * coordinated remapping.
     */
    private BitArray bitArray;
    /**
     * {@code dirty} records whether palette state has changed or been read since caller
     * bookkeeping cleared it. Constructors set it, mutation paths set it again, and
     * serialization does not reset it.
     */
    @Getter
    @Setter
    private boolean dirty;

    /**
     * Creates a dirty section palette whose every logical index initially selects
     * {@code first}. The initial zero-bit layout stores no words, and the default value
     * list uses object identity for lookup.
     */
    public Palette(V first) {
        this(first, INITIAL_VERSION);
    }

    /**
     * Creates a dirty section palette initially containing only {@code first}. The
     * requested {@code version} supplies initial packed-index storage, and the default
     * reference list identifies repeated values by object identity.
     */
    public Palette(V first, BitArrayVersion version) {
        this(first, new ReferenceArrayList<>(version.maxEntryIndex + 1), version);
    }

    /**
     * Creates a dirty section palette by retaining {@code palette} and appending
     * {@code first}. The supplied list must be empty for {@code first} to occupy default
     * index zero, and callers must not independently reorder or remove its entries.
     */
    public Palette(V first, List<V> palette, BitArrayVersion version) {
        this.palette = palette;
        // Preserve list index zero as the default selected by every initially zero section
        // index; callers supply an empty list for this convention.
        this.palette.add(first);
        this.bitArray = version.createArray(SECTION_SIZE);
        this.dirty = true;
    }

    /**
     * Returns whether the header's width bits contain the copy-last sentinel.
     */
    private static boolean hasCopyLastFlag(short header) {
        return (header >> 1) == 0x7F;
    }

    /**
     * Returns a header combining the layout width with its representation flag.
     */
    private static short createPaletteHeader(BitArrayVersion version, boolean nbt) {
        // The low header bit distinguishes integer and NBT values from the upper width bits,
        // allowing readers to select the matching entry decoder.
        return (short) ((version.bits << 1) | (nbt ? 0 : 1));
    }

    /**
     * Returns the layout selected by the header's width bits. A copy-last width yields
     * {@code null}, while an unsupported width throws {@code IllegalArgumentException};
     * callers handle copy-last before reading ordinary words.
     */
    private static BitArrayVersion getVersionFromPaletteHeader(short header) {
        return BitArrayVersion.get(header >> 1, true);
    }

    /**
     * Rejects a palette list too large for its packed index width. The highest possible
     * index is {@code paletteSize - 1}; an excessive size throws {@code PaletteException}
     * before entry decoding continues.
     */
    private static void checkVersion(BitArrayVersion version, int paletteSize) {
        if (version.maxEntryIndex < paletteSize - 1) {
            throw new PaletteException("Palette (version " + version.name() + ") is too large. Max size " + version.maxEntryIndex + ". Actual size " + paletteSize);
        }
    }

    /**
     * Returns whether the header selects NBT rather than integer entry encoding.
     */
    private static boolean isNBT(short header) {
        return (header & 1) == 0;
    }

    /**
     * Returns the copy-last header for the selected NBT or integer representation.
     */
    @SuppressWarnings("ALL")
    private static byte createCopyLastFlag(boolean nbt) {
        // Reserve width 0x7F for copy-last while retaining the low representation flag, so
        // readers reuse the preceding palette instead of expecting another payload.
        return (byte) ((0x7F << 1) | (nbt ? 0 : 1));
    }

    /**
     * Returns the value addressed by the logical section index. Ordinary packed layouts
     * require indices from zero through {@code 4095}, while the zero-bit singleton reads
     * the first value for every supplied index.
     */
    public V get(int index) {
        return this.palette.get(this.bitArray.get(index));
    }

    /**
     * Stores {@code value} at a logical section index and marks this palette dirty. Lookup
     * can append a value and resize index storage before the index write is validated, so
     * an invalid index can leave those earlier changes in place.
     */
    public void set(int index, V value) {
        var paletteIndex = this.paletteIndexFor(value);
        this.bitArray.set(index, paletteIndex);
        this.dirty = true;
    }

    /**
     * Writes this palette using variable-length signed integers for its serialized value
     * identifiers. When {@code last} is non-null and representation-equal, the output
     * contains only a copy-last header; the reader must receive the corresponding
     * preceding palette.
     *
     * <p>
     * All-default sections use a zero-bit header and one value, while other sections write
     * packed words and their value list in matching index order. Callback failure can
     * leave partial output because writing is incremental. This operation does not reset
     * {@code dirty}, and equivalent logical values with different list or bit-array
     * representations need not use the copy-last shortcut.
     */
    public void writeToNetwork(ByteBuf byteBuf, IntSerializer<V> serializer, Palette<V> last) {
        // Reuse the preceding encoded representation only when list and word representations
        // compare equal; the matching reader must receive that same preceding palette.
        if (last != null && last.equals(this)) {
            byteBuf.writeByte(createCopyLastFlag(false));
            return;
        }

        // All indices select the default value, so the zero-bit encoding preserves section
        // contents while omitting unused entries and packed words.
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

    /**
     * Replaces this palette from encoded words and variable-length signed integers
     * resolved by {@code deserializer}. NBT headers are rejected with
     * {@code PaletteException}. A copy-last header requires non-null {@code last} that is
     * distinct from this palette and has an independently backed value list. The copy
     * duplicates index storage and list entries while retaining the value references.
     *
     * <p>
     * Ordinary decoding clears the previous list before reading replacement words and
     * entries, so malformed input or callback failure can leave partial state. The method
     * marks this palette dirty before replacement. A zero-bit header carries one value
     * without a count, and nonzero layouts validate the count against their index capacity
     * before decoding the list.
     */
    public void readFromNetwork(ByteBuf byteBuf, IntDeserializer<V> deserializer, Palette<V> last) {
        var header = byteBuf.readUnsignedByte();
        if (isNBT(header)) {
            throw new PaletteException("Reading nbt palette data with non-nbt method!");
        }

        this.dirty = true;
        // Resolve the copy-last marker before ordinary width decoding, because its sentinel
        // width has no independently stored word or value payload.
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

    /**
     * Writes this palette using little-endian NBT values and packed index words. An
     * all-default section uses a zero-bit header and one value; other sections include
     * words followed by a fixed-width list count.
     *
     * <p>
     * The header and words precede value callbacks, preserving the index-to-entry
     * relationship expected by the matching reader. A serializer or I/O failure can leave
     * partial bytes in {@code byteBuf}; NBT I/O errors become {@code PaletteException}.
     * The caller owns recovery of that buffer and the dirty flag, which this operation
     * does not clear.
     */
    public void writeToStorage(ByteBuf byteBuf, NBTSerializer<V> serializer) {
        // All indices select the default value, so the zero-bit encoding preserves section
        // contents while omitting unused entries and packed words.
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

    /**
     * Replaces this palette by reading little-endian words and NBT entries from
     * {@code byteBuf}. Integer headers are rejected with {@code PaletteException}; each
     * deserializer call must consume exactly one entry so the following value remains
     * aligned.
     *
     * <p>
     * The method marks the palette dirty and clears its existing list before reading the
     * replacement layout and values. A malformed header, insufficient input, or callback
     * failure can therefore leave partially replaced state. The zero-bit representation
     * reads one entry without a list count; nonzero layouts validate the count against the
     * index width before decoding values.
     */
    public void readFromStorage(ByteBuf byteBuf, NBTDeserializer<V> deserializer) {
        var header = byteBuf.readUnsignedByte();
        if (!isNBT(header)) {
            throw new PaletteException("Reading non-nbt palette data with nbt method!");
        }

        this.dirty = true;
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

    /**
     * Writes this palette using little-endian fixed-width integers for its serialized
     * value identifiers. When {@code last} is non-null and representation-equal, the
     * output contains only a copy-last header; the reader must receive the corresponding
     * preceding palette.
     *
     * <p>
     * All-default sections use a zero-bit header and one value, while other sections write
     * packed words and their value list in matching index order. Callback failure can
     * leave partial output because writing is incremental. This operation does not reset
     * {@code dirty}, and equivalent logical values with different list or bit-array
     * representations need not use the copy-last shortcut.
     */
    public void writeToStorage(ByteBuf byteBuf, IntSerializer<V> serializer, Palette<V> last) {
        // Reuse the preceding encoded representation only when list and word representations
        // compare equal; the matching reader must receive that same preceding palette.
        if (last != null && last.equals(this)) {
            byteBuf.writeByte(createCopyLastFlag(false));
            return;
        }

        // All indices select the default value, so the zero-bit encoding preserves section
        // contents while omitting unused entries and packed words.
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

    /**
     * Replaces this palette from encoded words and little-endian fixed-width integers
     * resolved by {@code deserializer}. NBT headers are rejected with
     * {@code PaletteException}. A copy-last header requires non-null {@code last} that is
     * distinct from this palette and has an independently backed value list. The copy
     * duplicates index storage and list entries while retaining the value references.
     *
     * <p>
     * Ordinary decoding clears the previous list before reading replacement words and
     * entries, so malformed input or callback failure can leave partial state. The method
     * marks this palette dirty before replacement. A zero-bit header carries one value
     * without a count, and nonzero layouts validate the count against their index capacity
     * before decoding the list.
     */
    public void readFromStorage(ByteBuf byteBuf, IntDeserializer<V> deserializer, Palette<V> last) {
        var header = byteBuf.readUnsignedByte();
        if (isNBT(header)) {
            throw new PaletteException("Reading nbt palette data with non-nbt method!");
        }

        this.dirty = true;
        // Resolve the copy-last marker before ordinary width decoding, because its sentinel
        // width has no independently stored word or value payload.
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

    /**
     * Returns whether every stored section index selects the first palette value. A
     * one-element list qualifies immediately; otherwise all packed words must be zero even
     * when unused list entries and a wider layout remain.
     *
     * <p>
     * The writer uses this result to emit a zero-bit representation without first mutating
     * or compacting the palette. Checking indices rather than list size preserves the
     * shortcut after entries have been changed back to their default value. Unused nonzero
     * padding bits also prevent the shortcut because complete words are inspected.
     */
    public boolean oneEntryOnly() {
        if (this.palette.size() == 1) {
            return true;
        }

        // Historical list entries can remain after positions return to the default value, so
        // examine stored indices before choosing the zero-bit encoding.
        for (int word : this.bitArray.words()) {
            if (Integer.toUnsignedLong(word) != 0L) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns whether the predicate accepts every value retained in the palette list.
     * Unused entries participate too, so this operation is not limited to values currently
     * referenced by logical section positions.
     */
    public boolean allEntriesMatch(Predicate<V> predicate) {
        for (var entry : palette) {
            if (!predicate.test(entry)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Copies packed words and list entries into a distinct destination palette and marks
     * it dirty. Value objects remain shared, and the destination list is cleared before
     * copying; source and destination must not share that list or be the same palette.
     */
    public void copyTo(Palette<V> palette) {
        palette.bitArray = this.bitArray.copy();
        palette.palette.clear();
        palette.palette.addAll(this.palette);
        palette.dirty = true;
    }

    /**
     * Returns the version used by the current packed section-index array.
     */
    public BitArrayVersion getVersion() {
        return bitArray.version();
    }

    /**
     * Rebuilds value and index storage from the section's currently referenced values,
     * then marks the palette dirty. The original first entry is retained even if unused,
     * preserving its role as the default value. Other values are deduplicated by reference
     * in the new list.
     *
     * <p>
     * A temporary index mapping records each old position's new list index before any
     * existing storage is replaced. The new bit-array width is selected from the
     * next-entry count, which can be wider than the minimum required highest index. Both
     * rebuilt structures replace the originals only after the scan and index writes
     * complete; the value objects remain shared.
     */
    public void compact() {
        var newPalette = new ReferenceArrayList<V>();
        // Retain the original default at index zero before mapping used entries, so compaction
        // does not reassign the palette's distinguished first value.
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

        // Build replacement indices only after every old position has a new mapping, preserving
        // value associations while the old palette remains readable.
        var newbitArray = BitArrayVersion.getMinimalVersion(paletteIndex).createArray(SECTION_SIZE);
        for (int index = 0; index < SECTION_SIZE; index++) {
            newbitArray.set(index, indexMapping[index]);
        }

        this.palette = newPalette;
        this.bitArray = newbitArray;
        this.dirty = true;
    }

    /**
     * Replaces packed index storage with words read in little-endian order. The zero-bit
     * version installs the shared singleton without consuming words; nonzero versions
     * allocate the layout's required section word count before assigning the new bit
     * array.
     */
    private void readWords(ByteBuf byteBuf, BitArrayVersion version) {
        if (version == BitArrayVersion.V0) {
            this.bitArray = SingletonBitArray.INSTANCE;
            return;
        }

        var words = new int[version.getWordsForSize(SECTION_SIZE)];
        Arrays.setAll(words, i -> byteBuf.readIntLE());
        this.bitArray = version.createArray(SECTION_SIZE, words);
    }

    /**
     * Replaces index storage with the supplied wider version while preserving every
     * logical section index. The destination array is populated before replacing
     * {@code bitArray}, so a completed resize keeps each section position associated with
     * its existing palette value.
     *
     * <p>
     * The method scans all section positions even when the old layout is the zero-bit
     * singleton, whose reads return zero for every position. The value list is unchanged
     * by this helper. Successful replacement marks {@code dirty}; callers choose a version
     * capable of representing every old index.
     */
    private void onResize(BitArrayVersion version) {
        var newBitArray = version.createArray(SECTION_SIZE);
        for (int i = 0; i < SECTION_SIZE; i++) {
            newBitArray.set(i, this.bitArray.get(i));
        }

        // Publish the fully populated replacement after copying every logical index, so
        // existing list indices retain their position-to-value association.
        this.bitArray = newBitArray;
        this.dirty = true;
    }

    /**
     * Returns the list index of {@code value}, appending it when absent and growing packed
     * storage if needed. Lookup follows the current list's {@code indexOf} semantics,
     * which use identity for the default reference list.
     *
     * <p>
     * A new value joins the list before the width check, so a capacity exception can leave
     * an extra entry behind. When a larger version exists, resizing copies all existing
     * logical indices before the new index is returned for the caller's write. The helper
     * does not itself set a section position; {@code set} performs that final association.
     */
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

    /**
     * Returns whether another palette has equal value-list and packed-array
     * representations. The dirty flag is ignored, and default reference lists compare
     * value identities; equal logical sections with different layouts can compare unequal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Palette<?> palette1)) return false;
        return Objects.equal(palette, palette1.palette) && Objects.equal(bitArray, palette1.bitArray);
    }

    /**
     * Returns a hash of the current value list and packed indices. Palette mutations and
     * changes to shared value identity semantics can affect this result, so mutable
     * palettes should not be retained as stable hash keys.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(palette, bitArray);
    }
}
