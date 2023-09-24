package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPurpleGlazedTerracottaStack extends ItemStack {
    ItemType<ItemPurpleGlazedTerracottaStack> PURPLE_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemPurpleGlazedTerracottaStack.class)
            .vanillaItem(VanillaItemId.PURPLE_GLAZED_TERRACOTTA)
            .build();
}
