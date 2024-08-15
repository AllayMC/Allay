package org.allaymc.server.block.component.sign;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.component.RequireBlockProperty;
import org.allaymc.api.block.component.common.PlayerInteractInfo;
import org.allaymc.api.data.BlockFace;
import org.allaymc.api.block.BlockStateWithPos;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.PlaceBlockHelper;
import org.allaymc.server.block.component.common.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.util.Set;

/**
 * Allay Project 2024/8/15
 *
 * @author daoge_cmd
 */
@RequireBlockProperty(type = BlockPropertyType.Type.INT, name = "facing_direction")
public class BlockWallSignBaseComponentImpl extends BlockBaseComponentImpl {

    protected ItemType<?> dropItemType;

    public BlockWallSignBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, ItemType<?> dropItemType) {
        super(blockType);
        this.dropItemType = dropItemType;
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        checkPlaceMethodParam(dimension, blockState, placeBlockPos, placementInfo);
        dimension.setBlockState(
                placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                PlaceBlockHelper.processFacingDirectionProperty(blockState, placeBlockPos, placementInfo), placementInfo
        );
        return true;
    }

    @Override
    public boolean canKeepExisting(BlockStateWithPos current, BlockStateWithPos neighbor, BlockFace face) {
        var signFaceOpposite = BlockFace.fromId(current.blockState().getPropertyValue(VanillaBlockPropertyTypes.FACING_DIRECTION)).opposite();
        if (face != signFaceOpposite) return true;
        return neighbor.blockState().getBlockType().getMaterial().isSolid();
    }

    @Override
    public Set<ItemStack> getDrops(BlockStateWithPos blockState, ItemStack usedItem, Entity entity) {
        return Set.of(dropItemType.createItemStack(1));
    }

    @Override
    public ItemStack getSilkTouchDrop(BlockStateWithPos blockState) {
        return dropItemType.createItemStack(1);
    }
}
