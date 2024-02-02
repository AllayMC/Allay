package org.allaymc.server.network.processor.login;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.network.processor.login.ILoginPacketProcessor;
import org.allaymc.api.server.Server;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.NetworkSettingsPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayStatusPacket;
import org.cloudburstmc.protocol.bedrock.packet.RequestNetworkSettingsPacket;

/**
 * Allay Project 2024/2/2
 *
 * @author daoge_cmd
 */
public class RequestNetworkSettingsPacketProcessor extends ILoginPacketProcessor<RequestNetworkSettingsPacket> {
    @Override
    public void handle(EntityPlayer player, RequestNetworkSettingsPacket packet) {
        var protocolVersion = packet.getProtocolVersion();
        var supportedProtocolVersion = Server.getInstance().getNetworkServer().getCodec().getProtocolVersion();
        if (protocolVersion != supportedProtocolVersion) {
            var loginFailedPacket = new PlayStatusPacket();
            if (protocolVersion > supportedProtocolVersion) {
                loginFailedPacket.setStatus(PlayStatusPacket.Status.LOGIN_FAILED_SERVER_OLD);
            } else {
                loginFailedPacket.setStatus(PlayStatusPacket.Status.LOGIN_FAILED_CLIENT_OLD);
            }
            player.sendPacketImmediately(loginFailedPacket);
            return;
        }
        var settingsPacket = new NetworkSettingsPacket();
        settingsPacket.setCompressionAlgorithm(Server.SETTINGS.networkSettings().compressionAlgorithm());
        settingsPacket.setCompressionThreshold(Server.SETTINGS.networkSettings().compressionThreshold());
        player.sendPacketImmediately(settingsPacket);
        player.getClientSession().setCompression(settingsPacket.getCompressionAlgorithm());
        return;

    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.REQUEST_NETWORK_SETTINGS;
    }
}
