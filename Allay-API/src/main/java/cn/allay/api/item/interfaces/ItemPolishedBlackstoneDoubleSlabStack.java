package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstoneDoubleSlabStack extends ItemStack {
    ItemType<ItemPolishedBlackstoneDoubleSlabStack> POLISHED_BLACKSTONE_DOUBLE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemPolishedBlackstoneDoubleSlabStack.class)
            .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_DOUBLE_SLAB)
            .build();
}
