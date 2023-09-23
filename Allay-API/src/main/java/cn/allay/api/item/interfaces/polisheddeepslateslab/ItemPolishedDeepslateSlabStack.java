package cn.allay.api.item.interfaces.polisheddeepslateslab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedDeepslateSlabStack extends ItemStack {
    ItemType<ItemPolishedDeepslateSlabStack> POLISHED_DEEPSLATE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemPolishedDeepslateSlabStack.class)
            .vanillaItem(VanillaItemId.POLISHED_DEEPSLATE_SLAB)
            .build();
}
