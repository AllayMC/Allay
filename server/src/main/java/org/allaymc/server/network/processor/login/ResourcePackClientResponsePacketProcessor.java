package org.allaymc.server.network.processor.login;

import org.allaymc.api.message.TrKeys;
import org.allaymc.api.player.Player;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.AllayServer;
import org.allaymc.server.network.processor.ingame.ILoginPacketProcessor;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ResourcePackClientResponsePacket;

import java.util.UUID;

/**
 * {@code ResourcePackClientResponsePacketProcessor} advances resource-pack
 * negotiation from the client's declared status. Requested packs receive data
 * information, acknowledged possession receives the pack stack, and completion
 * delegates player spawning. Invalid pack identities and unsupported statuses
 * disconnect rather than selecting substitute packs.
 *
 * @author daoge_cmd
 */
public class ResourcePackClientResponsePacketProcessor extends ILoginPacketProcessor<ResourcePackClientResponsePacket> {
    /**
     * Responds to the client's resource-pack status through the selected protocol.
     * Requested identities are parsed and sent in supplied order; an invalid or
     * missing later pack disconnects without retracting earlier information packets.
     * Other encoding failures propagate, and completion delegates spawn admission.
     */
    @Override
    public void handle(Player player, ResourcePackClientResponsePacket packet) {
        var allayPlayer = (AllayPlayer) player;
        switch (packet.getStatus()) {
            case SEND_PACKS -> {
                var protocol = allayPlayer.getProtocol();
                var encoder = protocol.getEncoder();
                // Pack settings use kibibytes; data information advertises byte-sized chunks.
                int chunkSize = AllayServer.getSettings()
                        .resourcePackSettings()
                        .maxChunkSize() * 1024;
                for (var packId : packet.getPackIds()) {
                    UUID id;
                    try {
                        id = parsePackId(packId);
                    } catch (IllegalArgumentException ignored) {
                        // An unusable identity cannot select a registered pack;
                        // abort negotiation without sending substitutes or undoing
                        // information already sent for earlier requested packs.
                        player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_RESOURCEPACK);
                        return;
                    }

                    var pack = Registries.PACKS.get(id);
                    if (pack == null) {
                        player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_RESOURCEPACK);
                        return;
                    }

                    allayPlayer.sendPacket(encoder.encodeResourcePackDataInfo(pack, chunkSize));
                }
            }
            case HAVE_ALL_PACKS -> {
                var encoder = allayPlayer.getProtocol().getEncoder();
                allayPlayer.sendPacket(encoder.encodeResourcePackStack());
            }
            case COMPLETED -> allayPlayer.spawnEntityPlayer();
            default -> player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_NOREASON);
        }
    }

    /**
     * Returns the resource pack client response packet type handled here.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.RESOURCE_PACK_CLIENT_RESPONSE;
    }

    /**
     * Returns the {@code UUID} preceding an optional underscore-delimited version suffix.
     * A {@code null} identity or invalid {@code UUID} throws {@code IllegalArgumentException};
     * suffix contents are ignored rather than validated against pack metadata.
     */
    private static UUID parsePackId(String encodedId) {
        if (encodedId == null) {
            throw new IllegalArgumentException("Resource-pack ID cannot be null");
        }
        // Pack responses can append a version to the UUID; registry lookup uses
        // only the identity prefix, so the suffix must not enter UUID parsing.
        int versionSeparator = encodedId.indexOf('_');
        return UUID.fromString(versionSeparator < 0 ? encodedId : encodedId.substring(0, versionSeparator));
    }
}
