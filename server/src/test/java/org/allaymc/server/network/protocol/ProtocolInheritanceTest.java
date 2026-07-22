package org.allaymc.server.network.protocol;

import org.allaymc.server.network.protocol.v1001.PacketEncoder_v1001;
import org.allaymc.server.network.protocol.v1001.Protocol_v1001;
import org.allaymc.server.network.protocol.v766.PacketEncoder_v766;
import org.allaymc.server.network.protocol.v766.PacketEncoder_v766_NetEase;
import org.allaymc.server.network.protocol.v766.Protocol_v766;
import org.allaymc.server.network.protocol.v766.Protocol_v766_NetEase;
import org.allaymc.server.network.protocol.v818.PacketEncoder_v818;
import org.allaymc.server.network.protocol.v818.Protocol_v818;
import org.allaymc.server.network.protocol.v819.PacketEncoder_v819;
import org.allaymc.server.network.protocol.v819.PacketEncoder_v819_NetEase;
import org.allaymc.server.network.protocol.v819.Protocol_v819;
import org.allaymc.server.network.protocol.v819.Protocol_v819_NetEase;
import org.allaymc.server.network.protocol.v827.PacketEncoder_v827;
import org.allaymc.server.network.protocol.v827.Protocol_v827;
import org.allaymc.server.network.protocol.v844.PacketEncoder_v844;
import org.allaymc.server.network.protocol.v844.Protocol_v844;
import org.allaymc.server.network.protocol.v859.PacketEncoder_v859;
import org.allaymc.server.network.protocol.v859.Protocol_v859;
import org.allaymc.server.network.protocol.v860.PacketEncoder_v860;
import org.allaymc.server.network.protocol.v860.Protocol_v860;
import org.allaymc.server.network.protocol.v898.PacketEncoder_v898;
import org.allaymc.server.network.protocol.v898.Protocol_v898;
import org.allaymc.server.network.protocol.v924.PacketEncoder_v924;
import org.allaymc.server.network.protocol.v924.Protocol_v924;
import org.allaymc.server.network.protocol.v944.PacketEncoder_v944;
import org.allaymc.server.network.protocol.v944.Protocol_v944;
import org.allaymc.server.network.protocol.v975.PacketEncoder_v975;
import org.allaymc.server.network.protocol.v975.Protocol_v975;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProtocolInheritanceTest {
    @Test
    void oldestInternationalProtocolIsAnAbstractUnregisteredBaseline() {
        assertTrue(Modifier.isAbstract(Protocol_v766.class.getModifiers()));
    }

    @Test
    void internationalProtocolsExtendThePreviousInternationalVersion() {
        assertDirectChain(List.of(
                Protocol_v766.class,
                Protocol_v818.class,
                Protocol_v819.class,
                Protocol_v827.class,
                Protocol_v844.class,
                Protocol_v859.class,
                Protocol_v860.class,
                Protocol_v898.class,
                Protocol_v924.class,
                Protocol_v944.class,
                Protocol_v975.class,
                Protocol_v1001.class
        ));
    }

    @Test
    void internationalEncodersExtendThePreviousInternationalVersion() {
        assertDirectChain(List.of(
                PacketEncoder_v766.class,
                PacketEncoder_v818.class,
                PacketEncoder_v819.class,
                PacketEncoder_v827.class,
                PacketEncoder_v844.class,
                PacketEncoder_v859.class,
                PacketEncoder_v860.class,
                PacketEncoder_v898.class,
                PacketEncoder_v924.class,
                PacketEncoder_v944.class,
                PacketEncoder_v975.class,
                PacketEncoder_v1001.class
        ));
    }

    @Test
    void netEaseBranchesDirectlyFromTheSameVersionInternationalClass() {
        assertEquals(Protocol_v766.class, Protocol_v766_NetEase.class.getSuperclass());
        assertEquals(PacketEncoder_v766.class, PacketEncoder_v766_NetEase.class.getSuperclass());
        assertEquals(Protocol_v819.class, Protocol_v819_NetEase.class.getSuperclass());
        assertEquals(PacketEncoder_v819.class, PacketEncoder_v819_NetEase.class.getSuperclass());
    }

    @Test
    void protocolClassesExposeTheirExactCodecIdentity() {
        var international = List.of(
                new Protocol_v818(),
                new Protocol_v819(),
                new Protocol_v827(),
                new Protocol_v844(),
                new Protocol_v859(),
                new Protocol_v860(),
                new Protocol_v898(),
                new Protocol_v924(),
                new Protocol_v944(),
                new Protocol_v975(),
                new Protocol_v1001()
        );
        var expectedVersions = List.of(818, 819, 827, 844, 859, 860, 898, 924, 944, 975, 1001);

        for (int index = 0; index < international.size(); index++) {
            var protocol = international.get(index);
            assertEquals(ClientVariant.INTERNATIONAL, protocol.getVariant());
            assertEquals(expectedVersions.get(index), protocol.getProtocolVersion());
        }

        var netEaseV766 = new Protocol_v766_NetEase();
        var netEaseV819 = new Protocol_v819_NetEase();
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
