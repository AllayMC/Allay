package org.allaymc.server.block.component.liquid;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.eventbus.event.block.LiquidHardenEvent;
import org.allaymc.api.world.dimension.DimensionType;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.api.world.sound.SimpleSound;
import org.joml.Vector3ic;

import static org.allaymc.api.block.component.BlockLiquidBaseComponent.isSource;
import static org.allaymc.api.block.type.BlockTypes.AIR;

/**
 * {@code BlockWaterBaseComponentImpl} implements water-specific liquid rules, including source
 * creation, evaporation, waterlogging, and lava hardening. Water uses a fixed spread delay and
 * decay, while water tags group stationary and flowing variants for matching supply.
 *
 * <p>
 * Layer changes preserve source water when a new block can contain it and remove incompatible
 * layer-one water when the containing block changes. Hardening chooses stone at the water
 * position when incoming lava has a Y coordinate exactly one greater; other incoming lava
 * proposes cobblestone at its own position. The hardening event can cancel that replacement or
 * change its resulting state before the dimension writes it and emits the fizz sound.
 *
 * @author daoge_cmd
 */
public class BlockWaterBaseComponentImpl extends BlockLiquidBaseComponentImpl {
    /**
     * Creates the water behavior component for the supplied block type.
     */
    public BlockWaterBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Returns whether the supplied block type carries the water-family tag.
     */
    @Override
    public boolean isSameLiquidType(BlockType<?> blockType) {
        return blockType.hasBlockTag(BlockTags.WATER);
    }

    /**
     * Removes water immediately in dimensions whose type evaporates water. Other dimensions
     * dispatch the inherited neighbor hook and schedule normal liquid processing; evaporation
     * does not raise a separate decay event here.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        if (block.getDimension().getDimensionType().waterEvaporates()) {
            block.getDimension().setLiquid(block.getPosition(), null);
            return;
        }

        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
    }

    /**
     * Removes newly placed water when the dimension type enables water evaporation. Otherwise
     * this hook dispatches the inherited post-placement behavior; the evaporation branch
     * returns before that base notification runs.
     */
    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        if (oldBlock.getDimension().getDimensionType().waterEvaporates()) {
            oldBlock.getDimension().setLiquid(oldBlock.getPosition(), null);
            return;
        }

        super.afterPlaced(oldBlock, newBlockState, placementInfo);
    }

    /**
     * Preserves replaced layer-zero source water in layer {@code 1} when the replacement can
     * contain a liquid source. Flowing water and water already outside layer {@code 0} are
     * excluded after the inherited replacement hook runs.
     */
    @Override
    public void onReplace(Block block, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.onReplace(block, newBlockState, placementInfo);

        if (!isSource(block.getBlockState())) {
            return;
        }

        if (block.getLayer() != 0) {
            return;
        }

        var dim = block.getDimension();
        if (newBlockState.getBlockType() != AIR && newBlockState.getBlockStateData().canContainLiquidSource()) {
            // Save the replaced source in layer one before the replacement occupies
            // layer zero, preserving water for blocks that explicitly accept source containment.
            dim.setBlockState(block.getPosition(), block.getBlockState(), 1);
        }
    }

    /**
     * Reconciles layer-one water after a neighboring layer replacement. The local logic runs
     * only for a layer-zero {@code currentBlock} and a replacement outside the water family.
     * Removing the container restores source water to layer {@code 0} and clears layer {@code
     * 1}; incompatible replacement blocks clear layer-one water.
     */
    @Override
    public void afterNeighborLayerReplace(Block currentBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterNeighborLayerReplace(currentBlock, newBlockState, placementInfo);

        if (currentBlock.getLayer() != 0 || isSameLiquidType(newBlockState.getBlockType())) {
            return;
        }

        var dim = currentBlock.getDimension();
        var pos = currentBlock.getPosition();
        if (newBlockState.getBlockType() == AIR) {
            if (isSource(dim.getBlockState(pos, 1))) {
                // Restore only source water to the exposed base layer; the following
                // layer-one clear discards any contained flowing state instead of exposing it.
                dim.setBlockState(pos, BlockTypes.WATER.getDefaultState(), 0);
            }
            dim.setBlockState(pos, BlockTypes.AIR.getDefaultState(), 1);
            return;
        }

        if (!newBlockState.getBlockStateData().canContainLiquidSource()) {
            dim.setBlockState(pos, BlockTypes.AIR.getDefaultState(), 1);
        }
    }

    /**
     * Extinguishes a burning living entity touching water and emits smoke and an extinguishing
     * sound. Other entities and living entities already free of fire receive no additional
     * effect from this hook.
     */
    @Override
    public void onEntityInside(Block block, Entity entity) {
        if (entity instanceof EntityLiving living && living.isOnFire()) {
            living.extinguish();
            living.getDimension().addParticle(living.getLocation(), SimpleParticle.WHITE_SMOKE);
            living.getDimension().addSound(living.getLocation(), SimpleSound.FIRE_EXTINGUISH);
        }
    }

    /**
     * Attempts hardening when lava flows into this water block. A {@code null} incoming block
     * or a block outside the lava family returns {@code false}. Incoming lava whose Y
     * coordinate is exactly one greater than the water's proposes stone at the water position;
     * other incoming lava proposes cobblestone at its own position.
     *
     * <p>
     * The Y test does not independently validate horizontal adjacency; ordinary flow callers
     * provide neighboring blocks. The event describes the lava as the affected liquid and this
     * water state as the cause. Cancellation prevents the replacement and fizz sound.
     * Acceptance writes the event's state at the selected position, emits fizz at the incoming
     * lava, and returns {@code true}.
     */
    @Override
    public boolean tryHarden(Block block, Block flownIntoBy) {
        if (flownIntoBy == null) {
            return false;
        }

        var dimension = block.getDimension();
        if (!BlockTypes.LAVA.getBlockBehavior().isSameLiquidType(flownIntoBy.getBlockType())) {
            return false;
        }

        Vector3ic hardenedBlockPosition;
        BlockState hardenedBlockState;
        if (flownIntoBy.getPosition().y() == block.getPosition().y() + 1) {
            hardenedBlockPosition = block.getPosition();
            hardenedBlockState = BlockTypes.STONE.getDefaultState();
        } else {
            hardenedBlockPosition = flownIntoBy.getPosition();
            hardenedBlockState = BlockTypes.COBBLESTONE.getDefaultState();
        }
        var event = new LiquidHardenEvent(flownIntoBy, block.getBlockState(), hardenedBlockState, hardenedBlockPosition);
        if (!event.call()) {
            return false;
        }

        dimension.setBlockState(hardenedBlockPosition, event.getHardenedBlockState());
        flownIntoBy.addSound(SimpleSound.FIZZ);
        return true;
    }

    /**
     * Returns the single depth level lost by horizontal water flow.
     */
    @Override
    public int getFlowDecay(DimensionType dimensionType) {
        return 1;
    }

    /**
     * Returns the five-game-tick delay used for scheduled water flow updates.
     */
    @Override
    public int getFlowSpeed(DimensionType dimensionType) {
        return 5;
    }

    /**
     * Returns whether water participates in the shared automatic source-formation checks.
     */
    @Override
    public boolean canFormSource() {
        return true;
    }
}
