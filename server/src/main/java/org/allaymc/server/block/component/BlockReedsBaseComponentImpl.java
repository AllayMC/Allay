package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.NeighborUpdateContext;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.SimpleParticle;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.AGE_16;

/**
 * @author daoge_cmd
 */
public class BlockReedsBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockReedsBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (!canGrowHere(dimension, placeBlockPos, true)) {
            return false;
        }

        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    @Override
    public void onNeighborUpdate(NeighborUpdateContext context) {
        var block = context.block();
        if (!canGrowHere(block.getDimension(), block.getPosition(), true)) {
            block.breakBlock();
        }
    }

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

    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    /**
     * Check if sugar cane can live/grow here.
     *
     * @param dimension the dimension that the sugar cane is in
     * @param pos       the pos of the sugar cane
     * @param recursive whether to check the block below recursively
     * @return {@code true} if sugar cane can live/grow here, {@code false} otherwise
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
