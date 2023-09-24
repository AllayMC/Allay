package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemSkullStack extends ItemStack {
    ItemType<ItemItemSkullStack> ITEM_SKULL_TYPE = ItemTypeBuilder
            .builder(ItemItemSkullStack.class)
            .vanillaItem(VanillaItemId.ITEM_SKULL)
            .build();
}
