package org.allaymc.server.network.processor.login;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.ingame.ILoginPacketProcessor;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ClientToServerHandshakePacket;

/**
 * {@code ClientToServerHandshakePacketProcessor} accepts the client's encryption
 * handshake acknowledgement for an encryption-enabled connection. It delegates
 * the remaining login checks and resource-pack notification to
 * {@code AllayPlayer.completeLogin}; an acknowledgement without encryption only
 * produces a warning and leaves login incomplete.
 *
 * @author daoge_cmd
 */
@Slf4j
public class ClientToServerHandshakePacketProcessor extends ILoginPacketProcessor<ClientToServerHandshakePacket> {
    /**
     * Completes login only when the {@code AllayPlayer} connection has encryption
     * enabled. An unexpected acknowledgement is logged without disconnecting or
     * advancing login here; completion may itself reject the connection.
     */
    @Override
    public void handle(Player player, ClientToServerHandshakePacket packet) {
        var allayPlayer = (AllayPlayer) player;
        if (allayPlayer.isNetworkEncryptionEnabled()) {
            allayPlayer.completeLogin();
        } else {
            log.warn("Client {} sent ClientToServerHandshakePacket without encryption enabled", player.getOriginName());
        }
    }

    /**
     * Returns the client encryption handshake packet type handled here.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CLIENT_TO_SERVER_HANDSHAKE;
    }
}
