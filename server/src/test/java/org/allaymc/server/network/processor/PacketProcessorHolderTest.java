package org.allaymc.server.network.processor;

import org.allaymc.api.player.ClientState;
import org.allaymc.server.network.processor.ingame.ServerboundDataDrivenScreenClosedPacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundDataDrivenScreenClosedPacket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PacketProcessorHolderTest {

    @Test
    void shouldRegisterDataDrivenScreenClosedProcessorInGameState() {
        var holder = new PacketProcessorHolder();

        assertTrue(holder.setClientState(ClientState.CONNECTED, false));
        assertTrue(holder.setClientState(ClientState.LOGGED_IN, false));
        assertTrue(holder.setClientState(ClientState.SPAWNED, false));
        assertTrue(holder.setClientState(ClientState.IN_GAME, false));

        var packet = new ServerboundDataDrivenScreenClosedPacket();

        assertInstanceOf(ServerboundDataDrivenScreenClosedPacketProcessor.class, holder.getProcessor(packet));
    }
}
