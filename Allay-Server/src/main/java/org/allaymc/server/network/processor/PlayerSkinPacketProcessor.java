package org.allaymc.server.network.processor;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.client.skin.Skin;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerChangeSkinEvent;
import org.allaymc.api.network.processor.PacketProcessor;
import org.allaymc.api.server.Server;
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
            log.warn(player.getOriginName() + " want to use an invalid skin!");
            return;
        }

        var event = new PlayerChangeSkinEvent(player, player.getSkin(), newSkin);
        Server.getInstance().getEventBus().callEvent(event);
        if (event.isCancelled()) {
            return;
        }

        player.setSkin(newSkin);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAYER_SKIN;
    }
}
