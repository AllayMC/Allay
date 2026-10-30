package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.ClientCacheStatusPacket;
import org.cloudburstmc.protocol.bedrock.packet.PacketSignal;

/**
 * {@code ClientCacheStatusPacketProcessor} records whether the client advertises
 * chunk-cache support on its {@code AllayPlayer} connection. This notification
 * updates only the client capability flag;
 * effective cache use also depends on the server setting checked by the player.
 * Processing completes on the asynchronous receive path.
 *
 * @author daoge_cmd
 */
public class ClientCacheStatusPacketProcessor extends PacketProcessor<ClientCacheStatusPacket> {

    /**
     * Copies the advertised support flag into the {@code AllayPlayer} connection
     * and returns {@code HANDLED}. The caller must supply an {@code AllayPlayer}, and enabling this
     * flag alone does not override the server's cache configuration.
     */
    @Override
    public PacketSignal handleAsync(Player player, ClientCacheStatusPacket packet, long receiveTime) {
        ((AllayPlayer) player).setClientCacheEnabled(packet.isSupported());
        return PacketSignal.HANDLED;
    }

    /**
     * Returns the client cache status packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.CLIENT_CACHE_STATUS;
    }
}
