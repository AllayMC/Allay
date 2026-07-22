package org.allaymc.server.network.processor;

import org.allaymc.api.player.ClientState;
import org.allaymc.server.network.protocol.v766.Protocol_v766;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.LoginPacket;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PacketProcessorHolderTest {

    @Test
    void createsProcessorsPerConnectionAndReusesOneInstanceAcrossStates() {
        var creations = new AtomicInteger();
        Supplier<TrackingProcessor> factory = () -> new TrackingProcessor(creations.incrementAndGet());
        var registry = new PacketProcessorRegistry();
        registry.register(
                EnumSet.of(ClientState.CONNECTED, ClientState.LOGGED_IN),
                BedrockPacketType.LOGIN,
                factory
        );
        registry.freeze();

        var firstConnection = new PacketProcessorHolder(registry);
        var secondConnection = new PacketProcessorHolder(registry);
        var packet = new LoginPacket();

        assertTrue(firstConnection.setClientState(ClientState.CONNECTED));
        var firstConnectedProcessor = firstConnection.getProcessor(packet);
        assertNotNull(firstConnectedProcessor);
        assertTrue(firstConnection.setClientState(ClientState.LOGGED_IN));
        assertSame(firstConnectedProcessor, firstConnection.getProcessor(packet));

        assertTrue(secondConnection.setClientState(ClientState.CONNECTED));
        var secondConnectedProcessor = secondConnection.getProcessor(packet);
        assertNotNull(secondConnectedProcessor);
        assertNotSame(firstConnectedProcessor, secondConnectedProcessor);
        assertEquals(2, creations.get());
    }

    @Test
    void gameplayRegistryDoesNotContainBootstrapNegotiationProcessor() {
        var registry = new ExposedProtocol_v766().createGameplayRegistry();

        assertFalse(registry.getFactories().keySet().stream()
                .anyMatch(key -> key.packetType() == BedrockPacketType.REQUEST_NETWORK_SETTINGS));
        assertTrue(registry.getFactories().containsKey(
                new PacketProcessorRegistry.Key(ClientState.CONNECTED, BedrockPacketType.LOGIN)
        ));
    }

    private static final class TrackingProcessor extends PacketProcessor<LoginPacket> {
        @SuppressWarnings("unused")
        private final int connectionId;

        private TrackingProcessor(int connectionId) {
            this.connectionId = connectionId;
        }

        @Override
        public BedrockPacketType getPacketType() {
            return BedrockPacketType.LOGIN;
        }
    }

    private static final class ExposedProtocol_v766 extends Protocol_v766 {
        private PacketProcessorRegistry createGameplayRegistry() {
            var registry = new PacketProcessorRegistry();
            registerProcessors(registry);
            registry.freeze();
            return registry;
        }
    }
}
