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
 * {@code RequestPermissionsPacketProcessor} applies an operator's requested
 * subset of player abilities after a
 * cancellable update event. The target is the first online player whose
 * controlled entity {@code UUID} low bits match the incoming unique entity ID. Players
 * without controlled entities are skipped, and absent targets or nonoperator
 * requesters have no effect.
 *
 * <p>
 * The proposed set starts with the target's current abilities, then adds or
 * removes only the six controllable interaction abilities. Flight and other
 * unlisted abilities survive unchanged. A {@code null} custom-permission set therefore
 * removes that controllable subset rather than clearing every ability. The
 * event receives an immutable copy of the proposal; acceptance applies the
 * local set, while cancellation leaves the target untouched by this handler.
 *
 * @author zernix2077
 */
public class RequestPermissionsPacketProcessor extends PacketProcessor<RequestPermissionsPacket> {
    /**
     * {@code CONTROLLABLE_ABILITIES} defines the six interaction abilities
     * replaced by permission requests;
     * abilities outside this array retain their current values in the proposed
     * set.
     */
    protected static final PlayerAbility[] CONTROLLABLE_ABILITIES = {
            PlayerAbility.PLACE_BLOCK,
            PlayerAbility.BREAK_BLOCK,
            PlayerAbility.INTERACT_BLOCK,
            PlayerAbility.OPEN_CONTAINER,
            PlayerAbility.ATTACK_PLAYER,
            PlayerAbility.ATTACK_MOB
    };

    /**
     * Builds and applies an operator-requested ability update for the first
     * matching online target. The target lookup compares persistent {@code UUID} low
     * bits, not runtime entity IDs, and skips players lacking a controlled
     * entity.
     *
     * <p>
     * The proposal preserves existing abilities outside {@code CONTROLLABLE_ABILITIES}
     * and replaces only that subset according to the packet. {@code null} network
     * permissions act as an empty subset. A cancellable event observes a copied
     * proposal before the original local set is passed to the target, and this
     * handler applies nothing if the event rejects it.
     */
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

        // Start from existing abilities so packet-controlled interaction
        // permissions can change without accidentally clearing flight and other
        // abilities outside the supported subset.
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

        // Let plugins veto the complete proposal before applying it; the event
        // copies the set and cannot replace the local proposal used here.
        if (new PlayerAbilitiesUpdateRequestEvent(player, target, updated).call()) {
            target.setAbilities(updated);
        }
    }

    /**
     * Returns whether a network permission set contains the mapped player
     * ability. A {@code null} set acts as an empty set, so callers can use it to remove
     * their controllable ability subset.
     */
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

    /**
     * Returns the request permissions packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.REQUEST_PERMISSIONS;
    }
}
