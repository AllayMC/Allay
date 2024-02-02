package org.allaymc.server.network.processor.login;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.network.processor.login.ILoginPacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ClientToServerHandshakePacket;

/**
 * Allay Project 2024/2/2
 *
 * @author daoge_cmd
 */
@Slf4j
public class ClientToServerHandshakePacketProcessor extends ILoginPacketProcessor<ClientToServerHandshakePacket> {
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CLIENT_TO_SERVER_HANDSHAKE;
    }

    @Override
    public void handle(EntityPlayer player, ClientToServerHandshakePacket packet) {
        if (player.isNetworkEncryptionEnabled()) player.completeLogin();
        else log.warn("Client " + player.getOriginName() + " sent ClientToServerHandshakePacket without encryption enabled");
        return;
    }
}
