package org.allaymc.server.network.processor.common;

import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.allaymc.server.player.AllayPlayer;
import org.allaymc.server.player.ChunkCache;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ClientCacheBlobStatusPacket;
import org.cloudburstmc.protocol.bedrock.packet.PacketSignal;

/**
 * {@code ClientCacheBlobStatusPacketProcessor} processes client cache
 * acknowledgements and missing-blob requests for the player's connection.
 * The shared cache resolves transaction acknowledgements
 * and prepares any missing bytes using the negotiated protocol encoder. A
 * response is sent only when the cache returns one, and processing finishes on
 * the asynchronous receive path.
 *
 * @author daoge_cmd
 */
public class ClientCacheBlobStatusPacketProcessor extends PacketProcessor<ClientCacheBlobStatusPacket> {

    /**
     * Processes cache status for an {@code AllayPlayer} with login data and a selected
     * protocol. Any returned miss response is sent through that player, and
     * {@code HANDLED} prevents a second synchronous processing pass.
     */
    @Override
    public PacketSignal handleAsync(Player player, ClientCacheBlobStatusPacket packet, long receiveTime) {
        var allayPlayer = (AllayPlayer) player;
        var cache = ChunkCache.getInstance();

        var acks = packet.getAcks().toLongArray();
        var naks = packet.getNaks().toLongArray();
        var missResponse = cache.handleBlobStatus(
                allayPlayer.getProtocol().getEncoder(),
                allayPlayer.getLoginData().getUuid(),
                acks,
                naks
        );

        if (missResponse != null) {
            allayPlayer.sendPacket(missResponse);
        }

        return PacketSignal.HANDLED;
    }

    /**
     * Returns the client cache blob status packet type handled by this
     * processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CLIENT_CACHE_BLOB_STATUS;
    }
}
