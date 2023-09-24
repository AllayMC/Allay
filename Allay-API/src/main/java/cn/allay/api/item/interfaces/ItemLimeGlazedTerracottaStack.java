package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLimeGlazedTerracottaStack extends ItemStack {
    ItemType<ItemLimeGlazedTerracottaStack> LIME_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemLimeGlazedTerracottaStack.class)
            .vanillaItem(VanillaItemId.LIME_GLAZED_TERRACOTTA)
            .build();
}
