package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOrangeTerracottaStack extends ItemStack {
    ItemType<ItemOrangeTerracottaStack> ORANGE_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemOrangeTerracottaStack.class)
            .vanillaItem(VanillaItemId.ORANGE_TERRACOTTA)
            .build();
}
