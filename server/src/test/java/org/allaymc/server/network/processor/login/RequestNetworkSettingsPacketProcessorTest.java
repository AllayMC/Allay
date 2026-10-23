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

/**
 * {@code RequestNetworkSettingsPacketProcessorTest} checks delegation of client
 * variant detection to the connection's source transport interface.
 */
@ExtendWith(MockitoExtension.class)
class RequestNetworkSettingsPacketProcessorTest {

    /** Mock connection supplying its source transport. */
    @Mock
    private AllayPlayer allayPlayer;
    /** Mock transport classifying the client variant. */
    @Mock
    private AllayNetworkInterface networkInterface;
    /** Mock session passed to transport classification. */
    @Mock
    private BedrockServerSession session;

    /**
     * Models a transport reporting an international connection without exposing a
     * RakNet version to this processor. Detection must return the transport's
     * {@code false} result unchanged.
     */
    @Test
    void shouldTreatTransportWithoutRakVersionAsInternationalClient() {
        var processor = new RequestNetworkSettingsPacketProcessor();
        when(allayPlayer.getSourceInterface()).thenReturn(networkInterface);
        when(allayPlayer.getSession()).thenReturn(session);
        when(networkInterface.isNetEaseClient(session)).thenReturn(false);

        assertFalse(processor.isNetEaseClient(allayPlayer));
    }

    /**
     * Models a source transport identifying the connection as a NetEase client.
     * Detection must pass that session to the interface and return its positive
     * result.
     */
    @Test
    void shouldDetectNetEaseClientFromTransportInterface() {
        var processor = new RequestNetworkSettingsPacketProcessor();
        when(allayPlayer.getSourceInterface()).thenReturn(networkInterface);
        when(allayPlayer.getSession()).thenReturn(session);
        when(networkInterface.isNetEaseClient(session)).thenReturn(true);

        assertTrue(processor.isNetEaseClient(allayPlayer));
    }
}
