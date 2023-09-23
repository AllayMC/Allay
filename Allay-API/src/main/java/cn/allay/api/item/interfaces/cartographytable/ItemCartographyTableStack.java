package cn.allay.api.item.interfaces.cartographytable;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCartographyTableStack extends ItemStack {
    ItemType<ItemCartographyTableStack> CARTOGRAPHY_TABLE_TYPE = ItemTypeBuilder
            .builder(ItemCartographyTableStack.class)
            .vanillaItem(VanillaItemId.CARTOGRAPHY_TABLE)
            .build();
}
