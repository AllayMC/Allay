package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.network.processor.PacketProcessor;
import org.allaymc.server.utils.Utils;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.SetPlayerGameTypePacket;

/**
 * @author IWareQ
 */
public class SetPlayerGameTypePacketProcessor extends PacketProcessor<SetPlayerGameTypePacket> {

    @Override
    public void handleSync(EntityPlayer player, SetPlayerGameTypePacket packet, long receiveTime) {
        if (!player.isOperator()) return;
        player.setGameMode(Utils.toGameMode(GameType.from(packet.getGamemode())));
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SET_PLAYER_GAME_TYPE;
    }
}
