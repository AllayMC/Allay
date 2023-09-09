package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedExposedDoubleCutCopperSlabStack extends ItemStack {
    ItemType<ItemWaxedExposedDoubleCutCopperSlabStack> WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemWaxedExposedDoubleCutCopperSlabStack.class)
            .vanillaItem(VanillaItemId.WAXED_EXPOSED_DOUBLE_CUT_COPPER_SLAB)
            .build();
}
