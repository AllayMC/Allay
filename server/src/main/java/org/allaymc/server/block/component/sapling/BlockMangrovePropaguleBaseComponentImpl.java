package org.allaymc.server.block.component.sapling;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
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
 * {@code BlockMangrovePropaguleBaseComponentImpl} implements hanging and grounded mangrove
 * propagules over the shared sapling feature-growth machinery. Hanging placement recognizes
 * any leaves-tagged ceiling and starts at stage zero, while ground placement accepts the
 * sapling support set plus clay.
 *
 * <p>
 * Random updates advance hanging stages without tree growth. Grounded propagules require
 * sufficient light, advance through the same stages, and attempt the mangrove feature only
 * after maturity. Bone meal advances hanging propagules but can attempt a grounded tree
 * regardless of stage and light. Every normally completed bone-meal branch consumes the item
 * and emits particles, including mature hanging propagules and unsuccessful grounded attempts;
 * tree restoration follows the inherited partial-failure behavior.
 *
 * @author daoge_cmd
 */
public class BlockMangrovePropaguleBaseComponentImpl extends BlockSaplingBaseComponentImpl {

    /**
     * {@code MIN_LIGHT_LEVEL} gates grounded propagule stage advancement and tree attempts
     * during natural random updates, while bone meal bypasses it.
     */
    protected static final int MIN_LIGHT_LEVEL = 9;

    /**
     * {@code MAX_PROPAGULE_STAGE} marks mature propagules and bounds stage increments for both
     * hanging and grounded growth paths in this component.
     */
    protected static final int MAX_PROPAGULE_STAGE = 4;

    /**
     * Creates the mangrove propagule behavior component for the supplied block type.
     */
    public BlockMangrovePropaguleBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType, MangroveTreeFeature.IDENTIFIER, null, null);
    }

    /**
     * Places a hanging stage-zero propagule below a leaves-tagged block when the interaction
     * uses the downward face. Otherwise valid ground permits a nonhanging stage-zero state,
     * even when the attempted hanging branch did not find leaves.
     *
     * <p>
     * The ground fallback uses {@code canPlaceOn} without requiring an upper-face click. Both
     * forms write their initialized state directly rather than passing through the ordinary
     * sapling property processors. The method returns the dimension's final placement result.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        var blockBelow = dimension.getBlockState(placeBlockPos.x(), placeBlockPos.y() - 1, placeBlockPos.z());

        if (placementInfo != null && placementInfo.blockFace() == BlockFace.DOWN) {
            var blockAbove = dimension.getBlockState(placeBlockPos.x(), placeBlockPos.y() + 1, placeBlockPos.z());
            if (blockAbove.getBlockType().hasBlockTag(BlockTags.LEAVES)) {
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

        if (!canPlaceOn(blockBelow)) {
            return false;
        }

        var groundState = blockState
                .setPropertyValue(BlockPropertyTypes.HANGING, false)
                .setPropertyValue(BlockPropertyTypes.PROPAGULE_STAGE, 0);
        return dimension.setBlockState(
                placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                groundState,
                placementInfo
        );
    }

    /**
     * Returns whether ordinary sapling support or clay supports a grounded propagule.
     */
    @Override
    protected boolean canPlaceOn(BlockState blockState) {
        return super.canPlaceOn(blockState) ||
               blockState.getBlockType() == BlockTypes.CLAY;
    }

    /**
     * Validates the support face selected by the propagule's hanging flag. Hanging propagules
     * require leaves above, while grounded propagules require accepted ground below; unrelated
     * faces do not invoke those local removal checks.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        boolean isHanging = block.getPropertyValue(BlockPropertyTypes.HANGING);

        if (isHanging) {
            if (face == BlockFace.UP) {
                var blockAbove = block.offsetPos(BlockFace.UP);
                if (!blockAbove.getBlockType().hasBlockTag(BlockTags.LEAVES)) {
                    block.breakBlock();
                }
            }
        } else {
            if (face == BlockFace.DOWN) {
                var blockBelow = block.offsetPos(BlockFace.DOWN);
                if (!canPlaceOn(blockBelow.getBlockState())) {
                    block.breakBlock();
                }
            }
        }
    }

    /**
     * Advances propagule stages or attempts a grounded mangrove tree. Hanging propagules
     * increase their stage up to four without a light check and never enter tree generation
     * through this callback.
     *
     * <p>
     * Grounded propagules require internal light of at least nine, then either advance one
     * stage or invoke inherited tree growth at maturity. The override does not dispatch the
     * inherited random-update hook and writes stage changes directly without a growth event.
     * Tree success or failure remains governed by the inherited feature helper.
     */
    @Override
    public void onRandomUpdate(Block block) {
        boolean isHanging = block.getPropertyValue(BlockPropertyTypes.HANGING);
        int stage = block.getPropertyValue(BlockPropertyTypes.PROPAGULE_STAGE);

        if (isHanging) {
            if (stage < MAX_PROPAGULE_STAGE) {
                block.getDimension().setBlockState(
                        block.getPosition(),
                        block.getBlockState().setPropertyValue(BlockPropertyTypes.PROPAGULE_STAGE, stage + 1)
                );
            }
        } else {
            var lightLevel = block.getDimension().getLightEngine().getInternalLight(block.getPosition());
            // Grounded natural growth needs light; hanging maturation does not.
            if (lightLevel < MIN_LIGHT_LEVEL) {
                return;
            }

            if (stage < MAX_PROPAGULE_STAGE) {
                block.getDimension().setBlockState(
                        block.getPosition(),
                        block.getBlockState().setPropertyValue(BlockPropertyTypes.PROPAGULE_STAGE, stage + 1)
                );
            } else {
                growTree(block);
            }
        }
    }

    /**
     * Handles bone meal according to hanging state without inherited interaction handling.
     * Hanging propagules advance one stage when immature and still consume bone meal at
     * maturity. Grounded propagules sample a forty-five-percent no-growth branch, otherwise
     * attempt the tree directly.
     *
     * <p>
     * Every normally completed bone-meal branch emits particles and consumes the held item,
     * including feature attempts returning {@code false}. A thrown feature failure escapes
     * before those later effects. Grounded attempts do not require mature stage or the
     * natural-growth light threshold. The held {@code itemStack} must be nonnull because the
     * callback reads its type directly.
     */
    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (itemStack.getItemType() != ItemTypes.BONE_MEAL) {
            return false;
        }

        var block = interactInfo.getClickedBlock();
        boolean isHanging = block.getPropertyValue(BlockPropertyTypes.HANGING);

        if (isHanging) {
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
            if (ThreadLocalRandom.current().nextFloat() < 0.45f) {
                dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);
                interactInfo.player().tryConsumeItemInHand();
                return true;
            }

            if (growTree(block)) {
                dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);
                interactInfo.player().tryConsumeItemInHand();
                return true;
            }

            dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);
            interactInfo.player().tryConsumeItemInHand();
            return true;
        }
    }
}
