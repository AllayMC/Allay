package cn.allay.api.item.interfaces.wood;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWoodStack extends ItemStack {
    ItemType<ItemWoodStack> WOOD_TYPE = ItemTypeBuilder
            .builder(ItemWoodStack.class)
            .vanillaItem(VanillaItemId.WOOD)
            .build();
}
