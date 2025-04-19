package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.tag.BlockCustomTags;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.eventbus.event.block.LiquidHardenEvent;
import org.allaymc.api.world.DimensionInfo;
import org.cloudburstmc.protocol.bedrock.data.ParticleType;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;

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
        return blockType.hasBlockTag(BlockCustomTags.WATER);
    }

    @Override
    public void onNeighborUpdate(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        if (current.getDimension().getDimensionInfo() == DimensionInfo.NETHER) {
            current.getDimension().setLiquid(current.getPos(), null);
            return;
        }

        super.onNeighborUpdate(current, neighbor, face);
    }

    @Override
    public void afterPlaced(BlockStateWithPos oldBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        if (oldBlockState.getDimension().getDimensionInfo() == DimensionInfo.NETHER) {
            oldBlockState.getDimension().setLiquid(oldBlockState.getPos(), null);
            return;
        }

        super.afterPlaced(oldBlockState, newBlockState, placementInfo);
    }

    @Override
    public void onReplace(BlockStateWithPos currentBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.onReplace(currentBlockState, newBlockState, placementInfo);

        if (!isSource(currentBlockState)) {
            // Only source block can be moved to layer 1
            return;
        }

        if (currentBlockState.getLayer() != 0) {
            return;
        }

        var dim = currentBlockState.getDimension();
        if (newBlockState.getBlockType() != AIR && newBlockState.getBlockStateData().canContainLiquidSource()) {
            // If the old block is water and the new block can contain liquid,
            // we need to move water to layer 1
            dim.setBlockState(currentBlockState.getPos(), currentBlockState, 1);
        }
    }

    @Override
    public void afterNeighborLayerReplace(BlockStateWithPos currentBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterNeighborLayerReplace(currentBlockState, newBlockState, placementInfo);

        if (currentBlockState.getLayer() != 0 || isSameLiquidType(newBlockState.getBlockType())) {
            return;
        }

        var dim = currentBlockState.getDimension();
        var pos = currentBlockState.getPos();
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
    public void onCollideWithEntity(BlockStateWithPos current, Entity entity) {
        if (entity instanceof EntityDamageComponent damageComponent && damageComponent.getOnFireTicks() > 0) {
            damageComponent.setOnFireTicks(0);
            entity.getDimension().addParticle(entity.getLocation(), ParticleType.WHITE_SMOKE);
            entity.getDimension().addLevelSoundEvent(entity.getLocation(), SoundEvent.EXTINGUISH_FIRE);
        }
    }

    @Override
    public boolean tryHarden(BlockStateWithPos current, BlockStateWithPos flownIntoBy) {
        if (flownIntoBy == null) {
            return false;
        }

        var dimension = current.getDimension();
        // This method also considered BlockTypes.FLOWING_LAVA as the same liquid type
        if (!BlockTypes.LAVA.getBlockBehavior().isSameLiquidType(flownIntoBy.getBlockType())) {
            return false;
        }

        BlockState hardenedBlockState;
        if (flownIntoBy.getPos().y() == current.getPos().y() + 1) {
            hardenedBlockState = BlockTypes.STONE.getDefaultState();
        } else {
            hardenedBlockState = BlockTypes.COBBLESTONE.getDefaultState();
        }
        var event = new LiquidHardenEvent(flownIntoBy, current, hardenedBlockState);
        if (!event.call()) {
            return false;
        }

        dimension.setBlockState(flownIntoBy.getPos(), event.getHardenedBlockState());
        flownIntoBy.addLevelSoundEvent(SoundEvent.FIZZ);
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