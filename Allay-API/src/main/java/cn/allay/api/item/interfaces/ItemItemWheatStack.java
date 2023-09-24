package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemWheatStack extends ItemStack {
    ItemType<ItemItemWheatStack> ITEM_WHEAT_TYPE = ItemTypeBuilder
            .builder(ItemItemWheatStack.class)
            .vanillaItem(VanillaItemId.ITEM_WHEAT)
            .build();
}
