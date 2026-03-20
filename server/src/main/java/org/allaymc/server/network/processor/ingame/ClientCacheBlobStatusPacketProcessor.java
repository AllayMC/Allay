package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.allaymc.server.player.AllayPlayer;
import org.allaymc.server.player.ChunkCache;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ClientCacheBlobStatusPacket;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author daoge_cmd
 */
public class ClientCacheBlobStatusPacketProcessor extends PacketProcessor<ClientCacheBlobStatusPacket> {

    @Override
    public PacketSignal handleAsync(Player player, ClientCacheBlobStatusPacket packet, long receiveTime) {
        var allayPlayer = (AllayPlayer) player;
        var cache = ChunkCache.getInstance();

        var acks = packet.getAcks().toLongArray();
        var naks = packet.getNaks().toLongArray();
        var missResponse = cache.handleBlobStatus(allayPlayer.getLoginData().getUuid(), acks, naks);

        if (missResponse != null) {
            allayPlayer.sendPacket(missResponse);
        }

        return PacketSignal.HANDLED;
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CLIENT_CACHE_BLOB_STATUS;
    }
}
