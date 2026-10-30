package org.allaymc.server.block.component.plant;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.AGE_16;

/**
 * {@code BlockCactusBaseComponentImpl} implements cactus survival, age-driven upward growth,
 * and living-entity contact damage. The survival predicate requires horizontal air and sand
 * beneath the recursively checked column. Placement and neighbor changes use that full support
 * walk.
 *
 * <p>
 * Natural updates advance the current age, then reset it at maturity and search at most two
 * positions above for an air block. The growth gate uses a nonrecursive support check, so it
 * requires sand directly below the updated segment. New cactus placement happens before the
 * current age is written, without a cancellable growth event or rollback; contact separately
 * attempts half a point of damage against living entities.
 *
 * @author daoge_cmd
 */
public class BlockCactusBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the cactus behavior component for the supplied block type.
     */
    public BlockCactusBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places the supplied cactus state only when recursive support and horizontal clearance
     * checks pass. The accepted write bypasses ordinary property processing and returns the
     * dimension's placement result with the supplied interaction information.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (!canGrowHere(dimension, placeBlockPos, true)) {
            return false;
        }

        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    /**
     * Attempts half a point of contact damage against a living entity. Other entity types
     * receive no action here; the living entity's attack path determines whether the proposed
     * damage takes effect.
     */
    @Override
    public void onCollideWithEntity(Block block, Entity entity) {
        if (entity instanceof EntityLiving living) {
            living.attack(DamageContainer.contact(0.5f));
        }
    }

    /**
     * Breaks the cactus when current horizontal clearance or recursive lower support becomes
     * invalid. Every neighbor notification triggers this complete survival test, and the
     * override does not dispatch the base neighbor hook.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        if (!canGrowHere(block.getDimension(), block.getPosition(), true)) {
            block.breakBlock();
        }
    }

    /**
     * Advances cactus age or resets a mature age before attempting upward growth. Growth
     * requires the nonrecursive support predicate at the current position, then searches one
     * and two blocks above, passing existing cactus and stopping at the first other type.
     *
     * <p>
     * The first air position receives a new default cactus state, after which the current
     * segment's age is written. No growth event guards the new segment, and the dimension
     * write results are ignored. The scan is relative to the updated segment rather than a
     * separately measured total-column height.
     */
    @Override
    public void onRandomUpdate(Block block) {
        var dimension = block.getDimension();
        var age = block.getPropertyValue(AGE_16);
        if (age < AGE_16.getMax()) {
            block = block.setPropertyValue(AGE_16, age + 1);
        } else if (age == AGE_16.getMax()) {
            block = block.setPropertyValue(AGE_16, 0);
            if (canGrowHere(dimension, block.getPosition(), false)) {
                // Search only two positions above this updated segment; this loop does
                // not measure a total column height or validate the new segment's horizontal neighbors.
                for (var y = 1; y < 3; y++) {
                    var upperBlock = block.offsetPos(0, y, 0);
                    var blockType = upperBlock.getBlockType();
                    if (blockType == BlockTypes.AIR) {
                        dimension.setBlockState(upperBlock.getPosition(), BlockTypes.CACTUS.getDefaultState());
                        break;
                    } else if (blockType != BlockTypes.CACTUS) {
                        break;
                    }
                }
            }
        }

        dimension.setBlockState(block.getPosition(), block.getBlockState());
    }

    /**
     * Returns whether cactus clearance and lower support qualify at {@code pos}. Every
     * horizontal neighbor must be air. With {@code recursive} enabled, a lower cactus moves
     * the test down the column until a sand-tagged support is reached.
     *
     * <p>
     * With recursion disabled, the lower block itself must be sand, so an upper cactus segment
     * supported directly by cactus fails that form of the predicate. The method checks
     * existing column positions only; callers placing a new segment elsewhere must decide
     * which position's clearance they need to validate.
     */
    protected boolean canGrowHere(Dimension dimension, Vector3ic pos, boolean recursive) {
        for (var face : BlockFace.getHorizontalBlockFaces()) {
            if (dimension.getBlockState(face.offsetPos(pos)).getBlockType() != BlockTypes.AIR) {
                return false;
            }
        }

        var downBlockType = dimension.getBlockState(BlockFace.DOWN.offsetPos(pos)).getBlockType();
        if (recursive && downBlockType == BlockTypes.CACTUS) {
            return canGrowHere(dimension, BlockFace.DOWN.offsetPos(pos), true);
        }

        return downBlockType.hasBlockTag(BlockTags.SAND);
    }

    /**
     * Returns whether cactus receives random updates for age-driven upward growth.
     */
    @Override
    public boolean canRandomUpdate() {
        return true;
    }
}
