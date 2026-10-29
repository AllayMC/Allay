package org.allaymc.server.network.protocol.v860;

import org.allaymc.api.player.ClientState;
import org.allaymc.server.network.processor.PacketProcessorRegistry;
import org.allaymc.server.network.processor.login.NetEaseLoginPacketProcessor;
import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.cloudburstmc.protocol.bedrock.codec.v860_netease.Bedrock_v860_NetEase;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;

public class Protocol_v860_NetEase extends Protocol_v860 {
    public Protocol_v860_NetEase() {
        super(Bedrock_v860_NetEase.CODEC, ClientVariant.NETEASE);
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
        return new PacketEncoder_v860_NetEase(data);
    }
}
