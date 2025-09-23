package org.allaymc.server.network.processor.ingame;

import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author daoge_cmd
 */
public abstract class ILoginPacketProcessor<T extends BedrockPacket> extends PacketProcessor<T> {
    @Override
    public void handleSync(EntityPlayerImpl player, T packet, long receiveTime) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PacketSignal handleAsync(EntityPlayerImpl player, T packet, long receiveTime) {
        handle(player, packet);
        return PacketSignal.HANDLED;
    }

    public abstract void handle(EntityPlayerImpl player, T packet);
}
