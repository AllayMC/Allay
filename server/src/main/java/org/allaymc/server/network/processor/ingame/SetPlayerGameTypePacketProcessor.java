package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.permission.Permissions;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.NetworkHelper;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.SetPlayerGameTypePacket;

/**
 * {@code SetPlayerGameTypePacketProcessor} passes a permitted client game-mode
 * request to the controlled entity's
 * game-mode setter. The player must have the game-mode command permission
 * before conversion occurs. Entity-specific event handling and resulting mode
 * updates remain the responsibility of that setter.
 *
 * @author IWareQ
 */
public class SetPlayerGameTypePacketProcessor extends PacketProcessor<SetPlayerGameTypePacket> {

    /**
     * Converts and requests the player game mode when its command permission is
     * granted. Unauthorized requests are ignored; this handler passes the
     * conversion result directly to the entity without adding range validation
     * or guaranteeing event acceptance.
     */
    @Override
    public void handleSync(Player player, SetPlayerGameTypePacket packet, long receiveTime) {
        if (player.getControlledEntity().hasPermission(Permissions.COMMAND_GAMEMODE).asBoolean()) {
            player.getControlledEntity().setGameMode(NetworkHelper.fromNetwork(GameType.from(packet.getGamemode())));
        }
    }

    /**
     * Returns the set player game type packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SET_PLAYER_GAME_TYPE;
    }
}
