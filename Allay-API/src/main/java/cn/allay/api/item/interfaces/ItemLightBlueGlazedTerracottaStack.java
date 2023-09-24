package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightBlueGlazedTerracottaStack extends ItemStack {
    ItemType<ItemLightBlueGlazedTerracottaStack> LIGHT_BLUE_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemLightBlueGlazedTerracottaStack.class)
            .vanillaItem(VanillaItemId.LIGHT_BLUE_GLAZED_TERRACOTTA)
            .build();
}
