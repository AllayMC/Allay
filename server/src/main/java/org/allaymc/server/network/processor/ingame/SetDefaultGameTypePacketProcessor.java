package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.permission.Permissions;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.NetworkHelper;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.SetDefaultGameTypePacket;

/**
 * @author IWareQ
 */
public class SetDefaultGameTypePacketProcessor extends PacketProcessor<SetDefaultGameTypePacket> {

    @Override
    public void handleSync(Player player, SetDefaultGameTypePacket packet, long receiveTime) {
        if (player.getControlledEntity().hasPermission(Permissions.COMMAND_DEFAULTGAMEMODE).asBoolean()) {
            player.getControlledEntity().getWorld().getWorldData().setGameMode(NetworkHelper.fromNetwork(GameType.from(packet.getGamemode())));
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SET_DEFAULT_GAME_TYPE;
    }
}
