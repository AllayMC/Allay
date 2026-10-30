package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.data.Ability;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.RequestAbilityPacket;

/**
 * {@code RequestAbilityPacketProcessor} resolves boolean flight requests against
 * the player's flight permissions and required-flight state. Ability kinds other
 * than {@code FLYING}, or nonboolean values,
 * are ignored. Starting flight is allowed only when the player can fly, and
 * stopping flight is blocked when the player must remain flying.
 *
 * <p>
 * Rejected starts resend the authoritative ability state without setting the
 * entity's flight flag. Rejected stops explicitly retain flight and resend
 * abilities so the client can recover from its local prediction. Accepted
 * requests update the controlled entity through its normal flight setter. These
 * decisions use the player's current ability observations rather than trusting
 * the requested boolean, and this processor does not grant any missing flight
 * permission.
 *
 * @author daoge_cmd
 */
public class RequestAbilityPacketProcessor extends PacketProcessor<RequestAbilityPacket> {

    /**
     * Applies a boolean {@code FLYING} request according to the player's current flight
     * rules. Other abilities and value types are ignored, and the player must
     * have a controlled entity for a flight request.
     *
     * <p>
     * A request to begin flying is rejected with an ability resend when {@code canFly}
     * is {@code false}. A request to stop instead keeps the entity flying and resends
     * abilities when {@code isAlwaysFlying} is {@code true}. Remaining requests set the
     * entity's flight flag directly; rejected starts do not themselves change
     * that flag.
     */
    @Override
    public void handleSync(Player player, RequestAbilityPacket packet, long receiveTime) {
        if (packet.getAbility() != Ability.FLYING || packet.getType() != Ability.Type.BOOLEAN) {
            return;
        }

        var entity = player.getControlledEntity();
        if (packet.isBoolValue()) {
            // The client requested flight without permission; resend
            // authoritative abilities so its predicted flight request can be
            // corrected without granting flight.
            if (!player.canFly()) {
                player.viewPlayerAbilities(player);
                return;
            }

            entity.setFlying(true);
            return;
        }

        // A required-flight mode overrides a stop request; keep the entity
        // flying and resend abilities so the client retains the same
        // restriction.
        if (player.isAlwaysFlying()) {
            entity.setFlying(true);
            player.viewPlayerAbilities(player);
            return;
        }

        entity.setFlying(false);
    }

    /**
     * Returns the request ability packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.REQUEST_ABILITY;
    }
}
