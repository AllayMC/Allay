package org.allaymc.server.network.processor.login;

import org.allaymc.api.message.TrKeys;
import org.allaymc.api.player.Player;
import org.allaymc.server.AllayServer;
import org.allaymc.server.network.ProtocolInfo;
import org.allaymc.server.network.processor.ingame.ILoginPacketProcessor;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.data.PacketCompressionAlgorithm;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.NetworkSettingsPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayStatusPacket;
import org.cloudburstmc.protocol.bedrock.packet.RequestNetworkSettingsPacket;

/**
 * @author daoge_cmd
 */
public class RequestNetworkSettingsPacketProcessor extends ILoginPacketProcessor<RequestNetworkSettingsPacket> {
    @Override
    public void handle(Player player, RequestNetworkSettingsPacket packet) {
        var allayPlayer = (AllayPlayer) player;
        var protocolVersion = packet.getProtocolVersion();
        var codec = ProtocolInfo.findCodec(protocolVersion);
        if (codec == null) {
            // Cannot find a suitable codec for the client protocol version, let's check if it's too old or too new
            var loginFailedPacket = new PlayStatusPacket();
            if (protocolVersion > ProtocolInfo.getLatestCodec().getProtocolVersion()) {
                loginFailedPacket.setStatus(PlayStatusPacket.Status.LOGIN_FAILED_SERVER_OLD);
            } else if (protocolVersion < ProtocolInfo.getLowestCodec().getProtocolVersion()) {
                loginFailedPacket.setStatus(PlayStatusPacket.Status.LOGIN_FAILED_CLIENT_OLD);
            } else {
                // A version that is in the middle of the lowest and latest versions is not supported for
                // some reason. Since we don't have compatible status in PlayStatusPacket.Status, let's
                // disconnect the client with custom reason instead of sending PlayStatusPacket
                player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_BODY_VERSIONNOTSUPPORTED);
                return;
            }

            player.sendPacketImmediately(loginFailedPacket);
            return;
        }

        var session = allayPlayer.getSession();
        session.setCodec(codec);

        var settingsPacket = new NetworkSettingsPacket();
        settingsPacket.setCompressionAlgorithm(PacketCompressionAlgorithm.valueOf(AllayServer.getSettings().networkSettings().compressionAlgorithm().name()));
        // NOTICE: We don't need to set the compression threshold after 1.20.60
        player.sendPacketImmediately(settingsPacket);
        // NOTICE: The NetworkSettingsPacket shouldn't be compressed, so we set the compression after sending the packet
        session.setCompression(settingsPacket.getCompressionAlgorithm());
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.REQUEST_NETWORK_SETTINGS;
    }
}
