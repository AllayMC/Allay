package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.PacketSignal;
import org.cloudburstmc.protocol.bedrock.packet.SetPlayerInventoryOptionsPacket;

/**
 * {@code SetPlayerInventoryOptionsPacketProcessor} consumes client
 * inventory-option notifications without changing server
 * inventory state. The asynchronous hook finishes processing immediately, so
 * inventory presentation preferences in these packets are neither retained in
 * this processor nor forwarded to a synchronous world handler.
 *
 * @author daoge_cmd
 */
public class SetPlayerInventoryOptionsPacketProcessor extends PacketProcessor<SetPlayerInventoryOptionsPacket> {

    /**
     * Consumes the inventory-options packet and returns {@code HANDLED} without
     * inspecting its fields. No player state is updated, and the session does
     * not schedule a synchronous phase for this notification.
     */
    @Override
    public PacketSignal handleAsync(Player player, SetPlayerInventoryOptionsPacket packet, long receiveTime) {
        return PacketSignal.HANDLED;
    }

    /**
     * Returns the set player inventory options packet type handled by this
     * processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SET_PLAYER_INVENTORY_OPTIONS;
    }
}
