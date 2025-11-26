package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author daoge_cmd
 */
public abstract class ILoginPacketProcessor<T extends BedrockPacket> extends PacketProcessor<T> {
    @Override
    public void handleSync(Player player, T packet, long receiveTime) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PacketSignal handleAsync(Player player, T packet, long receiveTime) {
        handle(player, packet);
        return PacketSignal.HANDLED;
    }

    public abstract void handle(Player player, T packet);
}
