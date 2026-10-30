package org.allaymc.server.block.component.sponge;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.component.BlockBaseComponentImpl;

/**
 * {@code BlockWetSpongeBaseComponentImpl} implements immediate drying of a placed wet sponge
 * in dimensions that evaporate water. Post-placement handling first notifies inherited
 * components, then writes a dry sponge and emits fizz and explosion particles when the
 * dimension type enables that evaporation rule.
 *
 * @author daoge_cmd
 */
public class BlockWetSpongeBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the wet sponge behavior component for the supplied block type.
     */
    public BlockWetSpongeBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Dispatches inherited post-placement handling and dries the sponge in a water-evaporating
     * dimension. Other dimensions retain wet sponge; the dry-state write precedes fizz and
     * particles without checking that write's result.
     */
    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);

        var dimension = oldBlock.getDimension();
        if (dimension.getDimensionType().waterEvaporates()) {
            dimension.setBlockState(oldBlock.getPosition(), BlockTypes.SPONGE.getDefaultState());
            oldBlock.addSound(SimpleSound.FIZZ);
            oldBlock.addParticle(SimpleParticle.EXPLODE);
        }
    }
}
