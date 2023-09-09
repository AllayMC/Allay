package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCyanGlazedTerracottaStack extends ItemStack {
    ItemType<ItemCyanGlazedTerracottaStack> CYAN_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemCyanGlazedTerracottaStack.class)
            .vanillaItem(VanillaItemId.CYAN_GLAZED_TERRACOTTA)
            .build();
}
