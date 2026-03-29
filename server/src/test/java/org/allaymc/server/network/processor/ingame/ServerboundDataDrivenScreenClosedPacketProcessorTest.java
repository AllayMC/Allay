package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.player.Player;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundDataDrivenScreenClosedPacket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verify;

/**
 * Tests for DDUI close packet routing and transition filtering.
 *
 * @author Miroshka
 */
@ExtendWith(MockitoExtension.class)
class ServerboundDataDrivenScreenClosedPacketProcessorTest {

    @Mock
    private Player player;

    @Test
    void shouldRemoveActiveScreenWhenClientClosesDdui() {
        var packet = new ServerboundDataDrivenScreenClosedPacket();
        packet.setFormId(37);
        packet.setCloseReason(ServerboundDataDrivenScreenClosedPacket.CloseReason.CLIENT_CANCELED);

        var processor = new ServerboundDataDrivenScreenClosedPacketProcessor();
        processor.handleSync(player, packet, 0L);

        verify(player).removeDataDrivenScreen(37);
    }

    @Test
    void shouldRemoveCurrentScreenWhenClientOmitsFormId() {
        var packet = new ServerboundDataDrivenScreenClosedPacket();
        packet.setCloseReason(ServerboundDataDrivenScreenClosedPacket.CloseReason.CLIENT_CANCELED);

        var processor = new ServerboundDataDrivenScreenClosedPacketProcessor();
        processor.handleSync(player, packet, 0L);

        verify(player).removeDataDrivenScreen();
        verifyNoMoreInteractions(player);
    }

    @Test
    void shouldIgnoreProgrammaticCloseForMatchingFormId() {
        var packet = new ServerboundDataDrivenScreenClosedPacket();
        packet.setFormId(37);
        packet.setCloseReason(ServerboundDataDrivenScreenClosedPacket.CloseReason.PROGRAMMATIC_CLOSE);

        var processor = new ServerboundDataDrivenScreenClosedPacketProcessor();
        processor.handleSync(player, packet, 0L);

        verifyNoMoreInteractions(player);
    }

    @Test
    void shouldIgnoreBusyCloseWithoutFormId() {
        var packet = new ServerboundDataDrivenScreenClosedPacket();
        packet.setCloseReason(ServerboundDataDrivenScreenClosedPacket.CloseReason.USER_BUSY);

        var processor = new ServerboundDataDrivenScreenClosedPacketProcessor();
        processor.handleSync(player, packet, 0L);

        verifyNoMoreInteractions(player);
    }
}
