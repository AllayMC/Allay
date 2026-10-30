package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.component.BlockRespawnPointComponent;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerRespawnEvent;
import org.allaymc.api.math.location.Location3ic;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.player.Player;
import org.allaymc.api.server.Server;
import org.allaymc.server.network.processor.PacketProcessor;
import org.allaymc.server.player.AllayPlayer;
import org.allaymc.server.world.AllayDimension;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.RespawnPacket;

/**
 * {@code RespawnPacketProcessor} processes a ready client's respawn request when
 * its controlled entity can be
 * spawned again. The initial destination comes from the entity's validated
 * spawn point and any respawn-capable block there, then a player respawn event
 * can replace it. Invalid block-anchored destinations fall back to the global
 * spawn and update the stored spawn point.
 *
 * <p>
 * The server-ready packet is sent before the player is removed and re-added
 * through dimension callbacks. Re-adding completes before effects, health,
 * food, fire, and air are reset and teleportation is requested. Those steps are
 * ordered but do not form a rollback transaction; the processor does not
 * restore earlier membership or packet delivery if a later callback fails.
 * Requests arriving before the entity can spawn are ignored rather than queued
 * for retry.
 *
 * @author IWareQ | daoge_cmd
 */
@Slf4j
public class RespawnPacketProcessor extends PacketProcessor<RespawnPacket> {

    /**
     * Starts respawning an {@code AllayPlayer} only for {@code CLIENT_READY} packets whose
     * entity can be spawned. The respawn event supplies the final destination
     * after the initial block or global spawn selection.
     *
     * <p>
     * A server-ready packet is sent first, then removal from the current
     * {@code AllayDimension} invokes re-addition to that dimension from its removal
     * callback, which can run inline for an already unspawned entity. The nested
     * completion callback resets survival state and teleports to the selected
     * destination. Failures are not compensated here, and ignored early
     * requests are not automatically retried when the entity later becomes
     * spawnable.
     */
    @Override
    public void handleSync(Player player, RespawnPacket packet, long receiveTime) {
        if (packet.getState() != RespawnPacket.State.CLIENT_READY) {
            log.warn("Respawn state must be CLIENT_READY, but got {}", packet.getState());
            return;
        }

        var entity = player.getControlledEntity();
        if (!entity.canBeSpawned()) {
            // Ignore this early request without scheduling a retry; a later
            // client request must reach this handler after the entity becomes
            // spawnable.
            return;
        }

        var event = new PlayerRespawnEvent(entity);
        event.setRespawnLocation(this.findSpawnPoint(entity));
        event.call();

        var spawnPoint = event.getRespawnLocation();

        // Send server readiness before the remove-and-readd callbacks begin;
        // the respawn encoder supplies the destination and readiness state for
        // this connection without rebuilding the player identity.
        var allayPlayer = (AllayPlayer) player;
        allayPlayer.sendPacket(allayPlayer.getProtocol().getEncoder().encodeRespawn(spawnPoint));

        // Re-add from the removal callback so entity-manager removal, when
        // needed, finishes first. An unspawned entity takes an inline callback;
        // reset survival state and teleport after the add callback completes.
        var dimension = (AllayDimension) entity.getDimension();
        dimension.removePlayer(player, () -> {
            dimension.addPlayer(player, () -> {
                resetData(entity);
                entity.teleport(spawnPoint);
            });
        });
    }

    /**
     * Selects the validated personal spawn or a block-provided respawn
     * location. If a respawn-capable block declines by returning {@code null}, the
     * global spawn is stored on the entity and returned instead.
     *
     * <p>
     * When no such block remains, a spawn source with an invalid-spawn message
     * key sends that message and also resets the stored point to the global
     * spawn. Other sources keep their validated location. Thus fallback changes
     * the entity's future spawn preference as well as the destination returned
     * for this respawn, before the later respawn event can replace the
     * immediate location.
     */
    private Location3ic findSpawnPoint(EntityPlayer entity) {
        var spawnPoint = entity.validateAndGetSpawnPoint();
        var dimension = spawnPoint.dimension();

        var blockState = dimension.getBlockState(spawnPoint.x(), spawnPoint.y(), spawnPoint.z());
        var blockBehavior = blockState.getBlockType().getBlockBehavior();
        if (blockBehavior instanceof BlockRespawnPointComponent respawnPointComponent) {
            var respawnLocation = respawnPointComponent.onPlayerRespawn(entity, new Block(blockState, new Position3i(spawnPoint, dimension)));
            if (respawnLocation != null) {
                return respawnLocation;
            }

            // The respawn block declined its destination, so store the global
            // fallback as the future spawn preference as well as returning it.
            var globalSpawnPoint = Server.getInstance().getWorldPool().getGlobalSpawnPoint();
            entity.setSpawnPoint(globalSpawnPoint);
            return globalSpawnPoint;
        }

        // A block-anchored source carries an invalid-spawn message key; when no
        // respawn behavior remains, notify the player and reset its stored
        // point instead of continuing to reuse that anchor.
        var source = entity.getSpawnPointType();
        if (source.invalidSpawnKey != null) {
            entity.sendTranslatable(source.invalidSpawnKey);
            var globalSpawnPoint = Server.getInstance().getWorldPool().getGlobalSpawnPoint();
            entity.setSpawnPoint(globalSpawnPoint);
            return globalSpawnPoint;
        }

        return spawnPoint;
    }

    /**
     * Resets effects, health, food, fire, and air after the player is re-added
     * for respawn. The supplied entity remains the same object, and this method
     * does not change its inventory, game mode, or destination.
     */
    private void resetData(EntityPlayer player) {
        player.removeAllEffects();
        player.resetHealth();
        player.resetFoodData();
        player.extinguish();
        player.setAirSupplyTicks(player.getAirSupplyMaxTicks());
    }

    /**
     * Returns the respawn packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.RESPAWN;
    }
}
