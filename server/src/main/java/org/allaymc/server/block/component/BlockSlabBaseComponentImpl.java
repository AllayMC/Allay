package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.BlockSlabBaseComponent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.MinecraftVerticalHalf;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * @author daoge_cmd
 */
public class BlockSlabBaseComponentImpl extends BlockBaseComponentImpl implements BlockSlabBaseComponent {
    protected BlockId doubleSlabId;

    public BlockSlabBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, BlockId doubleSlabId) {
        super(blockType);
        this.doubleSlabId = doubleSlabId;
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
            return true;
        }

        var face = placementInfo.blockFace();
        var clickedBlockPos = placementInfo.clickedBlockPos();
        var clickedPos = placementInfo.clickedPos();
        var clickedBlock = placementInfo.getClickedBlockState();
        switch (face) {
            case UP -> {
                if (clickedBlock.getBlockType() == this.blockType && clickedBlock.getPropertyValue(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF) == MinecraftVerticalHalf.BOTTOM) {
                    dimension.setBlockState(clickedBlockPos, getDoubleSlabBlockType().getDefaultState());
                } else {
                    dimension.setBlockState(placeBlockPos, blockState.setProperty(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF, MinecraftVerticalHalf.BOTTOM));
                }
            }
            case DOWN -> {
                if (clickedBlock.getBlockType() == this.blockType && clickedBlock.getPropertyValue(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF) == MinecraftVerticalHalf.TOP) {
                    dimension.setBlockState(clickedBlockPos, getDoubleSlabBlockType().getDefaultState());
                } else {
                    dimension.setBlockState(placeBlockPos, blockState.setProperty(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF, MinecraftVerticalHalf.TOP));
                }
            }
            default -> {
                dimension.setBlockState(placeBlockPos, blockState.setProperty(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF, clickedPos.y() > 0.5f ? MinecraftVerticalHalf.TOP : MinecraftVerticalHalf.BOTTOM));
            }
        }
        return true;
    }

    @Override
    public boolean combine(Dimension dimension, BlockState blockState, Vector3ic combineBlockPos, PlayerInteractInfo placementInfo) {
        var combineBlock = dimension.getBlockState(combineBlockPos);
        if (combineBlock.getBlockType() != this.blockType) {
            return false;
        }

        var value = combineBlock.getPropertyValue(BlockPropertyTypes.MINECRAFT_VERTICAL_HALF);
        if (value != (placementInfo.clickedPos().y() > 0.5f ? MinecraftVerticalHalf.TOP : MinecraftVerticalHalf.BOTTOM)) {
            // They are complementary
            dimension.setBlockState(combineBlockPos, getDoubleSlabBlockType().getDefaultState());
            return true;
        }

        return false;
    }

    @Override
    public boolean canLiquidFlowIntoSide(BlockState blockState, BlockFace blockFace) {
        return !blockState.getBlockStateData().collisionShape().isFull(blockFace);
    }

    @Override
    public BlockType<?> getDoubleSlabBlockType() {
        return doubleSlabId.getBlockType();
    }
}
