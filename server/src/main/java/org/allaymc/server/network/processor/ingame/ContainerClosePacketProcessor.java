package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ContainerClosePacket;

/**
 * @author Cool_Loong | daoge_cmd
 */
public class ContainerClosePacketProcessor extends PacketProcessor<ContainerClosePacket> {

    @Override
    public void handleSync(Player player, ContainerClosePacket packet, long receiveTime) {
        var opened = player.getOpenedContainer(packet.getId());
        var suppressCloseResponse = false;

        // In edge cases, such as opening the chat at the exact moment a container is opened,
        // the client sends a container-close packet with an id of -1, which means that any
        // currently open container should be closed.
        if (opened == null && packet.getId() == -1) {
            opened = player.getOpenedContainer(ContainerTypes.INVENTORY);
            if (opened != null) {
                suppressCloseResponse = true;
            } else if (!player.getOpenedContainers().isEmpty()) {
                var allayPlayer = (AllayPlayer) player;
                for (var container : player.getOpenedContainers()) {
                    allayPlayer.setSuppressNextContainerClosePacket(true);
                    container.removeViewer(player);
                }
                return;
            }
        }

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

        var allayPlayer = (AllayPlayer) player;
        if (suppressCloseResponse) {
            allayPlayer.setSuppressNextContainerClosePacket(true);
        } else {
            allayPlayer.setContainerClosedByClient(true);
        }
        opened.removeViewer(player);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CONTAINER_CLOSE;
    }
}
