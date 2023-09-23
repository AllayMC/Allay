package cn.allay.api.item.interfaces.warpedslab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedSlabStack extends ItemStack {
    ItemType<ItemWarpedSlabStack> WARPED_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemWarpedSlabStack.class)
            .vanillaItem(VanillaItemId.WARPED_SLAB)
            .build();
}
