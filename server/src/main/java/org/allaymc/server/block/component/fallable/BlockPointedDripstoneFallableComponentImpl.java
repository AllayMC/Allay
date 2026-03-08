package org.allaymc.server.block.component.fallable;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityFallingBlock;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.eventbus.event.block.BlockFallEvent;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.CustomSound;
import org.joml.Vector3ic;

/**
 * Fallable component implementation for pointed dripstone.
 * <p>
 * Stalactites (hanging) fall as falling block entities when support is removed.
 * They break into items when landing and deal extra damage.
 * <p>
 * The falling order is from tip (bottom) to base (top), creating a cascading effect.
 *
 * @author daoge_cmd
 */
public class BlockPointedDripstoneFallableComponentImpl extends BlockFallableComponentImpl {

    public BlockPointedDripstoneFallableComponentImpl(String landingSound) {
        super(landingSound);
    }

    @Override
    protected boolean shouldFall(Dimension dimension, Vector3ic pos) {
        var state = dimension.getBlockState(pos);
        if (state.getBlockType() != BlockTypes.POINTED_DRIPSTONE) {
            return false;
        }

        // Only stalactites (hanging=true) fall as falling blocks
        var hanging = state.getPropertyValue(BlockPropertyTypes.HANGING);
        if (!hanging) {
            return false;
        }

        // Check if support above is removed
        var above = dimension.getBlockState(pos.x(), pos.y() + 1, pos.z());
        return !above.getBlockStateData().isSolid() &&
               above.getBlockType() != BlockTypes.POINTED_DRIPSTONE;
    }

    @Override
    protected void tryFall(Dimension dimension, Vector3ic pos, BlockState blockState) {
        if (!shouldFall(dimension, pos)) return;

        // Find the tip of the column (bottom-most dripstone)
        int tipY = pos.y();
        while (tipY > dimension.getDimensionInfo().minHeight()) {
            var below = dimension.getBlockState(pos.x(), tipY - 1, pos.z());
            if (below.getBlockType() != BlockTypes.POINTED_DRIPSTONE ||
                !below.getPropertyValue(BlockPropertyTypes.HANGING)) {
                break;
            }
            tipY--;
        }

        // Fall from tip upward
        fallColumnFromTip(dimension, pos.x(), tipY, pos.z());
    }

    /**
     * Falls the stalactite column starting from the tip (bottom) and propagating upward.
     */
    private void fallColumnFromTip(Dimension dimension, int x, int y, int z) {
        var currentState = dimension.getBlockState(x, y, z);
        if (currentState.getBlockType() != BlockTypes.POINTED_DRIPSTONE) {
            return;
        }

        if (!currentState.getPropertyValue(BlockPropertyTypes.HANGING)) {
            return;
        }

        // Call BlockFallEvent
        var event = new BlockFallEvent(new Block(currentState, new Position3i(x, y, z, dimension), 0));
        if (!event.call()) {
            return;
        }

        // Spawn falling block entity
        dimension.getEntityManager().addEntity(createFallingBlock(dimension, x, y, z, currentState), () -> {
            dimension.setBlockState(x, y, z, BlockTypes.AIR.getDefaultState());
        });

        // Fall the next block upward (toward the base)
        int nextY = y + 1;
        var nextState = dimension.getBlockState(x, nextY, z);
        if (nextState.getBlockType() == BlockTypes.POINTED_DRIPSTONE &&
            nextState.getPropertyValue(BlockPropertyTypes.HANGING)) {
            fallColumnFromTip(dimension, x, nextY, z);
        }
    }

    private EntityFallingBlock createFallingBlock(
            Dimension dimension, int x, int y, int z, BlockState blockState) {
        var fallingBlock = EntityTypes.FALLING_BLOCK.createEntity(
                EntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(x + 0.5f, y, z + 0.5f)
                        .build()
        );
        fallingBlock.setBlockState(blockState);
        return fallingBlock;
    }

    @Override
    public void onLanded(Location3d location, double fallDistance, BlockState blockState) {
        // Pointed dripstone breaks into items when landing
        location.dimension().dropItem(blockState.toItemStack(), location);
        location.dimension().addSound(location, new CustomSound(landingSound));
    }

    @Override
    public float calculateDamage(double fallDistance) {
        // Falling stalactite deals 6 damage per block fallen (after the first 2 blocks)
        // Max damage is 40
        if (fallDistance <= 2) {
            return 0;
        }
        return Math.min(40, (float) (fallDistance - 2) * 6);
    }

    @Override
    public DamageContainer createDamageContainer(float damage) {
        return DamageContainer.stalactite(damage);
    }
}
