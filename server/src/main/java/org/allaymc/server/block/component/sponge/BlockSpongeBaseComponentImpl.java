package org.allaymc.server.block.component.sponge;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.world.particle.BlockBreakParticle;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.component.BlockBaseComponentImpl;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * {@code BlockSpongeBaseComponentImpl} implements dry-sponge water absorption after placement
 * and neighbor changes. Absorption starts only when a directly adjacent base-layer block
 * belongs to the water family, then explores water and air positions using a distance-carrying
 * queue.
 *
 * <p>
 * The queue removes water before extending its frontier and uses count and distance bounds
 * between dequeued entries. All six neighbors of an entry are processed together, so the
 * removal count can pass its nominal limit and water one step beyond the enqueue radius can
 * still be removed. The search does not track visited air positions, and it ignores contained
 * liquid. A positive removal count converts the sponge and emits absorption effects without
 * rollback of earlier water writes.
 *
 * @author daoge_cmd
 */
public class BlockSpongeBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * {@code MAX_REMOVED_WATER_COUNT} bounds whether another queue entry is processed; one
     * entry's six-neighbor scan can exceed this count before rechecking.
     */
    protected static final int MAX_REMOVED_WATER_COUNT = 64;
    /**
     * {@code MAX_ABSORB_DISTANCE} bounds newly enqueued exploration positions, while water
     * adjacent to an entry at that distance can still be removed.
     */
    protected static final int MAX_ABSORB_DISTANCE = 6;

    /**
     * Creates the sponge behavior component for the supplied block type.
     */
    public BlockSpongeBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Dispatches inherited post-placement handling and attempts absorption at the replaced
     * block's position. The absorption helper reads current neighboring states, so the old
     * block snapshot supplies location rather than the newly placed sponge state.
     */
    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);
        tryAbsorbWater(oldBlock);
    }

    /**
     * Dispatches inherited neighbor handling and attempts absorption around the sponge's
     * current position. Every neighboring face can trigger the search, but a directly adjacent
     * base-layer water block is required before exploration begins.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        tryAbsorbWater(block);
    }

    /**
     * Converts the sponge to its wet state after the absorption search reports removed water.
     * Conversion, water-break particles, and the absorption sound follow earlier water-removal
     * writes; failed later writes do not restore those removed blocks.
     */
    protected void tryAbsorbWater(Block center) {
        if (performAbsorbWater(center)) {
            center.getDimension().setBlockState(center.getPosition(), BlockTypes.WET_SPONGE.getDefaultState());
            center.addParticle(new BlockBreakParticle(BlockTypes.WATER.getDefaultState()));
            center.addSound(SimpleSound.SPONGE_ABSORB);
        }
    }

    /**
     * Removes reachable base-layer water through a queue that also traverses air. A directly
     * adjacent water block is required to start. The result reports whether any water-removal
     * call was counted, without verifying those dimension write results.
     *
     * <p>
     * The count limit is checked before each queue entry, not inside its neighbor scan, and
     * the distance limit controls enqueueing rather than removal. Water adjacent to a boundary
     * entry is therefore still removed. No visited set suppresses repeated air paths, and
     * liquid stored in a block's second layer is outside this search.
     */
    protected boolean performAbsorbWater(Block center) {
        if (!hasAdjacentWater(center)) {
            return false;
        }

        Queue<Entry> queue = new ArrayDeque<>();
        queue.add(new Entry(center, 0));

        int removedWaterCount = 0;
        Entry entry;
        // Process one whole six-neighbor frontier entry before testing the count again;
        // the last entry can exceed the nominal removal count, and boundary neighbors can be removed.
        while (removedWaterCount < MAX_REMOVED_WATER_COUNT && (entry = queue.poll()) != null) {
            var currentDistance = entry.distance();

            for (var face : BlockFace.VALUES) {
                var neighbor = entry.block().offsetPos(face);
                var neighborType = neighbor.getBlockType();

                if (neighborType.hasBlockTag(BlockTags.WATER)) {
                    center.getDimension().setBlockState(neighbor.getPosition(), BlockTypes.AIR.getDefaultState());
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

    /**
     * Returns whether a directly adjacent base-layer block carries the water tag.
     */
    private boolean hasAdjacentWater(Block center) {
        for (var face : BlockFace.VALUES) {
            if (center.offsetPos(face).getBlockType().hasBlockTag(BlockTags.WATER)) {
                return true;
            }
        }

        return false;
    }

    /**
     * {@code Entry} represents one pending sponge-search block and its traversal distance.
     * @param block queued block snapshot
     * @param distance steps from sponge
     */
    protected record Entry(Block block, int distance) {
    }
}
