package org.allaymc.server.network.processor;

import org.allaymc.api.client.skin.Skin;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.PlayerSkinPacket;

/**
 * Allay Project 2024/2/27
 *
 * @author daoge_cmd
 */
public class PlayerSkinPacketProcessor extends PacketProcessor<PlayerSkinPacket> {

    @Override
    public void handleSync(EntityPlayer player, PlayerSkinPacket packet) {
        player.setSkin(Skin.fromNetwork(packet.getSkin()));
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.PLAYER_SKIN;
    }
}
