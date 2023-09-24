package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstoneBrickSlabStack extends ItemStack {
    ItemType<ItemPolishedBlackstoneBrickSlabStack> POLISHED_BLACKSTONE_BRICK_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemPolishedBlackstoneBrickSlabStack.class)
            .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_BRICK_SLAB)
            .build();
}
