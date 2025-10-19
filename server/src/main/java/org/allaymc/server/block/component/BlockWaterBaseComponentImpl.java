package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.eventbus.event.block.LiquidHardenEvent;
import org.allaymc.api.world.data.DimensionInfo;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.api.world.sound.SimpleSound;
import org.joml.Vector3ic;

import static org.allaymc.api.block.component.BlockLiquidBaseComponent.isSource;
import static org.allaymc.api.block.type.BlockTypes.AIR;

/**
 * @author daoge_cmd
 */
public class BlockWaterBaseComponentImpl extends BlockLiquidBaseComponentImpl {
    public BlockWaterBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean isSameLiquidType(BlockType<?> blockType) {
        return blockType.hasBlockTag(BlockTags.WATER);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face) {
        if (block.getDimension().getDimensionInfo() == DimensionInfo.NETHER) {
            block.getDimension().setLiquid(block.getPosition(), null);
            return;
        }

        super.onNeighborUpdate(block, neighbor, face);
    }

    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        if (oldBlock.getDimension().getDimensionInfo() == DimensionInfo.NETHER) {
            oldBlock.getDimension().setLiquid(oldBlock.getPosition(), null);
            return;
        }

        super.afterPlaced(oldBlock, newBlockState, placementInfo);
    }

    @Override
    public void onReplace(Block block, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.onReplace(block, newBlockState, placementInfo);

        if (!isSource(block.getBlockState())) {
            // Only source block can be moved to layer 1
            return;
        }

        if (block.getLayer() != 0) {
            return;
        }

        var dim = block.getDimension();
        if (newBlockState.getBlockType() != AIR && newBlockState.getBlockStateData().canContainLiquidSource()) {
            // If the old block is water and the new block can contain liquid,
            // we need to move water to layer 1
            dim.setBlockState(block.getPosition(), block.getBlockState(), 1);
        }
    }

    @Override
    public void afterNeighborLayerReplace(Block currentBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterNeighborLayerReplace(currentBlock, newBlockState, placementInfo);

        if (currentBlock.getLayer() != 0 || isSameLiquidType(newBlockState.getBlockType())) {
            return;
        }

        var dim = currentBlock.getDimension();
        var pos = currentBlock.getPosition();
        if (newBlockState.getBlockType() == AIR) {
            if (isSource(dim.getBlockState(pos, 1))) {
                // Move layer 1 water back to layer 0 only when the liquid is a source liquid
                dim.setBlockState(pos, BlockTypes.WATER.getDefaultState(), 0);
            }
            dim.setBlockState(pos, BlockTypes.AIR.getDefaultState(), 1);
            return;
        }

        if (!newBlockState.getBlockStateData().canContainLiquidSource()) {
            // New layer 0 block cannot contain liquid, remove layer 1 water
            dim.setBlockState(pos, BlockTypes.AIR.getDefaultState(), 1);
        }
    }

    @Override
    public void onEntityInside(Block block, Entity entity) {
        if (entity instanceof EntityLiving living && living.isOnFire()) {
            living.extinguish();
            living.getDimension().addParticle(living.getLocation(), SimpleParticle.WHITE_SMOKE);
            living.getDimension().addSound(living.getLocation(), SimpleSound.FIRE_EXTINGUISH);
        }
    }

    @Override
    public boolean tryHarden(Block block, Block flownIntoBy) {
        if (flownIntoBy == null) {
            return false;
        }

        var dimension = block.getDimension();
        // This method also considered BlockTypes.FLOWING_LAVA as the same liquid type
        if (!BlockTypes.LAVA.getBlockBehavior().isSameLiquidType(flownIntoBy.getBlockType())) {
            return false;
        }

        Vector3ic hardenedBlockPosition;
        BlockState hardenedBlockState;
        if (flownIntoBy.getPosition().y() == block.getPosition().y() + 1) {
            hardenedBlockPosition = block.getPosition();
            hardenedBlockState = BlockTypes.STONE.getDefaultState();
        } else {
            hardenedBlockPosition = flownIntoBy.getPosition();
            hardenedBlockState = BlockTypes.COBBLESTONE.getDefaultState();
        }
        var event = new LiquidHardenEvent(flownIntoBy, block.getBlockState(), hardenedBlockState, hardenedBlockPosition);
        if (!event.call()) {
            return false;
        }

        dimension.setBlockState(hardenedBlockPosition, event.getHardenedBlockState());
        flownIntoBy.addSound(SimpleSound.FIZZ);
        return true;
    }

    @Override
    public int getFlowDecay(DimensionInfo dimensionInfo) {
        return 1;
    }

    @Override
    public int getFlowSpeed(DimensionInfo dimensionInfo) {
        return 5;
    }

    @Override
    public boolean canFormSource() {
        return true;
    }
}