package org.allaymc.server.block.component.vine;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.SeaGrassType;
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

import static org.allaymc.api.block.property.type.BlockPropertyTypes.SEA_GRASS_TYPE;

/**
 * {@code BlockSeagrassBaseComponentImpl} implements underwater seagrass support, double-plant
 * growth, and shears drops. Placement requires water over solid ground other than magma or
 * soul sand. Neighbor changes require water for every form and enforce matching upper and
 * lower types for double seagrass.
 *
 * <p>
 * Bone meal converts ordinary seagrass into a bottom state and an upper state chosen through a
 * growth event. The bottom write occurs first and has no rollback if the upper write fails.
 * Ordinary drop eligibility excludes upper halves, and the drop calculation requires shears.
 *
 * <p>
 * Breaking a double half attempts normal breaking of its matching counterpart. The dimension
 * runs this callback before clearing the original position, so unchanged matching halves can
 * recursively invoke each other's break callbacks. A nested cancellation or state change can
 * interrupt that cycle; otherwise recursion can exhaust the stack before either pending break
 * reaches its air write.
 *
 * @author daoge_cmd
 */
public class BlockSeagrassBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * Creates the seagrass behavior component for the supplied block type.
     */
    public BlockSeagrassBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places seagrass only in water over solid nonmagma, nonsoul-sand ground. Both player and
     * programmatic placement use those predicates before inherited property processing and the
     * final dimension write.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (!isInWater(dimension, placeBlockPos)) {
            return false;
        }

        var downState = dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos));
        if (!downState.getBlockStateData().isSolid() ||
            downState.getBlockType() == BlockTypes.MAGMA ||
            downState.getBlockType() == BlockTypes.SOUL_SAND) {
            return false;
        }

        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    /**
     * Dispatches the inherited neighbor hook and first removes seagrass that no longer has
     * water. Single and lower-double states check their ground, while double halves also
     * require their matching counterpart on the relevant changed face.
     *
     * <p>
     * The water check runs for every neighbor face before the form-specific support logic.
     * Upper-double states do not require direct solid ground because their lower counterpart
     * supplies support. These combined conditions prevent a matching plant half from keeping a
     * dry segment alive after its liquid disappears.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        var seaGrassType = block.getPropertyValue(SEA_GRASS_TYPE);

        if (!isInWater(block.getDimension(), block.getPosition())) {
            block.breakBlock();
            return;
        }

        switch (seaGrassType) {
            case DEFAULT -> {
                if (face == BlockFace.DOWN) {
                    if (!isValidGround(neighbor.getBlockState())) {
                        block.breakBlock();
                    }
                }
            }
            case DOUBLE_BOT -> {
                if (face == BlockFace.DOWN) {
                    if (!isValidGround(neighbor.getBlockState())) {
                        block.breakBlock();
                    }
                } else if (face == BlockFace.UP) {
                    if (!isMatchingHalf(neighbor.getBlockState(), SeaGrassType.DOUBLE_TOP)) {
                        block.breakBlock();
                    }
                }
            }
            case DOUBLE_TOP -> {
                if (face == BlockFace.DOWN) {
                    if (!isMatchingHalf(neighbor.getBlockState(), SeaGrassType.DOUBLE_BOT)) {
                        block.breakBlock();
                    }
                }
            }
        }
    }

    /**
     * Attempts a double plant from ordinary seagrass after an unhandled bone-meal interaction.
     * The upper position must contain water and have air or water as its base state; an
     * existing double form rejects the action.
     *
     * <p>
     * The event proposes the upper state. Acceptance writes a default lower-double state at
     * the old position before writing the event result above, then consumes bone meal and
     * emits particles. Neither write result is checked, and a later upper failure does not
     * roll back the lower conversion.
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
        var seaGrassType = blockState.getPropertyValue(SEA_GRASS_TYPE);

        if (seaGrassType != SeaGrassType.DEFAULT) {
            return false;
        }

        var abovePos = pos.add(0, 1, 0, new Vector3i());
        if (!isInWater(dimension, abovePos)) {
            return false;
        }

        var aboveBlockType = dimension.getBlockState(abovePos).getBlockType();
        if (aboveBlockType != BlockTypes.AIR && !aboveBlockType.hasBlockTag(BlockTags.WATER)) {
            return false;
        }

        var newState = blockType.getDefaultState().setPropertyValue(SEA_GRASS_TYPE, SeaGrassType.DOUBLE_TOP);
        var event = new BlockGrowEvent(interactInfo.getClickedBlock(), newState);
        if (event.call()) {
            // Convert the original first, then place the event-selected upper state.
            // The writes are independent and do not restore the lower state after upper failure.
            dimension.setBlockState(pos, blockType.getDefaultState().setPropertyValue(SEA_GRASS_TYPE, SeaGrassType.DOUBLE_BOT));
            dimension.setBlockState(abovePos, event.getNewBlockState());

            interactInfo.player().tryConsumeItemInHand();
            dimension.addParticle(MathUtils.center(pos), SimpleParticle.BONE_MEAL);
            return true;
        }

        return false;
    }

    /**
     * Runs inherited break effects and attempts normal breaking of the matching double
     * counterpart. A lower half checks above and an upper half checks below; only the expected
     * seagrass type and form trigger the nested break.
     *
     * <p>
     * The dimension invokes this callback before clearing its original position. When both
     * matching halves remain present, each nested break can therefore call back into the other
     * half before either is removed. Cancellation or state mutation during nested event
     * handling can stop the cycle; otherwise the recursive callbacks can exhaust the stack.
     * This override does not install a reentry guard or clear either half before delegation.
     */
    @Override
    public void onBreak(Block block, ItemStack usedItem, Entity entity) {
        super.onBreak(block, usedItem, entity);

        var seaGrassType = block.getPropertyValue(SEA_GRASS_TYPE);
        var pos = block.getPosition();
        var dimension = block.getDimension();

        // The original half remains in the dimension during this callback. Breaking
        // its counterpart can re-enter the same pair before either pending break writes air.
        if (seaGrassType == SeaGrassType.DOUBLE_BOT) {
            var abovePos = pos.add(0, 1, 0, new Vector3i());
            var aboveState = dimension.getBlockState(abovePos);
            if (isMatchingHalf(aboveState, SeaGrassType.DOUBLE_TOP)) {
                dimension.breakBlock(abovePos, null, entity);
            }
        } else if (seaGrassType == SeaGrassType.DOUBLE_TOP) {
            var belowPos = BlockFace.DOWN.offsetPos(pos);
            var belowState = dimension.getBlockState(belowPos);
            if (isMatchingHalf(belowState, SeaGrassType.DOUBLE_BOT)) {
                dimension.breakBlock(belowPos, null, entity);
            }
        }
    }

    /**
     * Allows ordinary drops from single and lower-double seagrass, excluding upper-double
     * states. This override uses only the form property; the separate drop calculation checks
     * shears rather than applying inherited tool or entity eligibility here.
     */
    @Override
    public boolean isDroppable(Block block, ItemStack usedItem, Entity entity) {
        return block.getPropertyValue(SEA_GRASS_TYPE) != SeaGrassType.DOUBLE_TOP;
    }

    /**
     * Returns the inherited seagrass item drop when the tool is shears. A {@code null} tool or
     * another item returns an empty set, independently of the separate half-eligibility
     * predicate.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        if (usedItem != null && usedItem.getItemType() == ItemTypes.SHEARS) {
            return super.getDrops(block, usedItem, entity);
        }
        return Set.of();
    }

    /**
     * Returns whether water-family liquid or a water base state occupies {@code pos}. The
     * base-state fallback supports checks before placement turns water into a plant with
     * retained liquid.
     */
    protected boolean isInWater(Dimension dimension, Vector3ic pos) {
        var liquid = dimension.getLiquid(pos).right();
        if (liquid != null && liquid.getBlockType().hasBlockTag(BlockTags.WATER)) {
            return true;
        }
        var blockState = dimension.getBlockState(pos);
        return blockState.getBlockType().hasBlockTag(BlockTags.WATER);
    }

    /**
     * Returns whether the state is solid and neither magma nor soul sand.
     */
    protected boolean isValidGround(BlockState blockState) {
        var blockType = blockState.getBlockType();
        return blockState.getBlockStateData().isSolid() &&
               blockType != BlockTypes.MAGMA &&
               blockType != BlockTypes.SOUL_SAND;
    }

    /**
     * Returns whether the state is seagrass with the requested half encoding.
     */
    protected boolean isMatchingHalf(BlockState blockState, SeaGrassType expectedType) {
        return blockState.getBlockType() == BlockTypes.SEAGRASS &&
               blockState.getPropertyValue(SEA_GRASS_TYPE) == expectedType;
    }
}
