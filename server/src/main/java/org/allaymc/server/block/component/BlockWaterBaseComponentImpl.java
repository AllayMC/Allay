package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.world.DimensionInfo;
import org.cloudburstmc.protocol.bedrock.data.ParticleType;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;

import static org.allaymc.api.block.type.BlockTypes.AIR;

/**
 * @author daoge_cmd
 */
public class BlockWaterBaseComponentImpl extends BlockLiquidBaseComponentImpl {
    public BlockWaterBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onReplace(BlockStateWithPos currentBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.onReplace(currentBlockState, newBlockState, placementInfo);

        if (!isSource(currentBlockState.blockState())) {
            // Only source block can be moved to layer 1
            return;
        }

        if (currentBlockState.layer() != 0) {
            return;
        }

        var dim = currentBlockState.pos().dimension();
        if (newBlockState.getBlockType() != AIR && newBlockState.getBlockStateData().canContainLiquid()) {
            // If the old block is water and the new block can contain liquid,
            // we need to move water to layer 1
            dim.setBlockState(currentBlockState.pos(), BlockTypes.WATER.getDefaultState(), 1);
        }
    }

    @Override
    public void afterNeighborLayerReplace(BlockStateWithPos currentBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterNeighborLayerReplace(currentBlockState, newBlockState, placementInfo);

        if (currentBlockState.layer() != 0 || newBlockState.getBlockType() == BlockTypes.WATER) {
            return;
        }

        var dim = currentBlockState.pos().dimension();
        if (newBlockState.getBlockType() == AIR) {
            // Move layer 1 water back to layer 0
            dim.setBlockState(currentBlockState.pos(), BlockTypes.WATER.getDefaultState(), 0);
            dim.setBlockState(currentBlockState.pos(), BlockTypes.AIR.getDefaultState(), 1);
            return;
        }

        if (!newBlockState.getBlockStateData().canContainLiquid()) {
            // New layer 0 block cannot contain liquid, remove layer 1 water
            dim.setBlockState(currentBlockState.pos(), BlockTypes.AIR.getDefaultState(), 1);
        }
    }

    @Override
    public void onCollideWithEntity(BlockStateWithPos blockStateWithPos, Entity entity) {
        if (entity instanceof EntityDamageComponent damageComponent && damageComponent.getOnFireTicks() > 0) {
            damageComponent.setOnFireTicks(0);
            entity.getDimension().addParticle(entity.getLocation(), ParticleType.WHITE_SMOKE);
            entity.getDimension().addLevelSoundEvent(entity.getLocation(), SoundEvent.EXTINGUISH_FIRE);
        }
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