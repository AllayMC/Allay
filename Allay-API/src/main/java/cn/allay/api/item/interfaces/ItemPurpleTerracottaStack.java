package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPurpleTerracottaStack extends ItemStack {
    ItemType<ItemPurpleTerracottaStack> PURPLE_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemPurpleTerracottaStack.class)
            .vanillaItem(VanillaItemId.PURPLE_TERRACOTTA)
            .build();
}
