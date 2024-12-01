package org.allaymc.server.network.processor.login;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.network.ProtocolInfo;
import org.allaymc.api.server.Server;
import org.allaymc.server.network.processor.ILoginPacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.NetworkSettingsPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayStatusPacket;
import org.cloudburstmc.protocol.bedrock.packet.RequestNetworkSettingsPacket;

/**
 * @author daoge_cmd
 */
public class RequestNetworkSettingsPacketProcessor extends ILoginPacketProcessor<RequestNetworkSettingsPacket> {
    @Override
    public void handle(EntityPlayer player, RequestNetworkSettingsPacket packet) {
        var protocolVersion = packet.getProtocolVersion();
        var supportedProtocolVersion = ProtocolInfo.PACKET_CODEC.getProtocolVersion();
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
        // NOTICE: We don't need to set the compression threshold after MCBE 1.20.60
        player.sendPacketImmediately(settingsPacket);
        // NOTICE: The NetworkSettingsPacket shouldn't be compressed
        player.getClientSession().setCompression(settingsPacket.getCompressionAlgorithm());
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.REQUEST_NETWORK_SETTINGS;
    }
}
