package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

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
        Block newBlock = new Block(newBlockState, oldBlock.getPosition());
        if (!lit && isReceivingPower(newBlock)) {
            switchToLit(newBlock);
        }
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face) {
        super.onNeighborUpdate(block, neighbor, face);

        boolean powered = isReceivingPower(block);

        if (lit && !powered) {
            // Schedule turn off with delay (prevents flickering)
            block.getDimension().getBlockUpdateManager().scheduleBlockUpdateInDelay(
                    block.getPosition(), TURN_OFF_DELAY
            );
        } else if (!lit && powered) {
            // Turn on immediately
            switchToLit(block);
        }
    }

    @Override
    public void onScheduledUpdate(Block block) {
        // Only turn off if still not receiving power
        if (lit && !isReceivingPower(block)) {
            switchToUnlit(block);
        }
    }

    /**
     * Checks if the lamp is receiving redstone power from any direction.
     */
    protected boolean isReceivingPower(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();

        for (BlockFace face : BlockFace.values()) {
            Vector3ic neighborPos = face.offsetPos(pos);
            BlockState neighborState = dimension.getBlockState(neighborPos);
            Block neighborBlock = new Block(neighborState, new Position3i(neighborPos, dimension));

            // Check weak power (includes redstone wire signal)
            int weakPower = neighborState.getBehavior().getWeakPower(neighborBlock, face.opposite());
            if (weakPower > 0) {
                return true;
            }

            // Check power through solid blocks (only strong power can conduct through)
            if (neighborState.getBlockStateData().isOpaqueSolid()) {
                int powerThroughBlock = getStrongPowerIntoBlock(dimension, neighborPos, face.opposite());
                if (powerThroughBlock > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets strong power flowing into a solid block.
     * Only strong power can be conducted through solid blocks.
     *
     * @param dimension   the dimension
     * @param blockPos    the solid block position
     * @param excludeFace the face to exclude (pointing back to the lamp)
     */
    protected int getStrongPowerIntoBlock(Dimension dimension, Vector3ic blockPos, BlockFace excludeFace) {
        int maxPower = 0;
        for (BlockFace face : BlockFace.values()) {
            if (face == excludeFace) continue;

            Vector3ic checkPos = face.offsetPos(blockPos);
            BlockState state = dimension.getBlockState(checkPos);
            Block checkBlock = new Block(state, new Position3i(checkPos, dimension));

            // Only strong power can be conducted through solid blocks
            int strongPower = state.getBehavior().getStrongPower(checkBlock, face.opposite());
            maxPower = Math.max(maxPower, strongPower);
        }
        return maxPower;
    }

    protected void switchToLit(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        dimension.setBlockState(pos.x(), pos.y(), pos.z(), BlockTypes.LIT_REDSTONE_LAMP.getDefaultState());
    }

    protected void switchToUnlit(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();
        dimension.setBlockState(pos.x(), pos.y(), pos.z(), BlockTypes.REDSTONE_LAMP.getDefaultState());
    }
}
