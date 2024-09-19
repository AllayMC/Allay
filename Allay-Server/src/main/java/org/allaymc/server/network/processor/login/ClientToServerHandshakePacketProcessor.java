package org.allaymc.server.network.processor.login;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.entity.component.player.EntityPlayerNetworkComponentImpl;
import org.allaymc.server.network.processor.ILoginPacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ClientToServerHandshakePacket;

/**
 * @author daoge_cmd
 */
@Slf4j
public class ClientToServerHandshakePacketProcessor extends ILoginPacketProcessor<ClientToServerHandshakePacket> {
    @Override
    public void handle(EntityPlayer player, ClientToServerHandshakePacket packet) {
        if (player.isNetworkEncryptionEnabled())
            player.getManager().<EntityPlayerNetworkComponentImpl>getComponent(EntityPlayerNetworkComponentImpl.IDENTIFIER).completeLogin();
        else
            log.warn("Client {} sent ClientToServerHandshakePacket without encryption enabled", player.getOriginName());
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CLIENT_TO_SERVER_HANDSHAKE;
    }
}
