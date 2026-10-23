package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.eventbus.event.block.BlockIgniteEvent;
import org.allaymc.api.eventbus.event.entity.EntityCombustEvent;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.fire.BlockFireBaseComponentImpl;
import org.joml.Vector3dc;
import org.joml.Vector3i;
import org.joml.Vector3ic;

/**
 * {@code EntitySmallFireballPhysicsComponentImpl} resolves small fireballs
 * into direct damage and permitted ignition. Entity combustion is offered only
 * after the living target accepts projectile damage, while block impacts
 * search for an air cell that can support fire. Free-flight updates use zero
 * gravity and zero air drag.
 *
 * <p>
 * Fire placement combines occupancy with support or neighboring flammability.
 * An occupied impact cell triggers an ordered adjacent-cell search, and the
 * first eligible cell consumes the attempt even if its ignition event is
 * cancelled. An already empty impact cell is tried directly without an
 * adjacent fallback. Both impact paths remove the projectile after their
 * permitted work finishes.
 *
 * @author daoge_cmd
 */
public class EntitySmallFireballPhysicsComponentImpl extends EntityProjectilePhysicsComponentImpl {

    /**
     * {@code BASE_DAMAGE} supplies the five direct damage points offered to a
     * living target before combustion is considered.
     */
    protected static final int BASE_DAMAGE = 5;

    /**
     * {@code FIRE_DURATION_SECONDS} supplies the initial combustion duration
     * in seconds, converted to entity ticks before event dispatch.
     */
    protected static final int FIRE_DURATION_SECONDS = 5;

    /**
     * Returns zero downward acceleration for freely flying small fireballs.
     */
    @Override
    public double getGravity() {
        return 0;
    }

    /**
     * Returns zero air drag so free-flight small-fireball motion is retained.
     */
    @Override
    public double getDragFactorInAir() {
        return 0;
    }

    /**
     * Offers projectile damage and, after accepted damage, a cancellable
     * combustion event. The event can replace fire duration; cancellation
     * skips ignition while the projectile is still removed after target
     * processing.
     */
    @Override
    protected void onHitEntity(Entity other, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedLater()) {
            return;
        }

        if (other instanceof EntityLiving living) {
            var damage = DamageContainer.projectile(thisEntity, BASE_DAMAGE);
            if (living.attack(damage)) {
                // Convert the five-second combustion default to the twenty-tick
                // entity clock before handlers can alter the duration.
                var event = new EntityCombustEvent(
                        other,
                        EntityCombustEvent.CombusterType.ENTITY,
                        thisEntity,
                        20 * FIRE_DURATION_SECONDS
                );
                if (event.call()) {
                    living.setOnFireTicks(event.getOnFireTicks());
                }
            }
        }

        thisEntity.remove();
    }

    /**
     * Attempts ignition near the impact position and then requests projectile
     * removal. A queued removal suppresses both steps, while the block
     * dimension supplies the world context for placement.
     */
    @Override
    protected void onHitBlock(Block block, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedLater()) {
            return;
        }

        tryPlaceFire(block.getDimension(), hitPos);
        thisEntity.remove();
    }

    /**
     * Attempts fire placement at the floored impact cell or its first eligible
     * adjacent cell. An occupied impact cell scans {@code BlockFace.VALUES}
     * order and requires adjacent air plus placement support. The first
     * eligible neighbor ends the search after its placement attempt.
     *
     * <p>
     * Ignition-event cancellation does not continue to another neighbor
     * because {@code placeFireAt} returns no success result. An empty impact
     * cell is checked directly; failed support there does not start a neighbor
     * search. The helper does not remove the projectile or report whether a
     * fire block was installed.
     */
    protected void tryPlaceFire(Dimension dimension, Vector3dc hitPos) {
        var firePos = new Vector3i(
                (int) Math.floor(hitPos.x()),
                (int) Math.floor(hitPos.y()),
                (int) Math.floor(hitPos.z())
        );

        var blockStateAtHit = dimension.getBlockState(firePos);
        if (blockStateAtHit.getBlockType() != BlockTypes.AIR) {
            // An occupied impact cell uses the first eligible neighbor;
            // cancellation of that neighbor ignition still ends this search.
            for (BlockFace face : BlockFace.VALUES) {
                var adjacentPos = face.offsetPos(firePos);
                var adjacentBlockState = dimension.getBlockState(adjacentPos);
                if (adjacentBlockState.getBlockType() == BlockTypes.AIR) {
                    if (canPlaceFireAt(dimension, adjacentPos)) {
                        placeFireAt(dimension, adjacentPos);
                        return;
                    }
                }
            }
            return;
        }

        if (canPlaceFireAt(dimension, firePos)) {
            placeFireAt(dimension, firePos);
        }
    }

    /**
     * Returns whether the block below supports fire or a neighboring block has
     * positive flame odds. The caller must check target occupancy separately,
     * because this helper does not require the target cell itself to be air.
     */
    protected boolean canPlaceFireAt(Dimension dimension, Vector3ic pos) {
        var downBlockState = dimension.getBlockState(pos.x(), pos.y() - 1, pos.z());
        if (BlockFireBaseComponentImpl.canSupportFire(downBlockState)) {
            return true;
        }

        for (BlockFace face : BlockFace.VALUES) {
            var neighborPos = face.offsetPos(pos);
            var neighborBlockState = dimension.getBlockState(neighborPos);
            if (neighborBlockState.getBlockStateData().flameOdds() > 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * Dispatches an ignition event for the block below {@code pos} and
     * installs fire when permitted. The caller supplies an eligible air
     * position; this helper does not repeat support or occupancy checks after
     * event handlers run.
     */
    protected void placeFireAt(Dimension dimension, Vector3ic pos) {
        var supportBlock = new Block(
                dimension.getBlockState(pos.x(), pos.y() - 1, pos.z()),
                new Position3i(pos.x(), pos.y() - 1, pos.z(), dimension),
                0
        );

        var event = new BlockIgniteEvent(
                supportBlock,
                null,
                null,
                BlockIgniteEvent.BlockIgniteCause.FIREBALL
        );

        if (event.call()) {
            dimension.setBlockState(pos, BlockTypes.FIRE.getDefaultState());
        }
    }
}
