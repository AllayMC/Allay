package org.allaymc.server.network.processor.login;

import org.allaymc.server.network.AllayNetworkInterface;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RequestNetworkSettingsPacketProcessorTest {

    @Mock
    private AllayPlayer allayPlayer;
    @Mock
    private AllayNetworkInterface networkInterface;
    @Mock
    private BedrockServerSession session;

    @Test
    void shouldTreatTransportWithoutRakVersionAsInternationalClient() {
        var processor = new RequestNetworkSettingsPacketProcessor();
        when(allayPlayer.getSourceInterface()).thenReturn(networkInterface);
        when(allayPlayer.getSession()).thenReturn(session);
        when(networkInterface.isNetEaseClient(session)).thenReturn(false);

        assertFalse(processor.isNetEaseClient(allayPlayer));
    }

    @Test
    void shouldDetectNetEaseClientFromTransportInterface() {
        var processor = new RequestNetworkSettingsPacketProcessor();
        when(allayPlayer.getSourceInterface()).thenReturn(networkInterface);
        when(allayPlayer.getSession()).thenReturn(session);
        when(networkInterface.isNetEaseClient(session)).thenReturn(true);

        assertTrue(processor.isNetEaseClient(allayPlayer));
    }
}
