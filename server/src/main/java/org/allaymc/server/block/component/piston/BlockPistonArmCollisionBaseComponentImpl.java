package org.allaymc.server.block.component.piston;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.util.Set;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.FACING_DIRECTION;

/**
 * {@code BlockPistonArmCollisionBaseComponentImpl} represents the extended piston's
 * collision head in ordinary block callbacks. The arm drops no item of its own and can
 * cascade an entity-originated break to a matching piston base behind it. The base and
 * arm share a stored direction encoding that reverses horizontal faces.
 *
 * <p>
 * The cascade runs only when {@code entity} is non-{@code null}. It decodes both facing values
 * and compares them before requesting a base break with a {@code null} entity, stopping
 * that second break from cascading back through the paired component. Programmatic
 * breaks with a {@code null} entity skip this relationship check entirely. The
 * dimension can refuse the requested base break, and this callback does not reverse its
 * earlier inherited handling.
 *
 * @author daoge_cmd
 */
public class BlockPistonArmCollisionBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * Creates the collision-arm component for the supplied piston head block type.
     */
    public BlockPistonArmCollisionBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns an empty item set for a piston collision arm. The arm is a temporary
     * extension of its base, so its direct drop path does not create another piston
     * item.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of();
    }

    /**
     * Runs inherited break handling and requests a matching base break for an
     * entity-originated arm break. A {@code null} {@code entity} stops the cascade,
     * including the callback reached when the base itself breaks its arm.
     *
     * <p>
     * The arm's decoded facing locates the base behind it, and that position must
     * contain a normal or sticky piston with the same decoded direction. The requested
     * base break passes a {@code null} entity to avoid a reciprocal cascade. Its
     * success is not checked, so the pair is not removed atomically.
     */
    @Override
    public void onBreak(Block block, ItemStack usedItem, Entity entity) {
        super.onBreak(block, usedItem, entity);

        // Base-to-arm cascades pass no entity. Stop here on that path so
        // the reciprocal arm-to-base callback cannot repeatedly break the same piston pair.
        if (entity == null) {
            return;
        }

        var dimension = block.getDimension();
        var pos = block.getPosition();
        var blockState = block.getBlockState();

        int facing = blockState.getPropertyValue(FACING_DIRECTION);
        BlockFace armFace = BlockFace.fromIndex(facing);
        // The stored horizontal code is reversed relative to extension, while
        // vertical codes already identify the physical head direction used to locate its base.
        if (armFace.isHorizontal()) {
            armFace = armFace.opposite();
        }

        Vector3ic pistonPos = armFace.opposite().offsetPos(pos);

        var pistonState = dimension.getBlockState(pistonPos);
        if (pistonState.getBlockType() == BlockTypes.PISTON ||
            pistonState.getBlockType() == BlockTypes.STICKY_PISTON) {
            int pistonFacing = pistonState.getPropertyValue(FACING_DIRECTION);
            BlockFace pistonFace = BlockFace.fromIndex(pistonFacing);
            if (pistonFace.isHorizontal()) {
                pistonFace = pistonFace.opposite();
            }
            if (pistonFace == armFace) {
                dimension.breakBlock(pistonPos, null, null);
            }
        }
    }
}
