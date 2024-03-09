package org.allaymc.api.network.processor;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Allay Project 2024/2/2
 *
 * @author daoge_cmd
 */
// Add a 'I' prefix to prevent having same name to LoginPacketProcessor
public abstract class ILoginPacketProcessor<T extends BedrockPacket> extends PacketProcessor<T> {
    @Override
    public void handleSync(EntityPlayer player, T packet) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PacketSignal handleAsync(EntityPlayer player, T packet) {
        handle(player, packet);
        return PacketSignal.HANDLED;
    }

    public abstract void handle(EntityPlayer player, T packet);
}
