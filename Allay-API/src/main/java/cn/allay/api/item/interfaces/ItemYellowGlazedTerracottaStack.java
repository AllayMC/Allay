package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemYellowGlazedTerracottaStack extends ItemStack {
    ItemType<ItemYellowGlazedTerracottaStack> YELLOW_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemYellowGlazedTerracottaStack.class)
            .vanillaItem(VanillaItemId.YELLOW_GLAZED_TERRACOTTA)
            .build();
}
