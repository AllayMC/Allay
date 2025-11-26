package org.allaymc.server.eventbus.event.network;

import org.allaymc.api.player.Player;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;

/**
 * @author daoge_cmd
 */
public class PacketSendEvent extends PacketEvent {
    public PacketSendEvent(Player player, BedrockPacket packet) {
        super(player, packet);
    }
}
