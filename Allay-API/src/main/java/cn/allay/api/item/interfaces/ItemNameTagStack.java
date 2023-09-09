package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNameTagStack extends ItemStack {
    ItemType<ItemNameTagStack> NAME_TAG_TYPE = ItemTypeBuilder
            .builder(ItemNameTagStack.class)
            .vanillaItem(VanillaItemId.NAME_TAG)
            .build();
}
