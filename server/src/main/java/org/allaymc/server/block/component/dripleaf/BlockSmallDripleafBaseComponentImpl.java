package org.allaymc.server.block.component.dripleaf;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.BigDripleafTilt;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.*;

/**
 * {@code BlockSmallDripleafBaseComponentImpl} implements two-block small dripleaf placement,
 * survival, and conversion into big dripleaf. The halves share a cardinal direction and differ
 * by their upper-half flag. Clay supports a lower half directly, while selected dirt-like
 * ground also requires water at the plant position.
 *
 * <p>
 * Placement checks the upper position and writes the lower half before the upper, with no
 * rollback if the second write fails. Neighbor changes enforce the half relationship, and
 * ordinary drops are limited to lower halves broken with shears. Bone meal chooses a
 * two-to-five-block big-dripleaf column, validates its space, then replaces each position in
 * order and consumes the item after those writes.
 *
 * @author daoge_cmd
 */
public class BlockSmallDripleafBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * Creates the small dripleaf behavior component for the supplied block type.
     */
    public BlockSmallDripleafBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places lower and upper small-dripleaf states after survival and upper-space checks. The
     * upper position may be air or water, and both halves use the processed cardinal direction
     * with distinct {@code UPPER_BLOCK_BIT} values.
     *
     * <p>
     * The lower write runs first and short-circuits the upper write on failure. The return
     * value is {@code false} if either write fails, but a successful lower write is not
     * removed when the upper write fails. No separate rollback or growth event coordinates
     * these changes.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (!canSurvive(dimension, placeBlockPos)) {
            return false;
        }

        var abovePos = new Vector3i(placeBlockPos).add(0, 1, 0);
        var aboveState = dimension.getBlockState(abovePos);
        if (aboveState.getBlockType() != BlockTypes.AIR && !aboveState.getBlockType().hasBlockTag(BlockTags.WATER)) {
            return false;
        }

        var processedState = processBlockProperties(blockState, placeBlockPos, placementInfo);
        var lowerState = processedState.setPropertyValue(UPPER_BLOCK_BIT, false);
        var upperState = processedState.setPropertyValue(UPPER_BLOCK_BIT, true);

        // Keep the lower write before the upper write; a rejected lower placement
        // skips the upper half, but a later rejection leaves the lower write intact.
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), lowerState, placementInfo) &&
               dimension.setBlockState(abovePos.x(), abovePos.y(), abovePos.z(), upperState, placementInfo);
    }

    /**
     * Breaks a half whose required counterpart or support disappears. An upper half checks
     * only its lower neighbor; a lower half checks its ground and upper counterpart when those
     * respective faces change.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        var isUpper = block.getPropertyValue(UPPER_BLOCK_BIT);

        if (isUpper) {
            if (face == BlockFace.DOWN) {
                var belowState = neighbor.getBlockState();
                if (belowState.getBlockType() != blockType || belowState.getPropertyValue(UPPER_BLOCK_BIT)) {
                    block.breakBlock();
                }
            }
        } else {
            if (face == BlockFace.DOWN) {
                if (!canSurvive(block.getDimension(), block.getPosition())) {
                    block.breakBlock();
                }
            } else if (face == BlockFace.UP) {
                var aboveState = neighbor.getBlockState();
                if (aboveState.getBlockType() != blockType || !aboveState.getPropertyValue(UPPER_BLOCK_BIT)) {
                    block.breakBlock();
                }
            }
        }
    }

    /**
     * Suppresses ordinary drops from the upper half and delegates lower-half eligibility.
     * Lower halves still obey inherited creative-mode and required-tool checks before {@code
     * getDrops} decides whether the used tool yields an item.
     */
    @Override
    public boolean isDroppable(Block block, ItemStack usedItem, Entity entity) {
        if (block.getPropertyValue(UPPER_BLOCK_BIT)) {
            return false;
        }
        return super.isDroppable(block, usedItem, entity);
    }

    /**
     * Returns one small-dripleaf item when the supplied tool is shears. A {@code null} tool or
     * any other item yields an empty set; the separate eligibility hook restricts ordinary
     * drops to lower halves.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        if (usedItem != null && usedItem.getItemType() == ItemTypes.SHEARS) {
            return Set.of(ItemTypes.SMALL_DRIPLEAF_BLOCK.createItemStack());
        }
        return Set.of();
    }

    /**
     * Converts a small-dripleaf column to big dripleaf after an unhandled bone-meal
     * interaction. The callback finds the bottom position, retains its cardinal direction, and
     * samples a height from two through five. Every target must currently be air, water, or
     * this small-dripleaf type.
     *
     * <p>
     * Accepted space is replaced bottom-up with untilted stems and one head at the top. The
     * callback ignores individual write results, performs no rollback, and does not raise a
     * growth event. It consumes bone meal and emits particles after reaching the replacement
     * sequence.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (itemStack == null || itemStack.getItemType() != ItemTypes.BONE_MEAL) {
            return false;
        }

        var pos = interactInfo.clickedBlockPos();
        var blockState = dimension.getBlockState(pos);

        var bottomPos = new Vector3i(pos);
        if (blockState.getPropertyValue(UPPER_BLOCK_BIT)) {
            bottomPos.add(0, -1, 0);
        }
        while (true) {
            var belowPos = new Vector3i(bottomPos).add(0, -1, 0);
            var belowState = dimension.getBlockState(belowPos);
            if (belowState.getBlockType() == blockType && !belowState.getPropertyValue(UPPER_BLOCK_BIT)) {
                bottomPos.set(belowPos);
            } else {
                break;
            }
        }

        var bottomState = dimension.getBlockState(bottomPos);
        var direction = bottomState.getPropertyValue(MINECRAFT_CARDINAL_DIRECTION);

        // The sampled conversion height controls both clearance validation and the
        // later column writes, so a blocked taller sample does not retry a shorter one.
        var random = ThreadLocalRandom.current();
        int height = random.nextInt(4) + 2;

        for (int i = 0; i < height; i++) {
            var checkPos = new Vector3i(bottomPos).add(0, i, 0);
            var checkState = dimension.getBlockState(checkPos);
            var checkType = checkState.getBlockType();
            if (checkType != BlockTypes.AIR &&
                !checkType.hasBlockTag(BlockTags.WATER) &&
                checkType != blockType) {
                return false;
            }
        }

        // Replace the validated column bottom-up; separate write failures do not roll
        // back earlier segments or prevent consumption after this sequence finishes.
        var bigDripleafType = BlockTypes.BIG_DRIPLEAF;
        for (int i = 0; i < height; i++) {
            var placePos = new Vector3i(bottomPos).add(0, i, 0);
            BlockState newState;
            if (i < height - 1) {
                newState = bigDripleafType.getDefaultState()
                        .setPropertyValue(BIG_DRIPLEAF_HEAD, false)
                        .setPropertyValue(BIG_DRIPLEAF_TILT, BigDripleafTilt.NONE)
                        .setPropertyValue(MINECRAFT_CARDINAL_DIRECTION, direction);
            } else {
                newState = bigDripleafType.getDefaultState()
                        .setPropertyValue(BIG_DRIPLEAF_HEAD, true)
                        .setPropertyValue(BIG_DRIPLEAF_TILT, BigDripleafTilt.NONE)
                        .setPropertyValue(MINECRAFT_CARDINAL_DIRECTION, direction);
            }
            dimension.setBlockState(placePos, newState);
        }

        interactInfo.player().tryConsumeItemInHand();
        dimension.addParticle(MathUtils.center(pos), SimpleParticle.BONE_MEAL);
        return true;
    }

    /**
     * Returns whether the lower state and local water support small dripleaf. Clay always
     * qualifies. Grass, dirt, rooted dirt, and moss qualify only when {@code pos} contains
     * water, while a matching lower half can support another half.
     *
     * <p>
     * The method checks the supporting block type before consulting water, so wet unsupported
     * ground does not qualify and dry clay still does. This predicate also serves placement,
     * where the plant position may still contain a water block rather than a waterlogged
     * dripleaf state.
     */
    protected boolean canSurvive(Dimension dimension, Vector3ic pos) {
        var belowPos = BlockFace.DOWN.offsetPos(pos);
        var belowState = dimension.getBlockState(belowPos);
        var belowType = belowState.getBlockType();

        if (belowType == BlockTypes.CLAY) {
            return true;
        }

        if (belowType == BlockTypes.GRASS_BLOCK ||
            belowType == BlockTypes.DIRT ||
            belowType == BlockTypes.DIRT_WITH_ROOTS ||
            belowType == BlockTypes.MOSS_BLOCK) {
            return isInWater(dimension, pos);
        }

        if (belowType == blockType && !belowState.getPropertyValue(UPPER_BLOCK_BIT)) {
            return true;
        }

        return false;
    }

    /**
     * Returns whether either the liquid lookup or the base block at {@code pos} belongs to the
     * water family. The second check also recognizes a water block before dripleaf placement
     * replaces its base-layer state.
     */
    protected boolean isInWater(Dimension dimension, Vector3ic pos) {
        var liquid = dimension.getLiquid(pos).right();
        if (liquid != null && liquid.getBlockType().hasBlockTag(BlockTags.WATER)) {
            return true;
        }
        var blockState = dimension.getBlockState(pos);
        return blockState.getBlockType().hasBlockTag(BlockTags.WATER);
    }
}
