package org.allaymc.server.network.processor.login;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.player.Player;
import org.allaymc.server.AllayServer;
import org.allaymc.server.network.processor.ingame.ILoginPacketProcessor;
import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.ProtocolRegistry;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.data.EncodingSettings;
import org.cloudburstmc.protocol.bedrock.data.PacketCompressionAlgorithm;
import org.cloudburstmc.protocol.bedrock.netty.codec.compression.NetEaseCompression;
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

    @Override
    public void handle(Player player, RequestNetworkSettingsPacket packet) {
        var allayPlayer = (AllayPlayer) player;
        var protocolVersion = packet.getProtocolVersion();
        var settings = AllayServer.getSettings().networkSettings();

        boolean netEasePlayer = isNetEaseClient(allayPlayer);
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

        var variant = netEasePlayer ? ClientVariant.NETEASE : ClientVariant.INTERNATIONAL;
        var registry = ProtocolRegistry.getDefault();
        var protocol = registry.resolve(variant, protocolVersion);
        if (protocol == null) {
            // Cannot find a suitable codec for the client protocol version, let's check if it's too old or too new
            var loginFailedPacket = new PlayStatusPacket();
            var latest = registry.getLatest(variant);
            var lowest = registry.getLowest(variant);
            if (latest != null && protocolVersion > latest.getProtocolVersion()) {
                loginFailedPacket.setStatus(PlayStatusPacket.Status.LOGIN_FAILED_SERVER_OLD);
            } else if (lowest != null && protocolVersion < lowest.getProtocolVersion()) {
                loginFailedPacket.setStatus(PlayStatusPacket.Status.LOGIN_FAILED_CLIENT_OLD);
            } else {
                // A version that is in the middle of the lowest and latest versions is not supported for
                // some reason. Since we don't have compatible status in PlayStatusPacket.Status, let's
                // disconnect the client with custom reason instead of sending PlayStatusPacket
                player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_BODY_VERSIONNOTSUPPORTED);
                return;
            }

            allayPlayer.sendPacketImmediately(loginFailedPacket);
            return;
        }

        var session = allayPlayer.getSession();
        if (!allayPlayer.installProtocol(protocol)) {
            return;
        }
        if (!AllayServer.getSettings().networkSettings().enableEncodingProtection()) {
            session.getPeer().getCodecHelper().setEncodingSettings(EncodingSettings.UNLIMITED);
        }

        var settingsPacket = new NetworkSettingsPacket();
        settingsPacket.setCompressionAlgorithm(netEasePlayer
                ? PacketCompressionAlgorithm.ZLIB
                : PacketCompressionAlgorithm.valueOf(settings.compressionAlgorithm().name()));
        // NOTICE: We don't need to set the compression threshold after 1.20.60
        var sentPacket = allayPlayer.getProtocolSession().sendImmediatelyAndGet(allayPlayer, settingsPacket);
        if (!(sentPacket instanceof NetworkSettingsPacket sentSettings)) {
            player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_NOREASON);
            return;
        }
        var compressionAlgorithm = sentSettings.getCompressionAlgorithm();
        if (compressionAlgorithm == null) {
            player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_NOREASON);
            return;
        }
        // NOTICE: The NetworkSettingsPacket shouldn't be compressed, so we set the compression after sending the packet
        if (netEasePlayer) {
            if (compressionAlgorithm != PacketCompressionAlgorithm.ZLIB) {
                player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_NOREASON);
                return;
            }
            // NetEase clients use raw deflate format, set NetEaseCompression now
            // (initial compression was NOOP to handle uncompressed RequestNetworkSettingsPacket)
            session.getPeer().setCompression(new SimpleCompressionStrategy(new NetEaseCompression()));
        } else {
            session.setCompression(compressionAlgorithm);
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.REQUEST_NETWORK_SETTINGS;
    }

    boolean isNetEaseClient(AllayPlayer allayPlayer) {
        return allayPlayer.getSourceInterface().isNetEaseClient(allayPlayer.getSession());
    }

}
