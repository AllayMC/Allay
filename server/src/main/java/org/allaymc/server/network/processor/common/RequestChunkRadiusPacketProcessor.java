package org.allaymc.server.network.processor.common;

import org.allaymc.api.player.Player;
import org.allaymc.server.AllayServer;
import org.allaymc.server.network.processor.PacketProcessor;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.RequestChunkRadiusPacket;

/**
 * {@code RequestChunkRadiusPacketProcessor} applies a client chunk-radius request
 * to the player's controlled entity and acknowledges its resulting radius.
 * The requested radius, client maximum, and
 * server view distance jointly bound the value passed to the entity. Each input
 * uses chunks, and this processor adds no separate lower-bound check.
 *
 * <p>
 * Acknowledgement reads the entity after the setter returns rather than echoing
 * the client's request or the computed bound. This preserves any further
 * adjustment made by the entity implementation. The response is encoded through
 * the player's negotiated protocol and sent after the state update, so a later
 * encoding or sending failure does not undo the radius assignment.
 *
 * @author Cool_Loong
 */
public class RequestChunkRadiusPacketProcessor extends PacketProcessor<RequestChunkRadiusPacket> {
    /**
     * Applies the smallest of the requested radius, client maximum, and server
     * view distance. The player must be an {@code AllayPlayer} with a controlled entity
     * and a selected protocol; all radius values use chunks.
     *
     * <p>
     * The response reads the entity's radius after setting it instead of
     * echoing the raw request. This keeps the acknowledgement aligned with the
     * value retained by the entity even if its setter adjusts the requested
     * bound. This processor imposes no separate lower-bound validation before
     * invoking that setter.
     */
    @Override
    public void handleSync(Player player, RequestChunkRadiusPacket packet, long receiveTime) {
        // All three upper bounds apply to one radius; acknowledging the entity
        // afterward preserves any additional adjustment performed by its
        // setter.
        player.getControlledEntity().setChunkLoadingRadius(
                Math.min(Math.min(packet.getRadius(), packet.getMaxRadius()), AllayServer.getSettings().worldSettings().viewDistance()));
        var allayPlayer = (AllayPlayer) player;
        allayPlayer.sendPacket(allayPlayer.getProtocol().getEncoder().encodeChunkRadiusUpdated(
                player.getControlledEntity().getChunkLoadingRadius()
        ));
    }

    /**
     * Returns the chunk radius request packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.REQUEST_CHUNK_RADIUS;
    }
}
