package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.server.block.RedstoneHelper;

import java.time.Duration;

/**
 * Implementation of the redstone lamp block.
 * Lights up when receiving redstone power.
 *
 * @author daoge_cmd
 */
public class BlockRedstoneLampBaseComponentImpl extends BlockBaseComponentImpl {

    protected static final Duration TURN_OFF_DELAY = Duration.ofMillis(200); // 4 ticks

    protected final boolean lit;

    public BlockRedstoneLampBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, boolean lit) {
        super(blockType);
        this.lit = lit;
    }

    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);

        // Check if should be lit immediately after placement
        if (!lit && RedstoneHelper.isPoweredAt(oldBlock.getPosition())) {
            switchToLit(oldBlock.getPosition());
        }
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face) {
        super.onNeighborUpdate(block, neighbor, face);

        boolean powered = RedstoneHelper.isPoweredAt(block.getPosition());

        if (lit && !powered) {
            // Schedule turn off with delay (prevents flickering)
            block.getDimension().getBlockUpdateManager().scheduleBlockUpdateInDelay(
                    block.getPosition(), TURN_OFF_DELAY
            );
        } else if (!lit && powered) {
            // Turn on immediately
            switchToLit(block.getPosition());
        }
    }

    @Override
    public void onScheduledUpdate(Block block) {
        // Only turn off if still not receiving power
        if (lit && !RedstoneHelper.isPoweredAt(block.getPosition())) {
            switchToUnlit(block.getPosition());
        }
    }

    protected void switchToLit(Position3ic pos) {
        pos.dimension().setBlockState(pos.x(), pos.y(), pos.z(), BlockTypes.LIT_REDSTONE_LAMP.getDefaultState());
    }

    protected void switchToUnlit(Position3ic pos) {
        pos.dimension().setBlockState(pos.x(), pos.y(), pos.z(), BlockTypes.REDSTONE_LAMP.getDefaultState());
    }
}
