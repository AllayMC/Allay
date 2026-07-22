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
 * Selects the exact protocol requested by a client before enabling negotiated compression.
 *
 * <p>This processor runs with the bootstrap codec; successful negotiation installs the selected
 * codec before any version-specific packet is sent.</p>
 *
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
            var loginFailedPacket = new PlayStatusPacket();
            var latest = registry.getLatest(variant);
            var lowest = registry.getLowest(variant);
            if (latest != null && protocolVersion > latest.getProtocolVersion()) {
                loginFailedPacket.setStatus(PlayStatusPacket.Status.LOGIN_FAILED_SERVER_OLD);
            } else if (lowest != null && protocolVersion < lowest.getProtocolVersion()) {
                loginFailedPacket.setStatus(PlayStatusPacket.Status.LOGIN_FAILED_CLIENT_OLD);
            } else {
                // Bedrock has no status for a gap between supported versions, so use a generic reason.
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
        // Compression thresholds are ignored by clients from 1.20.60 onward.
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
        // Use the post-event value, since listeners may replace or mutate the settings packet.
        if (netEasePlayer) {
            if (compressionAlgorithm != PacketCompressionAlgorithm.ZLIB) {
                player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_NOREASON);
                return;
            }
            // NetEase uses raw deflate; it can only be enabled after the uncompressed settings packet.
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
