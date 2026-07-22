package org.allaymc.server.network.protocol;

import org.allaymc.server.network.protocol.v1001.PacketEncoderV1001;
import org.allaymc.server.network.protocol.v1001.ProtocolV1001;
import org.allaymc.server.network.protocol.v766.PacketEncoderV766;
import org.allaymc.server.network.protocol.v766.PacketEncoderV766NetEase;
import org.allaymc.server.network.protocol.v766.ProtocolV766;
import org.allaymc.server.network.protocol.v766.ProtocolV766NetEase;
import org.allaymc.server.network.protocol.v818.PacketEncoderV818;
import org.allaymc.server.network.protocol.v818.ProtocolV818;
import org.allaymc.server.network.protocol.v819.PacketEncoderV819;
import org.allaymc.server.network.protocol.v819.PacketEncoderV819NetEase;
import org.allaymc.server.network.protocol.v819.ProtocolV819;
import org.allaymc.server.network.protocol.v819.ProtocolV819NetEase;
import org.allaymc.server.network.protocol.v827.PacketEncoderV827;
import org.allaymc.server.network.protocol.v827.ProtocolV827;
import org.allaymc.server.network.protocol.v844.PacketEncoderV844;
import org.allaymc.server.network.protocol.v844.ProtocolV844;
import org.allaymc.server.network.protocol.v859.PacketEncoderV859;
import org.allaymc.server.network.protocol.v859.ProtocolV859;
import org.allaymc.server.network.protocol.v860.PacketEncoderV860;
import org.allaymc.server.network.protocol.v860.ProtocolV860;
import org.allaymc.server.network.protocol.v898.PacketEncoderV898;
import org.allaymc.server.network.protocol.v898.ProtocolV898;
import org.allaymc.server.network.protocol.v924.PacketEncoderV924;
import org.allaymc.server.network.protocol.v924.ProtocolV924;
import org.allaymc.server.network.protocol.v944.PacketEncoderV944;
import org.allaymc.server.network.protocol.v944.ProtocolV944;
import org.allaymc.server.network.protocol.v975.PacketEncoderV975;
import org.allaymc.server.network.protocol.v975.ProtocolV975;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProtocolInheritanceTest {
    @Test
    void oldestInternationalProtocolIsAnAbstractUnregisteredBaseline() {
        assertTrue(Modifier.isAbstract(ProtocolV766.class.getModifiers()));
    }

    @Test
    void internationalProtocolsExtendThePreviousInternationalVersion() {
        assertDirectChain(List.of(
                ProtocolV766.class,
                ProtocolV818.class,
                ProtocolV819.class,
                ProtocolV827.class,
                ProtocolV844.class,
                ProtocolV859.class,
                ProtocolV860.class,
                ProtocolV898.class,
                ProtocolV924.class,
                ProtocolV944.class,
                ProtocolV975.class,
                ProtocolV1001.class
        ));
    }

    @Test
    void internationalEncodersExtendThePreviousInternationalVersion() {
        assertDirectChain(List.of(
                PacketEncoderV766.class,
                PacketEncoderV818.class,
                PacketEncoderV819.class,
                PacketEncoderV827.class,
                PacketEncoderV844.class,
                PacketEncoderV859.class,
                PacketEncoderV860.class,
                PacketEncoderV898.class,
                PacketEncoderV924.class,
                PacketEncoderV944.class,
                PacketEncoderV975.class,
                PacketEncoderV1001.class
        ));
    }

    @Test
    void netEaseBranchesDirectlyFromTheSameVersionInternationalClass() {
        assertEquals(ProtocolV766.class, ProtocolV766NetEase.class.getSuperclass());
        assertEquals(PacketEncoderV766.class, PacketEncoderV766NetEase.class.getSuperclass());
        assertEquals(ProtocolV819.class, ProtocolV819NetEase.class.getSuperclass());
        assertEquals(PacketEncoderV819.class, PacketEncoderV819NetEase.class.getSuperclass());
    }

    @Test
    void protocolClassesExposeTheirExactCodecIdentity() {
        var international = List.of(
                new ProtocolV818(),
                new ProtocolV819(),
                new ProtocolV827(),
                new ProtocolV844(),
                new ProtocolV859(),
                new ProtocolV860(),
                new ProtocolV898(),
                new ProtocolV924(),
                new ProtocolV944(),
                new ProtocolV975(),
                new ProtocolV1001()
        );
        var expectedVersions = List.of(818, 819, 827, 844, 859, 860, 898, 924, 944, 975, 1001);

        for (int index = 0; index < international.size(); index++) {
            var protocol = international.get(index);
            assertEquals(ClientVariant.INTERNATIONAL, protocol.getVariant());
            assertEquals(expectedVersions.get(index), protocol.getProtocolVersion());
        }

        var netEaseV766 = new ProtocolV766NetEase();
        var netEaseV819 = new ProtocolV819NetEase();
        assertEquals(ClientVariant.NETEASE, netEaseV766.getVariant());
        assertEquals(766, netEaseV766.getProtocolVersion());
        assertEquals(ClientVariant.NETEASE, netEaseV819.getVariant());
        assertEquals(819, netEaseV819.getProtocolVersion());
    }

    private static void assertDirectChain(List<Class<?>> chain) {
        for (int index = 1; index < chain.size(); index++) {
            assertEquals(chain.get(index - 1), chain.get(index).getSuperclass());
        }
    }
}
