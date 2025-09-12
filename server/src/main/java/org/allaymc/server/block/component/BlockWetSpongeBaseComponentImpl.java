package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.world.data.DimensionInfo;
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
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);

        var dimension = oldBlock.getDimension();
        if (dimension.getDimensionInfo() == DimensionInfo.NETHER) {
            dimension.setBlockState(oldBlock.getPosition(), BlockTypes.SPONGE.getDefaultState());
            oldBlock.addLevelSoundEvent(SoundEvent.FIZZ);
            oldBlock.addParticle(ParticleType.EXPLODE);
        }
    }
}
