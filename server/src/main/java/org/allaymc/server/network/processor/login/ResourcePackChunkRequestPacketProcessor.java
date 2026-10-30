package org.allaymc.server.network.processor.login;

import org.allaymc.api.message.TrKeys;
import org.allaymc.api.player.Player;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.AllayServer;
import org.allaymc.server.network.processor.ingame.ILoginPacketProcessor;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePackChunkRequestPacket;

import static org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType.RESOURCE_PACK_CHUNK_REQUEST;

/**
 * {@code ResourcePackChunkRequestPacketProcessor} serves one requested chunk of
 * a registered resource pack through the player's negotiated encoder. The
 * configured chunk size is converted to bytes, and unknown packs or illegal
 * chunk arguments terminate resource-pack negotiation with a disconnect.
 *
 * @author daoge_cmd
 */
public class ResourcePackChunkRequestPacketProcessor extends ILoginPacketProcessor<ResourcePackChunkRequestPacket> {
    /**
     * Sends a chunk for an existing pack using the configured byte chunk size.
     * Missing packs disconnect immediately; {@code IllegalArgumentException}
     * during encoding or sending also disconnects, while other failures propagate.
     * This processor does not release a generated response when the send path
     * rejects it or throws before transport submission.
     */
    @Override
    public void handle(Player player, ResourcePackChunkRequestPacket packet) {
        var allayPlayer = (AllayPlayer) player;
        var protocol = allayPlayer.getProtocol();
        var pack = Registries.PACKS.get(packet.getPackId());
        if (pack == null) {
            player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_RESOURCEPACK);
            return;
        }

        // Pack settings use kibibytes; the encoder addresses chunks in bytes.
        int chunkSize = AllayServer.getSettings()
                .resourcePackSettings()
                .maxChunkSize() * 1024;
        try {
            allayPlayer.sendPacket(protocol.getEncoder().encodeResourcePackChunkData(
                    pack,
                    packet.getChunkIndex(),
                    chunkSize
            ));
        } catch (IllegalArgumentException ignored) {
            // Invalid size or index cannot identify a usable transfer chunk;
            // treat argument failures in this encode-and-send path as resource-pack
            // negotiation failure instead of continuing the transfer.
            player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_RESOURCEPACK);
        }
    }

    /**
     * Returns the resource pack chunk request packet type handled here.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return RESOURCE_PACK_CHUNK_REQUEST;
    }
}
