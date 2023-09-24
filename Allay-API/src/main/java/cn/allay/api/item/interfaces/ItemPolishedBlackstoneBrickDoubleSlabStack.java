package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstoneBrickDoubleSlabStack extends ItemStack {
    ItemType<ItemPolishedBlackstoneBrickDoubleSlabStack> POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemPolishedBlackstoneBrickDoubleSlabStack.class)
            .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_BRICK_DOUBLE_SLAB)
            .build();
}
