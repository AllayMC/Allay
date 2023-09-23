package cn.allay.api.item.interfaces.polisheddeepslatedoubleslab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedDeepslateDoubleSlabStack extends ItemStack {
    ItemType<ItemPolishedDeepslateDoubleSlabStack> POLISHED_DEEPSLATE_DOUBLE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemPolishedDeepslateDoubleSlabStack.class)
            .vanillaItem(VanillaItemId.POLISHED_DEEPSLATE_DOUBLE_SLAB)
            .build();
}
