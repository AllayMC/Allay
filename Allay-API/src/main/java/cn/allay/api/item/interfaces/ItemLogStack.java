package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLogStack extends ItemStack {
    ItemType<ItemLogStack> LOG_TYPE = ItemTypeBuilder
            .builder(ItemLogStack.class)
            .vanillaItem(VanillaItemId.LOG)
            .build();
}
