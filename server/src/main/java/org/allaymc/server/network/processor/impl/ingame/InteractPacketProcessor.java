package org.allaymc.server.network.processor.impl.ingame;

import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.InteractPacket;

/**
 * @author Cool_Loong
 */
public class InteractPacketProcessor extends PacketProcessor<InteractPacket> {
    @Override
    public void handleSync(EntityPlayer player, InteractPacket packet, long receiveTime) {
        if (packet.getAction() == InteractPacket.Action.OPEN_INVENTORY) {
            player.getContainer(FullContainerType.PLAYER_INVENTORY).addViewer(player);
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.INTERACT;
    }
}
