package org.allaymc.server.block.component.bamboo;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.BambooLeafSize;
import org.allaymc.api.block.property.enums.BambooStalkThickness;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockGrowEvent;
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
 * {@code BlockBambooBaseComponentImpl} implements bamboo-column placement, growth, and
 * appearance updates. A new stalk on ordinary valid ground becomes a sapling, while placement
 * on existing bamboo chooses stalk thickness, leaves, and an age flag from the column beneath
 * it.
 *
 * <p>
 * Natural growth is gated by age, randomness, and light, whereas bone meal finds the column
 * top and attempts growth without those gates. Accepted growth writes a new top before
 * updating lower leaves and propagating thick stalks downward. A sixteen-segment limit bounds
 * further extension, and the age flag can stop random growth earlier. Breaking a segment
 * clears the lower bamboo's age flag so the surviving column can grow again.
 *
 * @author daoge_cmd
 */
public class BlockBambooBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the bamboo behavior component for the supplied block type.
     */
    public BlockBambooBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places bamboo or a sapling according to the supporting column. Player placement requires
     * the upper face; ordinary valid ground receives a sapling, and an existing sapling gives
     * the new stalk thin thickness and small leaves.
     *
     * <p>
     * Placement above bamboo derives thickness and leaves from lower segments, updates the
     * lower leaves, and propagates thick stalks before the final placement call. Height can
     * also set the new age flag. These earlier appearance writes are separate world changes
     * and are not rolled back if the final placement fails.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null && placementInfo.blockFace() != BlockFace.UP) {
            return false;
        }

        var downState = dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos));
        var downType = downState.getBlockType();

        if (downType != BlockTypes.BAMBOO && downType != BlockTypes.BAMBOO_SAPLING) {
            if (!BlockBambooSaplingBaseComponentImpl.canSupportBamboo(dimension, placeBlockPos)) {
                return false;
            }
            return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                    BlockTypes.BAMBOO_SAPLING.getDefaultState(), placementInfo);
        }

        if (downType == BlockTypes.BAMBOO_SAPLING) {
            blockState = blockState
                    .setPropertyValue(BAMBOO_LEAF_SIZE, BambooLeafSize.SMALL_LEAVES)
                    .setPropertyValue(BAMBOO_STALK_THICKNESS, BambooStalkThickness.THIN);
        } else {
            var thickness = determineThickness(dimension, placeBlockPos);
            var leafSize = determineLeafSize(dimension, placeBlockPos, thickness);
            blockState = blockState
                    .setPropertyValue(BAMBOO_STALK_THICKNESS, thickness)
                    .setPropertyValue(BAMBOO_LEAF_SIZE, leafSize);

            updateLeavesBelow(dimension, BlockFace.DOWN.offsetPos(placeBlockPos));

            if (thickness == BambooStalkThickness.THICK) {
                updateThicknessBelow(dimension, BlockFace.DOWN.offsetPos(placeBlockPos));
            }

            int height = countBambooHeight(dimension, placeBlockPos);
            if (shouldStopGrowing(height)) {
                blockState = blockState.setPropertyValue(AGE_BIT, true);
            }
        }

        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    /**
     * Dispatches the base neighbor hook and checks lower support changes. Bamboo or a bamboo
     * sapling directly below remains valid; otherwise the shared ground-support predicate
     * decides whether the current bamboo breaks.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (face == BlockFace.DOWN) {
            var downType = neighbor.getBlockType();
            if (downType != BlockTypes.BAMBOO && downType != BlockTypes.BAMBOO_SAPLING &&
                !BlockBambooSaplingBaseComponentImpl.canSupportBamboo(block.getDimension(), block.getPosition())) {
                block.breakBlock();
            }
        }
    }

    /**
     * Performs inherited breaking effects, then clears the age flag on bamboo immediately
     * below. The lower segment can subsequently pass the natural-growth age gate; a sapling or
     * other lower block is left unchanged.
     */
    @Override
    public void onBreak(Block block, ItemStack usedItem, Entity entity) {
        super.onBreak(block, usedItem, entity);

        var belowPos = BlockFace.DOWN.offsetPos(block.getPosition());
        var belowState = block.getDimension().getBlockState(belowPos);
        if (belowState.getBlockType() == BlockTypes.BAMBOO) {
            var newState = belowState.setPropertyValue(AGE_BIT, false);
            block.getDimension().setBlockState(belowPos, newState);
        }
    }

    /**
     * Returns whether bamboo receives random updates for natural column growth.
     */
    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    /**
     * Attempts natural bamboo growth after dispatching the inherited random-update hook. An
     * aged segment stops immediately, and the one-third random gate and internal light of at
     * least nine above the segment must both pass before {@code growFromTop} examines the
     * column.
     */
    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        if (block.getPropertyValue(AGE_BIT)) {
            return;
        }

        // Throttle natural growth independently of successful bone-meal interactions.
        if (ThreadLocalRandom.current().nextInt(3) != 0) {
            return;
        }

        var abovePos = block.getPosition().add(0, 1, 0, new Vector3i());
        // Natural extension measures light above the triggering segment.
        if (block.getDimension().getLightEngine().getInternalLight(abovePos) < 9) {
            return;
        }

        growFromTop(block.getDimension(), block.getPosition());
    }

    /**
     * Attempts bone-meal growth at the highest bamboo segment after inherited interaction
     * handling. A column of sixteen segments rejects the interaction; successful growth
     * consumes the held item and emits bone-meal particles at the clicked position.
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
        var topPos = findTopBamboo(dimension, pos);
        int totalHeight = countTotalHeight(dimension, topPos);
        // Bone meal observes the same sixteen-segment extension limit as growth.
        if (totalHeight >= 16) {
            return false;
        }

        if (growFromTop(dimension, topPos)) {
            interactInfo.player().tryConsumeItemInHand();
            dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);
            return true;
        }

        return false;
    }

    /**
     * Returns one bamboo item when this stalk segment is broken.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.BAMBOO.createItemStack(1));
    }

    /**
     * Attempts one new bamboo segment above the column containing {@code pos}. The highest
     * segment must have air above, and the complete column including a sapling must remain
     * below sixteen segments. Thickness, leaves, and the age flag are computed before a
     * cancellable growth event.
     *
     * <p>
     * Acceptance places the event's state above the old top, then updates lower leaves and
     * optionally propagates thick stalks. The return value reports event acceptance after
     * those calls; the helper does not inspect dimension write results or undo earlier writes.
     * Rejection leaves the column unchanged by this helper.
     */
    protected boolean growFromTop(Dimension dimension, Vector3ic pos) {
        var topPos = findTopBamboo(dimension, pos);

        var abovePos = topPos.add(0, 1, 0, new Vector3i());
        if (dimension.getBlockState(abovePos).getBlockType() != BlockTypes.AIR) {
            return false;
        }

        int totalHeight = countTotalHeight(dimension, topPos);
        // Include a basal sapling in the height limit before selecting the new top.
        if (totalHeight >= 16) {
            return false;
        }

        var thickness = determineThickness(dimension, abovePos);
        var leafSize = determineLeafSize(dimension, abovePos, thickness);
        var newState = BlockTypes.BAMBOO.getDefaultState()
                .setPropertyValue(BAMBOO_STALK_THICKNESS, thickness)
                .setPropertyValue(BAMBOO_LEAF_SIZE, leafSize);

        if (shouldStopGrowing(totalHeight + 1)) {
            newState = newState.setPropertyValue(AGE_BIT, true);
        }

        var event = new BlockGrowEvent(
                new Block(dimension.getBlockState(topPos), new org.allaymc.api.math.position.Position3i(topPos, dimension)),
                newState
        );
        if (event.call()) {
            // Update existing stalk appearance only after the growth proposal is accepted.
            // The subsequent independent writes do not restore the old top on failure.
            dimension.setBlockState(abovePos, event.getNewBlockState());
            updateLeavesBelow(dimension, topPos);
            if (thickness == BambooStalkThickness.THICK) {
                updateThicknessBelow(dimension, topPos);
            }
            return true;
        }
        return false;
    }

    /**
     * Returns a new mutable coordinate for the highest contiguous bamboo segment. The scan
     * starts at {@code pos}, follows bamboo above it, and leaves the caller's coordinate
     * object unchanged.
     */
    protected Vector3ic findTopBamboo(Dimension dimension, Vector3ic pos) {
        var current = new Vector3i(pos);
        while (true) {
            var above = new Vector3i(current.x, current.y + 1, current.z);
            if (dimension.getBlockState(above).getBlockType() != BlockTypes.BAMBOO) {
                return current;
            }
            current.set(above);
        }
    }

    /**
     * Returns the column height including {@code fromTop} and contiguous lower bamboo or
     * saplings. Callers supply the top position; the starting block contributes one even when
     * its state is not inspected.
     */
    protected int countTotalHeight(Dimension dimension, Vector3ic fromTop) {
        int count = 1;
        var current = new Vector3i(fromTop);
        while (true) {
            current.y--;
            var type = dimension.getBlockState(current).getBlockType();
            if (type == BlockTypes.BAMBOO || type == BlockTypes.BAMBOO_SAPLING) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    /**
     * Returns the proposed height at {@code pos} above contiguous bamboo or saplings. The
     * prospective segment contributes one even before placement, allowing the caller to select
     * its age flag from the resulting height.
     */
    protected int countBambooHeight(Dimension dimension, Vector3ic pos) {
        int count = 0;
        var current = new Vector3i(pos);
        current.y--;
        while (true) {
            var type = dimension.getBlockState(current).getBlockType();
            if (type == BlockTypes.BAMBOO || type == BlockTypes.BAMBOO_SAPLING) {
                count++;
                current.y--;
            } else {
                break;
            }
        }
        return count + 1; // include the block at pos
    }

    /**
     * Returns whether the proposed height receives the natural-growth stop flag. Heights below
     * eleven continue, heights from eleven through fourteen stop with one-quarter probability,
     * and heights of fifteen or more always stop.
     */
    protected boolean shouldStopGrowing(int height) {
        // The age flag stops random growth before the separate hard extension limit.
        if (height >= 15) return true;
        if (height >= 11) return ThreadLocalRandom.current().nextInt(4) == 0;
        return false;
    }

    /**
     * Returns thick stalks when at least three contiguous lower segments are bamboo or
     * saplings. A shorter qualifying column produces thin stalks, and the prospective position
     * itself is excluded from the count.
     */
    protected BambooStalkThickness determineThickness(Dimension dimension, Vector3ic pos) {
        int count = 0;
        var current = new Vector3i(pos);
        current.y--;
        // Only lower segments determine the prospective stalk thickness.
        while (count < 3) {
            var type = dimension.getBlockState(current).getBlockType();
            if (type == BlockTypes.BAMBOO || type == BlockTypes.BAMBOO_SAPLING) {
                count++;
                current.y--;
            } else {
                break;
            }
        }
        return count >= 3 ? BambooStalkThickness.THICK : BambooStalkThickness.THIN;
    }

    /**
     * Selects the prospective segment's leaves from thickness and the lower column. Thick
     * stalks immediately receive large leaves. Thin stalks count up to two contiguous lower
     * bamboo or sapling segments, producing large, small, or absent leaves.
     *
     * <p>
     * The supplied {@code thickness} can therefore override the count that the thin-stalk path
     * would otherwise use. The method reads lower states only and does not modify existing
     * leaves; {@code updateLeavesBelow} handles the appearance changes after a placement or
     * accepted growth call.
     */
    protected BambooLeafSize determineLeafSize(Dimension dimension, Vector3ic pos, BambooStalkThickness thickness) {
        if (thickness == BambooStalkThickness.THICK) {
            return BambooLeafSize.LARGE_LEAVES;
        }
        int count = 0;
        var current = new Vector3i(pos);
        current.y--;
        // Thin stalks grade leaf size from the lower column.
        while (count < 2) {
            var type = dimension.getBlockState(current).getBlockType();
            if (type == BlockTypes.BAMBOO || type == BlockTypes.BAMBOO_SAPLING) {
                count++;
                current.y--;
            } else {
                break;
            }
        }
        if (count >= 2) return BambooLeafSize.LARGE_LEAVES;
        if (count >= 1) return BambooLeafSize.SMALL_LEAVES;
        return BambooLeafSize.NO_LEAVES;
    }

    /**
     * Updates up to three existing bamboo segments starting at {@code pos}. The starting
     * segment gets large leaves, the next small leaves, and the third no leaves; encountering
     * a nonbamboo block stops the downward scan.
     */
    protected void updateLeavesBelow(Dimension dimension, Vector3ic pos) {
        var current = new Vector3i(pos);
        int distance = 1;
        // Limit leaf reshaping to the three segments immediately beneath the new top.
        while (distance <= 3) {
            var state = dimension.getBlockState(current);
            if (state.getBlockType() != BlockTypes.BAMBOO) break;

            BambooLeafSize newLeafSize;
            if (distance == 1) {
                newLeafSize = BambooLeafSize.LARGE_LEAVES;
            } else if (distance == 2) {
                newLeafSize = BambooLeafSize.SMALL_LEAVES;
            } else {
                newLeafSize = BambooLeafSize.NO_LEAVES;
            }

            if (state.getPropertyValue(BAMBOO_LEAF_SIZE) != newLeafSize) {
                var newState = state.setPropertyValue(BAMBOO_LEAF_SIZE, newLeafSize);
                dimension.setBlockState(current, newState);
            }

            current.y--;
            distance++;
        }
    }

    /**
     * Changes contiguous bamboo at and below {@code pos} to thick stalks. The scan stops at
     * the first nonbamboo block, so a basal sapling is not converted or traversed.
     */
    protected void updateThicknessBelow(Dimension dimension, Vector3ic pos) {
        var current = new Vector3i(pos);
        while (true) {
            var state = dimension.getBlockState(current);
            if (state.getBlockType() != BlockTypes.BAMBOO) break;

            if (state.getPropertyValue(BAMBOO_STALK_THICKNESS) != BambooStalkThickness.THICK) {
                var newState = state.setPropertyValue(BAMBOO_STALK_THICKNESS, BambooStalkThickness.THICK);
                dimension.setBlockState(current, newState);
            }

            current.y--;
        }
    }
}
