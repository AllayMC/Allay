package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedDoubleSlabStack extends ItemStack {
    ItemType<ItemWarpedDoubleSlabStack> WARPED_DOUBLE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemWarpedDoubleSlabStack.class)
            .vanillaItem(VanillaItemId.WARPED_DOUBLE_SLAB)
            .build();
}
