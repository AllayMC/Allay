package org.allaymc.server.datastruct.palette;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufInputStream;
import org.allaymc.api.utils.Utils;
import org.allaymc.server.datastruct.bitarray.BitArrayVersion;
import org.cloudburstmc.nbt.NBTInputStream;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.util.stream.LittleEndianDataInputStream;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author daoge_cmd
 */
class PaletteTest {

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

    @Test
    void testOneEntryOnly() {
        var e0 = new Entry(0);
        var e1 = new Entry(1);
        var palette = new Palette<>(e0, BitArrayVersion.V0);
        palette.set(1, e1);
        palette.set(1, e0);
        // Bit array version won't downgrade
        assertEquals(BitArrayVersion.V1, palette.getVersion());
        // But the palette should be one entry only
        assertTrue(palette.oneEntryOnly());
    }

    @Test
    void testPersistent() {
        var e0 = new Entry(0);
        var e1 = new Entry(1);
        var p0 = new Palette<>(e0, BitArrayVersion.V0);
        p0.set(1, e1);

        ByteBuf buffer = ByteBufAllocator.DEFAULT.ioBuffer();
        p0.writeToStorage(buffer, PDSerializerImpl.INSTANCE);

        var p1 = new Palette<>(e0, BitArrayVersion.V0);
        p1.readFromStorage(buffer, PDDeserializerImpl.INSTANCE);
        assertEquals(e0, p1.get(0));
        assertEquals(e1, p1.get(1));
    }

    @Test
    void testPersistentV2() {
        var e0 = new Entry(0);
        var e1 = new Entry(1);
        var e2 = new Entry(2);

        var p0 = new Palette<>(e0, BitArrayVersion.V0);
        p0.set(1, e1);
        p0.set(2, e2);
        ByteBuf b0 = ByteBufAllocator.DEFAULT.ioBuffer();
        p0.writeToStorage(b0, PDSerializerImpl.INSTANCE);

        var p1 = new Palette<>(e0, BitArrayVersion.V2);
        p1.set(1, e1);
        p1.set(2, e2);
        ByteBuf b1 = ByteBufAllocator.DEFAULT.ioBuffer();
        p1.writeToStorage(b1, PDSerializerImpl.INSTANCE);

        var bytes0 = Utils.convertByteBuf2Array(b0);
        var bytes1 = Utils.convertByteBuf2Array(b1);
        assertArrayEquals(bytes0, bytes1);
    }

    record Entry(int id) {
    }

    static class PDSerializerImpl implements NBTSerializer<Entry> {

        static PDSerializerImpl INSTANCE = new PDSerializerImpl();

        @Override
        public NbtMap serialize(Entry value) {
            return NbtMap.builder().putInt("id", value.id).build();
        }
    }

    static class PDDeserializerImpl implements NBTDeserializer<Entry> {

        static PDDeserializerImpl INSTANCE = new PDDeserializerImpl();

        @Override
        public Entry deserialize(ByteBuf buffer) {
            try (var bufInputStream = new ByteBufInputStream(buffer);
                 var input = new LittleEndianDataInputStream(bufInputStream);
                 var nbtInputStream = new NBTInputStream(input)) {
                var nbt = (NbtMap) nbtInputStream.readTag();
                return new Entry(nbt.getInt("id"));
            } catch (IOException e) {
                throw new PaletteException(e);
            }
        }
    }
}