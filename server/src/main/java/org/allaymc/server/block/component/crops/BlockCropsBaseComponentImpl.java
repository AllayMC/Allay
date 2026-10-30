package org.allaymc.server.block.component.crops;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.eventbus.event.block.BlockFadeEvent;
import org.allaymc.api.eventbus.event.block.BlockGrowEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.GROWTH_8;

/**
 * {@code BlockCropsBaseComponentImpl} implements farmland crop placement, support checks,
 * growth probability, and bone-meal interaction. Player placement requires the upper face of
 * farmland, and a lower neighbor change removes crops whose farmland support is gone. Mature
 * crop drops are supplied by subclasses.
 *
 * <p>
 * Natural growth combines light, nearby farmland moisture, and the arrangement of matching
 * crops. Moist farmland contributes more growth points; surrounding farmland contributes less
 * than the central block, and crowded row patterns halve the result. Low light instead
 * proposes fading before ordinary breaking. Bone meal bypasses the natural probability
 * calculation and proposes a bounded multi-stage increase, with held-item consumption
 * occurring only after the growth event accepts.
 *
 * @author Dhaiven | daoge_cmd
 */
public abstract class BlockCropsBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the crops behavior component for the supplied block type.
     */
    public BlockCropsBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places a crop only on the upper face of farmland. {@code placementInfo} must be nonnull
     * because this implementation reads it directly; accepted placement delegates property
     * processing and the final write to the base component.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo.blockFace() != BlockFace.UP ||
            placementInfo.getClickedBlock().getBlockType() != BlockTypes.FARMLAND) {
            return false;
        }

        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    /**
     * Dispatches the base neighbor hook and breaks the crop when lower support changes away
     * from farmland. Other neighbor faces do not run the local support-removal branch in this
     * implementation.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        if (face == BlockFace.DOWN && neighbor.getBlockType() != BlockTypes.FARMLAND) {
            block.breakBlock();
        }
    }

    /**
     * Calculates the crop's per-random-update growth probability from nearby farmland and
     * matching crops. The center farmland contributes two points when dry or four when moist;
     * each surrounding farmland contributes {@code 0.25} when dry or {@code 0.75} when moist
     * through {@code (farmlandPoints - 1) / 4}.
     *
     * <p>
     * Matching crops on both row axes, or on any diagonal when that first pattern is absent,
     * halve the accumulated points once. The result maps those points through {@code 1 / (25 /
     * points + 1)}; no qualifying farmland yields a zero chance through floating-point
     * division.
     */
    protected float calculateGrowthChance(Block block) {
        var points = 0.0f;

        var under = block.offsetPos(BlockFace.DOWN);
        for (var x = -1; x <= 1; x++) {
            for (var z = -1; z <= 1; z++) {
                var neighbor = under.offsetPos(x, 0, z);
                if (neighbor.getBlockType() == BlockTypes.FARMLAND) {
                    var farmlandPoints = 0f;
                    if (neighbor.getPropertyValue(BlockPropertyTypes.MOISTURIZED_AMOUNT) > 0) {
                        farmlandPoints = 4;
                    } else {
                        farmlandPoints = 2;
                    }
                    // Neighbor farmland supports growth less strongly than the central soil;
                    // apply its reduced contribution before the later crop-crowding penalty.
                    if (x != 0 || z != 0) {
                        farmlandPoints = (farmlandPoints - 1f) / 4f;
                    }
                    points += farmlandPoints;
                }
            }
        }

        var north = block.offsetPos(BlockFace.NORTH);
        var south = block.offsetPos(BlockFace.SOUTH);
        var west = block.offsetPos(BlockFace.WEST);
        var east = block.offsetPos(BlockFace.EAST);

        var northSouth = north.getBlockType() == this.blockType || south.getBlockType() == this.blockType;
        var westEast = west.getBlockType() == this.blockType || east.getBlockType() == this.blockType;
        // Apply at most one crowding penalty: the crossed-row pattern takes
        // precedence, while diagonals matter only when that first pattern is absent.
        if (northSouth && westEast) {
            points /= 2f;
        } else if (
                north.offsetPos(BlockFace.WEST).getBlockType() == this.blockType ||
                north.offsetPos(BlockFace.EAST).getBlockType() == this.blockType ||
                south.offsetPos(BlockFace.WEST).getBlockType() == this.blockType ||
                south.offsetPos(BlockFace.EAST).getBlockType() == this.blockType
        ) {
            points /= 2f;
        }

        // Convert the accumulated soil score into a probability before random sampling.
        return 1f / (25f / points + 1f);
    }

    /**
     * Attempts bone-meal growth after inherited interaction handling. Only bone meal reaches
     * the growth hook, and the player consumes the held item and receives particles when that
     * hook reports a successful accepted proposal.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (itemStack.getItemType() != ItemTypes.BONE_MEAL) {
            return false;
        }

        if (onBoneMealUsed(dimension, interactInfo.clickedBlockPos(), interactInfo.getClickedBlock().getBlockState())) {
            interactInfo.player().tryConsumeItemInHand();
            dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);
            return true;
        }

        return false;
    }

    /**
     * Proposes a random two-to-five-stage crop increase, capped at growth seven. A mature crop
     * returns {@code false}; cancellation also returns {@code false}, while acceptance writes
     * the event's state without verifying the dimension's write result.
     */
    protected boolean onBoneMealUsed(Dimension dimension, Vector3ic pos, BlockState crop) {
        var growth = crop.getPropertyValue(GROWTH_8);
        if (growth == GROWTH_8.getMax()) {
            return false;
        }

        // Bone meal advances several stages at once while preserving the terminal growth encoding.
        var newCrop = crop.setPropertyValue(GROWTH_8, Math.min(growth + ThreadLocalRandom.current().nextInt(4) + 2, 7));
        var event = new BlockGrowEvent(new Block(crop, new Position3i(pos, dimension)), newCrop);
        if (event.call()) {
            dimension.setBlockState(pos, event.getNewBlockState());
            return true;
        }

        return false;
    }

    /**
     * Returns whether farmland crops receive random updates for natural growth.
     */
    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    /**
     * Dispatches the inherited update and chooses low-light removal or natural growth.
     * Internal light below eight raises a fade event and, if accepted, breaks the crop through
     * the ordinary breaking path rather than writing the event's replacement state.
     *
     * <p>
     * Under adequate light, an immature crop advances one stage when its calculated
     * probability passes. The growth event may cancel that advance or replace the proposed
     * state before the dimension write. Mature crops remain unchanged by the natural-growth
     * branch.
     */
    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        // Low light selects removal instead of the soil-growth calculation.
        if (block.getDimension().getLightEngine().getInternalLight(block.getPosition()) < 8) {
            var event = new BlockFadeEvent(block, BlockTypes.AIR.getDefaultState());
            if (event.call()) {
                block.breakBlock();
            }
        } else {
            var growth = block.getPropertyValue(GROWTH_8);
            if (growth < GROWTH_8.getMax() && ThreadLocalRandom.current().nextFloat() <= calculateGrowthChance(block)) {
                var newCrop = block.setPropertyValue(GROWTH_8, growth + 1);
                var event = new BlockGrowEvent(block, newCrop.getBlockState());
                if (event.call()) {
                    block.getDimension().setBlockState(block.getPosition(), event.getNewBlockState());
                }
            }
        }
    }
}
