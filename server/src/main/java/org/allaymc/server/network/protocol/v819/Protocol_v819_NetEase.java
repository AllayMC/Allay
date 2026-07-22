package org.allaymc.server.network.protocol.v819;

import org.allaymc.api.player.ClientState;
import org.allaymc.server.network.processor.PacketProcessorRegistry;
import org.allaymc.server.network.processor.login.NetEaseLoginPacketProcessor;
import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.cloudburstmc.protocol.bedrock.codec.v819_netease.Bedrock_v819_NetEase;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;

/** NetEase v819 protocol. */
public class Protocol_v819_NetEase extends Protocol_v819 {
    public Protocol_v819_NetEase() {
        super(Bedrock_v819_NetEase.CODEC, ClientVariant.NETEASE);
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
        return new PacketEncoder_v819_NetEase(data);
    }
}
