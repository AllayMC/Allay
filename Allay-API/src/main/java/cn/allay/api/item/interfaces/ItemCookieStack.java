package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCookieStack extends ItemStack {
    ItemType<ItemCookieStack> COOKIE_TYPE = ItemTypeBuilder
            .builder(ItemCookieStack.class)
            .vanillaItem(VanillaItemId.COOKIE)
            .build();
}
