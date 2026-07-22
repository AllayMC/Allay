package org.allaymc.server.network.protocol;

import org.allaymc.api.player.ClientState;
import org.allaymc.server.network.processor.PacketProcessorHolder;
import org.allaymc.server.network.processor.login.LoginPacketProcessor;
import org.allaymc.server.network.processor.login.NetEaseLoginPacketProcessor;
import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.LoginPacket;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundDataDrivenScreenClosedPacket;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundDataStorePacket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(AllayTestExtension.class)
class ProtocolProcessorSelectionTest {

    @Test
    void netEaseProtocolsOverrideTheInternationalLoginProcessorFactory() {
        var registry = ProtocolRegistry.getDefault();
        var loginPacket = new LoginPacket();

        for (var protocol : registry.getProtocols()) {
            var holder = protocol.createProcessorHolder();
            assertTrue(holder.setClientState(ClientState.CONNECTED));
            var processor = holder.getProcessor(loginPacket);

            if (protocol.getVariant() == ClientVariant.NETEASE) {
                assertInstanceOf(NetEaseLoginPacketProcessor.class, processor, protocol::toString);
            } else {
                assertSame(LoginPacketProcessor.class, processor.getClass(), protocol::toString);
            }
        }
    }

    @Test
    void gameplayProcessorsMatchTheirCodecRegistrationBoundaries() {
        for (var protocol : ProtocolRegistry.getDefault().getProtocols()) {
            var holder = protocol.createProcessorHolder();
            assertTrue(holder.setClientState(ClientState.CONNECTED));
            assertTrue(holder.setClientState(ClientState.LOGGED_IN));
            assertTrue(holder.setClientState(ClientState.SPAWNED));
            assertTrue(holder.setClientState(ClientState.IN_GAME));

            assertProcessorMatchesCodec(protocol, holder, new ServerboundDataStorePacket(), 898);
            assertProcessorMatchesCodec(protocol, holder, new ServerboundDataDrivenScreenClosedPacket(), 944);
        }
    }

    private static void assertProcessorMatchesCodec(
            Protocol protocol,
            PacketProcessorHolder holder,
            BedrockPacket packet,
            int introducedIn
    ) {
        var message = protocol + ", " + packet.getPacketType();
        var codecRegistered = protocol.getCodec().getPacketDefinition(packet.getClass()) != null;
        assertEquals(protocol.getProtocolVersion() >= introducedIn, codecRegistered, message);
        assertEquals(codecRegistered, holder.getProcessor(packet) != null, message);
    }
}
