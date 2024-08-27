package org.allaymc.server.block.component.sign;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.PlaceBlockHelper;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.poi.BlockStateWithPos;
import org.allaymc.api.block.poi.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

/**
 * Allay Project 2024/8/17
 *
 * @author daoge_cmd
 */
@RequireBlockProperty(type = BlockPropertyType.Type.BOOLEAN, name = "attached_bit")
@RequireBlockProperty(type = BlockPropertyType.Type.INT, name = "facing_direction")
@RequireBlockProperty(type = BlockPropertyType.Type.INT, name = "ground_sign_direction")
@RequireBlockProperty(type = BlockPropertyType.Type.BOOLEAN, name = "hanging")
public class BlockHangingSignBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockHangingSignBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        checkPlaceMethodParam(dimension, blockState, placeBlockPos, placementInfo);
        if (placementInfo == null) return super.place(dimension, blockState, placeBlockPos, null);

        var face = placementInfo.blockFace();
        if (face == BlockFace.UP) return false;
        if (face == BlockFace.DOWN) {
            blockState = blockState.setProperty(VanillaBlockPropertyTypes.HANGING, true);
            var upperBlock = dimension.getBlockState(placeBlockPos.x(), placeBlockPos.y() + 1, placeBlockPos.z());
            if (!upperBlock.getBlockType().getMaterial().isSolid()) return false;

            var shape = upperBlock.getBlockStateData().collisionShape();
            var full = shape.isFull(BlockFace.DOWN);
            var centerFull = shape.isCenterFull(BlockFace.DOWN);
            if (placementInfo.player().isSneaking() || (centerFull && !full)) {
                blockState = blockState.setProperty(VanillaBlockPropertyTypes.ATTACHED_BIT, true);
                blockState = PlaceBlockHelper.processGroundSignDirectionProperty(blockState, placeBlockPos, placementInfo);
            } else if (full) {
                blockState = PlaceBlockHelper.processFacingDirectionProperty(blockState, placeBlockPos, placementInfo);
            } else return false;
        } else {
            blockState = blockState.setProperty(VanillaBlockPropertyTypes.FACING_DIRECTION, face.opposite().rotateY().ordinal());
        }

        dimension.setBlockState(placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(), blockState, placementInfo);
        return true;
    }

    @Override
    public boolean canKeepExisting(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        if (!current.blockState().getPropertyValue(VanillaBlockPropertyTypes.HANGING)) return true;
        if (face != BlockFace.UP) return true;
        var upperBlock = neighbor.blockState();
        if (!upperBlock.getBlockType().getMaterial().isSolid()) return false;
        return upperBlock.getBlockStateData().collisionShape().isCenterFull(BlockFace.DOWN);
    }
}
