package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.action.SimpleEntityAction;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.EntityEventPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author daoge_cmd
 */
@Slf4j
public class EntityEventPacketProcessor extends PacketProcessor<EntityEventPacket> {

    @Override
    public PacketSignal handleAsync(Player player, EntityEventPacket packet, long receiveTime) {
        return switch (packet.getType()) {
            case EATING_ITEM -> {
                var entity = player.getControlledEntity();
                if (packet.getData() == 0 || packet.getRuntimeEntityId() != entity.getRuntimeId()) {
                    log.warn("{} entity event eid mismatch", player.getOriginName());
                    yield PacketSignal.HANDLED;
                }

                entity.applyAction(SimpleEntityAction.EAT);
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
