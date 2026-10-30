package org.allaymc.server.network.processor;

import org.allaymc.api.player.ClientState;
import org.allaymc.server.network.protocol.v766.Protocol_v766;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.LoginPacket;
import org.junit.jupiter.api.Test;

import java.util.EnumSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code PacketProcessorHolderTest} checks connection-local processor sharing
 * and the separation of bootstrap negotiation from gameplay registrations.
 */
class PacketProcessorHolderTest {

    /**
     * Registers one supplier across two client states and constructs two holders.
     * State changes must reuse one connection's processor while another connection
     * receives a distinct instance.
     */
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

    /**
     * Builds the gameplay registry exposed by the oldest international protocol.
     * Login must remain registered while bootstrap network-settings negotiation
     * is absent from that gameplay mapping.
     */
    @Test
    void gameplayRegistryDoesNotContainBootstrapNegotiationProcessor() {
        var registry = new ExposedProtocol_v766().createGameplayRegistry();

        assertFalse(registry.getFactories().keySet().stream()
                .anyMatch(key -> key.packetType() == BedrockPacketType.REQUEST_NETWORK_SETTINGS));
        assertTrue(registry.getFactories().containsKey(
                new PacketProcessorRegistry.Key(ClientState.CONNECTED, BedrockPacketType.LOGIN)
        ));
    }

    /**
     * {@code TrackingProcessor} represents one factory-created login processor
     * whose identity can be compared across holders and client states.
     */
    private static final class TrackingProcessor extends PacketProcessor<LoginPacket> {
        /** Creation ordinal identifying this connection's processor. */
        @SuppressWarnings("unused")
        private final int connectionId;

        /**
         * Creates a tracked processor retaining its factory's creation ordinal.
         */
        private TrackingProcessor(int connectionId) {
            this.connectionId = connectionId;
        }

        /**
         * Returns the login packet type used by the sharing scenario.
         */
        @Override
        public BedrockPacketType getPacketType() {
            return BedrockPacketType.LOGIN;
        }
    }

    /**
     * {@code ExposedProtocol_v766} exposes gameplay registration without opening
     * a network connection for the registry assertions.
     */
    private static final class ExposedProtocol_v766 extends Protocol_v766 {
        /**
         * Returns a new frozen registry containing this protocol's gameplay
         * processors. Freezing satisfies the registry's read requirement before
         * the test inspects its mappings and keeps later registration from
         * changing the asserted snapshot.
         */
        private PacketProcessorRegistry createGameplayRegistry() {
            var registry = new PacketProcessorRegistry();
            registerProcessors(registry);
            registry.freeze();
            return registry;
        }
    }
}
