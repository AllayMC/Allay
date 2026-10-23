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
 * {@code BlockPointedDripstoneFallableComponentImpl} queues unsupported hanging
 * dripstone columns as falling entities from tip toward base. Standing dripstone is
 * excluded, and support above can be either a solid block or another pointed
 * dripstone. Each queued entity retains its original state for later item conversion
 * and stalactite damage.
 *
 * <p>
 * The component locates the lowest connected hanging block before recursively
 * submitting the column upward. Each source block is cleared in its own deferred
 * addition callback, so queue submission order does not create an atomic column
 * transition. Landing emits an item and sound instead of replacing the block, while
 * impact damage ignores the first two fallen blocks and caps the remaining per-block
 * damage.
 *
 * @author daoge_cmd
 */
public class BlockPointedDripstoneFallableComponentImpl extends BlockFallableComponentImpl {

    /**
     * Creates pointed dripstone falling behavior with the supplied landing sound.
     */
    public BlockPointedDripstoneFallableComponentImpl(String landingSound) {
        super(landingSound);
    }

    /**
     * Returns whether pointed dripstone is hanging and has lost its upper support. A
     * solid upper block or any pointed dripstone above prevents falling, while
     * standing dripstone always returns {@code false}.
     */
    @Override
    protected boolean shouldFall(Dimension dimension, Vector3ic pos) {
        var state = dimension.getBlockState(pos);
        if (state.getBlockType() != BlockTypes.POINTED_DRIPSTONE) {
            return false;
        }
        var hanging = state.getPropertyValue(BlockPropertyTypes.HANGING);
        if (!hanging) {
            return false;
        }
        var above = dimension.getBlockState(pos.x(), pos.y() + 1, pos.z());
        return !above.getBlockStateData().isSolid() &&
               above.getBlockType() != BlockTypes.POINTED_DRIPSTONE;
    }

    /**
     * Finds the bottom of an unsupported hanging column and submits its fall upward
     * from that tip. The search stops at dimension minimum height or the first
     * different block type or orientation.
     */
    @Override
    protected void tryFall(Dimension dimension, Vector3ic pos, BlockState blockState) {
        if (!shouldFall(dimension, pos)) return;
        int tipY = pos.y();
        while (tipY > dimension.getDimensionType().getMinHeight()) {
            var below = dimension.getBlockState(pos.x(), tipY - 1, pos.z());
            if (below.getBlockType() != BlockTypes.POINTED_DRIPSTONE ||
                !below.getPropertyValue(BlockPropertyTypes.HANGING)) {
                break;
            }
            tipY--;
        }
        fallColumnFromTip(dimension, pos.x(), tipY, pos.z());
    }

    /**
     * Submits connected hanging dripstone blocks in tip-to-base order for deferred
     * falling-entity addition. Each position must still contain hanging pointed
     * dripstone when recursion visits it.
     *
     * <p>
     * The fall notification precedes each entity submission, and that entity's
     * addition callback later clears its source. Recursion proceeds immediately after
     * submission rather than waiting for the callback. Rejected additions or failed
     * source writes therefore do not roll back previously submitted entities or
     * prevent later source positions from being visited.
     */
    private void fallColumnFromTip(Dimension dimension, int x, int y, int z) {
        var currentState = dimension.getBlockState(x, y, z);
        if (currentState.getBlockType() != BlockTypes.POINTED_DRIPSTONE) {
            return;
        }

        if (!currentState.getPropertyValue(BlockPropertyTypes.HANGING)) {
            return;
        }
        var event = new BlockFallEvent(new Block(currentState, new Position3i(x, y, z, dimension), 0));
        if (!event.call()) {
            return;
        }
        dimension.getEntityManager().addEntity(createFallingBlock(dimension, x, y, z, currentState), () -> {
            dimension.setBlockState(x, y, z, BlockTypes.AIR.getDefaultState());
        });
        // Continue upward after submission rather than callback completion, keeping
        // request order from tip to base while each source awaits its own deferred
        // removal.
        int nextY = y + 1;
        var nextState = dimension.getBlockState(x, nextY, z);
        if (nextState.getBlockType() == BlockTypes.POINTED_DRIPSTONE &&
            nextState.getPropertyValue(BlockPropertyTypes.HANGING)) {
            fallColumnFromTip(dimension, x, nextY, z);
        }
    }

    /**
     * Creates an unregistered falling entity at the supplied block coordinates and
     * horizontal center. The entity retains {@code blockState}, and the caller
     * separately queues its addition and source removal.
     */
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

    /**
     * Drops the falling dripstone state as an item and plays its configured landing
     * sound. This landing path does not place a block, so the former entity becomes
     * an item result instead.
     */
    @Override
    public void onLanded(Location3d location, double fallDistance, BlockState blockState) {
        location.dimension().dropItem(blockState.toItemStack(), location);
        location.dimension().addSound(location, new CustomSound(landingSound));
    }

    /**
     * Returns six damage points per block fallen after the first two blocks, capped
     * at forty. Distances of two blocks or less produce {@code 0} impact damage.
     */
    @Override
    public float calculateDamage(double fallDistance) {
        if (fallDistance <= 2) {
            return 0;
        }
        // Only descent beyond two blocks contributes, and forty limits the final
        // damage.
        return Math.min(40, (float) (fallDistance - 2) * 6);
    }

    /**
     * Creates the stalactite-specific damage descriptor for the supplied impact
     * amount.
     */
    @Override
    public DamageContainer createDamageContainer(float damage) {
        return DamageContainer.stalactite(damage);
    }
}
