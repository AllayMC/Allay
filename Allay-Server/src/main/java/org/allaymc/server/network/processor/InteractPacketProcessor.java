package org.allaymc.server.network.processor;

import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.network.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.InteractPacket;

/**
 * Allay Project 11/22/2023
 *
 * @author Cool_Loong
 */
public class InteractPacketProcessor extends PacketProcessor<InteractPacket> {
    @Override
    public void handleSync(EntityPlayer player, InteractPacket pk) {
        switch (pk.getAction()) {
            case OPEN_INVENTORY -> {
                player.getContainer(FullContainerType.PLAYER_INVENTORY).addViewer(player);
            }
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.INTERACT;
    }
}
