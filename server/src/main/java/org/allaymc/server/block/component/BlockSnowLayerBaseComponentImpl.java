package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockFadeEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

import java.util.Set;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.HEIGHT;

/**
 * @author IWareQ
 */
public class BlockSnowLayerBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockSnowLayerBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);

        if (block.getDimension().getLightService().getInternalLight(block.getPosition()) > 11) {
            var event = new BlockFadeEvent(block, BlockTypes.AIR.getDefaultState());
            if (event.call()) {
                block.getDimension().setBlockState(block.getPosition(), event.getNewBlockState());
            }
        }
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return super.place(dimension, blockState, placeBlockPos, null);
        }

        var clickedBlockState = placementInfo.getClickedBlock();
        if (clickedBlockState.getBlockType() == BlockTypes.SNOW_LAYER) {
            var height = clickedBlockState.getPropertyValue(HEIGHT);
            if (height == HEIGHT.getMax()) {
                return false;
            }

            blockState = blockState.setPropertyValue(HEIGHT, height + 1);
        }

        if (!canBeSupportedAt(dimension.getBlockState(BlockFace.DOWN.offsetPos(placeBlockPos)))) {
            return false;
        }

        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
    }

    private boolean canBeSupportedAt(BlockState blockState) {
        var shape = blockState.getBlockStateData().collisionShape();
        return shape.isFull(BlockFace.UP);
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return Set.of(ItemTypes.SNOWBALL.createItemStack((int) Math.max(1, (double) (block.getPropertyValue(HEIGHT) / 2))));
    }

    @Override
    public boolean canRandomUpdate() {
        return true;
    }
}
