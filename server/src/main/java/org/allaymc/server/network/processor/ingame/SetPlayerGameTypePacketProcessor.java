package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.permission.Permissions;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.NetworkHelper;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.SetPlayerGameTypePacket;

/**
 * @author IWareQ
 */
public class SetPlayerGameTypePacketProcessor extends PacketProcessor<SetPlayerGameTypePacket> {

    @Override
    public void handleSync(Player player, SetPlayerGameTypePacket packet, long receiveTime) {
        if (player.getControlledEntity().hasPermission(Permissions.COMMAND_GAMEMODE).asBoolean()) {
            player.getControlledEntity().setGameMode(NetworkHelper.fromNetwork(GameType.from(packet.getGamemode())));
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SET_PLAYER_GAME_TYPE;
    }
}
