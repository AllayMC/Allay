package org.allaymc.server.block.component.sapling;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.NeighborUpdate;
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
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.SimpleParticle;
import org.allaymc.server.world.feature.tree.MangroveTreeFeature;
import org.joml.Vector3ic;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Mangrove propagule block component implementation.
 * <p>
 * Mangrove propagules have two forms:
 * <ul>
 *   <li>Hanging (HANGING=true): Hangs from mangrove leaves, has 5 growth stages (0-4), cannot grow into tree</li>
 *   <li>Non-hanging (HANGING=false): Planted on ground, can grow into mangrove tree</li>
 * </ul>
 *
 * @author daoge_cmd
 */
public class BlockMangrovePropaguleBaseComponentImpl extends BlockSaplingBaseComponentImpl {

    /**
     * Minimum light level required for propagule to grow into tree.
     */
    protected static final int MIN_LIGHT_LEVEL = 9;

    /**
     * Maximum growth stage for hanging propagules (0-4).
     */
    protected static final int MAX_PROPAGULE_STAGE = 4;

    public BlockMangrovePropaguleBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType, MangroveTreeFeature.IDENTIFIER, null, null);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        var blockBelow = dimension.getBlockState(placeBlockPos.x(), placeBlockPos.y() - 1, placeBlockPos.z());

        // Check if it's being placed as hanging (under leaves)
        if (placementInfo != null && placementInfo.blockFace() == BlockFace.DOWN) {
            var blockAbove = dimension.getBlockState(placeBlockPos.x(), placeBlockPos.y() + 1, placeBlockPos.z());
            if (blockAbove.getBlockType().hasBlockTag(BlockTags.LEAVES)) {
                // Place as hanging propagule
                var hangingState = blockState
                        .setPropertyValue(BlockPropertyTypes.HANGING, true)
                        .setPropertyValue(BlockPropertyTypes.PROPAGULE_STAGE, 0);
                return dimension.setBlockState(
                        placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                        hangingState,
                        placementInfo
                );
            }
        }

        // Check if block below can support non-hanging propagule
        if (!canPlaceOn(blockBelow)) {
            return false;
        }

        // Place as non-hanging propagule
        var groundState = blockState
                .setPropertyValue(BlockPropertyTypes.HANGING, false)
                .setPropertyValue(BlockPropertyTypes.PROPAGULE_STAGE, 0);
        return dimension.setBlockState(
                placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                groundState,
                placementInfo
        );
    }

    @Override
    protected boolean canPlaceOn(BlockState blockState) {
        return super.canPlaceOn(blockState) ||
               blockState.getBlockType() == BlockTypes.CLAY;
    }

    @Override
    public void onNeighborUpdate(NeighborUpdate context) {
        var block = context.block();
        var face = context.face();
        boolean isHanging = block.getPropertyValue(BlockPropertyTypes.HANGING);

        if (isHanging) {
            // Hanging propagule: check block above (must be leaves)
            if (face == BlockFace.UP) {
                var blockAbove = block.offsetPos(BlockFace.UP);
                if (!blockAbove.getBlockType().hasBlockTag(BlockTags.LEAVES)) {
                    block.breakBlock();
                }
            }
        } else {
            // Non-hanging propagule: check block below (must be valid support)
            if (face == BlockFace.DOWN) {
                var blockBelow = block.offsetPos(BlockFace.DOWN);
                if (!canPlaceOn(blockBelow.getBlockState())) {
                    block.breakBlock();
                }
            }
        }
    }

    @Override
    public void onRandomUpdate(Block block) {
        boolean isHanging = block.getPropertyValue(BlockPropertyTypes.HANGING);
        int stage = block.getPropertyValue(BlockPropertyTypes.PROPAGULE_STAGE);

        if (isHanging) {
            // Hanging propagules grow through stages 0-4 but do not become trees
            if (stage < MAX_PROPAGULE_STAGE) {
                block.getDimension().setBlockState(
                        block.getPosition(),
                        block.getBlockState().setPropertyValue(BlockPropertyTypes.PROPAGULE_STAGE, stage + 1)
                );
            }
        } else {
            // Non-hanging propagules can grow into trees
            var lightLevel = block.getDimension().getLightEngine().getInternalLight(block.getPosition());
            if (lightLevel < MIN_LIGHT_LEVEL) {
                return;
            }

            // Non-hanging propagules use stages 0-4:
            // Stages 0-3: growing, stage 4: ready to become tree
            if (stage < MAX_PROPAGULE_STAGE) {
                // Grow to next stage
                block.getDimension().setBlockState(
                        block.getPosition(),
                        block.getBlockState().setPropertyValue(BlockPropertyTypes.PROPAGULE_STAGE, stage + 1)
                );
            } else {
                // At max stage: try to grow tree
                growTree(block);
            }
        }
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (itemStack.getItemType() != ItemTypes.BONE_MEAL) {
            return false;
        }

        var block = interactInfo.getClickedBlock();
        boolean isHanging = block.getPropertyValue(BlockPropertyTypes.HANGING);

        if (isHanging) {
            // Bone meal on hanging propagule: increase stage by 1
            int stage = block.getPropertyValue(BlockPropertyTypes.PROPAGULE_STAGE);
            if (stage < MAX_PROPAGULE_STAGE) {
                dimension.setBlockState(
                        block.getPosition(),
                        block.getBlockState().setPropertyValue(BlockPropertyTypes.PROPAGULE_STAGE, stage + 1)
                );
            }
            dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);
            interactInfo.player().tryConsumeItemInHand();
            return true;
        } else {
            // Bone meal on non-hanging propagule: try to grow tree (with 45% failure rate)
            if (ThreadLocalRandom.current().nextFloat() < 0.45f) {
                // Failed, but still consume and show particle
                dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);
                interactInfo.player().tryConsumeItemInHand();
                return true;
            }

            if (growTree(block)) {
                dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);
                interactInfo.player().tryConsumeItemInHand();
                return true;
            }

            // Growth failed, still show particle and consume
            dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);
            interactInfo.player().tryConsumeItemInHand();
            return true;
        }
    }
}
