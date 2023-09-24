package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWhiteGlazedTerracottaStack extends ItemStack {
    ItemType<ItemWhiteGlazedTerracottaStack> WHITE_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemWhiteGlazedTerracottaStack.class)
            .vanillaItem(VanillaItemId.WHITE_GLAZED_TERRACOTTA)
            .build();
}
