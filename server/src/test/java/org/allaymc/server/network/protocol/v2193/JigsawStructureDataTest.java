package org.allaymc.server.network.protocol.v2193;

import io.netty.buffer.Unpooled;
import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.ProtocolRegistry;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code JigsawStructureDataTest} checks jigsaw payload availability at the
 * v2193 protocol boundary.
 */
@ExtendWith(AllayTestExtension.class)
class JigsawStructureDataTest {

    /**
     * Requests the v2193 jigsaw payload repeatedly and serializes one result.
     * The packets must be distinct, contain a nonempty outer tag, and be
     * accepted by that protocol's codec.
     */
    @Test
    void v2193SendsJigsawStructureDataThatItsCodecEncodes() {
        var protocol = ProtocolRegistry.getDefault().resolve(ClientVariant.INTERNATIONAL, 2193);
        var packet = protocol.getEncoder().encodeJigsawStructureData();

        assertNotNull(packet);
        assertFalse(packet.getJigsawStructureDataTag().isEmpty());
        assertNotSame(packet, protocol.getEncoder().encodeJigsawStructureData());
        var codec = protocol.getCodec();
        var buffer = Unpooled.buffer();
        try {
            assertDoesNotThrow(() -> codec.tryEncode(codec.createHelper(), buffer, packet));
        } finally {
            // The codec probe owns its temporary output buffer, so release it
            // even when serialization fails or its assertion aborts.
            buffer.release();
        }
    }

    /**
     * Selects v2169 immediately before the jigsaw encoder override is
     * introduced. Its inherited encoding operation must return {@code null} to
     * signal that no jigsaw packet is supplied.
     */
    @Test
    void olderProtocolsDoNotSendJigsawStructureData() {
        var protocol = ProtocolRegistry.getDefault().resolve(ClientVariant.INTERNATIONAL, 2169);

        assertNull(protocol.getEncoder().encodeJigsawStructureData());
    }
}
