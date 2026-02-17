package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.SeaGrassType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockGrowEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.SimpleParticle;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.Set;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.SEA_GRASS_TYPE;

/**
 * @author daoge_cmd
 */
public class BlockSeagrassBaseComponentImpl extends BlockBaseComponentImpl {

    public BlockSeagrassBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (!isInWater(dimension, placeBlockPos)) {
            return false;
        }

        var downState = dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos));
        if (!downState.getBlockStateData().isSolid() ||
            downState.getBlockType() == BlockTypes.MAGMA ||
            downState.getBlockType() == BlockTypes.SOUL_SAND) {
            return false;
        }

        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        var seaGrassType = block.getPropertyValue(SEA_GRASS_TYPE);

        // Check water
        if (!isInWater(block.getDimension(), block.getPosition())) {
            block.breakBlock();
            return;
        }

        switch (seaGrassType) {
            case DEFAULT -> {
                if (face == BlockFace.DOWN) {
                    if (!isValidGround(neighbor.getBlockState())) {
                        block.breakBlock();
                    }
                }
            }
            case DOUBLE_BOT -> {
                if (face == BlockFace.DOWN) {
                    if (!isValidGround(neighbor.getBlockState())) {
                        block.breakBlock();
                    }
                } else if (face == BlockFace.UP) {
                    if (!isMatchingHalf(neighbor.getBlockState(), SeaGrassType.DOUBLE_TOP)) {
                        block.breakBlock();
                    }
                }
            }
            case DOUBLE_TOP -> {
                if (face == BlockFace.DOWN) {
                    if (!isMatchingHalf(neighbor.getBlockState(), SeaGrassType.DOUBLE_BOT)) {
                        block.breakBlock();
                    }
                }
            }
        }
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (itemStack == null || itemStack.getItemType() != ItemTypes.BONE_MEAL) {
            return false;
        }

        var pos = interactInfo.clickedBlockPos();
        var blockState = dimension.getBlockState(pos);
        var seaGrassType = blockState.getPropertyValue(SEA_GRASS_TYPE);

        // Only normal seagrass can be grown with bone meal
        if (seaGrassType != SeaGrassType.DEFAULT) {
            return false;
        }

        var abovePos = pos.add(0, 1, 0, new Vector3i());
        if (!isInWater(dimension, abovePos)) {
            return false;
        }

        var aboveBlockType = dimension.getBlockState(abovePos).getBlockType();
        if (aboveBlockType != BlockTypes.AIR && !aboveBlockType.hasBlockTag(BlockTags.WATER)) {
            return false;
        }

        var newState = blockType.getDefaultState().setPropertyValue(SEA_GRASS_TYPE, SeaGrassType.DOUBLE_TOP);
        var event = new BlockGrowEvent(interactInfo.getClickedBlock(), newState);
        if (event.call()) {
            // Set bottom half
            dimension.setBlockState(pos, blockType.getDefaultState().setPropertyValue(SEA_GRASS_TYPE, SeaGrassType.DOUBLE_BOT));
            // Set top half
            dimension.setBlockState(abovePos, event.getNewBlockState());

            interactInfo.player().tryConsumeItemInHand();
            dimension.addParticle(MathUtils.center(pos), SimpleParticle.BONE_MEAL);
            return true;
        }

        return false;
    }

    @Override
    public void onBreak(Block block, ItemStack usedItem, Entity entity) {
        super.onBreak(block, usedItem, entity);

        var seaGrassType = block.getPropertyValue(SEA_GRASS_TYPE);
        var pos = block.getPosition();
        var dimension = block.getDimension();

        // Break the other half of double seagrass
        if (seaGrassType == SeaGrassType.DOUBLE_BOT) {
            var abovePos = pos.add(0, 1, 0, new Vector3i());
            var aboveState = dimension.getBlockState(abovePos);
            if (isMatchingHalf(aboveState, SeaGrassType.DOUBLE_TOP)) {
                dimension.breakBlock(abovePos, null, entity);
            }
        } else if (seaGrassType == SeaGrassType.DOUBLE_TOP) {
            var belowPos = BlockFace.DOWN.offsetPos(pos);
            var belowState = dimension.getBlockState(belowPos);
            if (isMatchingHalf(belowState, SeaGrassType.DOUBLE_BOT)) {
                dimension.breakBlock(belowPos, null, entity);
            }
        }
    }

    @Override
    public boolean isDroppable(Block block, ItemStack usedItem, Entity entity) {
        // Only drop from bottom half or single seagrass
        return block.getPropertyValue(SEA_GRASS_TYPE) != SeaGrassType.DOUBLE_TOP;
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        if (usedItem != null && usedItem.getItemType() == ItemTypes.SHEARS) {
            return super.getDrops(block, usedItem, entity);
        }
        return Set.of();
    }

    protected boolean isInWater(Dimension dimension, Vector3ic pos) {
        var liquid = dimension.getLiquid(pos).right();
        if (liquid != null && liquid.getBlockType().hasBlockTag(BlockTags.WATER)) {
            return true;
        }
        var blockState = dimension.getBlockState(pos);
        return blockState.getBlockType().hasBlockTag(BlockTags.WATER);
    }

    protected boolean isValidGround(BlockState blockState) {
        var blockType = blockState.getBlockType();
        return blockState.getBlockStateData().isSolid() &&
               blockType != BlockTypes.MAGMA &&
               blockType != BlockTypes.SOUL_SAND;
    }

    protected boolean isMatchingHalf(BlockState blockState, SeaGrassType expectedType) {
        return blockState.getBlockType() == BlockTypes.SEAGRASS &&
               blockState.getPropertyValue(SEA_GRASS_TYPE) == expectedType;
    }
}
