package org.allaymc.server.network.processor.login;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.player.Player;
import org.allaymc.server.AllayServer;
import org.allaymc.server.network.multiversion.MultiVersion;
import org.allaymc.server.network.ProtocolInfo;
import org.allaymc.server.network.netease.NetEaseCompression;
import org.allaymc.server.network.processor.ingame.ILoginPacketProcessor;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.data.PacketCompressionAlgorithm;
import org.cloudburstmc.protocol.bedrock.netty.codec.compression.SimpleCompressionStrategy;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.NetworkSettingsPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayStatusPacket;
import org.cloudburstmc.protocol.bedrock.packet.RequestNetworkSettingsPacket;

/**
 * @author daoge_cmd
 */
@Slf4j
public class RequestNetworkSettingsPacketProcessor extends ILoginPacketProcessor<RequestNetworkSettingsPacket> {

    /**
     * NetEase clients use RakNet protocol version 8, while international clients use version 11.
     */
    private static final int NETEASE_RAKNET_PROTOCOL_VERSION = 8;

    @Override
    @MultiVersion(version = "1.21.50-NetEase", details = "NetEase clients are detected via RakNet protocol version 8 and use raw deflate compression")
    public void handle(Player player, RequestNetworkSettingsPacket packet) {
        var allayPlayer = (AllayPlayer) player;
        var protocolVersion = packet.getProtocolVersion();
        var settings = AllayServer.getSettings().networkSettings();

        // Detect if this is a NetEase client based on RakNet protocol version
        boolean netEasePlayer = allayPlayer.getSession().getPeer().getRakVersion() == NETEASE_RAKNET_PROTOCOL_VERSION;
        allayPlayer.setNetEasePlayer(netEasePlayer);

        // Check NetEase client support configuration
        if (netEasePlayer) {
            if (!settings.neteaseClientSupport()) {
                log.debug("Rejected NetEase client connection from {} - NetEase support is disabled",
                        player.getSocketAddress());
                player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_BODY_VERSIONNOTSUPPORTED);
                return;
            }
            log.debug("NetEase client detected from {}", player.getSocketAddress());
        } else {
            if (settings.neteaseClientSupport() && settings.onlyAllowNeteaseClient()) {
                log.debug("Rejected international client connection from {} - Only NetEase clients allowed",
                        player.getSocketAddress());
                player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_BODY_VERSIONNOTSUPPORTED);
                return;
            }
        }

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
        settingsPacket.setCompressionAlgorithm(PacketCompressionAlgorithm.valueOf(settings.compressionAlgorithm().name()));
        // NOTICE: We don't need to set the compression threshold after 1.20.60
        player.sendPacketImmediately(settingsPacket);
        // NOTICE: The NetworkSettingsPacket shouldn't be compressed, so we set the compression after sending the packet
        if (netEasePlayer) {
            // NetEase clients use raw deflate format, set NetEaseCompression now
            // (initial compression was NOOP to handle uncompressed RequestNetworkSettingsPacket)
            session.getPeer().setCompression(new SimpleCompressionStrategy(new NetEaseCompression()));
        } else {
            session.setCompression(settingsPacket.getCompressionAlgorithm());
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.REQUEST_NETWORK_SETTINGS;
    }
}
