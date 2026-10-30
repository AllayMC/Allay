package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.GameMode;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.EntityPickRequestPacket;
import org.cloudburstmc.protocol.bedrock.packet.PacketSignal;

/**
 * {@code EntityPickRequestPacketProcessor} validates a creative player's request
 * to pick an existing entity from its
 * current dimension. Noncreative players and missing targets are logged and
 * return unhandled. Valid requests are consumed, but this implementation does
 * not yet create or place an item representing the selected entity.
 *
 * @author IWareQ
 */
@Slf4j
public class EntityPickRequestPacketProcessor extends PacketProcessor<EntityPickRequestPacket> {
    /**
     * Checks creative mode and target existence before consuming an entity-pick
     * request. Invalid requests return {@code UNHANDLED} to the inherited empty
     * synchronous hook, and valid requests currently leave the player's
     * inventory unchanged.
     */
    @Override
    public PacketSignal handleAsync(Player player, EntityPickRequestPacket packet, long receiveTime) {
        var entity = player.getControlledEntity();
        if (entity.getGameMode() != GameMode.CREATIVE) {
            log.warn("Player {} tried to pick an entity in a non-creative gamemode", player.getOriginName());
            return PacketSignal.UNHANDLED;
        }

        var dimension = entity.getDimension();
        var pickedEntity = dimension.getEntityManager().getEntity(packet.getRuntimeEntityId());
        if (pickedEntity == null) {
            log.warn("Player {} tried to pick an entity that doesn't exist", player.getOriginName());
            return PacketSignal.UNHANDLED;
        }

        // TODO: Add an entity-to-item conversion honoring isWithData, then
        // place the resulting item in the creative player's hand after target
        // validation.
        return PacketSignal.HANDLED;
    }

    /**
     * Returns the entity pick request packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.ENTITY_PICK_REQUEST;
    }
}
