package org.allaymc.server.block.component.banner;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.blockentity.interfaces.BlockEntityBanner;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.block.component.BlockBaseComponentImpl;

import java.util.Set;

/**
 * {@code BlockStandingBannerBaseComponentImpl} maintains standing banners
 * supported by a solid block below. Both normal and silk-touch drops recreate the
 * stored banner decoration, while a missing banner block entity falls back to a
 * plain banner item.
 *
 * @author daoge_cmd
 */
public class BlockStandingBannerBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * Creates the standingbanner block component for the supplied registered block
     * type.
     */
    public BlockStandingBannerBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Runs shared neighbor handling before checking the banner's floor. Only a
     * downward-side update can break the standing banner, and a nonsolid block
     * below triggers the usual break path.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        if (face == BlockFace.DOWN && !neighbor.getBlockStateData().isSolid()) {
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
