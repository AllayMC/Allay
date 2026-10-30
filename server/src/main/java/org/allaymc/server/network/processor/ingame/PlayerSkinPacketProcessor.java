package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.eventbus.event.player.PlayerChangeSkinEvent;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.allaymc.server.player.SkinConvertor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.PlayerSkinPacket;

/**
 * {@code PlayerSkinPacketProcessor} validates a submitted serialized skin before
 * offering its converted form to
 * the player skin-change event. Invalid serialized skins are logged and
 * ignored. An accepted event supplies the skin assigned to the controlled
 * entity, so listeners may replace the converted skin before application.
 *
 * @author daoge_cmd
 */
@Slf4j
public class PlayerSkinPacketProcessor extends PacketProcessor<PlayerSkinPacket> {
    /**
     * Rejects invalid serialized skins and dispatches a change event for valid
     * input. The packet must contain a skin and the player must control an
     * entity; cancellation preserves the current skin, while acceptance uses
     * the event's replacement value.
     */
    @Override
    public void handleSync(Player player, PlayerSkinPacket packet, long receiveTime) {
        var newSkin = packet.getSkin();
        if (!newSkin.isValid()) {
            log.warn("{} want to use an invalid skin!", player.getOriginName());
            return;
        }

        var entity = player.getControlledEntity();
        var event = new PlayerChangeSkinEvent(entity, entity.getSkin(), SkinConvertor.fromSerializedSkin(newSkin));
        // Use the event's final skin only after approval so listeners can veto
        // the change or replace the converted client payload.
        if (event.call()) {
            entity.setSkin(event.getNewSkin());
        }
    }

    /**
     * Returns the player skin packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAYER_SKIN;
    }
}
