package org.allaymc.server.player;

import org.allaymc.api.form.Forms;
import org.allaymc.server.network.AllayNetworkInterface;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.Protocol;
import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.ModalFormRequestPacket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(AllayTestExtension.class)
class AllayPlayerFormUpdateTest {

    @Test
    void updateResendsTheOpenFormWithItsOwnId() {
        var player = new TestPlayer();
        var form = Forms.simple().title("casino").content("spin");
        var id = player.viewForm(form);

        form.content("result");
        assertTrue(player.updateForm(form));

        assertEquals(2, player.sent.size());
        var update = (ModalFormRequestPacket) player.sent.get(1);
        assertEquals(id, update.getFormId());
        assertTrue(update.getFormData().contains("result"));
        assertSame(form, player.getForms().get(id));
    }

    @Test
    void formsThatAreNotOpenAreNotUpdated() {
        var player = new TestPlayer();
        var answered = Forms.simple().title("answered");
        var id = player.viewForm(answered);
        player.removeForm(id);

        assertFalse(player.updateForm(answered));
        assertFalse(player.updateForm(Forms.simple().title("never opened")));
        assertEquals(1, player.sent.size());
    }

    private static final class TestPlayer extends AllayPlayer {
        private final List<BedrockPacket> sent = new ArrayList<>();
        private final Protocol protocol = mock(Protocol.class);

        private TestPlayer() {
            super(mock(BedrockServerSession.class), mock(AllayNetworkInterface.class));
            var encoder = mock(PacketEncoder.class);
            when(protocol.getEncoder()).thenReturn(encoder);
            when(encoder.encodeForm(anyInt(), any())).thenAnswer(invocation -> {
                var packet = new ModalFormRequestPacket();
                packet.setFormId(invocation.getArgument(0));
                packet.setFormData(((org.allaymc.api.form.type.Form) invocation.getArgument(1)).toJson());
                return packet;
            });
        }

        @Override
        public Protocol getProtocol() {
            return protocol;
        }

        @Override
        public void sendPacket(BedrockPacket packet) {
            sent.add(packet);
        }
    }
}
