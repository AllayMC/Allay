package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.component.EntityRideableComponent;
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
        var entity = player.getControlledEntity();
        if (packet.getAction() == InteractPacket.Action.OPEN_INVENTORY) {
            entity.getContainer(ContainerTypes.INVENTORY).addViewer(player);
        } else if (packet.getAction() == InteractPacket.Action.LEAVE_VEHICLE &&
                   entity.getVehicle() instanceof EntityRideableComponent rideableComponent) {
            rideableComponent.removePassenger(entity);
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.INTERACT;
    }
}
