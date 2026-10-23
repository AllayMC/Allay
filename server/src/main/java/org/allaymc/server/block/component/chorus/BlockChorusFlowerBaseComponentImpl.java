package org.allaymc.server.block.component.chorus;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.utils.tuple.Pair;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.AGE_6;

/**
 * {@code BlockChorusFlowerBaseComponentImpl} implements chorus-flower support, stem-sensitive
 * growth, branching, and terminal aging. Flowers accept end stone or chorus plants beneath
 * them, and random updates examine both the flower's age and the nearby stem before choosing
 * vertical growth or horizontal attempts.
 *
 * <p>
 * The stem scan tracks a bounded downward height and whether a lower segment has a horizontal
 * plant neighbor. Those observations affect vertical probability and branch-attempt sampling.
 * Growth converts the old flower to a plant before writing a new flower with adjusted age,
 * while an unsuccessful update sets the terminal age. These direct writes are not mediated by
 * a growth event or rollback, and placement without player information follows the inherited
 * path.
 *
 * @author IWareQ
 */
public class BlockChorusFlowerBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * {@code MAX_STEM_HEIGHT} bounds the downward stem scan and disables upward growth once
     * that height is reached.
     */
    private static final int MAX_STEM_HEIGHT = 5;

    /**
     * Creates the chorus flower behavior component for the supplied block type.
     */
    public BlockChorusFlowerBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Dispatches the base neighbor hook and breaks a flower after invalid lower support
     * changes. Only a change from the lower face runs this support check; the accepted support
     * types are end stone and chorus plant.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (face == BlockFace.DOWN && !canBeSupportedAt(block.offsetPos(BlockFace.DOWN).getBlockState())) {
            block.breakBlock();
        }
    }

    /**
     * Chooses upward growth, horizontal branching, or terminal aging for a nonterminal flower.
     * Stem height and branch presence determine upward probability, while age limits which
     * growth paths remain available. The upward path checks horizontal air around the position
     * above before extending.
     *
     * <p>
     * Horizontal directions are shuffled, and the sampled attempt budget is decremented before
     * each attempted direction. Each accepted branch converts the current position and writes
     * a flower one age older; if no path grows, the current flower receives maximum age. These
     * operations use direct world writes and do not dispatch cancellable growth events.
     */
    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        var currentAge = block.getPropertyValue(AGE_6);
        if (currentAge >= AGE_6.getMax()) {
            return;
        }

        var stemInfo = findStem(block);
        var stemHeight = stemInfo.left();
        var hasBranch = stemInfo.right();

        // Older flowers lose upward extension before reaching terminal age.
        if (currentAge < 5 && shouldGrowUpward(stemHeight, hasBranch) && isSurroundingAir(block.offsetPos(BlockFace.UP), null)) {
            growInDirection(block, BlockFace.UP, 0);
            return;
        }

        // Horizontal branching stops earlier than the upward-growth path.
        if (currentAge < 4) {
            // Existing branching reduces the sampled budget; the pre-attempt decrement
            // below means the number of inspected directions is smaller than that budget.
            var maxAttempts = ThreadLocalRandom.current().nextInt(hasBranch ? 0 : 1, (hasBranch ? 3 : 4) + 1);

            List<BlockFace> directions = new ArrayList<>(List.of(BlockFace.getHorizontalBlockFaces()));
            Collections.shuffle(directions);

            boolean grew = false;
            for (var face : directions) {
                maxAttempts--;
                if (maxAttempts <= 0) {
                    break;
                }

                var side = block.offsetPos(face);
                if (canBranchTo(side, face)) {
                    growInDirection(block, face, 1);
                    grew = true;
                }
            }

            if (grew) {
                block.getDimension().setBlockState(block.getPosition(), BlockTypes.CHORUS_PLANT.getDefaultState());
                return;
            }
        }

        // Terminal age prevents further random growth after every available path fails.
        block.getDimension().setBlockState(block.getPosition(), block.getBlockState().setPropertyValue(AGE_6, AGE_6.getMax()));
    }

    /**
     * Places a chorus flower on an upper face of end stone or chorus plant. A {@code null}
     * {@code placementInfo} delegates to inherited placement instead of applying the player
     * support and face checks.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return super.place(dimension, blockState, placeBlockPos, null);
        }

        if (canBeSupportedAt(placementInfo.getClickedBlock().getBlockState()) && placementInfo.blockFace() == BlockFace.UP) {
            return dimension.setBlockState(placeBlockPos, blockState);
        }

        return false;
    }

    /**
     * Returns whether chorus flowers receive random updates for branch growth.
     */
    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    /**
     * Returns whether end stone or chorus plant supplies flower support.
     */
    protected boolean canBeSupportedAt(BlockState blockState) {
        return blockState.getBlockType() == BlockTypes.END_STONE || blockState.getBlockType() == BlockTypes.CHORUS_PLANT;
    }

    /**
     * Samples upward growth from the measured stem height and branch flag. Heights zero and
     * one always qualify, heights two through four use decreasing probabilities, and height
     * five or more never qualifies.
     *
     * <p>
     * An existing branch lowers each eligible taller-stem probability and removes upward
     * growth entirely at height four. This method only selects a growth path; the caller
     * separately checks flower age and air around the proposed upper position before
     * performing the world writes.
     */
    protected boolean shouldGrowUpward(int stemHeight, boolean hasBranch) {
        if (stemHeight >= MAX_STEM_HEIGHT) {
            return false;
        }

        // Taller stems receive less upward growth, and existing lateral branches
        // reduce it further before the caller considers a new horizontal branch.
        var chance = switch (stemHeight) {
            case 0, 1 -> 1f; // 100% chance for short stems
            case 2 -> hasBranch ? 0.5f : 0.6f;
            case 3 -> hasBranch ? 0.25f : 0.4f;
            case 4 -> hasBranch ? 0f : 0.2f;
            default -> 0f; // No growth beyond max
        };

        return ThreadLocalRandom.current().nextFloat() < chance;
    }

    /**
     * Returns whether a horizontal branch target and the block below it are air. Surrounding
     * horizontal blocks must also be air except toward the originating flower, which is
     * excluded using {@code fromFace.opposite()}.
     */
    protected boolean canBranchTo(Block side, BlockFace fromFace) {
        return side.getBlockType() == BlockTypes.AIR &&
               side.offsetPos(BlockFace.DOWN).getBlockType() == BlockTypes.AIR &&
               isSurroundingAir(side, fromFace.opposite());
    }

    /**
     * Returns whether all checked horizontal neighbors are air around {@code current}. {@code
     * ignore} may be {@code null} to check every side or a face excluded for an attached
     * branch.
     */
    protected boolean isSurroundingAir(Block current, BlockFace ignore) {
        for (var face : BlockFace.getHorizontalBlockFaces()) {
            if (face == ignore) {
                continue;
            }

            if (current.offsetPos(face).getBlockType() != BlockTypes.AIR) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns the bounded count of contiguous chorus plants below the flower and a
     * branch-presence flag. A branch is any horizontal chorus-plant neighbor of a scanned
     * lower segment; the scan stops at the first other block or {@code MAX_STEM_HEIGHT}.
     */
    protected Pair<Integer, Boolean> findStem(Block current) {
        int stemHeight = 0;
        boolean hasBranch = false;
        var down = current;

        for (int i = 0; i < MAX_STEM_HEIGHT; i++) {
            down = down.offsetPos(BlockFace.DOWN);
            if (down.getBlockType() != BlockTypes.CHORUS_PLANT) {
                break;
            }

            stemHeight++;

            for (var face : BlockFace.getHorizontalBlockFaces()) {
                if (down.offsetPos(face).getBlockType() == BlockTypes.CHORUS_PLANT) {
                    hasBranch = true;
                    break;
                }
            }
        }

        return new Pair<>(stemHeight, hasBranch);
    }

    /**
     * Converts the current flower into a plant and writes a new flower in {@code face}'s
     * direction. The new flower derives its age from the original snapshot plus {@code
     * addingAge}, capped at the property's maximum.
     *
     * <p>
     * The old-position conversion happens before the destination write and grow sound, and
     * there is no rollback or growth-event cancellation in this helper. Callers must establish
     * their intended target and surrounding-space conditions before invoking it; the helper
     * itself does not verify that the destination is air.
     */
    protected void growInDirection(Block current, BlockFace face, int addingAge) {
        // Convert the originating flower before creating its continuation. Both writes
        // are independent, so this helper cannot restore the flower after a rejected destination write.
        current.getDimension().setBlockState(current.getPosition(), BlockTypes.CHORUS_PLANT.getDefaultState());

        var newPos = current.offsetPos(face).getPosition();
        var newBlockState = current.getBlockState();
        var currentAge = newBlockState.getPropertyValue(AGE_6);
        newBlockState = newBlockState.setPropertyValue(AGE_6, Math.min(AGE_6.getMax(), currentAge + addingAge));
        current.getDimension().setBlockState(newPos, newBlockState);
        current.getDimension().addSound(newPos, SimpleSound.CHORUS_FLOWER_GROW);
    }
}
