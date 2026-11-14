package org.allaymc.server.world.chunk;

import io.netty.buffer.Unpooled;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.server.datastruct.palette.Palette;
import org.allaymc.server.network.protocol.NetworkBlockPalette;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(AllayTestExtension.class)
class ChunkEncoderPaletteTest {

    @Test
    void sectionsAreEncodedInTheClientsPalette() {
        var stairs = BlockTypes.WHITE_WOOL_STAIRS.getDefaultState();
        var stone = BlockTypes.STONE.getDefaultState();
        var section = new AllayChunkSection((byte) 0);
        section.setBlockState(0, 0, 0, stairs, 0);
        section.setBlockState(1, 0, 0, stone, 0);

        for (var palette : new NetworkBlockPalette[]{NetworkBlockPalette.of("2168"), NetworkBlockPalette.current()}) {
            var buffer = Unpooled.wrappedBuffer(ChunkEncoder.encodeSectionBlob(section, palette));
            buffer.skipBytes(3); // version, layer count, section y
            var layer = new Palette<Integer>(0);
            layer.readFromNetwork(buffer, id -> id, null);

            assertEquals(palette.toNetwork(stairs), layer.get(HashUtils.hashChunkSectionXYZ(0, 0, 0)), palette.name());
            assertEquals(palette.toNetwork(stone), layer.get(HashUtils.hashChunkSectionXYZ(1, 0, 0)), palette.name());
        }
    }
}
