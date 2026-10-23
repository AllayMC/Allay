package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.InteractPacket;

/**
 * {@code InteractPacketProcessor} handles the inventory-opening action in a
 * client interaction packet. Opening
 * adds the connection as a viewer of its controlled entity's inventory,
 * delegating container presentation to that container. Other interaction
 * actions have no effect in this processor.
 *
 * @author Cool_Loong
 */
public class InteractPacketProcessor extends PacketProcessor<InteractPacket> {
    /**
     * Adds the player as an inventory viewer for {@code OPEN_INVENTORY} actions. The
     * player must have a controlled entity with an inventory container; all
     * other actions are ignored without changing viewers.
     */
    @Override
    public void handleSync(Player player, InteractPacket packet, long receiveTime) {
        if (packet.getAction() == InteractPacket.Action.OPEN_INVENTORY) {
            player.getControlledEntity().getContainer(ContainerTypes.INVENTORY).addViewer(player);
        }
    }

    /**
     * Returns the interact packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.INTERACT;
    }
}
