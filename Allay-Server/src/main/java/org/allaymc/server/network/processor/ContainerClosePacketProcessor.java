package org.allaymc.server.network.processor;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ContainerClosePacket;

/**
 * Allay Project 11/22/2023
 *
 * @author Cool_Loong
 */
public class ContainerClosePacketProcessor extends PacketProcessor<ContainerClosePacket> {
    @Override
    public void handleSync(EntityPlayer player, ContainerClosePacket packet) {
        var opened = player.getOpenedContainer(packet.getId());
        if (opened == null) throw new IllegalStateException("Player is not viewing an inventory");
        opened.removeViewer(player);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CONTAINER_CLOSE;
    }
}
