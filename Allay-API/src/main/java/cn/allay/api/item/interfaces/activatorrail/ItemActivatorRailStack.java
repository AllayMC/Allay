package cn.allay.api.item.interfaces.activatorrail;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemActivatorRailStack extends ItemStack {
    ItemType<ItemActivatorRailStack> ACTIVATOR_RAIL_TYPE = ItemTypeBuilder
            .builder(ItemActivatorRailStack.class)
            .vanillaItem(VanillaItemId.ACTIVATOR_RAIL)
            .build();
}
