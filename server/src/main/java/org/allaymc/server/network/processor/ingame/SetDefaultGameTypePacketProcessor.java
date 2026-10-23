package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.permission.Permissions;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.NetworkHelper;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.SetDefaultGameTypePacket;

/**
 * {@code SetDefaultGameTypePacketProcessor} passes permitted default-game-mode
 * requests to the controlled entity's world
 * data. The default-game-mode command permission gates the request
 * independently of player-specific game-mode changes. This handler changes the
 * world default through its setter without iterating over current players.
 *
 * @author IWareQ
 */
public class SetDefaultGameTypePacketProcessor extends PacketProcessor<SetDefaultGameTypePacket> {

    /**
     * Converts and requests the world default game mode when the corresponding
     * command permission is granted. Unauthorized requests are ignored, and the
     * conversion result is passed directly to world data without additional
     * range validation here.
     */
    @Override
    public void handleSync(Player player, SetDefaultGameTypePacket packet, long receiveTime) {
        if (player.getControlledEntity().hasPermission(Permissions.COMMAND_DEFAULTGAMEMODE).asBoolean()) {
            player.getControlledEntity().getWorld().getWorldData().setGameMode(NetworkHelper.fromNetwork(GameType.from(packet.getGamemode())));
        }
    }

    /**
     * Returns the set default game type packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SET_DEFAULT_GAME_TYPE;
    }
}
