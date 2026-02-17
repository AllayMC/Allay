package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
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
 * @author daoge_cmd
 */
public class BlockMushroomBaseComponentImpl extends BlockBaseComponentImpl {

    protected final Identifier mushroomFeatureId;

    public BlockMushroomBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, Identifier mushroomFeatureId) {
        super(blockType);
        this.mushroomFeatureId = mushroomFeatureId;
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
        if (!canSurvive(block.getDimension(), block.getPosition())) {
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

        interactInfo.player().tryConsumeItemInHand();
        dimension.addParticle(MathUtils.center(interactInfo.clickedBlockPos()), SimpleParticle.BONE_MEAL);

        // 40% chance to attempt growth
        if (ThreadLocalRandom.current().nextInt(5) < 2) {
            growMushroom(interactInfo.getClickedBlock());
        }

        return true;
    }

    protected boolean growMushroom(Block block) {
        var feature = Registries.WORLD_FEATURES.get(mushroomFeatureId);
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
        var downPos = BlockFace.DOWN.offsetPos(pos);
        var downState = dimension.getBlockState(downPos);
        var downBlockType = downState.getBlockType();

        // Can always grow on mycelium, podzol, or nylium
        if (downBlockType == BlockTypes.MYCELIUM ||
            downBlockType == BlockTypes.PODZOL ||
            downBlockType == BlockTypes.CRIMSON_NYLIUM ||
            downBlockType == BlockTypes.WARPED_NYLIUM) {
            return true;
        }

        // On other solid blocks, requires light level < 13
        if (downState.getBlockStateData().isSolid()) {
            return dimension.getLightEngine().getInternalLight(pos) < 13;
        }

        return false;
    }
}
