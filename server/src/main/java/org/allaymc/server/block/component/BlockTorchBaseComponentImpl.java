package org.allaymc.server.block.component;

import com.google.common.collect.BiMap;
import com.google.common.collect.EnumBiMap;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.NeighborUpdate;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.enums.TorchFacingDirection;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.world.Dimension;
import org.joml.Vector3ic;

/**
 * @author daoge_cmd
 */
public class BlockTorchBaseComponentImpl extends BlockBaseComponentImpl {
    private static final BiMap<BlockFace, TorchFacingDirection> TORCH_FACING_DIRECTION_MAPPER = EnumBiMap.create(BlockFace.class, TorchFacingDirection.class);

    static {
        TORCH_FACING_DIRECTION_MAPPER.put(BlockFace.DOWN, TorchFacingDirection.UNKNOWN);
        TORCH_FACING_DIRECTION_MAPPER.put(BlockFace.UP, TorchFacingDirection.TOP);
        TORCH_FACING_DIRECTION_MAPPER.put(BlockFace.NORTH, TorchFacingDirection.SOUTH);
        TORCH_FACING_DIRECTION_MAPPER.put(BlockFace.SOUTH, TorchFacingDirection.NORTH);
        TORCH_FACING_DIRECTION_MAPPER.put(BlockFace.WEST, TorchFacingDirection.EAST);
        TORCH_FACING_DIRECTION_MAPPER.put(BlockFace.EAST, TorchFacingDirection.WEST);
    }

    public BlockTorchBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onNeighborUpdate(NeighborUpdate context) {
        super.onNeighborUpdate(context);

        var block = context.block();
        var neighbor = context.neighbor();
        var face = context.face();

        if (face == BlockFace.UP) {
            return;
        }

        var torchFacingDirection = block.getPropertyValue(BlockPropertyTypes.TORCH_FACING_DIRECTION);
        var blockFace = TORCH_FACING_DIRECTION_MAPPER.inverse().get(torchFacingDirection);
        if (!canBeSupportedAt(block.offsetPos(blockFace.opposite()).getBlockState(), blockFace)) {
            block.breakBlock();
        }
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        if (placementInfo == null) {
            return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState);
        }

        var face = placementInfo.blockFace();
        if (face == BlockFace.DOWN || !canBeSupportedAt(placementInfo.getClickedBlock().getBlockState(), face)) {
            face = findValidFace(dimension, placeBlockPos);
        }

        if (face == null) {
            return false;
        }

        blockState = blockState.setPropertyValue(BlockPropertyTypes.TORCH_FACING_DIRECTION, TORCH_FACING_DIRECTION_MAPPER.get(face));
        return dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
    }

    private BlockFace findValidFace(Dimension dimension, Vector3ic placeBlockPos) {
        for (var face : new BlockFace[]{BlockFace.SOUTH, BlockFace.WEST, BlockFace.NORTH, BlockFace.EAST, BlockFace.DOWN}) {
            if (canBeSupportedAt(dimension.getBlockState(face.offsetPos(placeBlockPos)), face.opposite())) {
                return face.opposite();
            }
        }

        return null;
    }

    private boolean canBeSupportedAt(BlockState blockState, BlockFace face) {
        var shape = blockState.getBlockStateData().collisionShape();
        return face == BlockFace.UP ? shape.isCenterFull(BlockFace.UP) : shape.isFull(face);
    }
}
