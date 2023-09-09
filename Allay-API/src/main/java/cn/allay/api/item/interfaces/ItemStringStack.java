package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStringStack extends ItemStack {
    ItemType<ItemStringStack> STRING_TYPE = ItemTypeBuilder
            .builder(ItemStringStack.class)
            .vanillaItem(VanillaItemId.STRING)
            .build();
}
