package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWhiteTerracottaStack extends ItemStack {
    ItemType<ItemWhiteTerracottaStack> WHITE_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemWhiteTerracottaStack.class)
            .vanillaItem(VanillaItemId.WHITE_TERRACOTTA)
            .build();
}
