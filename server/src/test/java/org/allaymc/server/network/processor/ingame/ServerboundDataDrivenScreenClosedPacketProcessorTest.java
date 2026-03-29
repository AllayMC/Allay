package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.player.Player;
import org.cloudburstmc.protocol.bedrock.packet.ServerboundDataDrivenScreenClosedPacket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ServerboundDataDrivenScreenClosedPacketProcessorTest {

    @Mock
    private Player player;

    @Test
    void shouldRemoveActiveScreenWhenClientClosesDdui() {
        var packet = new ServerboundDataDrivenScreenClosedPacket();
        packet.setFormId(0);

        var processor = new ServerboundDataDrivenScreenClosedPacketProcessor();
        processor.handleSync(player, packet, 0L);

        verify(player).removeDataDrivenScreen();
    }
}
