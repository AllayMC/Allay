package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrownGlazedTerracottaStack extends ItemStack {
    ItemType<ItemBrownGlazedTerracottaStack> BROWN_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemBrownGlazedTerracottaStack.class)
            .vanillaItem(VanillaItemId.BROWN_GLAZED_TERRACOTTA)
            .build();
}
