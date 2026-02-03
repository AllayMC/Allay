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
import org.allaymc.server.block.component.BlockFireBaseComponentImpl;
import org.joml.Vector3dc;
import org.joml.Vector3i;
import org.joml.Vector3ic;

/**
 * Physics component implementation for small fireball entities.
 *
 * @author daoge_cmd
 */
public class EntitySmallFireballPhysicsComponentImpl extends EntityProjectilePhysicsComponentImpl {

    /**
     * The base damage dealt by the small fireball.
     * According to Minecraft Wiki, small fireballs deal 5HP damage.
     */
    protected static final int BASE_DAMAGE = 5;

    /**
     * The duration in seconds that entities are set on fire when hit.
     */
    protected static final int FIRE_DURATION_SECONDS = 5;

    @Override
    public double getGravity() {
        // Small fireballs are not affected by gravity
        return 0;
    }

    @Override
    public double getDragFactorInAir() {
        // No air drag for fireballs
        return 0;
    }

    @Override
    protected void onHitEntity(Entity other, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedLater()) {
            return;
        }

        if (other instanceof EntityLiving living) {
            // Deal damage
            var damage = DamageContainer.projectile(thisEntity, BASE_DAMAGE);
            if (living.attack(damage)) {
                // Set entity on fire if damage was dealt
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

    @Override
    protected void onHitBlock(Block block, Vector3dc hitPos) {
        if (thisEntity.willBeDespawnedLater()) {
            return;
        }

        // Try to place fire at the hit position
        tryPlaceFire(block.getDimension(), hitPos);
        thisEntity.remove();
    }

    /**
     * Attempts to place fire at the hit position.
     * Fire can be placed if:
     * 1. The hit position is air
     * 2. The block below can support fire (solid top surface)
     * OR there are flammable blocks nearby
     *
     * @param dimension the dimension
     * @param hitPos    the hit position
     */
    protected void tryPlaceFire(Dimension dimension, Vector3dc hitPos) {
        var firePos = new Vector3i(
                (int) Math.floor(hitPos.x()),
                (int) Math.floor(hitPos.y()),
                (int) Math.floor(hitPos.z())
        );

        // Check if the position is air
        var blockStateAtHit = dimension.getBlockState(firePos);
        if (blockStateAtHit.getBlockType() != BlockTypes.AIR) {
            // Try adjacent positions
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

        // Check if fire can be placed at hit position
        if (canPlaceFireAt(dimension, firePos)) {
            placeFireAt(dimension, firePos);
        }
    }

    /**
     * Checks if fire can be placed at the given position.
     *
     * @param dimension the dimension
     * @param pos       the position to check
     * @return true if fire can be placed
     */
    protected boolean canPlaceFireAt(Dimension dimension, Vector3ic pos) {
        // Check if block below can support fire
        var downBlockState = dimension.getBlockState(pos.x(), pos.y() - 1, pos.z());
        if (BlockFireBaseComponentImpl.canSupportFire(downBlockState)) {
            return true;
        }

        // Check if any neighbor is flammable
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
     * Places fire at the given position.
     *
     * @param dimension the dimension
     * @param pos       the position to place fire
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
