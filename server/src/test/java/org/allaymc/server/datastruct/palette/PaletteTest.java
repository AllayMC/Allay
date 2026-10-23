package org.allaymc.server.datastruct.palette;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufUtil;
import org.allaymc.server.datastruct.bitarray.BitArrayVersion;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code PaletteTest} checks palette-width growth, zero-index shortcuts, and little-endian
 * NBT storage round trips.
 * @author daoge_cmd
 */
class PaletteTest {

    /**
     * Adds distinct fixture entries to an initially singleton palette. Each newly required
     * index must select a sufficiently wide bit-array version and remain readable at its
     * assigned section position.
     */
    @Test
    void testGeneric() {
        var e0 = new Entry(0);
        var palette = new Palette<>(e0, BitArrayVersion.V0);
        assertEquals(e0, palette.get(0));

        var e1 = new Entry(1);
        palette.set(1, e1);
        assertEquals(BitArrayVersion.V1, palette.getVersion());
        assertEquals(e1, palette.get(1));

        var e2 = new Entry(2);
        palette.set(2, e2);
        assertEquals(BitArrayVersion.V2, palette.getVersion());
        assertEquals(e2, palette.get(2));
    }

    /**
     * Changes one section position away from and back to the default entry. The palette
     * must retain its widened layout while still recognizing that every stored index
     * selects the first value.
     */
    @Test
    void testOneEntryOnly() {
        var e0 = new Entry(0);
        var e1 = new Entry(1);
        var palette = new Palette<>(e0, BitArrayVersion.V0);
        palette.set(1, e1);
        palette.set(1, e0);
        assertEquals(BitArrayVersion.V1, palette.getVersion());
        assertTrue(palette.oneEntryOnly());
    }

    /**
     * Serializes a two-entry palette as little-endian NBT and reads it into another
     * palette. Both the default entry and changed position must retain their fixture
     * values.
     */
    @Test
    void testStorage() {
        var e0 = new Entry(0);
        var e1 = new Entry(1);
        var p0 = new Palette<>(e0, BitArrayVersion.V0);
        p0.set(1, e1);

        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        p0.writeToStorage(buffer, PDSerializerImpl.INSTANCE);

        var p1 = new Palette<>(e0, BitArrayVersion.V0);
        p1.readFromStorage(buffer, PDDeserializerImpl.INSTANCE);
        assertEquals(e0, p1.get(0));
        assertEquals(e1, p1.get(1));
    }

    /**
     * Builds equal three-entry sections through different initial bit-array versions and
     * serializes both. The resulting bytes must match once both palettes reach the same
     * packed representation.
     */
    @Test
    void testStorageV2() {
        var e0 = new Entry(0);
        var e1 = new Entry(1);
        var e2 = new Entry(2);

        var p0 = new Palette<>(e0, BitArrayVersion.V0);
        p0.set(1, e1);
        p0.set(2, e2);
        ByteBuf b0 = ByteBufAllocator.DEFAULT.buffer();
        p0.writeToStorage(b0, PDSerializerImpl.INSTANCE);

        var p1 = new Palette<>(e0, BitArrayVersion.V2);
        p1.set(1, e1);
        p1.set(2, e2);
        ByteBuf b1 = ByteBufAllocator.DEFAULT.buffer();
        p1.writeToStorage(b1, PDSerializerImpl.INSTANCE);

        var bytes0 = ByteBufUtil.getBytes(b0);
        var bytes1 = ByteBufUtil.getBytes(b1);
        assertArrayEquals(bytes0, bytes1);
    }

    /**
     * {@code Entry} represents an immutable integer identifier used by the palette
     * serialization fixtures.
     * @param id serialized fixture value identifier
     */
    record Entry(int id) {
    }

    /**
     * {@code PDSerializerImpl} encodes fixture entries as NBT compounds with an integer
     * identifier.
     */
    static class PDSerializerImpl implements NBTSerializer<Entry> {
        /**
         * {@code INSTANCE} provides the shared stateless {@code PDSerializerImpl} used by
         * palette storage tests, avoiding per-call adapter allocation.
         */
        static PDSerializerImpl INSTANCE = new PDSerializerImpl();

        /**
         * Returns an NBT compound containing the fixture's integer identifier.
         */
        @Override
        public NbtMap serialize(Entry value) {
            return NbtMap.builder().putInt("id", value.id).build();
        }
    }

    /**
     * {@code PDDeserializerImpl} decodes NBT fixture compounds into immutable palette
     * entries.
     */
    static class PDDeserializerImpl implements NBTDeserializer<Entry> {
        /**
         * {@code INSTANCE} provides the shared stateless {@code PDDeserializerImpl} used
         * by palette storage tests, avoiding per-call adapter allocation.
         */
        static PDDeserializerImpl INSTANCE = new PDDeserializerImpl();

        /**
         * Reads one little-endian NBT compound and returns an entry containing its fixture
         * identifier. I/O errors become {@code PaletteException}; successful decoding
         * advances the supplied buffer past that entry.
         */
        @Override
        public Entry deserialize(ByteBuf buffer) {
            try (var reader = NbtUtils.createReaderLE(new ByteBufInputStream(buffer))) {
                var nbt = (NbtMap) reader.readTag();
                return new Entry(nbt.getInt("id"));
            } catch (IOException e) {
                throw new PaletteException(e);
            }
        }
    }
}
