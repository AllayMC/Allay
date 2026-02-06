package org.allaymc.server.block.component.banner;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.NeighborUpdateContext;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.interfaces.BlockEntityBanner;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.joml.Vector3ic;

import java.util.Objects;
import java.util.Set;

/**
 * @author daoge_cmd
 */
public class BlockWallBannerBaseComponentImpl extends BlockBaseComponentImpl {

    public BlockWallBannerBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        return dimension.setBlockState(
                placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                blockState.setPropertyValue(BlockPropertyTypes.FACING_DIRECTION, placementInfo.blockFace().ordinal()),
                placementInfo
        );
    }

    @Override
    public void onNeighborUpdate(NeighborUpdateContext context) {
        super.onNeighborUpdate(context);

        var block = context.block();
        var neighbor = context.neighbor();
        var face = context.face();
        var bannerFace = Objects.requireNonNull(BlockFace.fromIndex(block.getPropertyValue(BlockPropertyTypes.FACING_DIRECTION)));
        if (face == bannerFace.opposite() && !neighbor.getBlockStateData().isSolid()) {
            block.breakBlock();
        }
    }

    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return createBannerDrop(block);
    }

    @Override
    public ItemStack getSilkTouchDrop(Block block) {
        return createBannerDropSingle(block);
    }

    protected Set<ItemStack> createBannerDrop(Block block) {
        return Set.of(createBannerDropSingle(block));
    }

    protected ItemStack createBannerDropSingle(Block block) {
        var blockEntity = block.getBlockEntity();
        if (blockEntity instanceof BlockEntityBanner bannerEntity) {
            var item = ItemTypes.BANNER.createItemStack(1, bannerEntity.getBaseColor().ordinalInverted());
            item.setBannerType(bannerEntity.getBannerType());
            item.setPatterns(bannerEntity.getPatterns());
            return item;
        }
        return ItemTypes.BANNER.createItemStack(1);
    }
}
