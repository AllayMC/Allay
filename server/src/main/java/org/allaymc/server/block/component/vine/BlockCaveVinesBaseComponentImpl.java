package org.allaymc.server.block.component.vine;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
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
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.GROWING_PLANT_AGE;

/**
 * {@code BlockCaveVinesBaseComponentImpl} implements cave-vine support, downward extension,
 * and glow-berry interactions. Construction supplies berry and head flags for the registered
 * variant, so drops and interactions follow the configured variant rather than inferring those
 * roles from a mutable property.
 *
 * <p>
 * Only configured heads opt into random updates. A random update can mature the current age
 * independently of its downward growth proposal, and an air target receives either a berry
 * head or plain cave vine. Bone meal converts a berry-free variant directly, while picking
 * berries drops an item before resetting the position to a plain age-zero vine. Those direct
 * interaction writes do not share the growth event's cancellation boundary.
 *
 * @author daoge_cmd
 */
public class BlockCaveVinesBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code MAX_AGE} caps cave-vine maturation and supplies the age assigned by bone meal
     * when creating a berry-bearing head variant.
     */
    protected static final int MAX_AGE = 25;

    /**
     * Configured berry-bearing variant flag.
     */
    protected final boolean hasBerries;
    /**
     * Configured random-update head flag.
     */
    protected final boolean isHead;

    /**
     * Creates cave-vine behavior with fixed berry and head roles for its block type. The flags
     * control interaction, drops, and random-update eligibility rather than being recalculated
     * from the supplied block state.
     */
    public BlockCaveVinesBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, boolean hasBerries, boolean isHead) {
        super(blockType);
        this.hasBerries = hasBerries;
        this.isHead = isHead;
    }

    /**
     * Places cave vines below solid support or another recognized cave-vine type. A nonnull
     * {@code placementInfo} must use the downward face, while programmatic placement still
     * applies the upper-support predicate.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null) {
            if (placementInfo.blockFace() != BlockFace.DOWN) {
                return false;
            }
        }

        var aboveState = dimension.getBlockState(BlockFace.UP.offsetPos(placeBlockPos));
        if (!canBeSupportedBy(aboveState)) {
            return false;
        }

        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    /**
     * Dispatches the inherited neighbor hook and breaks vines after invalid upper support
     * changes. Other faces leave the local support rule untouched; recognized cave-vine
     * variants support one another regardless of solidity.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (face == BlockFace.UP) {
            if (!canBeSupportedBy(neighbor.getBlockState())) {
                block.breakBlock();
            }
        }
    }

    /**
     * Returns whether the configured cave-vine variant is a random-update head.
     */
    @Override
    public boolean canRandomUpdate() {
        return isHead;
    }

    /**
     * Processes current-vine maturation independently from a downward extension proposal. A
     * one-quarter maturation roll adds four age units up to twenty-five, but a mature age does
     * not prevent the later extension branch in this implementation.
     *
     * <p>
     * The position below must be air. A separate one-in-ten choice proposes a berry-bearing
     * head, otherwise plain cave vine, both at age zero. The growth event can cancel only that
     * downward write; an earlier accepted maturation write remains. The current block is not
     * converted to a distinct body variant by this callback.
     */
    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        var random = ThreadLocalRandom.current();
        var age = block.getPropertyValue(GROWING_PLANT_AGE);
        var dimension = block.getDimension();

        // Maturation is independent of extension below, so an age update survives
        // a later blocked destination or a cancelled downward growth event.
        if (random.nextInt(4) == 0 && age < MAX_AGE) {
            var newAge = Math.min(age + 4, MAX_AGE);
            var newState = block.getBlockState().setPropertyValue(GROWING_PLANT_AGE, newAge);
            dimension.setBlockState(block.getPosition(), newState);
        }

        var belowPos = block.getPosition().add(0, -1, 0, new Vector3i());
        if (dimension.getBlockState(belowPos).getBlockType() != BlockTypes.AIR) {
            return;
        }

        BlockState newVineState;
        if (random.nextInt(10) == 0) {
            newVineState = BlockTypes.CAVE_VINES_HEAD_WITH_BERRIES.getDefaultState()
                    .setPropertyValue(GROWING_PLANT_AGE, 0);
        } else {
            newVineState = BlockTypes.CAVE_VINES.getDefaultState()
                    .setPropertyValue(GROWING_PLANT_AGE, 0);
        }

        var event = new BlockGrowEvent(block, newVineState);
        if (event.call()) {
            dimension.setBlockState(belowPos, event.getNewBlockState());
        }
    }

    /**
     * Handles bone meal or berry picking after inherited interaction handling. Bone meal
     * converts a configured berry-free variant into a maximum-age berry head, consumes the
     * item, and emits particles; berry-bearing variants reject that bone-meal branch.
     *
     * <p>
     * Other interactions on a berry-bearing variant drop one glow berry before replacing the
     * block with plain age-zero cave vine. These interaction writes have no growth-event
     * cancellation or rollback, and their dimension results are ignored. A berry-free ordinary
     * interaction returns {@code false}.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        var clickedBlock = interactInfo.getClickedBlock();

        if (itemStack != null && itemStack.getItemType() == ItemTypes.BONE_MEAL) {
            if (!hasBerries) {
                var newState = BlockTypes.CAVE_VINES_HEAD_WITH_BERRIES.getDefaultState()
                        .setPropertyValue(GROWING_PLANT_AGE, MAX_AGE);
                dimension.setBlockState(interactInfo.clickedBlockPos(), newState);
                interactInfo.player().tryConsumeItemInHand();
                dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);
                return true;
            }
            return false;
        }

        if (hasBerries) {
            var dropPos = MathUtils.center(interactInfo.clickedBlockPos());
            dimension.dropItem(ItemTypes.GLOW_BERRIES.createItemStack(1), dropPos);

            var newState = BlockTypes.CAVE_VINES.getDefaultState()
                    .setPropertyValue(GROWING_PLANT_AGE, 0);
            dimension.setBlockState(interactInfo.clickedBlockPos(), newState);
            return true;
        }

        return false;
    }

    /**
     * Returns one glow berry for a configured berry-bearing variant and no items otherwise.
     * This decision uses the constructor flag rather than the plant age or the supplied tool.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        if (hasBerries) {
            return Set.of(ItemTypes.GLOW_BERRIES.createItemStack(1));
        }
        return Set.of();
    }

    /**
     * Returns whether a recognized cave-vine type or solid state supplies upper support.
     */
    protected boolean canBeSupportedBy(BlockState blockState) {
        var type = blockState.getBlockType();
        if (isCaveVines(type)) {
            return true;
        }
        return blockState.getBlockStateData().isSolid();
    }

    /**
     * Returns whether the type is plain cave vine or either berry variant.
     */
    protected boolean isCaveVines(BlockType<?> type) {
        return type == BlockTypes.CAVE_VINES ||
               type == BlockTypes.CAVE_VINES_BODY_WITH_BERRIES ||
               type == BlockTypes.CAVE_VINES_HEAD_WITH_BERRIES;
    }
}
