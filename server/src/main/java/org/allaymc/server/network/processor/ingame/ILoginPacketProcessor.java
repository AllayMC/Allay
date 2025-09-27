package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author daoge_cmd
 */
public abstract class ILoginPacketProcessor<T extends BedrockPacket> extends PacketProcessor<T> {
    @Override
    public void handleSync(EntityPlayer player, T packet, long receiveTime) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PacketSignal handleAsync(EntityPlayer player, T packet, long receiveTime) {
        handle(player, packet);
        return PacketSignal.HANDLED;
    }

    public abstract void handle(EntityPlayer player, T packet);
}
