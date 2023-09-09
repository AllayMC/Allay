package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemBedStack extends ItemStack {
    ItemType<ItemItemBedStack> ITEM_BED_TYPE = ItemTypeBuilder
            .builder(ItemItemBedStack.class)
            .vanillaItem(VanillaItemId.ITEM_BED)
            .build();
}
