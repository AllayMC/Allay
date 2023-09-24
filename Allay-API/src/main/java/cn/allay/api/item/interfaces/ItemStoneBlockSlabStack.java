package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStoneBlockSlabStack extends ItemStack {
    ItemType<ItemStoneBlockSlabStack> STONE_BLOCK_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemStoneBlockSlabStack.class)
            .vanillaItem(VanillaItemId.STONE_BLOCK_SLAB)
            .build();
}
