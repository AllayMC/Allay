package org.allaymc.server.block.component.plant;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.interfaces.ItemSeaPickleStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.CLUSTER_COUNT;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.DEAD_BIT;

/**
 * {@code BlockSeaPickleBaseComponentImpl} implements sea-pickle support, cluster stacking,
 * water-state tracking, and bone-meal spread. Cluster count is stored one below the item
 * quantity, and the dead flag reflects whether the position currently contains water.
 * Placement requires solid support and applies that flag before the inherited write.
 *
 * <p>
 * Neighbor-layer changes reread the layer-zero pickle instead of interpreting the old
 * modified-layer snapshot as the plant. Matching items grow unfinished clusters directly. Bone
 * meal requires a live pickle on a coral-named block, fills the current cluster, and samples
 * nearby air positions over coral with water. Those spread writes are direct and individually
 * unchecked, so interaction success and consumption do not require a successful new pickle
 * placement.
 *
 * @author daoge_cmd
 */
public class BlockSeaPickleBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the sea pickle behavior component for the supplied block type.
     */
    public BlockSeaPickleBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places a pickle on solid support and initializes its dead flag from current water
     * presence. Player placement requires the upper face, while a {@code null} {@code
     * placementInfo} still applies the same support and water checks.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null && placementInfo.blockFace() != BlockFace.UP) {
            return false;
        }

        var downState = dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos));
        if (!downState.getBlockStateData().isSolid()) {
            return false;
        }

        boolean inWater = isInWater(dimension, placeBlockPos);
        blockState = blockState.setPropertyValue(DEAD_BIT, !inWater);

        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    /**
     * Adds one pickle to an existing unfinished sea-pickle cluster and recomputes its dead
     * flag. The method returns {@code true} after its write call without inspecting that
     * result; other types and full clusters return {@code false}.
     */
    @Override
    public boolean combine(Dimension dimension, BlockState blockState, Vector3ic combineBlockPos, PlayerInteractInfo placementInfo) {
        var existingState = dimension.getBlockState(combineBlockPos);
        if (existingState.getBlockType() != BlockTypes.SEA_PICKLE) {
            return false;
        }

        var currentCount = existingState.getPropertyValue(CLUSTER_COUNT);
        if (currentCount >= CLUSTER_COUNT.getMax()) {
            return false;
        }

        var newState = existingState.setPropertyValue(CLUSTER_COUNT, currentCount + 1);
        boolean inWater = isInWater(dimension, combineBlockPos);
        newState = newState.setPropertyValue(DEAD_BIT, !inWater);
        dimension.setBlockState(combineBlockPos, newState);
        return true;
    }

    /**
     * Dispatches the inherited neighbor hook and breaks a pickle when a changed lower neighbor
     * is nonsolid. Other faces do not run this local support check or recompute the dead flag.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (face == BlockFace.DOWN) {
            if (!neighbor.getBlockStateData().isSolid()) {
                block.breakBlock();
            }
        }
    }

    /**
     * Dispatches the inherited layer hook and rereads the pickle at layer {@code 0}. The
     * supplied block describes the modified layer's old state, so current water presence and
     * the actual pickle state determine any dead-flag update.
     */
    @Override
    public void afterNeighborLayerReplace(Block currentBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterNeighborLayerReplace(currentBlock, newBlockState, placementInfo);
        // currentBlock is the old state of the modified layer, not this layer.
        // Read the actual block state at layer 0 from the dimension.
        var dimension = currentBlock.getDimension();
        var pos = currentBlock.getPosition();
        var blockState = dimension.getBlockState(pos);
        if (blockState.getBlockType() != BlockTypes.SEA_PICKLE) return;
        boolean inWater = isInWater(dimension, pos);
        boolean currentDead = blockState.getPropertyValue(DEAD_BIT);
        if (currentDead == inWater) {
            dimension.setBlockState(pos, blockState.setPropertyValue(DEAD_BIT, !inWater));
        }
    }

    /**
     * Handles cluster stacking or bone meal after inherited interaction handling. A matching
     * pickle item increases an unfinished cluster and consumes the held item. Bone meal
     * requires a live pickle with a coral-named support and first fills the existing cluster.
     *
     * <p>
     * The spread scan samples a five-by-three-by-five region, excluding the source, and
     * considers one-sixth of its candidates. New positions must be air, above coral-named
     * support, and contain water. The callback consumes bone meal after the scan even if no
     * target qualifies, and it ignores dimension write results rather than providing
     * coordinated placement or cancellation.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (itemStack instanceof ItemSeaPickleStack) {
            var pos = interactInfo.clickedBlockPos();
            var existingState = dimension.getBlockState(pos);
            if (existingState.getBlockType() == BlockTypes.SEA_PICKLE) {
                var currentCount = existingState.getPropertyValue(CLUSTER_COUNT);
                if (currentCount < CLUSTER_COUNT.getMax()) {
                    boolean inWater = isInWater(dimension, pos);
                    var newState = existingState
                            .setPropertyValue(CLUSTER_COUNT, currentCount + 1)
                            .setPropertyValue(DEAD_BIT, !inWater);
                    dimension.setBlockState(pos, newState);
                    interactInfo.player().tryConsumeItemInHand();
                    return true;
                }
            }
        }

        if (itemStack == null || itemStack.getItemType() != ItemTypes.BONE_MEAL) {
            return false;
        }

        var clickedBlock = interactInfo.getClickedBlock();
        var dead = clickedBlock.getPropertyValue(DEAD_BIT);
        if (dead) {
            return false;
        }

        var downState = dimension.getBlockState(BlockFace.DOWN.offsetPos(interactInfo.clickedBlockPos()));
        if (!isCoralBlock(downState)) {
            return false;
        }

        var currentCount = clickedBlock.getPropertyValue(CLUSTER_COUNT);
        if (currentCount < CLUSTER_COUNT.getMax()) {
            var newState = clickedBlock.getBlockState().setPropertyValue(CLUSTER_COUNT, CLUSTER_COUNT.getMax());
            dimension.setBlockState(interactInfo.clickedBlockPos(), newState);
        }

        var pos = interactInfo.clickedBlockPos();
        var random = ThreadLocalRandom.current();
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                for (int y = -1; y <= 1; y++) {
                    if (x == 0 && y == 0 && z == 0) continue;
                    // Sample candidates independently so a valid coral patch is only partly
                    // populated by one bone-meal action rather than filled at every eligible position.
                    if (random.nextInt(6) != 0) continue;

                    var targetPos = new org.joml.Vector3i(pos.x() + x, pos.y() + y, pos.z() + z);
                    if (dimension.getBlockState(targetPos).getBlockType() != BlockTypes.AIR) continue;
                    var targetDown = dimension.getBlockState(BlockFace.DOWN.offsetPos(targetPos));
                    if (!isCoralBlock(targetDown)) continue;
                    if (!isInWater(dimension, targetPos)) continue;

                    dimension.setBlockState(targetPos,
                            BlockTypes.SEA_PICKLE.getDefaultState()
                                    .setPropertyValue(CLUSTER_COUNT, 0)
                                    .setPropertyValue(DEAD_BIT, false)
                    );
                }
            }
        }

        interactInfo.player().tryConsumeItemInHand();
        dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);
        return true;
    }

    /**
     * Returns one item for each pickle represented by the cluster encoding. {@code
     * CLUSTER_COUNT} is zero-based, so the stack count is the stored property plus one for
     * every age or dead state.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        var count = block.getPropertyValue(CLUSTER_COUNT) + 1;
        return Set.of(blockType.getItemType().createItemStack(count));
    }

    /**
     * Returns whether the current liquid lookup identifies a water-family state.
     */
    protected boolean isInWater(Dimension dimension, Vector3ic pos) {
        var liquid = dimension.getLiquid(pos).right();
        return liquid != null && liquid.getBlockType().hasBlockTag(BlockTags.WATER);
    }

    /**
     * Returns whether the block identifier text contains {@code "coral_block"}. The name test
     * is used for bone-meal spread eligibility and does not distinguish living coral from
     * other matching identifiers.
     */
    protected boolean isCoralBlock(BlockState blockState) {
        var name = blockState.getBlockType().getIdentifier().toString();
        return name.contains("coral_block");
    }
}
