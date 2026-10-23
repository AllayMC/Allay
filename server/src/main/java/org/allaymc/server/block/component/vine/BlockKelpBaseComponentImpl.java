package org.allaymc.server.block.component.vine;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockGrowEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.KELP_AGE;

/**
 * {@code BlockKelpBaseComponentImpl} implements water-dependent kelp placement, age-limited
 * natural extension, and bone-meal growth. Kelp accepts solid ground except magma and soul
 * sand, or another kelp segment. A newly placed segment receives a random age below the
 * natural-growth cutoff.
 *
 * <p>
 * When a column extends, the previous top is assigned the cutoff age before the new segment is
 * written. Natural growth stops at that age, but bone meal can extend a mature top with a
 * capped new age. Breaking a segment randomizes the kelp immediately below so the surviving
 * top can resume natural growth. These column updates use separate writes after any
 * growth-event acceptance and do not roll back the first write if the second fails.
 *
 * @author daoge_cmd
 */
public class BlockKelpBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code MAX_AGE} disables natural extension for a segment and bounds the age passed to a
     * new segment during bone-meal growth.
     */
    protected static final int MAX_AGE = 25;

    /**
     * Creates the kelp behavior component for the supplied block type.
     */
    public BlockKelpBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places kelp into water above suitable ground or existing kelp. The new segment receives
     * a random age from zero through twenty-four. A lower kelp is first assigned the maximum
     * age to prevent its natural extension.
     *
     * <p>
     * That lower-state change happens before inherited placement processing and remains if the
     * final placement fails. Both support and water checks run for programmatic placement as
     * well as player placement. This method's result is the inherited placement result rather
     * than a coordinated result for the whole column.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (!isInWater(dimension, placeBlockPos)) {
            return false;
        }

        var downState = dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos));
        var downType = downState.getBlockType();
        if (downType != BlockTypes.KELP) {
            if (!downState.getBlockStateData().isSolid() ||
                downType == BlockTypes.MAGMA ||
                downType == BlockTypes.SOUL_SAND) {
                return false;
            }
        }

        var age = ThreadLocalRandom.current().nextInt(MAX_AGE);
        blockState = blockState.setPropertyValue(KELP_AGE, age);

        // Disable the old top before adding the new segment. The earlier age
        // write is not rolled back if inherited placement later rejects the new kelp.
        if (downType == BlockTypes.KELP) {
            var downPos = BlockFace.DOWN.offsetPos(placeBlockPos);
            var newDownState = downState.setPropertyValue(KELP_AGE, MAX_AGE);
            dimension.setBlockState(downPos, newDownState);
        }

        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    /**
     * Dispatches the inherited neighbor hook and breaks kelp after an invalid lower-support
     * change. Other kelp or solid nonmagma, nonsoul-sand support qualifies; this callback does
     * not independently recheck water at the kelp position.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (face == BlockFace.DOWN) {
            var downType = neighbor.getBlockType();
            if (downType != BlockTypes.KELP && !neighbor.getBlockStateData().isSolid()) {
                block.breakBlock();
                return;
            }
            if (downType == BlockTypes.MAGMA || downType == BlockTypes.SOUL_SAND) {
                block.breakBlock();
            }
        }
    }

    /**
     * Returns whether kelp receives random updates for age-limited natural extension.
     */
    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    /**
     * Attempts natural extension for a segment younger than twenty-five after inherited update
     * dispatch. A fifteen-percent roll must pass, and the upper target must contain water
     * while its base state is air or water.
     *
     * <p>
     * The proposal gives the new segment the old age plus one. Event acceptance first sets the
     * current segment to maximum age, then writes the event's state above; cancellation skips
     * both writes. Individual dimension write results are ignored and the earlier age change
     * has no rollback.
     */
    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        var age = block.getPropertyValue(KELP_AGE);
        if (age >= MAX_AGE) {
            return;
        }

        // Natural extension is probabilistic; bone meal bypasses this random gate.
        if (ThreadLocalRandom.current().nextInt(100) >= 15) {
            return;
        }

        var abovePos = block.getPosition().add(0, 1, 0, new Vector3i());
        if (!isInWater(block.getDimension(), abovePos)) {
            return;
        }

        if (block.getDimension().getBlockState(abovePos).getBlockType() != BlockTypes.AIR &&
            !block.getDimension().getBlockState(abovePos).getBlockType().hasBlockTag(BlockTags.WATER)) {
            return;
        }

        var dimension = block.getDimension();

        var newState = blockType.getDefaultState().setPropertyValue(KELP_AGE, age + 1);
        var event = new BlockGrowEvent(block, newState);
        if (event.call()) {
            dimension.setBlockState(block.getPosition(), block.getBlockState().setPropertyValue(KELP_AGE, MAX_AGE));
            dimension.setBlockState(abovePos, event.getNewBlockState());
        }
    }

    /**
     * Runs inherited breaking behavior and randomizes the age of kelp immediately below. The
     * new age stays below the natural-growth cutoff, allowing the exposed surviving segment to
     * attempt growth again.
     */
    @Override
    public void onBreak(Block block, ItemStack usedItem, Entity entity) {
        super.onBreak(block, usedItem, entity);

        var belowPos = BlockFace.DOWN.offsetPos(block.getPosition());
        var belowState = block.getDimension().getBlockState(belowPos);
        if (belowState.getBlockType() == BlockTypes.KELP) {
            var newAge = ThreadLocalRandom.current().nextInt(MAX_AGE);
            var newState = belowState.setPropertyValue(KELP_AGE, newAge);
            block.getDimension().setBlockState(belowPos, newState);
        }
    }

    /**
     * Attempts bone-meal extension above the highest contiguous kelp segment. The upper target
     * must contain water with air or water in its base layer; the new age is one above the
     * top's age, capped at twenty-five.
     *
     * <p>
     * An accepted growth event first ages the old top to the cutoff and then writes its
     * replacement above. The callback consumes bone meal and emits particles after those
     * writes without checking either result. A blocked target or cancelled event returns
     * {@code false} before those state changes.
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
        var topPos = findTopKelp(dimension, pos);
        var topState = dimension.getBlockState(topPos);
        var topAge = topState.getPropertyValue(KELP_AGE);

        var abovePos = topPos.add(0, 1, 0, new Vector3i());
        if (!isInWater(dimension, abovePos)) {
            return false;
        }
        if (dimension.getBlockState(abovePos).getBlockType() != BlockTypes.AIR &&
            !dimension.getBlockState(abovePos).getBlockType().hasBlockTag(BlockTags.WATER)) {
            return false;
        }

        var newState = blockType.getDefaultState().setPropertyValue(KELP_AGE, Math.min(topAge + 1, MAX_AGE));
        var event = new BlockGrowEvent(
                new Block(topState, new Position3i(topPos, dimension)),
                newState
        );
        if (event.call()) {
            dimension.setBlockState(topPos, topState.setPropertyValue(KELP_AGE, MAX_AGE));
            dimension.setBlockState(abovePos, event.getNewBlockState());
            interactInfo.player().tryConsumeItemInHand();
            dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);
            return true;
        }

        return false;
    }

    /**
     * Returns one kelp item for ordinary breaking of each segment.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.KELP.createItemStack(1));
    }

    /**
     * Returns a new mutable coordinate for the highest contiguous kelp above {@code pos}. The
     * scan stops at the first nonkelp upper block and leaves the caller's coordinate object
     * unchanged.
     */
    protected Vector3ic findTopKelp(Dimension dimension, Vector3ic pos) {
        var current = new Vector3i(pos);
        while (true) {
            var above = new Vector3i(current.x, current.y + 1, current.z);
            if (dimension.getBlockState(above).getBlockType() != BlockTypes.KELP) {
                return current;
            }
            current.set(above);
        }
    }

    /**
     * Returns whether a water-family liquid or base-layer state occupies {@code pos}. The
     * base-state fallback also recognizes water before kelp placement changes the position to
     * a water-containing plant.
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
