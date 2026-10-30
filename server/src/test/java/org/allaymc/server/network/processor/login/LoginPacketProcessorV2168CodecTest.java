package org.allaymc.server.network.processor.login;

import org.allaymc.server.network.protocol.Protocol;
import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.player.AllayPlayer;
import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.protocol.bedrock.codec.v2168.Bedrock_v2168;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * {@code LoginPacketProcessorV2168CodecTest} checks base-release codec selection
 * without replacing later protocol versions or the matching hotfix codec.
 */
@ExtendWith({AllayTestExtension.class, MockitoExtension.class})
class LoginPacketProcessorV2168CodecTest {

    /** Mock connection receiving codec-switch requests. */
    @Mock
    private AllayPlayer player;
    /** Mock negotiated protocol describing the branch. */
    @Mock
    private Protocol protocol;

    /**
     * Reports a base-release game version while a later wire protocol is selected.
     * Codec selection must succeed without requesting the older {@code v2168}
     * base codec.
     */
    @ParameterizedTest
    @ValueSource(ints = {2169, 2193})
    void keepsTheNegotiatedCodecForLaterProtocols(int version) {
        when(player.getProtocol()).thenReturn(protocol);
        when(protocol.getVariant()).thenReturn(ClientVariant.INTERNATIONAL);
        when(protocol.getProtocolVersion()).thenReturn(version);

        assertTrue(new LoginPacketProcessor().selectV2168Codec(player, "1.26.40.3"));

        verify(player, never()).switchProtocolCodec(Bedrock_v2168.CODEC);
    }

    /**
     * Reports an international {@code v2168} connection running the base game
     * release. Codec selection must forward the base-codec switch and preserve
     * its successful result.
     */
    @Test
    void switchesToTheBaseCodecForA12640Client() {
        when(player.getProtocol()).thenReturn(protocol);
        when(protocol.getVariant()).thenReturn(ClientVariant.INTERNATIONAL);
        when(protocol.getProtocolVersion()).thenReturn(Bedrock_v2168.CODEC.getProtocolVersion());
        when(player.switchProtocolCodec(Bedrock_v2168.CODEC)).thenReturn(true);

        assertTrue(new LoginPacketProcessor().selectV2168Codec(player, "1.26.40.3"));

        verify(player).switchProtocolCodec(Bedrock_v2168.CODEC);
    }

    /**
     * Reports the hotfix game-version stem on an international {@code v2168}
     * connection. Selection must retain its negotiated codec and avoid requesting
     * the base-release serializer.
     */
    @Test
    void keepsTheHotfixCodecForA12644Client() {
        when(player.getProtocol()).thenReturn(protocol);
        when(protocol.getVariant()).thenReturn(ClientVariant.INTERNATIONAL);
        when(protocol.getProtocolVersion()).thenReturn(Bedrock_v2168.CODEC.getProtocolVersion());

        assertTrue(new LoginPacketProcessor().selectV2168Codec(player, "1.26.44.3"));

        verify(player, never()).switchProtocolCodec(Bedrock_v2168.CODEC);
    }
}
