package cn.allay.api.item.interfaces.magentaglazedterracotta;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagentaGlazedTerracottaStack extends ItemStack {
    ItemType<ItemMagentaGlazedTerracottaStack> MAGENTA_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
            .builder(ItemMagentaGlazedTerracottaStack.class)
            .vanillaItem(VanillaItemId.MAGENTA_GLAZED_TERRACOTTA)
            .build();
}
