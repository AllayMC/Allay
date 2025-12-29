package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ContainerClosePacket;

/**
 * @author Cool_Loong | daoge_cmd
 */
@Slf4j
public class ContainerClosePacketProcessor extends PacketProcessor<ContainerClosePacket> {

    @Override
    public void handleSync(Player player, ContainerClosePacket packet, long receiveTime) {
        var opened = player.getOpenedContainer(packet.getId());
        if (opened == null) {
            // This may be a container closed server-side already removed from the open list, and
            // in that case we can directly respond to the client with a ContainerClosePacket
            var response = new ContainerClosePacket();
            response.setId(packet.getId());
            response.setServerInitiated(false);
            response.setType(packet.getType());
            player.sendPacket(response);
            return;
        }

        ((AllayPlayer) player).setContainerClosedByClient(true);
        opened.removeViewer(player);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CONTAINER_CLOSE;
    }
}
