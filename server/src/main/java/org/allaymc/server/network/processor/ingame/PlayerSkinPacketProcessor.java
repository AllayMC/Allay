package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerChangeSkinEvent;
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
    public void handleSync(EntityPlayer player, PlayerSkinPacket packet, long receiveTime) {
        var newSkin = packet.getSkin();
        if (!newSkin.isValid()) {
            log.warn("{} want to use an invalid skin!", player.getOriginName());
            return;
        }

        var event = new PlayerChangeSkinEvent(player, player.getSkin(), SkinConvertor.fromSerializedSkin(newSkin));
        if (event.call()) {
            player.setSkin(event.getNewSkin());
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAYER_SKIN;
    }
}
