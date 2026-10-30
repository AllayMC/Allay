package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.player.Player;
import org.allaymc.server.container.impl.UnopenedContainerId;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.MobEquipmentPacket;

/**
 * {@code MobEquipmentPacketProcessor} applies client hotbar selections from the
 * player-inventory equipment channel.
 * Equipment packets for other containers are ignored. The controlled entity
 * receives the requested hotbar slot with client notification disabled,
 * allowing its existing slot logic to update server state without echoing this
 * selection.
 *
 * @author Cool_Loong
 */
public class MobEquipmentPacketProcessor extends PacketProcessor<MobEquipmentPacket> {
    /**
     * Requests a hand-slot change only for the player inventory container ID.
     * The hotbar slot is passed to the entity with notification disabled, and
     * this handler does not use the packet's supplied item payload.
     */
    @Override
    public void handleSync(Player player, MobEquipmentPacket packet, long receiveTime) {
        if (packet.getContainerId() != UnopenedContainerId.PLAYER_INVENTORY) {
            return;
        }
        // The client already selected this hotbar slot; suppressing its normal
        // notification avoids echoing that selection while the entity updates
        // its server-side hand state.
        player.getControlledEntity().setHandSlot(packet.getHotbarSlot(), false);
    }

    /**
     * Returns the mob equipment packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.MOB_EQUIPMENT;
    }
}
