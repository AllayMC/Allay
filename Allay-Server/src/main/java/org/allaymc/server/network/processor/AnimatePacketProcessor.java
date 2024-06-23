package org.allaymc.server.network.processor;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.network.processor.PacketProcessor;
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
    public PacketSignal handleAsync(EntityPlayer player, AnimatePacket packet) {
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
