package org.allaymc.server.network.processor.impl.ingame;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.server.Server;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.SetDefaultGameTypePacket;

/**
 * @author IWareQ
 */
public class SetDefaultGameTypePacketProcessor extends PacketProcessor<SetDefaultGameTypePacket> {

    @Override
    public void handleSync(EntityPlayer player, SetDefaultGameTypePacket packet, long receiveTime) {
        if (!player.isOperator()) return;
        Server.SETTINGS.genericSettings().defaultGameType(GameType.from(packet.getGamemode()));
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SET_DEFAULT_GAME_TYPE;
    }
}
