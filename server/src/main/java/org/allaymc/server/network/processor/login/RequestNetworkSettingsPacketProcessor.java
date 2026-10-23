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
 * {@code RequestNetworkSettingsPacketProcessor} selects a client's exact protocol
 * and negotiates compression before login packets use that protocol. Transport
 * classification selects the international or NetEase registry branch, and
 * configuration can reject either client category before lookup. Unsupported
 * versions outside a branch's range receive an old-client or old-server status;
 * gaps within that range disconnect without choosing a nearby protocol.
 *
 * <p>
 * The selected protocol is installed before settings are sent, but compression
 * is enabled only after the immediate settings send returns its post-event
 * packet. A cancelled, replaced-with-another-type, or algorithm-free result
 * disconnects without enabling compression here. NetEase requires advertised
 * {@code ZLIB} with a raw-deflate transport strategy; international clients use
 * the final advertised algorithm. A failure after protocol installation does
 * not restore bootstrap state or undo that installation.
 *
 * @author daoge_cmd
 */
@Slf4j
public class RequestNetworkSettingsPacketProcessor extends ILoginPacketProcessor<RequestNetworkSettingsPacket> {

    /**
     * Installs the requested protocol and enables compression after sending valid
     * settings. The player must be an {@code AllayPlayer}; transport policy and
     * exact-version lookup determine whether negotiation reaches installation.
     *
     * <p>
     * An unavailable protocol sends the appropriate range status or disconnects
     * for an unsupported gap. Failed installation stops processing. After a
     * successful install, disabling encoding protection changes codec limits
     * before the settings send. The returned post-event settings packet controls
     * compression; cancellation, another packet type, a missing algorithm, or a
     * non-{@code ZLIB} NetEase replacement disconnects. Compression setup follows
     * the send, and later failures do not roll back protocol or helper changes.
     */
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
                // An in-range gap identifies neither an old client nor an old
                // server; disconnect with the generic unsupported-version reason
                // instead of selecting an incompatible nearby protocol.
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
        // Send negotiation settings before changing compression so the client
        // receives the advertised algorithm using the transport strategy that
        // was active before negotiation.
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
        // Send listeners can mutate or replace settings; install the algorithm
        // from the packet actually returned by that send rather than the
        // originally requested value.
        if (netEasePlayer) {
            if (compressionAlgorithm != PacketCompressionAlgorithm.ZLIB) {
                player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_NOREASON);
                return;
            }
            // NetEase advertises ZLIB while its transport emits raw deflate;
            // install that strategy only after the settings send so negotiation
            // itself remains readable with the previous transport strategy.
            session.getPeer().setCompression(new SimpleCompressionStrategy(new NetEaseCompression()));
        } else {
            session.setCompression(compressionAlgorithm);
        }
    }

    /**
     * Returns the bootstrap network-settings request packet type handled here.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.REQUEST_NETWORK_SETTINGS;
    }

    /**
     * Returns whether the source transport classifies this connection as NetEase.
     */
    boolean isNetEaseClient(AllayPlayer allayPlayer) {
        return allayPlayer.getSourceInterface().isNetEaseClient(allayPlayer.getSession());
    }

}
