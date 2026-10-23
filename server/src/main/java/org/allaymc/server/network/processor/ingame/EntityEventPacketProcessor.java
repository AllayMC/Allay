package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.action.SimpleEntityAction;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.EntityEventPacket;
import org.cloudburstmc.protocol.bedrock.packet.PacketSignal;

/**
 * {@code EntityEventPacketProcessor} handles client eating notifications that
 * identify the controlled player
 * entity and carry nonzero event data. Invalid eating notifications are logged
 * and consumed, preventing their dispatch as accepted eating actions. Other
 * entity event kinds return unhandled and reach the inherited empty synchronous
 * hook.
 *
 * @author daoge_cmd
 */
@Slf4j
public class EntityEventPacketProcessor extends PacketProcessor<EntityEventPacket> {

    /**
     * Applies the eating action only when event data is nonzero and runtime
     * identity matches the controlled entity. Invalid eating events are logged
     * and consumed; other event kinds return {@code UNHANDLED} without applying an
     * action.
     */
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

    /**
     * Returns the entity event packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.ENTITY_EVENT;
    }
}
