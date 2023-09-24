package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedDeepslateStack extends ItemStack {
    ItemType<ItemPolishedDeepslateStack> POLISHED_DEEPSLATE_TYPE = ItemTypeBuilder
            .builder(ItemPolishedDeepslateStack.class)
            .vanillaItem(VanillaItemId.POLISHED_DEEPSLATE)
            .build();
}
