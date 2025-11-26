package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.eventbus.event.player.PlayerChangeSkinEvent;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.allaymc.server.player.SkinConvertor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.PlayerSkinPacket;

/**
 * @author daoge_cmd
 */
@Slf4j
public class PlayerSkinPacketProcessor extends PacketProcessor<PlayerSkinPacket> {
    @Override
    public void handleSync(Player player, PlayerSkinPacket packet, long receiveTime) {
        var newSkin = packet.getSkin();
        if (!newSkin.isValid()) {
            log.warn("{} want to use an invalid skin!", player.getOriginName());
            return;
        }

        var entity = player.getControlledEntity();
        var event = new PlayerChangeSkinEvent(entity, entity.getSkin(), SkinConvertor.fromSerializedSkin(newSkin));
        if (event.call()) {
            entity.setSkin(event.getNewSkin());
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAYER_SKIN;
    }
}
