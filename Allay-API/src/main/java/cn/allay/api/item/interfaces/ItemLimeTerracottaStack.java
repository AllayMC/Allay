package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLimeTerracottaStack extends ItemStack {
    ItemType<ItemLimeTerracottaStack> LIME_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemLimeTerracottaStack.class)
            .vanillaItem(VanillaItemId.LIME_TERRACOTTA)
            .build();
}
