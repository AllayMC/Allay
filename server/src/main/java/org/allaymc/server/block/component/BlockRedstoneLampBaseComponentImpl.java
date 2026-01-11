package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;

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
        if (!lit && oldBlock.isPowered()) {
            switchToLit(oldBlock);
        }
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face) {
        super.onNeighborUpdate(block, neighbor, face);

        var powered = block.isPowered();

        if (lit && !powered) {
            // Schedule turn off with delay (prevents flickering)
            block.scheduleUpdateInDelay(TURN_OFF_DELAY);
        } else if (!lit && powered) {
            // Turn on immediately
            switchToLit(block);
        }
    }

    @Override
    public void onScheduledUpdate(Block block) {
        // Only turn off if still not receiving power
        if (lit && !block.isPowered()) {
            switchToUnlit(block);
        }
    }

    protected void switchToLit(Block block) {
        block.replaceState(BlockTypes.LIT_REDSTONE_LAMP.getDefaultState());
    }

    protected void switchToUnlit(Block block) {
        block.replaceState(BlockTypes.REDSTONE_LAMP.getDefaultState());
    }
}
