package org.allaymc.server.network.processor;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.SetPlayerGameTypePacket;

/**
 * Allay 27/01/2024
 *
 * @author IWareQ
 */
public class SetPlayerGameTypePacketProcessor extends PacketProcessor<SetPlayerGameTypePacket> {

    @Override
    public void handleSync(EntityPlayer player, SetPlayerGameTypePacket packet) {
        if (!player.isOp()) return;
        player.setGameType(GameType.from(packet.getGamemode()));
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SET_PLAYER_GAME_TYPE;
    }
}
