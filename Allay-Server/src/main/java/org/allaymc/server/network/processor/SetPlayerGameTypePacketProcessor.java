package org.allaymc.server.network.processor;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.network.DataPacketProcessor;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.SetPlayerGameTypePacket;

/**
 * Allay 27/01/2024
 *
 * @author IWareQ
 */
public class SetPlayerGameTypePacketProcessor extends DataPacketProcessor<SetPlayerGameTypePacket> {

    @Override
    public void handle(EntityPlayer player, SetPlayerGameTypePacket pk) {
        if (!player.isOp()) return;
        player.setGameType(GameType.from(pk.getGamemode()));
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SET_PLAYER_GAME_TYPE;
    }
}
