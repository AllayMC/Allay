package cn.allay.api.item.interfaces.itemcake;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemCakeStack extends ItemStack {
    ItemType<ItemItemCakeStack> ITEM_CAKE_TYPE = ItemTypeBuilder
            .builder(ItemItemCakeStack.class)
            .vanillaItem(VanillaItemId.ITEM_CAKE)
            .build();
}
