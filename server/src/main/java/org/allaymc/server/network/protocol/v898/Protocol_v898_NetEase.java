package org.allaymc.server.network.protocol.v898;

import org.allaymc.api.player.ClientState;
import org.allaymc.server.network.processor.PacketProcessorRegistry;
import org.allaymc.server.network.processor.login.NetEaseLoginPacketProcessor;
import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;

/**
 * NetEase 1.21.130 (protocol 898) protocol, used by the 3.10 client.
 *
 * @author daoge_cmd
 */
public class Protocol_v898_NetEase extends Protocol_v898 {
    public Protocol_v898_NetEase() {
        super(NetEaseCodec898.CODEC, ClientVariant.NETEASE);
    }

    @Override
    protected void registerProcessors(PacketProcessorRegistry registry) {
        super.registerProcessors(registry);
        registry.register(
                ClientState.CONNECTED,
                BedrockPacketType.LOGIN,
                NetEaseLoginPacketProcessor::new
        );
    }

    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoder_v898_NetEase(data);
    }
}
