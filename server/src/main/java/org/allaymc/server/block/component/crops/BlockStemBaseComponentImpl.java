package org.allaymc.server.block.component.crops;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockGrowEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.data.BlockId;
import org.allaymc.server.item.data.ItemId;
import org.joml.Vector3ic;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.FACING_DIRECTION;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.GROWTH;

/**
 * @author daoge_cmd
 */
public class BlockStemBaseComponentImpl extends BlockCropsBaseComponentImpl {
    protected final BlockId fruitId;
    protected final ItemId seedsId;

    public BlockStemBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, BlockId fruitId, ItemId seedsId) {
        super(blockType);
        this.fruitId = fruitId;
        this.seedsId = seedsId;
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        if (face == BlockFace.DOWN && neighbor.getBlockType() != BlockTypes.FARMLAND) {
            block.breakBlock();
            return;
        }

        var stemFace = Objects.requireNonNull(BlockFace.fromIndex(block.getPropertyValue(FACING_DIRECTION)));
        if (stemFace == BlockFace.DOWN || stemFace == BlockFace.UP) {
            // No fruit block is connected to the stem,
            // so we don't need to check if the fruit block
            // is still exists
            return;
        }

        var fruitBlock = block.offsetPos(stemFace);
        if (fruitBlock.getBlockType() != fruitId.getBlockType()) {
            // Fruit block is not connected to the stem,
            // so reset the stem direction to BlockFace.DOWN
            block.getDimension().setBlockState(block.getPosition(), block.getBlockState().setPropertyValue(FACING_DIRECTION, BlockFace.DOWN.ordinal()));
        }
    }

    @Override
    public void onRandomUpdate(Block block) {
        if (ThreadLocalRandom.current().nextFloat() <= calculateGrowthChance(block) &&
            block.getDimension().getLightEngine().getInternalLight(block.getPosition()) >= 8) {
            var growth = block.getPropertyValue(GROWTH);
            if (growth < GROWTH.getMax()) {
                var newCrop = block.setPropertyValue(GROWTH, growth + 1);
                var event = new BlockGrowEvent(block, newCrop.getBlockState());
                if (event.call()) {
                    block.getDimension().setBlockState(block.getPosition(), event.getNewBlockState());
                }
                return;
            }

            for (var face : BlockFace.getHorizontalBlockFaces()) {
                if (block.offsetPos(face).getBlockType() == fruitId.getBlockType()) {
                    // Fruit block already exists
                    return;
                }
            }

            var face = BlockFace.getHorizontalBlockFaces()[ThreadLocalRandom.current().nextInt(4)];
            var fruitBlock = block.offsetPos(face);
            if (fruitBlock.getBlockType() == BlockTypes.AIR) {
                var downBlockType = fruitBlock.offsetPos(BlockFace.DOWN).getBlockType();
                if (downBlockType != BlockTypes.FARMLAND &&
                    downBlockType != BlockTypes.DIRT &&
                    downBlockType != BlockTypes.GRASS_BLOCK) {
                    return;
                }

                var event = new BlockGrowEvent(
                        new Block(BlockTypes.AIR.getDefaultState(), new Position3i(fruitBlock.getPosition(), block.getDimension())),
                        fruitId.getBlockType().getDefaultState()
                );
                if (event.call()) {
                    // Melon block can only be placed on farmland, dirt, or grass block
                    // Update stem direction
                    block.getDimension().setBlockState(block.getPosition(), block.getBlockState().setPropertyValue(FACING_DIRECTION, face.ordinal()));
                    // Place melon block
                    block.getDimension().setBlockState(fruitBlock.getPosition(), event.getNewBlockState());
                }
            }
        }
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo.blockFace() != BlockFace.UP ||
            placementInfo.getClickedBlock().getBlockType() != BlockTypes.FARMLAND) {
            return false;
        }

        // Do not use processBlockProperties here, because the FACING_DIRECTION property should keep
        // the default value (BlockFace.DOWN) rather than facing to the player
        return dimension.setBlockState(
                placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                blockState, placementInfo
        );
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(seedsId.getItemType().createItemStack(1));
    }
}
