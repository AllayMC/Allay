package org.allaymc.server.block.component.sapling;

import lombok.Getter;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.BlockNeighborUpdateContext;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.feature.WorldFeature;
import org.allaymc.api.world.feature.WorldFeatureContext;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Sapling base component implementation.
 * Handles sapling growth through random tick and bone meal.
 *
 * @author daoge_cmd
 */
@Getter
public class BlockSaplingBaseComponentImpl extends BlockBaseComponentImpl {

    protected final Identifier treeFeatureId;
    protected final Identifier secondaryTreeFeatureId;
    protected final Identifier megaTreeFeatureId;

    public BlockSaplingBaseComponentImpl(
            BlockType<? extends BlockBehavior> blockType,
            Identifier treeFeatureId,
            Identifier secondaryTreeFeatureId,
            Identifier megaTreeFeatureId
    ) {
        super(blockType);
        this.treeFeatureId = treeFeatureId;
        this.secondaryTreeFeatureId = secondaryTreeFeatureId;
        this.megaTreeFeatureId = megaTreeFeatureId;
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        // Check if block below can support sapling
        var blockBelow = dimension.getBlockState(placeBlockPos.x(), placeBlockPos.y() - 1, placeBlockPos.z());
        if (!canPlaceOn(blockBelow)) {
            return false;
        }

        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    @Override
    public void onNeighborUpdate(BlockNeighborUpdateContext context) {
        super.onNeighborUpdate(context);

        var block = context.block();
        var face = context.face();
        if (face == BlockFace.DOWN) {
            var blockBelow = block.offsetPos(BlockFace.DOWN);
            if (!canPlaceOn(blockBelow.getBlockState())) {
                block.breakBlock();
            }
        }
    }

    /**
     * Check if sapling can be placed on this block.
     */
    protected boolean canPlaceOn(BlockState blockState) {
        return blockState.getBlockType().hasBlockTag(BlockTags.DIRT);
    }

    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        // Check light level
        var lightLevel = block.getDimension().getLightEngine().getInternalLight(block.getPosition());
        if (lightLevel < 9) {
            return;
        }

        // Get age bit
        boolean aged = block.getPropertyValue(BlockPropertyTypes.AGE_BIT);

        if (!aged) {
            // First stage: set to mature
            block.getDimension().setBlockState(
                    block.getPosition(),
                    block.getBlockState().setPropertyValue(BlockPropertyTypes.AGE_BIT, true)
            );
        } else {
            // Second stage: try to grow tree
            growTree(block);
        }
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        // Handle bone meal
        if (itemStack.getItemType() == ItemTypes.BONE_MEAL) {
            // 45% failure rate
            if (ThreadLocalRandom.current().nextFloat() < 0.45f) {
                // Show particle even on failure
                dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);
                interactInfo.player().tryConsumeItemInHand();
                return true;
            }

            var block = interactInfo.getClickedBlock();
            if (growTree(block)) {
                dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);
                interactInfo.player().tryConsumeItemInHand();
                return true;
            }

            // Particle even if growth failed
            dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);
            interactInfo.player().tryConsumeItemInHand();
            return true;
        }

        return false;
    }

    /**
     * Attempt to grow a tree at the sapling position.
     *
     * @param block the sapling block
     * @return true if the tree was successfully grown
     */
    protected boolean growTree(Block block) {
        var dimension = block.getDimension();
        var pos = block.getPosition();

        // Check for 2x2 sapling arrangement for mega trees
        if (megaTreeFeatureId != null) {
            var corner = findSaplingCorner(block);
            if (corner != null) {
                // Found 2x2 arrangement - try mega tree
                var megaFeature = Registries.WORLD_FEATURES.get(megaTreeFeatureId);
                if (megaFeature != null) {
                    return tryGrowMegaTree(block, corner, megaFeature);
                }
            }
        }

        // Determine which tree feature to use
        WorldFeature feature = selectTreeFeature();

        if (feature == null) {
            return false;
        }

        // Create runtime context
        var context = new WorldFeatureContext(dimension.getChunkManager());

        // Remove sapling first
        dimension.setBlockState(pos.x(), pos.y(), pos.z(), BlockTypes.AIR.getDefaultState());

        // Try to place tree
        boolean success = feature.place(context, pos.x(), pos.y(), pos.z());

        if (!success) {
            // Restore sapling if tree failed to grow
            dimension.setBlockState(pos.x(), pos.y(), pos.z(), block.getBlockState());
        }

        return success;
    }

    /**
     * Select which tree feature to use.
     * If secondaryTreeFeatureId is set, randomly choose between primary and secondary.
     */
    protected WorldFeature selectTreeFeature() {
        if (secondaryTreeFeatureId != null && ThreadLocalRandom.current().nextFloat() < 0.1f) {
            return Registries.WORLD_FEATURES.get(secondaryTreeFeatureId);
        }
        return treeFeatureId != null ? Registries.WORLD_FEATURES.get(treeFeatureId) : null;
    }

    /**
     * Try to grow a mega tree from a 2x2 sapling arrangement.
     */
    protected boolean tryGrowMegaTree(Block block, Vector3ic corner, WorldFeature megaFeature) {
        var dimension = block.getDimension();
        var saplingState = block.getBlockState();

        // Remove all 4 saplings
        for (int dx = 0; dx <= 1; dx++) {
            for (int dz = 0; dz <= 1; dz++) {
                dimension.setBlockState(corner.x() + dx, corner.y(), corner.z() + dz, BlockTypes.AIR.getDefaultState());
            }
        }

        // Create runtime context
        var context = new WorldFeatureContext(dimension.getChunkManager());

        // Try to place mega tree at the corner
        boolean success = megaFeature.place(context, corner.x(), corner.y(), corner.z());

        if (!success) {
            // Restore all 4 saplings
            for (int dx = 0; dx <= 1; dx++) {
                for (int dz = 0; dz <= 1; dz++) {
                    dimension.setBlockState(corner.x() + dx, corner.y(), corner.z() + dz, saplingState);
                }
            }
        }

        return success;
    }

    /**
     * Find the corner of a 2x2 sapling arrangement.
     * Returns the position of the north-west corner, or null if not found.
     */
    protected Vector3ic findSaplingCorner(Block block) {
        var pos = block.getPosition();
        var dimension = block.getDimension();
        var saplingType = block.getBlockType();

        // Check all 4 possible corners where this sapling could be
        int[][] offsets = {{0, 0}, {-1, 0}, {0, -1}, {-1, -1}};

        for (int[] offset : offsets) {
            int cornerX = pos.x() + offset[0];
            int cornerZ = pos.z() + offset[1];

            boolean valid = true;
            for (int dx = 0; dx <= 1 && valid; dx++) {
                for (int dz = 0; dz <= 1 && valid; dz++) {
                    var checkBlock = dimension.getBlockState(cornerX + dx, pos.y(), cornerZ + dz);
                    if (checkBlock.getBlockType() != saplingType) {
                        valid = false;
                    }
                }
            }

            if (valid) {
                return new org.joml.Vector3i(cornerX, pos.y(), cornerZ);
            }
        }

        return null;
    }
}
