package org.allaymc.server.network.protocol.v766;

import org.allaymc.api.player.ClientState;
import org.allaymc.server.network.processor.PacketProcessorRegistry;
import org.allaymc.server.network.processor.login.NetEaseLoginPacketProcessor;
import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.cloudburstmc.protocol.bedrock.codec.v766_netease.Bedrock_v766_NetEase;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;

/** NetEase v766 protocol. */
public class ProtocolV766NetEase extends ProtocolV766 {
    public ProtocolV766NetEase() {
        super(Bedrock_v766_NetEase.CODEC, ClientVariant.NETEASE);
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
        return new PacketEncoderV766NetEase(data);
    }
}
