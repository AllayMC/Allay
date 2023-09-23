package cn.allay.api.item.interfaces.grayglazedterracotta;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrayGlazedTerracottaStack extends ItemStack {
    ItemType<ItemGrayGlazedTerracottaStack> GRAY_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemGrayGlazedTerracottaStack.class)
            .vanillaItem(VanillaItemId.GRAY_GLAZED_TERRACOTTA)
            .build();
}
