package org.allaymc.server.network.protocol.v819;

import org.allaymc.api.player.ClientState;
import org.allaymc.server.network.processor.PacketProcessorRegistry;
import org.allaymc.server.network.processor.login.NetEaseLoginPacketProcessor;
import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.cloudburstmc.protocol.bedrock.codec.v819_netease.Bedrock_v819_NetEase;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;

/**
 * {@code Protocol_v819_NetEase} configures the NetEase variant of wire protocol
 * 819 using its distribution-specific codec. The same-version international
 * implementation supplies baseline processing, then the connected-state login
 * factory is replaced with NetEase login handling and packet creation uses the
 * corresponding NetEase encoder.
 */
public class Protocol_v819_NetEase extends Protocol_v819 {
    /**
     * Creates an uninitialized NetEase protocol using its v819 codec. The
     * constructor records the distribution and codec identity; callers must
     * invoke {@code initialize} after the subtype has been fully constructed.
     */
    public Protocol_v819_NetEase() {
        super(Bedrock_v819_NetEase.CODEC, ClientVariant.NETEASE);
    }

    /**
     * Registers the inherited processors, then replaces the connected-state
     * login factory with NetEase login handling. The registry is still mutable
     * during this hook; later freezing preserves the replacement for newly
     * created connection holders.
     */
    @Override
    protected void registerProcessors(PacketProcessorRegistry registry) {
        super.registerProcessors(registry);
        // Register after the parent so the same state/type key selects NetEase
        // login while other inherited registrations remain available.
        registry.register(
                ClientState.CONNECTED,
                BedrockPacketType.LOGIN,
                NetEaseLoginPacketProcessor::new
        );
    }

    /**
     * Creates the {@code PacketEncoder_v819_NetEase} implementation using the
     * exact supplied data instance. The enclosing initialization lifecycle
     * verifies this shared identity before publishing the protocol for
     * connection use.
     */
    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoder_v819_NetEase(data);
    }
}
