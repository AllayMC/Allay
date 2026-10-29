package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.player.ClientPlayMode;
import org.allaymc.api.player.InputInteractionModel;
import org.allaymc.api.player.InputMode;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.packet.PlayerAuthInputPacket;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PlayerAuthInputPacketProcessorTest {

    @Test
    void shouldIgnoreProtocolInputCountMarkers() {
        var player = mock(AllayPlayer.class);
        when(player.getInputMode()).thenReturn(InputMode.MOUSE);
        when(player.getPlayMode()).thenReturn(ClientPlayMode.NORMAL);
        when(player.getInputInteractionModel()).thenReturn(InputInteractionModel.CLASSIC);
        var packet = new PlayerAuthInputPacket();
        packet.setInputMode(org.cloudburstmc.protocol.bedrock.data.InputMode.COUNT);
        packet.setPlayMode(org.cloudburstmc.protocol.bedrock.data.ClientPlayMode.NORMAL);
        packet.setInputInteractionModel(org.cloudburstmc.protocol.bedrock.data.InputInteractionModel.COUNT);

        new TestProcessor().update(player, packet);

        verify(player, never()).setInputMode(any(InputMode.class));
        verify(player, never()).setPlayMode(any(ClientPlayMode.class));
        verify(player, never()).setInputInteractionModel(any(InputInteractionModel.class));
    }

    private static final class TestProcessor extends PlayerAuthInputPacketProcessor {

        private void update(AllayPlayer player, PlayerAuthInputPacket packet) {
            super.updatePlayerInputState(player, packet);
        }
    }
}
