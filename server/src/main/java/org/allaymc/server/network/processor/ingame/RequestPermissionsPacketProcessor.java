package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.eventbus.event.server.PlayerAbilitiesUpdateRequestEvent;
import org.allaymc.api.player.Player;
import org.allaymc.api.player.PlayerAbility;
import org.allaymc.api.server.Server;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.data.Ability;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.RequestPermissionsPacket;

import java.util.EnumSet;
import java.util.Set;

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
        var requestedPermissions = packet.getCustomPermissions();
        for (var ability : CONTROLLABLE_ABILITIES) {
            if (hasNetworkAbility(requestedPermissions, ability)) {
                updated.add(ability);
            } else {
                updated.remove(ability);
            }
        }

        if (new PlayerAbilitiesUpdateRequestEvent(player, target, updated).call()) {
            target.setAbilities(updated);
        }
    }

    protected boolean hasNetworkAbility(Set<Ability> abilities, PlayerAbility ability) {
        if (abilities == null) {
            return false;
        }

        return abilities.contains(switch (ability) {
            case PLACE_BLOCK -> Ability.BUILD;
            case BREAK_BLOCK -> Ability.MINE;
            case INTERACT_BLOCK -> Ability.DOORS_AND_SWITCHES;
            case OPEN_CONTAINER -> Ability.OPEN_CONTAINERS;
            case ATTACK_PLAYER -> Ability.ATTACK_PLAYERS;
            case ATTACK_MOB -> Ability.ATTACK_MOBS;
            case FLYING -> Ability.FLYING;
            case MAY_FLY -> Ability.MAY_FLY;
            case INFINITE_BLOCK -> Ability.INSTABUILD;
            case NO_CLIP -> Ability.NO_CLIP;
        });
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.REQUEST_PERMISSIONS;
    }
}
