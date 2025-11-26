package org.allaymc.server.network.processor.login;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.ingame.ILoginPacketProcessor;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ClientToServerHandshakePacket;

/**
 * @author daoge_cmd
 */
@Slf4j
public class ClientToServerHandshakePacketProcessor extends ILoginPacketProcessor<ClientToServerHandshakePacket> {
    @Override
    public void handle(Player player, ClientToServerHandshakePacket packet) {
        var allayPlayer = (AllayPlayer) player;
        if (allayPlayer.isNetworkEncryptionEnabled()) {
            allayPlayer.completeLogin();
        } else {
            log.warn("Client {} sent ClientToServerHandshakePacket without encryption enabled", player.getOriginName());
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CLIENT_TO_SERVER_HANDSHAKE;
    }
}
