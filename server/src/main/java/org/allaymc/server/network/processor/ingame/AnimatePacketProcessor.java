package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.entity.action.SimpleEntityAction;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.AnimatePacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.common.PacketSignal;

/**
 * @author Cool_Loong
 */
public class AnimatePacketProcessor extends PacketProcessor<AnimatePacket> {
    @Override
    public PacketSignal handleAsync(EntityPlayerImpl player, AnimatePacket packet, long receiveTime) {
        if (packet.getAction() == AnimatePacket.Action.SWING_ARM) {
            player.applyAction(SimpleEntityAction.SWING_ARM);
            return PacketSignal.HANDLED;
        }

        return PacketSignal.UNHANDLED;
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.ANIMATE;
    }
}
