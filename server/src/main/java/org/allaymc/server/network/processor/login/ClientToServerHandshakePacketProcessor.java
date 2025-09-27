package org.allaymc.server.network.processor.login;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.entity.component.player.EntityPlayerClientComponentImpl;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.allaymc.server.network.processor.ingame.ILoginPacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ClientToServerHandshakePacket;

/**
 * @author daoge_cmd
 */
@Slf4j
public class ClientToServerHandshakePacketProcessor extends ILoginPacketProcessor<ClientToServerHandshakePacket> {
    @Override
    public void handle(EntityPlayer player, ClientToServerHandshakePacket packet) {
        var clientComponent = (EntityPlayerClientComponentImpl) ((EntityPlayerImpl) player).getPlayerClientComponent();
        if (clientComponent.isNetworkEncryptionEnabled()) {
            clientComponent.completeLogin();
        } else {
            log.warn("Client {} sent ClientToServerHandshakePacket without encryption enabled", player.getOriginName());
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CLIENT_TO_SERVER_HANDSHAKE;
    }
}
