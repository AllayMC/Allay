package org.allaymc.server.block.component.plant;

import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.feature.WorldFeatureContext;
import org.allaymc.api.world.particle.SimpleParticle;
import org.joml.Vector3ic;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Block component for azalea and flowering azalea blocks.
 * Handles bone meal interaction to grow azalea trees.
 *
 * @author daoge_cmd
 */
public class BlockAzaleaBaseComponentImpl extends BlockBaseComponentImpl {

    protected final Identifier treeFeatureId;

    public BlockAzaleaBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, Identifier treeFeatureId) {
        super(blockType);
        this.treeFeatureId = treeFeatureId;
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo != null && placementInfo.blockFace() != BlockFace.UP) {
            return false;
        }
        if (!canSurvive(dimension, placeBlockPos)) {
            return false;
        }
        return super.place(dimension, blockState, placeBlockPos, placementInfo);
    }

    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        if (face == BlockFace.DOWN && !canSurvive(block.getDimension(), block.getPosition())) {
            block.breakBlock();
        }
    }

    @Override
    public boolean onInteract(ItemStack itemStack, Dimension dimension, PlayerInteractInfo interactInfo) {
        if (super.onInteract(itemStack, dimension, interactInfo)) {
            return true;
        }

        if (itemStack == null || itemStack.getItemType() != ItemTypes.BONE_MEAL) {
            return false;
        }

        // 45% failure rate
        interactInfo.player().tryConsumeItemInHand();
        dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);

        if (ThreadLocalRandom.current().nextFloat() < 0.45f) {
            return true;
        }

        growTree(interactInfo.getClickedBlock());
        return true;
    }

    protected boolean growTree(Block block) {
        var feature = Registries.WORLD_FEATURES.get(treeFeatureId);
        if (feature == null) {
            return false;
        }

        var dimension = block.getDimension();
        var pos = block.getPosition();
        var originalState = block.getBlockState();

        var context = new WorldFeatureContext(dimension.getChunkManager());
        dimension.setBlockState(pos.x(), pos.y(), pos.z(), BlockTypes.AIR.getDefaultState());

        boolean success = feature.place(context, pos.x(), pos.y(), pos.z());
        if (!success) {
            dimension.setBlockState(pos.x(), pos.y(), pos.z(), originalState);
        }
        return success;
    }

    protected boolean canSurvive(Dimension dimension, Vector3ic pos) {
        var downBlockType = dimension.getBlockState(BlockFace.DOWN.offsetPos(pos)).getBlockType();
        return downBlockType.hasBlockTag(BlockTags.DIRT) ||
               downBlockType == BlockTypes.CLAY;
    }
}
