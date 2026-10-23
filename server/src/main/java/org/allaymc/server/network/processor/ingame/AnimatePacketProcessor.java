package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.entity.action.SimpleEntityAction;
import org.allaymc.api.eventbus.event.player.PlayerSwingArmEvent;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.AnimatePacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.PacketSignal;

/**
 * {@code AnimatePacketProcessor} handles client arm-swing animations on the
 * asynchronous receive path. A swing fires the player swing event and then
 * applies the corresponding entity
 * action; the event result is not consulted here. Other animation actions use
 * the inherited empty synchronous handling after returning unhandled.
 *
 * @author Cool_Loong
 */
public class AnimatePacketProcessor extends PacketProcessor<AnimatePacket> {
    /**
     * Fires the swing event and applies the arm action for {@code SWING_ARM} packets.
     * The player must have a controlled entity, event dispatch does not gate
     * the action, and other animation types return {@code UNHANDLED}.
     */
    @Override
    public PacketSignal handleAsync(Player player, AnimatePacket packet, long receiveTime) {
        if (packet.getAction() == AnimatePacket.Action.SWING_ARM) {
            new PlayerSwingArmEvent(player.getControlledEntity()).call();
            player.getControlledEntity().applyAction(SimpleEntityAction.SWING_ARM);
            return PacketSignal.HANDLED;
        }

        return PacketSignal.UNHANDLED;
    }

    /**
     * Returns the animate packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.ANIMATE;
    }
}
