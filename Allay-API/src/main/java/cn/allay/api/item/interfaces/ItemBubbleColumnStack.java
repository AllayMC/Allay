package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBubbleColumnStack extends ItemStack {
    ItemType<ItemBubbleColumnStack> BUBBLE_COLUMN_TYPE = ItemTypeBuilder
            .builder(ItemBubbleColumnStack.class)
            .vanillaItem(VanillaItemId.BUBBLE_COLUMN)
            .build();
}
