package org.allaymc.server.block.component.banner;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
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
 * {@code BlockWallBannerBaseComponentImpl} places and maintains wall banners
 * attached to a solid side face. Both normal and silk-touch drops recreate the
 * stored banner decoration, while a missing banner block entity falls back to a
 * plain banner item.
 *
 * @author daoge_cmd
 */
public class BlockWallBannerBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * Creates the wallbanner block component for the supplied registered block
     * type.
     */
    public BlockWallBannerBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Places the wall banner facing the clicked block face. {@code placementInfo}
     * is required, and the selected face ordinal is written into
     * {@code FACING_DIRECTION} before the dimension placement call.
     */
    @Override
    public boolean place(Dimension dimension, BlockState blockState, Vector3ic placeBlockPos, PlayerInteractInfo placementInfo) {
        return dimension.setBlockState(
                placeBlockPos.x(), placeBlockPos.y(), placeBlockPos.z(),
                blockState.setPropertyValue(BlockPropertyTypes.FACING_DIRECTION, placementInfo.blockFace().ordinal()),
                placementInfo
        );
    }

    /**
     * Runs shared neighbor handling before checking the wall support. Only changes
     * on the attached side can break the banner, and a nonsolid support causes the
     * usual block break path.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        var bannerFace = Objects.requireNonNull(BlockFace.fromIndex(block.getPropertyValue(BlockPropertyTypes.FACING_DIRECTION)));
        if (face == bannerFace.opposite() && !neighbor.getBlockStateData().isSolid()) {
            block.breakBlock();
        }
    }

    /**
     * Returns a single banner item carrying the block entity's decoration.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        return createBannerDrop(block);
    }

    /**
     * Returns a decorated banner item for the silk-touch drop path.
     */
    @Override
    public ItemStack getSilkTouchDrop(Block block) {
        return createBannerDropSingle(block);
    }

    /**
     * Returns a singleton set containing the prepared decorated banner item.
     */
    protected Set<ItemStack> createBannerDrop(Block block) {
        return Set.of(createBannerDropSingle(block));
    }

    /**
     * Creates a banner item with the block entity's base color, type, and
     * patterns. A missing or differently typed block entity selects a default
     * banner item without stored decoration.
     */
    protected ItemStack createBannerDropSingle(Block block) {
        var blockEntity = block.getBlockEntity();
        if (blockEntity instanceof BlockEntityBanner bannerEntity) {
            var item = ItemTypes.BANNER.createItemStack(1, bannerEntity.getBaseColor().ordinalInverted());
            item.setBannerType(bannerEntity.getBannerType());
            item.setPatterns(bannerEntity.getPatterns());
            return item;
        }
        // A missing banner entity leaves no decoration to recover, so preserve the
        // item drop using a default banner instead of omitting the drop.
        return ItemTypes.BANNER.createItemStack(1);
    }
}
