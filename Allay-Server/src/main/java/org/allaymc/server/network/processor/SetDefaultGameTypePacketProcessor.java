package org.allaymc.server.network.processor;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.network.processor.PacketProcessor;
import org.allaymc.api.server.Server;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.SetDefaultGameTypePacket;

/**
 * Allay 27/01/2024
 *
 * @author IWareQ
 */
public class SetDefaultGameTypePacketProcessor extends PacketProcessor<SetDefaultGameTypePacket> {

    @Override
    public void handleSync(EntityPlayer player, SetDefaultGameTypePacket packet) {
        if (!player.isOp()) return;
        Server.SETTINGS.genericSettings().defaultGameType(GameType.from(packet.getGamemode()));
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SET_DEFAULT_GAME_TYPE;
    }
}
