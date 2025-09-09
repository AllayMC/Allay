package org.allaymc.server.network.processor.impl.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.player.GameMode;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.EntityPickRequestPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author IWareQ
 */
@Slf4j
public class EntityPickRequestPacketProcessor extends PacketProcessor<EntityPickRequestPacket> {
    @Override
    public PacketSignal handleAsync(EntityPlayer player, EntityPickRequestPacket packet, long receiveTime) {
        if (player.getGameMode() != GameMode.CREATIVE) {
            log.warn("Player {} tried to pick an entity in a non-creative gamemode", player.getOriginName());
            return PacketSignal.UNHANDLED;
        }

        var dimension = player.getDimension();
        var entity = dimension.getEntityManager().getEntity(packet.getRuntimeEntityId());
        if (entity == null) {
            log.warn("Player {} tried to pick an entity that doesn't exist", player.getOriginName());
            return PacketSignal.UNHANDLED;
        }

        // TODO: player.setItemInHand(entity.toPickedItem(packet.isWithData()));
        return PacketSignal.HANDLED;
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.ENTITY_PICK_REQUEST;
    }
}
