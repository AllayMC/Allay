package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.dto.NeighborUpdate;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.AGE_16;

/**
 * @author daoge_cmd
 */
public class BlockCactusBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockCactusBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
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
    public void onCollideWithEntity(Block block, Entity entity) {
        if (entity instanceof EntityLiving living) {
            living.attack(DamageContainer.contact(0.5f));
        }
    }

    @Override
    public void onNeighborUpdate(NeighborUpdate context) {
        var block = context.block();
        if (!canGrowHere(block.getDimension(), block.getPosition(), true)) {
            block.breakBlock();
        }
    }

    @Override
    public void onRandomUpdate(Block block) {
        var dimension = block.getDimension();
        var age = block.getPropertyValue(AGE_16);
        if (age < AGE_16.getMax()) {
            block = block.setPropertyValue(AGE_16, age + 1);
        } else if (age == AGE_16.getMax()) {
            block = block.setPropertyValue(AGE_16, 0);
            if (canGrowHere(dimension, block.getPosition(), false)) {
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
     * Check if cactus can live/grow here.
     *
     * @param dimension the dimension that the cactus is in
     * @param pos       the pos of the cactus
     * @param recursive whether to check the block below recursively
     * @return {@code true} if cactus can live/grow here, {@code false} otherwise
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

    @Override
    public boolean canRandomUpdate() {
        return true;
    }
}
