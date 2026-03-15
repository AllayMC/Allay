package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.eventbus.event.server.PlayerAbilitiesUpdateRequestEvent;
import org.allaymc.api.player.Player;
import org.allaymc.api.player.PlayerAbility;
import org.allaymc.api.server.Server;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.RequestPermissionsPacket;

import java.util.EnumSet;

/**
 * @author zernix2077
 */
public class RequestPermissionsPacketProcessor extends PacketProcessor<RequestPermissionsPacket> {
    protected static final PlayerAbility[] CONTROLLABLE_ABILITIES = {
            PlayerAbility.PLACE_BLOCK,
            PlayerAbility.BREAK_BLOCK,
            PlayerAbility.INTERACT_BLOCK,
            PlayerAbility.OPEN_CONTAINER,
            PlayerAbility.ATTACK_PLAYER,
            PlayerAbility.ATTACK_MOB
    };

    @Override
    public void handleSync(Player player, RequestPermissionsPacket packet, long receiveTime) {
        if (!Server.getInstance().getPlayerManager().isOperator(player)) {
            return;
        }

        Player target = null;
        for (var onlinePlayer : Server.getInstance().getPlayerManager().getPlayers().values()) {
            var entity = onlinePlayer.getControlledEntity();
            if (entity != null && entity.getUniqueId().getLeastSignificantBits() == packet.getUniqueEntityId()) {
                target = onlinePlayer;
                break;
            }
        }
        if (target == null) {
            return;
        }

        var updated = EnumSet.noneOf(PlayerAbility.class);
        updated.addAll(target.getAbilities());
        for (int bit = 0; bit < CONTROLLABLE_ABILITIES.length; bit++) {
            var ability = CONTROLLABLE_ABILITIES[bit];
            if ((packet.getCustomPermissions() & (1 << bit)) != 0) {
                updated.add(ability);
            } else {
                updated.remove(ability);
            }
        }

        if (new PlayerAbilitiesUpdateRequestEvent(player, target, updated).call()) {
            target.setAbilities(updated);
        }
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.REQUEST_PERMISSIONS;
    }
}
