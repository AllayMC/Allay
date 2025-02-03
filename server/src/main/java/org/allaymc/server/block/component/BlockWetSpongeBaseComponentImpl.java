package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.DimensionInfo;
import org.cloudburstmc.protocol.bedrock.data.ParticleType;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;

/**
 * @author daoge_cmd
 */
public class BlockWetSpongeBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockWetSpongeBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void afterPlaced(BlockStateWithPos oldBlockState, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlockState, newBlockState, placementInfo);

        if (oldBlockState.dimension().getDimensionInfo() == DimensionInfo.NETHER) {
            oldBlockState.dimension().setBlockState(oldBlockState.pos(), BlockTypes.SPONGE.getDefaultState());
            oldBlockState.dimension().addLevelSoundEvent(MathUtils.center(oldBlockState.pos()), SoundEvent.FIZZ);
            oldBlockState.dimension().addParticle(MathUtils.center(oldBlockState.pos()), ParticleType.EXPLODE);
        }
    }
}
