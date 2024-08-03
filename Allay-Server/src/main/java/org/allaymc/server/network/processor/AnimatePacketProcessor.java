package org.allaymc.server.network.processor;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerAnimationEvent;
import org.allaymc.api.network.processor.PacketProcessor;
import org.allaymc.api.server.Server;
import org.cloudburstmc.protocol.bedrock.packet.AnimatePacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * Allay Project 11/22/2023
 *
 * @author Cool_Loong
 */
public class AnimatePacketProcessor extends PacketProcessor<AnimatePacket> {
    @Override
    public PacketSignal handleAsync(EntityPlayer player, AnimatePacket packet, long receiveTime) {
        var event = new PlayerAnimationEvent(player, packet.getAction(), packet.getRowingTime());
        Server.getInstance().getEventBus().callEvent(event);
        if (event.isCancelled()) {
            return PacketSignal.HANDLED;
        }

        if (packet.getAction() == AnimatePacket.Action.SWING_ARM) {
            player.getCurrentChunk().addChunkPacket(packet, chunkLoader -> chunkLoader != player);
            return PacketSignal.HANDLED;
        }

        return PacketSignal.UNHANDLED;
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.ANIMATE;
    }
}
