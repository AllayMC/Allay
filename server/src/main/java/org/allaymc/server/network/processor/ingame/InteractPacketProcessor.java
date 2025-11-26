package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.InteractPacket;

/**
 * @author Cool_Loong
 */
public class InteractPacketProcessor extends PacketProcessor<InteractPacket> {
    @Override
    public void handleSync(Player player, InteractPacket packet, long receiveTime) {
        if (packet.getAction() == InteractPacket.Action.OPEN_INVENTORY) {
            player.getControlledEntity().getContainer(ContainerTypes.INVENTORY).addViewer(player);
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.INTERACT;
    }
}
