package cn.allay.api.item.interfaces.cobbleddeepslatedoubleslab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCobbledDeepslateDoubleSlabStack extends ItemStack {
    ItemType<ItemCobbledDeepslateDoubleSlabStack> COBBLED_DEEPSLATE_DOUBLE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemCobbledDeepslateDoubleSlabStack.class)
            .vanillaItem(VanillaItemId.COBBLED_DEEPSLATE_DOUBLE_SLAB)
            .build();
}
