package org.allaymc.server.block.component.plant;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.AGE_16;

/**
 * {@code BlockReedsBaseComponentImpl} implements sugar-cane support, age-driven extension, and
 * bone-meal growth. A valid basal segment rests on sand- or dirt-tagged ground with adjacent
 * water at ground level. Recursive survival checks walk downward through reeds to that basal
 * support.
 *
 * <p>
 * Natural updates increment age and, at maturity, use the nonrecursive basal check before
 * searching two positions above for a new segment. Bone meal moves down at most one segment
 * and fills air positions in that same two-block region. The bone-meal callback emits
 * particles without consuming the held item, and both growth paths use direct block writes
 * without cancellable growth proposals or rollback.
 *
 * @author daoge_cmd
 */
public class BlockReedsBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the reeds behavior component for the supplied block type.
     */
    public BlockReedsBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places the supplied reed state when recursive support reaches valid watered ground. The
     * accepted write bypasses ordinary property processing and returns the dimension's result
     * with the supplied optional placement information.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (!canGrowHere(dimension, placeBlockPos, true)) {
            return false;
        }

        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    /**
     * Breaks the reed when recursive support no longer reaches valid watered ground. Every
     * neighboring change invokes that support test; this override does not dispatch the
     * inherited neighbor hook first.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        if (!canGrowHere(block.getDimension(), block.getPosition(), true)) {
            block.breakBlock();
        }
    }

    /**
     * Validates recursive support, then increments age or resets a mature age before an
     * extension attempt. Invalid support breaks the reed immediately. A mature basal segment
     * checks up to two positions above, passing existing reeds and stopping at the first other
     * nonair type.
     *
     * <p>
     * The first air position receives default reeds before the updated age is written back.
     * The method ignores dimension write results and does not dispatch a growth event. The
     * upper scan is relative to the updated position rather than a separately measured
     * full-column height.
     */
    @Override
    public void onRandomUpdate(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        if (!canGrowHere(dimension, pos, true)) {
            dimension.breakBlock(pos, null, null);
            return;
        }

        var age = block.getPropertyValue(AGE_16);
        if (age < AGE_16.getMax()) {
            block = block.setPropertyValue(AGE_16, age + 1);
        } else if (age == AGE_16.getMax()) {
            block = block.setPropertyValue(AGE_16, 0);
            if (canGrowHere(dimension, pos, false)) {
                // The basal support check limits which segment grows, while this two-position
                // scan bounds extension above that segment without measuring a separate column height.
                for (var y = 1; y < 3; y++) {
                    var blockType = dimension.getBlockState(pos.x(), pos.y() + y, pos.z()).getBlockType();
                    if (blockType == BlockTypes.AIR) {
                        dimension.setBlockState(pos.x(), pos.y() + y, pos.z(), BlockTypes.REEDS.getDefaultState());
                        break;
                    } else if (blockType != BlockTypes.REEDS) {
                        break;
                    }
                }
            }
        }

        dimension.setBlockState(pos, block.getBlockState());
    }

    /**
     * Attempts bone-meal extension from the clicked position or the reed immediately below it.
     * A valid nonrecursive basal support check permits writes into each air position one and
     * two blocks above that base.
     *
     * <p>
     * The method can report {@code true} even when those positions already contain reeds or
     * another occupied state, because success follows support rather than a counted placement.
     * It emits particles without consuming bone meal, does not run inherited interaction
     * handling, and does not dispatch a growth event for these writes.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (itemStack.getItemType() != ItemTypes.BONE_MEAL) {
            return false;
        }

        var pos = interactInfo.clickedBlockPos();
        if (dimension.getBlockState(BlockFace.DOWN.offsetPos(pos)).getBlockType() == BlockTypes.REEDS) {
            pos = BlockFace.DOWN.offsetPos(pos);
        }

        if (canGrowHere(dimension, pos, false)) {
            // Bone meal fills both candidate heights without stopping after the first write.
            for (var y = 1; y < 3; y++) {
                if (dimension.getBlockState(pos.x(), pos.y() + y, pos.z()).getBlockType() == BlockTypes.AIR) {
                    dimension.setBlockState(pos.x(), pos.y() + y, pos.z(), BlockTypes.REEDS.getDefaultState());
                }
            }

            dimension.addParticle(MathUtils.center(pos), SimpleParticle.BONE_MEAL);
            return true;
        }

        return false;
    }

    /**
     * Returns whether reeds receive random updates for support and age growth.
     */
    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    /**
     * Returns whether the reed position reaches watered sand- or dirt-tagged ground. A
     * recursive check follows lower reeds until it reaches the basal support. The support then
     * needs water at one of its four horizontal neighbor positions.
     *
     * <p>
     * The nonrecursive form requires that qualifying ground directly below the supplied
     * position, so a segment resting on another reed fails it. Water is read through the
     * liquid lookup, which can recognize contained water as well as a base-layer water block.
     */
    protected boolean canGrowHere(Dimension dimension, Vector3ic pos, boolean recursive) {
        var downBlockType = dimension.getBlockState(BlockFace.DOWN.offsetPos(pos)).getBlockType();
        if (recursive && downBlockType == BlockTypes.REEDS) {
            return canGrowHere(dimension, BlockFace.DOWN.offsetPos(pos), true);
        }

        if (downBlockType.hasBlockTag(BlockTags.SAND) || downBlockType.hasBlockTag(BlockTags.DIRT)) {
            for (var face : BlockFace.getHorizontalBlockFaces()) {
                var liquid = dimension.getLiquid(BlockFace.DOWN.offsetPos(face.offsetPos(pos))).right();
                if (liquid != null && liquid.getBlockType().hasBlockTag(BlockTags.WATER)) {
                    return true;
                }
            }
        }

        return false;
    }
}
