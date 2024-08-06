package org.allaymc.server.network.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.client.skin.Skin;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerChangeSkinEvent;
import org.allaymc.api.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.PlayerSkinPacket;

/**
 * Allay Project 2024/2/27
 *
 * @author daoge_cmd
 */
@Slf4j
public class PlayerSkinPacketProcessor extends PacketProcessor<PlayerSkinPacket> {

    @Override
    public void handleSync(EntityPlayer player, PlayerSkinPacket packet, long receiveTime) {
        var newSkin = Skin.fromNetwork(packet.getSkin());
        if (!newSkin.isValid()) {
            log.warn("{} want to use an invalid skin!", player.getOriginName());
            return;
        }

        var event = new PlayerChangeSkinEvent(player, player.getSkin(), newSkin);
        event.call();
        if (event.isCancelled()) return;

        player.setSkin(event.getNewSkin());
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAYER_SKIN;
    }
}
