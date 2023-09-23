package cn.allay.api.item.interfaces.rail;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRailStack extends ItemStack {
    ItemType<ItemRailStack> RAIL_TYPE = ItemTypeBuilder
            .builder(ItemRailStack.class)
            .vanillaItem(VanillaItemId.RAIL)
            .build();
}
