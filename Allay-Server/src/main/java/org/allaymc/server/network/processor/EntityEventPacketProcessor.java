package org.allaymc.server.network.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityEventType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.EntityEventPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Allay Project 2024/7/7
 *
 * @author daoge_cmd
 */
@Slf4j
public class EntityEventPacketProcessor extends PacketProcessor<EntityEventPacket> {

    @Override
    public PacketSignal handleAsync(EntityPlayer player, EntityEventPacket packet, long receiveTime) {
        return switch (packet.getType()) {
            case EATING_ITEM -> {
                if (packet.getData() == 0 || packet.getRuntimeEntityId() != player.getRuntimeId()) {
                    log.warn(player.getOriginName() + " entity event eid mismatch");
                    yield PacketSignal.HANDLED;
                }

                var forwardPacket = new EntityEventPacket();
                forwardPacket.setRuntimeEntityId(player.getRuntimeId());
                forwardPacket.setType(EntityEventType.EATING_ITEM);
                forwardPacket.setData(packet.getData());
                player.sendPacket(forwardPacket);
                player.sendPacketToViewers(forwardPacket);

                yield PacketSignal.HANDLED;
            }
            default -> PacketSignal.UNHANDLED;
        };
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.ENTITY_EVENT;
    }
}
