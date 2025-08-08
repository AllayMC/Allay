package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.tag.BlockCustomTags;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.cloudburstmc.protocol.bedrock.data.LevelEvent;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author daoge_cmd
 */
public class BlockSpongeBaseComponentImpl extends BlockBaseComponentImpl {
    protected static final int MAX_REMOVED_WATER_COUNT = 64;
    protected static final int MAX_ABSORB_DISTANCE = 6;

    public BlockSpongeBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);
        tryAbsorbWater(oldBlock);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face) {
        super.onNeighborUpdate(block, neighbor, face);
        tryAbsorbWater(block);
    }

    protected void tryAbsorbWater(Block center) {
        if (performAbsorbWater(center)) {
            center.getDimension().setBlockState(center.getPos(), BlockTypes.WET_SPONGE.getDefaultState());
            center.addLevelEvent(LevelEvent.PARTICLE_DESTROY_BLOCK, BlockTypes.WATER.getDefaultState().blockStateHash());
            center.addLevelSoundEvent(SoundEvent.SPONGE_ABSORB);
        }
    }

    protected boolean performAbsorbWater(Block center) {
        if (!hasAdjacentWater(center)) {
            return false;
        }

        Queue<Entry> queue = new ArrayDeque<>();
        queue.add(new Entry(center, 0));

        int removedWaterCount = 0;
        while (!queue.isEmpty() && removedWaterCount < MAX_REMOVED_WATER_COUNT) {
            var entry = queue.poll();
            var currentDistance = entry.distance();

            for (var face : BlockFace.values()) {
                var neighbor = entry.block().offsetPos(face);
                var neighborType = neighbor.getBlockType();

                if (neighborType.hasBlockTag(BlockCustomTags.WATER)) {
                    center.getDimension().setBlockState(neighbor.getPos(), BlockTypes.AIR.getDefaultState());
                    removedWaterCount++;
                    if (currentDistance < MAX_ABSORB_DISTANCE) {
                        queue.add(new Entry(neighbor, currentDistance + 1));
                    }
                } else if (neighborType == BlockTypes.AIR) {
                    if (currentDistance < MAX_ABSORB_DISTANCE) {
                        queue.add(new Entry(neighbor, currentDistance + 1));
                    }
                }
            }
        }

        return removedWaterCount > 0;
    }

    private boolean hasAdjacentWater(Block center) {
        for (var face : BlockFace.values()) {
            if (center.offsetPos(face).getBlockType().hasBlockTag(BlockCustomTags.WATER)) {
                return true;
            }
        }

        return false;
    }

    protected record Entry(Block block, int distance) {
    }
}
