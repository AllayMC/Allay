package cn.allay.api.item.interfaces.waxeddoublecutcopperslab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWaxedDoubleCutCopperSlabStack extends ItemStack {
    ItemType<ItemWaxedDoubleCutCopperSlabStack> WAXED_DOUBLE_CUT_COPPER_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemWaxedDoubleCutCopperSlabStack.class)
            .vanillaItem(VanillaItemId.WAXED_DOUBLE_CUT_COPPER_SLAB)
            .build();
}
