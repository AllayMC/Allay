package org.allaymc.server.block.component;

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
import org.joml.Vector3ic;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.CLUSTER_COUNT;
import static org.allaymc.api.block.property.type.BlockPropertyTypes.DEAD_BIT;

/**
 * @author daoge_cmd
 */
public class BlockSeaPickleBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockSeaPickleBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null && placementInfo.blockFace() != BlockFace.UP) {
            return false;
        }

        // Must be on solid block
        var downState = dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos));
        if (!downState.getBlockStateData().isSolid()) {
            return false;
        }

        // Determine alive/dead based on water presence
        boolean inWater = isInWater(dimension, placeBlockPos);
        blockState = blockState.setPropertyValue(DEAD_BIT, !inWater);

        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

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

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        if (face == BlockFace.DOWN) {
            if (!neighbor.getBlockStateData().isSolid()) {
                block.breakBlock();
            }
        }
    }

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

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        // Stacking: clicking an existing sea pickle with a sea pickle item
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

        // Check if on coral block
        var downState = dimension.getBlockState(BlockFace.DOWN.offsetPos(interactInfo.clickedBlockPos()));
        if (!isCoralBlock(downState)) {
            return false;
        }

        // Fill to 4 first
        var currentCount = clickedBlock.getPropertyValue(CLUSTER_COUNT);
        if (currentCount < CLUSTER_COUNT.getMax()) {
            var newState = clickedBlock.getBlockState().setPropertyValue(CLUSTER_COUNT, CLUSTER_COUNT.getMax());
            dimension.setBlockState(interactInfo.clickedBlockPos(), newState);
        }

        // Spread to nearby coral blocks
        var pos = interactInfo.clickedBlockPos();
        var random = ThreadLocalRandom.current();
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                for (int y = -1; y <= 1; y++) {
                    if (x == 0 && y == 0 && z == 0) continue;
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

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        var count = block.getPropertyValue(CLUSTER_COUNT) + 1;
        return Set.of(blockType.getItemType().createItemStack(count));
    }

    protected boolean isInWater(Dimension dimension, Vector3ic pos) {
        var liquid = dimension.getLiquid(pos).right();
        return liquid != null && liquid.getBlockType().hasBlockTag(BlockTags.WATER);
    }

    protected boolean isCoralBlock(BlockState blockState) {
        var name = blockState.getBlockType().getIdentifier().toString();
        return name.contains("coral_block");
    }
}
