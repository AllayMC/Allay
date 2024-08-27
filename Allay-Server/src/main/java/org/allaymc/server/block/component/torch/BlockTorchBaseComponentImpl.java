package org.allaymc.server.block.component.torch;

import com.google.common.collect.BiMap;
import com.google.common.collect.EnumBiMap;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.poi.BlockStateWithPos;
import org.allaymc.api.block.poi.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.TorchFacingDirection;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.tag.BlockTags;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

@RequireBlockProperty(type = BlockPropertyType.Type.ENUM, name = "torch_facing_direction")
public class BlockTorchBaseComponentImpl extends BlockBaseComponentImpl {
    private static final BiMap<BlockFace, TorchFacingDirection> map = EnumBiMap.create(BlockFace.class, TorchFacingDirection.class);

    static {
        map.put(BlockFace.DOWN, TorchFacingDirection.UNKNOWN);
        map.put(BlockFace.UP, TorchFacingDirection.TOP);
        map.put(BlockFace.NORTH, TorchFacingDirection.SOUTH);
        map.put(BlockFace.SOUTH, TorchFacingDirection.NORTH);
        map.put(BlockFace.WEST, TorchFacingDirection.EAST);
        map.put(BlockFace.EAST, TorchFacingDirection.WEST);
    }

    public BlockTorchBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        checkPlaceMethodParam(dimension, blockState, placeBlockPos, placementInfo);
        if (placementInfo == null) {
            dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
            return true;
        }

        var oldBlock = dimension.getBlockState(placeBlockPos);
        var torchFace = map.get(placementInfo.blockFace());

        if (!oldBlock.getBlockType().hasBlockTag(BlockTags.REPLACEABLE) || torchFace == TorchFacingDirection.UNKNOWN) return false;

        var targetBlock = dimension.getBlockState(placementInfo.clickBlockPos());
        BlockType<?> blockType2 = targetBlock.getBlockType();
        if (blockType2.getMaterial().isSolid()) {
            blockState = blockState.setProperty(BlockPropertyTypes.TORCH_FACING_DIRECTION, torchFace);
        } else {
            blockState = blockState.setProperty(BlockPropertyTypes.TORCH_FACING_DIRECTION, TorchFacingDirection.TOP);
            var downBlock = dimension.getBlockState(placeBlockPos.x(), placeBlockPos.y() - 1, placeBlockPos.z());
            BlockType<?> blockType1 = downBlock.getBlockType();
            if (!blockType1.getMaterial().isSolid()) return false;
        }

        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
        return true;
    }

    @Override
    public boolean canKeepExisting(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        if (face == BlockFace.UP) return true;
        var torchFacingDirection = current.blockState().getPropertyValue(BlockPropertyTypes.TORCH_FACING_DIRECTION);
        var blockFace = map.inverse().get(torchFacingDirection);
        var block = current.pos().dimension().getBlockState(blockFace.opposite().offsetPos(current.pos()));
        return block.getBlockType().getMaterial().isSolid();
    }
}
