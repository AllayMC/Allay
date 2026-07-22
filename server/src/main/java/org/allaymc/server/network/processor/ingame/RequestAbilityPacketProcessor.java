package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.data.Ability;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.RequestAbilityPacket;

/**
 * @author daoge_cmd
 */
public class RequestAbilityPacketProcessor extends PacketProcessor<RequestAbilityPacket> {

    @Override
    public void handleSync(Player player, RequestAbilityPacket packet, long receiveTime) {
        if (packet.getAbility() != Ability.FLYING || packet.getType() != Ability.Type.BOOLEAN) {
            return;
        }

        var entity = player.getControlledEntity();
        if (packet.isBoolValue()) {
            if (!player.canFly()) {
                player.viewPlayerAbilities(player);
                return;
            }

            entity.setFlying(true);
            return;
        }

        if (player.isAlwaysFlying()) {
            entity.setFlying(true);
            player.viewPlayerAbilities(player);
            return;
        }

        entity.setFlying(false);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.REQUEST_ABILITY;
    }
}
