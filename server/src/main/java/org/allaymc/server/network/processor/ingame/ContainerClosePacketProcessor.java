package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
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
            log.warn("Player is not viewing an inventory");
            return;
        }

        opened.removeViewer(player);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CONTAINER_CLOSE;
    }
}
