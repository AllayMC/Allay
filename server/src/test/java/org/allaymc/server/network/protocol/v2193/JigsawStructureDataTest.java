package org.allaymc.server.network.protocol.v2193;

import io.netty.buffer.Unpooled;
import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.ProtocolRegistry;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AllayTestExtension.class)
class JigsawStructureDataTest {

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
            buffer.release();
        }
    }

    @Test
    void olderProtocolsDoNotSendJigsawStructureData() {
        var protocol = ProtocolRegistry.getDefault().resolve(ClientVariant.INTERNATIONAL, 2169);

        assertNull(protocol.getEncoder().encodeJigsawStructureData());
    }
}
