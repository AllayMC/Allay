package org.allaymc.server.network.processor.login;

import org.allaymc.server.network.protocol.Protocol;
import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.player.AllayPlayer;
import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.protocol.bedrock.codec.v2168.Bedrock_v2168;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({AllayTestExtension.class, MockitoExtension.class})
class LoginPacketProcessorV2168CodecTest {

    @Mock
    private AllayPlayer player;
    @Mock
    private Protocol protocol;

    @Test
    void switchesToTheBaseCodecForA12640Client() {
        when(player.getProtocol()).thenReturn(protocol);
        when(protocol.getVariant()).thenReturn(ClientVariant.INTERNATIONAL);
        when(protocol.getProtocolVersion()).thenReturn(Bedrock_v2168.CODEC.getProtocolVersion());
        when(player.switchProtocolCodec(Bedrock_v2168.CODEC)).thenReturn(true);

        assertTrue(new LoginPacketProcessor().selectV2168Codec(player, "1.26.40.3"));

        verify(player).switchProtocolCodec(Bedrock_v2168.CODEC);
    }

    @Test
    void keepsTheHotfixCodecForA12644Client() {
        when(player.getProtocol()).thenReturn(protocol);
        when(protocol.getVariant()).thenReturn(ClientVariant.INTERNATIONAL);
        when(protocol.getProtocolVersion()).thenReturn(Bedrock_v2168.CODEC.getProtocolVersion());

        assertTrue(new LoginPacketProcessor().selectV2168Codec(player, "1.26.44.3"));

        verify(player, never()).switchProtocolCodec(Bedrock_v2168.CODEC);
    }
}
