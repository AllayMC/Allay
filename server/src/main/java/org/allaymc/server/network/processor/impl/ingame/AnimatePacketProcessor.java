package org.allaymc.server.network.processor.impl.ingame;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerAnimationEvent;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.AnimatePacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author Cool_Loong
 */
public class AnimatePacketProcessor extends PacketProcessor<AnimatePacket> {
    @Override
    public PacketSignal handleAsync(EntityPlayer player, AnimatePacket packet, long receiveTime) {
        var event = new PlayerAnimationEvent(player, packet.getAction(), packet.getRowingTime());
        event.call();
        if (event.isCancelled()) return PacketSignal.HANDLED;

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
